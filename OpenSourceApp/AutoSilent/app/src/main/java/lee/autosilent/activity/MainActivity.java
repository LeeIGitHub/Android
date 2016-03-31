package lee.autosilent.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;
import com.rey.material.widget.ListView;

import java.text.MessageFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import lee.afk.afknet.AfkWifiTools;
import lee.afk.afkutils.log.LeeLog;
import lee.autosilent.R;
import lee.autosilent.adapter.TaskAdapter;
import lee.autosilent.adapter.TaskRAdater;
import lee.autosilent.bean.Task;
import lee.autosilent.database.TaskDataBase;
//import lee.autosilent.service.SilentService;
import lee.autosilent.utils.AudioUtils;

public class MainActivity extends BaseActivity {
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                showAddDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Bind(R.id.cm_rv_task)
    RecyclerView mRvTask;

    @Bind(R.id.cm_lv_task)
    ListView mLvTask;

    private AudioUtils mAudioUtils;

    private TaskDataBase mTaskDataBase;

    private TaskAdapter mAdapter;
    private List<Task> mTaskList;

    private Task mTaskDelete;

    private TaskRAdater mRAdapter;

    private ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT){
        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            switch (actionState){
                case ItemTouchHelper.ACTION_STATE_SWIPE:
                    float alpha = 1- Math.abs(dX/viewHolder.itemView.getWidth());
                    viewHolder.itemView.setAlpha(alpha);
                    viewHolder.itemView.setScaleX(alpha);
                    viewHolder.itemView.setScaleY(alpha);
                    break;
            }
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();

//            LeeLog.p(MessageFormat.format("ahaha ===>>{0}", position));

            deleteTask(position);
        }
    };

    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);

    @Override
    protected void init() {
        super.init();
        mAudioUtils = new AudioUtils(this);

        mTaskDataBase = new TaskDataBase(this);

//        Intent intent = new Intent(this, SilentService.class);
//        bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected int returnContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                showAddDialog();
//            }
//        });

        mAdapter = new TaskAdapter(this,null);
        mLvTask.setAdapter(mAdapter);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvTask.setLayoutManager(manager);

        mRvTask.setItemAnimator(new DefaultItemAnimator());

        mRAdapter = new TaskRAdater(null);

        mRvTask.setAdapter(mRAdapter);

        itemTouchHelper.attachToRecyclerView(mRvTask);

        showTaskList();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
//        unbindService(mServiceConnection);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        super.onBackPressed();
    }

    @OnClick(R.id.am_fab)
    void pressFab(com.rey.material.widget.FloatingActionButton fab){
//        fab.setLineMorphingState((fab.getLineMorphingState()+1)%2,true);
        showAddDialog();
    }
//
//    ServiceConnection mServiceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {}
//        @Override
//        public void onServiceDisconnected(ComponentName name) {}
//    };

    private void showAddDialog(){
        Dialog.Builder builder = new SimpleDialog.Builder(R.style.SimpleDialogLight){
            @Override
            public void onPositiveActionClicked(DialogFragment fragment) {
                super.onPositiveActionClicked(fragment);
                saveTask(getSelectedIndex());
            }

            @Override
            public void onNegativeActionClicked(DialogFragment fragment) {
                super.onNegativeActionClicked(fragment);
            }
        };

        String title = MessageFormat.format("When we are in the {0} WIFI,the phone will be ...", AfkWifiTools.getWifiNowSSID(this));

        ((SimpleDialog.Builder) builder).items(
        getResources().getStringArray(R.array.ringer_mode), 0)
                .title(title)
        .positiveAction("Add")
        .negativeAction("Cancel");

        DialogFragment fragment = DialogFragment.newInstance(builder);
        fragment.show(getSupportFragmentManager(), null);
    }

    private void saveTask(int mode){
        Task task = mTaskDataBase.saveTask(mode, mAudioUtils);
        mRAdapter.add(task);
    }

    private void resaveTask(int position, Task task){
        mTaskDataBase.saveTask(task);
        mRAdapter.add(position,task);
    }

    private void deleteTask(final int position){
        int line = mRAdapter.getData(position).getLine();
        mTaskDelete = null;

        mRAdapter.remove(position);
        mTaskDelete = mTaskDataBase.deleteTask(line);

        LeeLog.p(MessageFormat.format("ahaha delete {0}:{1}", position, mTaskDelete.getMode()));

        if(mTaskDelete == null)
            return ;
        Snackbar.make(mRvTask, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resaveTask(position,mTaskDelete);
                LeeLog.p(MessageFormat.format("ahaha resave {0}:{1}", position, mTaskDelete.getMode()));
            }
        }).show();
    }


    private void showTaskList(){
        getTaskListData();
        refreshTaskList();
    }

    private void refreshTaskList(){
        if(mTaskList == null)
            return ;
        if (mTaskList.size() <= 0)
            return ;

        mAdapter.refresh(mTaskList);

        mRAdapter.refresh(mTaskList);
    }

    private void getTaskListData(){
        mTaskList = mTaskDataBase.getAllTask();
    }

}