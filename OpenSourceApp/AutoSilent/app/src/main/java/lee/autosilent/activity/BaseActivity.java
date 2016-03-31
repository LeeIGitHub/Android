package lee.autosilent.activity;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import lee.afk.afkbase.AfkBaseAppCompatActivity;

/**
 * Created by Lee on 2015/10/20.
 */
public abstract class BaseActivity extends AfkBaseAppCompatActivity {

    @Override
    protected void init() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
