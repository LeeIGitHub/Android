package lee.autosilent.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lee.autosilent.R;
import lee.autosilent.bean.Task;

/**
 * Created by Lee on 2015/11/6.
 */
public class TaskRAdater extends MyBaseRecycleAdapter<TaskRAdater.ViewHolder,Task>{

    private LayoutInflater mInfalter;

    public TaskRAdater(List<Task> data) {
        super(data);
    }

    private View mRootview;

    private Context mContext;

    private String[] mModeName;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        mRootview = View.inflate(parent.getContext(), R.layout.item_task, null);

        if(mInfalter == null)
            mInfalter = LayoutInflater.from(parent.getContext());
        mRootview = mInfalter.inflate(R.layout.item_task, parent,false);

        return new ViewHolder(mRootview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position, Task data) {
        if(mContext == null)
            mContext = holder.itemView.getContext();

        if(mModeName == null)
            mModeName = mContext.getResources().getStringArray(R.array.ringer_mode);

        holder.itTvMode.setText(mModeName[data.getMode()]);

        holder.itTvSsid.setText(data.getSsid());


    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.it_tv_ssid)
        TextView itTvSsid;
        @Bind(R.id.it_tv_mode)
        TextView itTvMode;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }





}
