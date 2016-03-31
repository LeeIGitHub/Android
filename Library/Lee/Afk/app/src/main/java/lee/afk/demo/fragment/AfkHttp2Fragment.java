package lee.afk.demo.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import butterknife.Bind;
import lee.afk.demo.R;
import lee.afk.demo.bean.AHA;
import lee.afk.view.afkimageview.NetAfkImageView;

/**
 * Created by Lee on 2016/3/8.
 */
public class AfkHttp2Fragment extends BaseFragment {

    @Bind(R.id.fa2_naiv)
    NetAfkImageView mAiv;

    BaseMyHttpTest2 myHttpTest2 = new BaseMyHttpTest2();

    @Override
    protected int returnContentView() {
        return R.layout.fragment_afkhttp2;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mAiv.setImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1183223528,3058066243&fm=116&gp=0.jpg");

        BaseMyHttpTest2.MyHttpListener<AHA> listener = new BaseMyHttpTest2.MyHttpListener<>();
        myHttpTest2.getLKJASDLKJ(getActivity(), listener);
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
