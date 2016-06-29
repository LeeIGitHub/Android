package lee.jandan.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import lee.afk.afkhttp.volley.VolleyError;
import lee.afk.afkutils.log.LeeLog;
import lee.jandan.R;
import lee.jandan.adapter.TucaoAdapter;
import lee.jandan.adapter.WuliaoPicViewPagerAdapter;
import lee.jandan.bean.JandanBean;
import lee.jandan.bean.JandanPic;
import lee.jandan.bean.TucaoComment;
import lee.jandan.broadcast.BroadcastReceiverTools;
import lee.jandan.http.HttpTools;
import lee.jandan.http.JandanHttpListener;

/**
 * Created by Lee on 2016/6/12.
 */
public class WuLiaoPicMatchActivity extends BaseActivity implements ViewPager.OnPageChangeListener, JandanHttpListener<JandanBean> {
    public static void start(Context context, Fragment fragment, ArrayList<JandanPic> data, int position, View view) {
        Intent intent = new Intent(context, WuLiaoPicMatchActivity.class);
        intent.putExtra("w", data);
        intent.putExtra("a", position);
//        ActivityOperation.startAc(context, intent);
        ActivityOperation.startAcWithView(context, fragment, intent, view, context.getString(R.string.tn_ip_iv_to_awm_vp), 0x00a);
    }

    @Bind(R.id.fwm_vp)
    ViewPager mVp;

    @Bind(R.id.fwm_lv_tucao)
    ListView mLvTucao;

    @Bind(R.id.fwm_ll_tucao_content)
    LinearLayout mLlTucaoContent;

    private List<JandanPic> mPicList;

    /**
     * 无聊图 viewpager 适配器
     */
    WuliaoPicViewPagerAdapter mAdapter;

    /**
     * 存取当前位置
     */
    private int mPosition;

    private TucaoComment mTucaoComment;

    private TucaoAdapter mTucaoAdapter;

    private BroadcastReceiver mReceiver;

    /**
     * 窗口模式
     */
    private int mWindowsModel;

    /**
     * 吐槽窗口是否显示
     */
    private boolean mTucaoIsVisible;

    @Override
    protected void onResume() {
        super.onResume();
        BroadcastReceiverTools.registerReceiver(this, mReceiver, BroadcastReceiverTools.BROADCAST_REFRESH_SUCCESS_JANDAN_WULIAO_PIC);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    protected int returnContentView() {
        return R.layout.activity_wuliaopic_match;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mPicList = (List<JandanPic>) intent.getExtras().get("w");
        mPosition = intent.getExtras().getInt("a", 0);

        mTucaoAdapter = new TucaoAdapter(this, null);

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case BroadcastReceiverTools.BROADCAST_REFRESH_SUCCESS_JANDAN_WULIAO_PIC:
                        mPicList = (List<JandanPic>) intent.getSerializableExtra("wuliaotu");
                        mAdapter.refresh(mPicList);
                        break;
                }
            }
        };
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        transparentStatusBar();
//        transparentNavigationBar();
//        toggleWindowsModel();
        hideActionBar();

        mAdapter = new WuliaoPicViewPagerAdapter(this, mPicList);
        mVp.setAdapter(mAdapter);
        mVp.setCurrentItem(mPosition);
        mVp.addOnPageChangeListener(this);

        mLvTucao.setAdapter(mTucaoAdapter);

        getTucaoData(mPicList.get(mPosition).getComment_ID());

    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View v) {
    }

    @OnClick(R.id.fwm_btn_tucao)
    public void onTucaoClick(View view) {
//        toggleTucaoLayoutVisible(mTucaoIsVisible);
//        mTucaoIsVisible = !mTucaoIsVisible;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mPosition = position;
        String commentId = mPicList.get(position).getComment_ID();
        getTucaoData(commentId);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onSuccess(JandanBean tucaoComment, String response) {
        mTucaoComment = TucaoComment.parse(response);
        updateTucaoList();
    }

    @Override
    public void onFailed(VolleyError error) {
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void onBackPressed() {
        /**
         * set result
         * 设置返回listview 的 position 定位
         */
        Intent result = new Intent();
        result.putExtra("position", mPosition);
        setResult(Activity.RESULT_OK, result);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            finishAfterTransition();
        else
            super.onBackPressed();
    }

    /**
     * 获取吐槽数据
     *
     * @param tucaoId
     */
    private void getTucaoData(String tucaoId) {
        HttpTools.getTucao(this, this, this, tucaoId);
    }

    /**
     * 更新吐槽上的数据
     */
    private void updateTucaoList() {
        if (mTucaoComment == null || mTucaoComment.getTucaoList() == null) {
            mTucaoAdapter.refresh(null);
            return;
        }
        mTucaoAdapter.refresh(mTucaoComment.getTucaoList());
//        mTucaoAdapter.refresh(mTucaoComment.getParentPosts());
    }

    /**
     * 切换窗口模式
     * 一种是全屏（隐藏status bar）
     * 第二种是正常模式（显示status bar）
     */
    public void toggleWindowsModel() {
        mWindowsModel = mWindowsModel != 0 ? 0 : 1;
        if (mWindowsModel == 0) {/** 0 是正常模式**/
//            showStatusBar();
            fullScreen(false);
            toggleTucaoLayoutVisible(true);
        } else {/** 1 是全屏模式**/
//            hideStatusBar();
            fullScreen(true);
            toggleTucaoLayoutVisible(false);
        }
    }

    private void toggleTucaoLayoutVisible(final boolean visible) {
        int x = (int) mLlTucaoContent.getX();
        int y = (int) mLlTucaoContent.getY();
        int width = mLlTucaoContent.getWidth();
        int height = mLlTucaoContent.getHeight();
        int radius = (int) Math.sqrt(width * width + height * height);
        Animator animator = ViewAnimationUtils.createCircularReveal(mLlTucaoContent, x, y, visible ? 0 : radius, visible ? radius : 0);
//        animator.setDuration(1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (visible)
                    mLlTucaoContent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!visible)
                    mLlTucaoContent.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.start();
    }
}
