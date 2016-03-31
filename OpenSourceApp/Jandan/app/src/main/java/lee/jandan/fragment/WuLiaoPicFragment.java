package lee.jandan.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import lee.afk.afkhttp.volley.VolleyError;
import lee.afk.afkhttp2.AfkHttpListener2;
import lee.afk.afkutils.log.LeeLog;
import lee.jandan.R;
import lee.jandan.adapter.WuliaoPicAdapter;
import lee.jandan.bean.JandanBean;
import lee.jandan.bean.WuliaoPic;
import lee.jandan.http.HttpTools;
import lee.jandan.http.JandanHttp;
import lee.jandan.http.JandanHttpListener;

/**
 * Created by Lee on 2016/3/14.
 */
public class WuLiaoPicFragment extends BaseFragment {
    @Bind(R.id.fw_rv)
    RecyclerView mRv;

    @Bind(R.id.fw_lv)
    ListView mLv;


    @Override
    protected int returnContentView() {
        return R.layout.fragment_wuliaopic;
    }

    @Override
    protected void initData(Bundle bundle) {
        HttpTools.getWuliaoPic(getActivity(), new JandanHttpListener<WuliaoPic.Bean>() {
            @Override
            public void onSuccess(WuliaoPic.Bean o) {
                WuliaoPicAdapter adapter = new WuliaoPicAdapter(getActivity(),null);
                mLv.setAdapter(adapter);
                adapter.add(o.getComments());
            }

            @Override
            public void onFailed(VolleyError error) {

            }

            @Override
            public void onFinish() {

            }
        },WuLiaoPicFragment.this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}