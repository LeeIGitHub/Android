package lee.afk.view.afkimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

import lee.afk.afkutils.bitmap.AfkBitmapUtil;

/**
 * Created by Lee on 2015/9/21.
 */
public class AfkImageView extends AfkBaseImageView {

    public enum AnimType {
        ALPHA_ANIM,
        CENTER_EXPAND,
    }

    /**
     * 记录切换动画类型
     */
    private AnimType mAnimType;


    /**
     * FPS
     */
    private final int FPS = 50;

    /**
     * 每帧刷新间隔
     */
    private final int REFRESH_INTERVAL = 1000 / FPS;

    /**
     * 当 mBitmap 被赋值时，记录该时间， 暂时作为动画启动时间
     * 由于当前没有设置延迟等功能，所以mBitmap被赋值时，将直接有过度动画效果
     */
    private long mAnimationStartTime;

    private long mAnimationNowTime;

    private long mAnimationRemainingTime;

    private Paint mPaint;

    private TransitionAnimation mTransitionAnimation;

    private Drawable mDrawable;

    /**
     * 用来记录动画是否结束
     */
    private boolean mAnimationFinish;

    /**
     * 是否播放动画的开关
     */
    private boolean mTransitionAnimatorEnable;

    /**
     * 动画时间、效果渐变时间
     */
    private int mDuration;

    public AfkImageView(Context context) {
        this(context, null);
    }

    public AfkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    /**
     * ==========================================================================================
     * =====================                                                                           =======================
     * =====================                            override                                   =======================
     * =====================                                                                           =======================
     * ===========================================================================================
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDrawable == null)
            return;

        if (mImageWidth == 0 || mImageHeight == 0) {
            return;
        }

        if (mTransitionAnimation == null || mTransitionAnimatorEnable == false) {
            drawWithOutTransitionAnimation(canvas);
        } else {
            drawWithAnimation(canvas);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMatchParentWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMatchParentHeight = MeasureSpec.getSize(heightMeasureSpec);


        if (mDrawable != null) {
            determanationViewSize();
            determinationImageSize();

            setMeasuredDimension((int) mWidth, (int) mHeight);
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        setDrawable(new BitmapDrawable(getResources(), bm));
    }

    @Override
    public void setImageResource(int resId) {
        setDrawable(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), resId)));
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        setDrawable(drawable);
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {
                invalidate();
            }
            return false;
        }
    });

    /**
     * ==========================================================================================
     * =====================                                                                           =======================
     * =====================                             private                                   =======================
     * =====================                                                                           =======================
     * ===========================================================================================
     */
    private void init() {
        mPaint = new Paint();
//        mAnimType = AnimType.ALPHA_ANIM;
        mAnimType = AnimType.CENTER_EXPAND;

        mDuration = 500;

        setTransitionAnimation(mAnimType);
        setTransitionAnimationEnable(true);
    }


    /**
     * 集中 处理 设置图片
     *
     * @param drawable
     */
    private void setDrawable(Drawable drawable) {

        if (drawable == null || drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            /**
             * （呼叫豆神）
             *   这里要做设置图片为null的处理，就是什么都没有的图片
             */
            super.setImageResource(android.R.color.transparent);
            return;
        }

        mDrawable = drawable;

        mImageOldWidth = mDrawable.getIntrinsicWidth();
        mImageOldHeight = mDrawable.getIntrinsicHeight();

        mAnimationStartTime = System.currentTimeMillis();

        determanationViewSize();
        determinationImageSize();

        mDrawable = new BitmapDrawable(getResources(), AfkBitmapUtil.setBitmapSize(AfkBitmapUtil.getBitmapFromDrawable(mDrawable), (int) mImageWidth, (int) mImageHeight));

        if(getScaleType() != ScaleType.FIT_XY){
            mDrawable.setBounds(0, 0, (int) mWidth, (int) mHeight);
//            mDrawable.setHotspot();
        }

        mTransitionAnimation.setImage(mDrawable);

        invalidate();
    }

    /**
     * 设置切换动画类型
     *
     * @param type
     */
    private void setTransitionAnimation(AnimType type) {
        switch (type) {
            case ALPHA_ANIM:
                setTransitionAnimation(new AlphaAnimation());
                setDuration(500);
                break;
            case CENTER_EXPAND:
                setTransitionAnimation(new CenterExpandAnimation());
                setDuration(500);
                break;
        }
    }

    private void drawWithOutTransitionAnimation(Canvas canvas) {
        canvas.save();
        mDrawable.draw(canvas);
        canvas.restore();
    }

    private void drawWithAnimation(Canvas canvas) {
        countRemainingTime();
        float progress;
        float duration = mTransitionAnimation.duration();
        float retime = mAnimationRemainingTime;
        progress = retime / duration;
        if (progress > 1)
            progress = 1;
        else if (progress < 0)
            progress = 0;
        mTransitionAnimation.changeProgress(progress);
        mAnimationFinish = mTransitionAnimation.draw(canvas);
        if (mAnimationFinish) {
            //animation is finish
            /**
             * 当动画结束后，关闭动画效果
             */
//            setTransitionAnimationEnable(false);
        } else {
            delayedRefresh(REFRESH_INTERVAL);
        }
    }

    /**
     * 计算下一帧延迟刷新时间
     */
    private void countRemainingTime() {
        mAnimationNowTime = System.currentTimeMillis();
        mAnimationRemainingTime = mTransitionAnimation.duration() - (mAnimationNowTime - mAnimationStartTime);
    }

    /**
     * 间隔一段时间，去执行刷新下一帧
     *
     * @param delayedTime
     */
    private void delayedRefresh(int delayedTime) {
        mHandler.sendEmptyMessageDelayed(0, delayedTime);
    }

    /**
     * ==========================================================================================
     * =====================                                                                           =======================
     * =====================                              public                                    =======================
     * =====================                                                                           =======================
     * ===========================================================================================
     */

    public void setTransitionAnimation(TransitionAnimation animation) {
        this.mTransitionAnimation = animation;
    }

    /**
     * 设置是否播放动画切换效果
     *
     * @param enable
     */
    public void setTransitionAnimationEnable(boolean enable) {
        this.mTransitionAnimatorEnable = enable;

        setDuration(mDuration);
    }

    /**
     * 设置动画类型（使用foundation提供的切换效果）
     * 注：现在只提供了一种效果
     *
     * @param type
     */
    public void setAnimType(AfkImageView.AnimType type) {
        this.mAnimType = type;

        setTransitionAnimation(mAnimType);
    }

    /**
     * 设置渐变动画时间
     *
     * @param duration
     */
    public void setDuration(int duration) {
        this.mDuration = duration;
        if (mTransitionAnimation != null)
            mTransitionAnimation.setDuration(duration);
    }
}
