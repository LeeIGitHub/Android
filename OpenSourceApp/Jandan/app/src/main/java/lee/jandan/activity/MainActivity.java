package lee.jandan.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import butterknife.Bind;
import lee.afk.afkutils.log.LeeLog;
import lee.jandan.R;
import lee.jandan.fragment.WuLiaoPicFragment;

public class MainActivity extends BaseActivity {

    @Bind(R.id.cm_tb)
    Toolbar mTb;

    @Override
    protected int returnContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle bundle) {

//        startActivity(new Intent(this, LogisticsVehicleMonitor.class));
    }

    @Override
    protected void initView(Bundle bundle) {
//        transparentNavigationBar();

        mTb.setLogo(R.mipmap.ic_launcher);
        mTb.setTitle("煎蛋");
        setSupportActionBar(mTb);
        getSupportFragmentManager().beginTransaction().add(R.id.cm_content, new WuLiaoPicFragment(), WuLiaoPicFragment.class.getName()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}
