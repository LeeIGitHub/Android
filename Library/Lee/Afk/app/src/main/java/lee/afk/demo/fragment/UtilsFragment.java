package lee.afk.demo.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import lee.afk.demo.R;
import lee.afk.demo.activity.BaseActivity;

/**
 * Created by Lee on 2015/10/19.
 */
public class UtilsFragment extends BaseFragment {
    @Bind(R.id.fu_tb)
    Toolbar mTb;

    @Bind(R.id.fu_tl)
    TabLayout mTl;

    TabLayout.Tab mAudioManagerTab;
    @Override
    protected int returnContentView() {
        return R.layout.fragment_utils;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTb.setTitle("afk utils");
        ((BaseActivity)getActivity()).getSupportActionBar().hide();

        addFragment(R.id.fu_container, new AudioFragment());

        mAudioManagerTab = mTl.newTab().setText("AudioManager");
        mTl.addTab(mAudioManagerTab);

        mTl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == mAudioManagerTab.getPosition()){
                    replaceFragment(R.id.fu_container,new AudioFragment());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
