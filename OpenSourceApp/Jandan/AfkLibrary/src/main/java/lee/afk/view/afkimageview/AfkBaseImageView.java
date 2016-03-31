package lee.afk.view.afkimageview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import lee.afk.afkhttp.volley.toolbox.NetworkImageView;
import lee.afk.afkutils.log.LeeLog;

/**
 * Created by dahan on 2016/3/30.
 */
public abstract class AfkBaseImageView extends NetworkImageView {

    /**
     * 记录原本的宽度
     */
    protected float mImageOldWidth;

    /**
     * 记录原本的高度
     */
    protected float mImageOldHeight;

    /**
     * 图片的宽度
     */
    protected float mImageWidth;

    /**
     * 图片的高度
     */
    protected float mImageHeight;

    /**
     * 图片缩放比例 X轴
     */
    protected float mImageScaleX;

    /**
     * 图片缩放比例 Y轴
     */
    protected float mImageScaleY;

    /**
     * 控件的宽度
     */
    protected float mWidth;

    /**
     * 控件的高度
     */
    protected float mHeight;

    /**
     * match_parent 的宽度值
     */
    protected int mMatchParentWidth;

    /**
     * match_parent 的高度值
     */
    protected int mMatchParentHeight;

    /**
     * 图片的左边间距
     */
    protected float mImageLeft;

    /**
     * 图片的顶部间距
     */
    protected float mImageTop;


    public AfkBaseImageView(Context context) {
        super(context);
    }

    public AfkBaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AfkBaseImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public AfkBaseImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }




    /**
     * 计算控件的宽高
     */
    protected void determanationViewSize() {
        int minWidth = 0;
        int minHeight = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            minWidth = getMinimumWidth();
            minHeight = getMinimumHeight();
        }

        mWidth = getViewSize(getLayoutParams().width, mMatchParentWidth, mImageOldWidth, minWidth);
        mHeight = getViewSize(getLayoutParams().height, mMatchParentHeight, mImageOldHeight, minHeight);

        requestLayout();
        LeeLog.p("计算view尺寸 width = {0}, height = {1} ", mWidth, mHeight);
    }

    /**
     * 计算 并获取 控件的尺寸
     *
     * @param layoutParamasSize
     * @param maxSize
     * @param imageSize
     * @return
     */
    protected float getViewSize(int layoutParamasSize, int maxSize, float imageSize, float minSize) {
        float result;
        switch (layoutParamasSize) {
            case ViewGroup.LayoutParams.MATCH_PARENT:
                result = maxSize;
                break;
            case ViewGroup.LayoutParams.WRAP_CONTENT:
                result = imageSize;
                break;
            default:
                result = layoutParamasSize;
                break;
        }

        result = Math.max(result, minSize);
        return result;
    }

    /**
     * 确定 图片和View的宽高
     */
    protected void determinationImageSize() {
        float scaleXY = mImageOldWidth / mImageOldHeight;

        if (getScaleType() == ScaleType.FIT_XY) {
            mImageWidth = mWidth;
            mImageHeight = mHeight;
        } else {//if(getScaleType() == ScaleType.FIT_CENTER){      //default mode
            float viewScaleXY = mWidth / mHeight;
            float imageDview = scaleXY / viewScaleXY;
            if (imageDview > 1) {
                mImageWidth = mWidth;
                mImageHeight = mImageWidth / scaleXY;
            } else {
                mImageHeight = mHeight;
                mImageWidth = mImageHeight * scaleXY;
            }
        }

        mImageScaleX = mImageWidth / mImageOldWidth;
        mImageScaleY = mImageHeight / mImageOldHeight;

        mImageLeft = (mWidth - mImageWidth) / 2 / mImageScaleX;
        mImageTop = (mHeight - mImageHeight) / 2 / mImageScaleY;

        LeeLog.p("计算 图片 尺寸 width = {0}, height = {1} ", mImageWidth, mImageHeight);
        LeeLog.p("计算图片的缩放比 x = {0}, y = {1}", mImageScaleX, mImageScaleY);
        LeeLog.p("计算图片 绘制的位置 x = {0}, y = {1}", mImageLeft, mImageTop);
    }
}
