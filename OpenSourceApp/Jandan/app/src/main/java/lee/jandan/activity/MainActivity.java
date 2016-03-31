package lee.jandan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import java.util.logging.LogManager;

import butterknife.Bind;
import lee.jandan.R;
import lee.jandan.bean.WuliaoPic;
import lee.jandan.fragment.TestFragment;
import lee.jandan.fragment.WuLiaoPicFragment;
import lee.jandan.test.activity.LogisticsVehicleMonitor;

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

        mTb.setLogo(R.mipmap.ic_launcher);
        mTb.setTitle("煎蛋");
        setSupportActionBar(mTb);
        getSupportFragmentManager().beginTransaction().add(R.id.cm_content, new TestFragment(), TestFragment.class.toString()).commit();
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
