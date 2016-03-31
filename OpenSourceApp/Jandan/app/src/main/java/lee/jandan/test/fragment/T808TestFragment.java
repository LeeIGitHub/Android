package lee.jandan.test.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import lee.jandan.fragment.BaseFragment;

/**
 * Created by dahan on 2016/3/17.
 */
public class T808TestFragment extends BaseFragment{
    @Override
    protected int returnContentView() {
        return 0;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        T808MessageFactory.Create()
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
