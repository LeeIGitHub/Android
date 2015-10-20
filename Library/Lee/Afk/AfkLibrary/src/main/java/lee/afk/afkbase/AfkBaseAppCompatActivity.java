package lee.afk.afkbase;

import android.os.Bundle;
import android.os.Handler;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * this activity extends android.support.v7.app.AppCompatActivity;
 */
public abstract class AfkBaseAppCompatActivity extends AppCompatActivity
        implements OnClickListener,Handler.Callback {

    protected Handler mHandler = new Handler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(returnContentView());
        init();
        initData(savedInstanceState);
        initView(savedInstanceState);
    }

    protected abstract void init();

    /**
     * 返回layout res id 相当于调用setCotentView
     *
     * @return
     */
    protected abstract int returnContentView();

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化控件
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    /**
     * 获取activity的根view
     *
     * @return
     */
    protected View getRootView() {
        return getWindow().getDecorView().findViewById(android.R.id.content);
    }

}
