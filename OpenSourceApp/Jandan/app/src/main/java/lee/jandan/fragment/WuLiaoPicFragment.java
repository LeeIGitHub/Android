package lee.jandan.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import butterknife.Bind;
import lee.afk.afkhttp.volley.VolleyError;
import lee.jandan.R;
import lee.jandan.activity.ActivityOperation;
import lee.jandan.adapter.WuliaoPicAdapter;
import lee.jandan.bean.WuliaoPic;
import lee.jandan.broadcast.BroadcastReceiverTools;
import lee.jandan.http.HttpTools;
import lee.jandan.http.JandanHttpListener;
import lee.jandan.test.activity.TestMovieActivity;

/**
 * Created by Lee on 2016/3/14.
 */
public class WuLiaoPicFragment extends BaseFragment implements JandanHttpListener<WuliaoPic.Bean>, AbsListView.OnScrollListener {
    @Bind(R.id.fw_rv)
    RecyclerView mRv;

    @Bind(R.id.fw_lv)
    ListView mLv;

    private WuliaoPicAdapter mAdapter;

    /**
     * 刷新的 receiver;
     */
    private BroadcastReceiver mReceiver;

    /**
     * 存储当前获取图片页数
     */
    private int mPicPage = 1;

    @Override
    protected int returnContentView() {
        return R.layout.fragment_wuliaopic;
    }

    @Override
    protected void initData(Bundle bundle) {
        getMore();

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mAdapter = new WuliaoPicAdapter(getActivity(), null, WuLiaoPicFragment.this);
        mLv.setAdapter(mAdapter);
        mLv.setOnScrollListener(this);

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case BroadcastReceiverTools.BROADCAST_REFRESH_JANDAN_WULIAO_PIC:
                        getMore();
                        break;
                }
            }
        };
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            if (view.getLastVisiblePosition() == view.getCount() - 1) {
                getMore();//加载更多
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onSuccess(WuliaoPic.Bean bean, String request) {
        getWuliaoPicSuccess(bean);
    }

    @Override
    public void onFailed(VolleyError error) {
        mPicPage--;
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            //获取滑倒图片的位置
            case 0x00a:
                int position = data.getIntExtra("position", -1);
//                mLv.setSelection(position);
                mLv.smoothScrollToPosition(position);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BroadcastReceiverTools.registerReceiver(getActivity(),mReceiver,BroadcastReceiverTools.BROADCAST_REFRESH_JANDAN_WULIAO_PIC);
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private void getWuliaoPic(int page) {
        HttpTools.getWuliaoPic(getActivity(), this, page, WuLiaoPicFragment.this);
    }

    private void getWuliaoPicSuccess(WuliaoPic.Bean bean) {
        if (bean == null || bean.getComments() == null || bean.getComments().size() < 1)
            return;

        mAdapter.add(bean.getComments());
    }

    private void getMore() {
        getWuliaoPic(mPicPage++);
    }


}