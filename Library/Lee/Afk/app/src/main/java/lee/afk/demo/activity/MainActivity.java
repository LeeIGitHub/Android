package lee.afk.demo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import lee.afk.demo.R;
import lee.afk.demo.fragment.AfkNetFragment;
import lee.afk.demo.fragment.NavigationDrawerFragment;
import lee.afk.demo.fragment.UtilsFragment;

/**
 * Created by dahan on 2015/10/19.
 */
public class MainActivity extends BaseActivity {
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void init() {

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
//        getSupportActionBar().hide();
        mNavigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.am_drawer);
        mNavigationDrawerFragment.setUp(R.id.am_drawer, (DrawerLayout) findViewById(R.id.am_dl));
        getSupportFragmentManager().beginTransaction().add(R.id.am_container,new AfkNetFragment()).commit();

        mNavigationDrawerFragment.setOnDrawerItemClickListener(new NavigationDrawerFragment.OnDrawerItemClickListener() {
            @Override
            public void onCLick(View v) {
                switch (v.getId()){
                    case R.id.dm_btn_afk_utils:
                        changeFragment(new UtilsFragment());
                        break;
                    case R.id.dm_btn_afk_net:
                        changeFragment(new AfkNetFragment());
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.am_container,fragment).commit();
    }
}
