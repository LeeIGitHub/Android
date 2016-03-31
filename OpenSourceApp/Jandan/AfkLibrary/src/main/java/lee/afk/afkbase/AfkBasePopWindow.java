package lee.afk.afkbase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public abstract class AfkBasePopWindow implements OnClickListener {

    public interface PopOnClickListener {

        public void onClick(View v);

        public void onResult(Bundle data);
    }


    protected PopupWindow mPopupWindow;

    /**
     * popwindow 的根view
     */
    private View mRootView;

    /**
     * popwindow 的父界面（需要做相对位置时会用到）
     */
    protected View mParentView;

    protected Activity mActivity;

    protected Context mContext;


    /**
     * 监听popwindow上的点击事件
     */
    protected PopOnClickListener mPopOnClickListener;

    private int mAnimStyle;

    public AfkBasePopWindow(Context context, View parentView) {
        this(context, null, parentView, -1);
    }

    public AfkBasePopWindow(Activity context, View parentView) {
        this(context, context, parentView, -1);
    }

    public AfkBasePopWindow(Context context, Activity activity, View parentView, int animStyle) {
        this.mContext = context;
        this.mActivity = activity;
        this.mParentView = parentView;
        this.mAnimStyle = animStyle;
        initWindow();
    }


    /**
     * 初始化popupwindow
     */
    protected void initWindow() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = inflater.inflate(setContentView(), null);
        mPopupWindow = new PopupWindow(mRootView, setWidth(), setHeight());
        mPopupWindow.setAnimationStyle(mAnimStyle);
        mPopupWindow.setOutsideTouchable(true);

        initData();
        initView();

        mPopupWindow.update();
    }


    /**
     * 设置popwindow的layout id
     *
     * @return
     */
    protected abstract int setContentView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置宽度
     *
     * @return
     */
    protected int setWidth() {
        return LayoutParams.MATCH_PARENT;
    }

    /**
     * 设置高度
     *
     * @return
     */
    protected int setHeight() {
        return LayoutParams.MATCH_PARENT;
    }

    /**
     * 设置cancle按钮
     *
     * @param res
     */
    protected void setCancleView(int res) {
        findViewById(res).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 设置动画
     *
     * @param res
     */
    protected void setAnimationStyle(int res) {
        mPopupWindow.setAnimationStyle(res);
        mPopupWindow.update();
    }

    /**
     * 将view setOnclickListenner(this)
     *
     * @param res
     */
    protected void setOnClickView(int res) {
        findViewById(res).setOnClickListener(this);
    }

    /**
     * 获取view（类似activity中的findviewbyId）
     *
     * @param id
     * @return
     */
    protected View findViewById(int id) {
        return mRootView.findViewById(id);
    }

    /**
     * 显示popwindow 可重写
     */
    protected void showPop() {
        if (mPopupWindow != null)
            mPopupWindow.showAtLocation(mParentView, Gravity.CENTER, 0, 0);
    }

    /**
     * 关闭popwindow 可重写
     */
    protected void dismissPop() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 这里设置PopupWindow.setInputMethodMode
     * @param mode
     */
    protected void setInputMode(int mode){
        mPopupWindow.setInputMethodMode(mode);
        mPopupWindow.update();
    }


    @Override
    public void onClick(View v) {
        if (mPopOnClickListener != null) {
            mPopOnClickListener.onClick(v);
        }
    }


    /**
     * 显示pop
     * 给调用者的方法
     */
    public void show() {
        showPop();
    }

    /**
     * 关闭pop
     * 给调用者的方法
     */
    public void dismiss() {
        dismissPop();
    }

    /**
     * 返回popwindow是否关闭
     * 给调用者的方法
     */
    public boolean isShowing() {
        if (mPopupWindow != null)
            return mPopupWindow.isShowing();
        return false;
    }

    /**
     * 不让popwindow挡住键盘
     */
    public void setDoNotBlockTheKeyBoard(){
        /**
         * 这句话让popupwindow 不挡住 输入法键盘
         */
        //mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);

        setInputMode(PopupWindow.INPUT_METHOD_NEEDED);
    }

    public void setOnClickListenner(PopOnClickListener listener) {
        this.mPopOnClickListener = listener;
    }
}
