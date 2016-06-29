package lee.jandan.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import lee.jandan.R;
import lee.jandan.activity.BaseActivity;

/**
 * Created by Lee on 2016/6/27.
 */
public class TestMovieActivity extends BaseActivity {
    @Bind(R.id.atm_tv)
    TextView mTv;

    @OnClick(R.id.atm_btn)
    public void onBut(View view) {
        Intent intent = new Intent(TestMovieActivity.this, TestMovieActivity.class);
        intent.putExtra("0", "singleTask");
        Bundle data = new Bundle();
        data.putString("1","singleTask");
        intent.putExtras(data);
        startActivity(intent);
    }

    private String text;

    private String getIntentState;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getIntentState = "on new Intent :";
        getIntentData(intent);
    }

    @Override
    protected int returnContentView() {
        return R.layout.activity_test_movie;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getIntentState = "get intent ";
        getIntentData(getIntent());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }

    private void getIntentData(Intent intent) {
        text = intent.getStringExtra("0");
        if (text == null)
            text = "null";

        mTv.setText(getIntentState + text);

    }
}
