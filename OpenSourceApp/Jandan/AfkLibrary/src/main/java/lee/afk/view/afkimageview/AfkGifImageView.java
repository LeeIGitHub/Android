package lee.afk.view.afkimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import lee.afk.afkhttp.afkVolley.InputStreamRequest;
import lee.afk.afkhttp.volley.Response;
import lee.afk.afkhttp.volley.VolleyError;
import lee.afk.afkhttp2.AfkHttpUtils2;

/**
 * Created by dahan on 2016/3/25.
 */
public class AfkGifImageView extends AfkBaseImageView {

    /**
     * 播放gif的关键类
     */
    private Movie mMovie;

    /**
     * 是否正在播放
     */
    private boolean mIsPlaying;

    /**
     * Src图片 ID；
     */
    private int mSrcResourceId;

    /**
     * 记录动画开始时间
     */
    private long mMovieStart;

    /**
     * 记录动画当前播放的时间
     */
    private int mMovieIndex;

    public AfkGifImageView(Context context) {
        this(context, null);
    }

    public AfkGifImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AfkGifImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 做一些初始化
     */
    private void init() {
        mIsPlaying = true;


        if (mSrcResourceId > 0) {
            setImageResource(mSrcResourceId);
        }

        //启动硬件加速？
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mMovie == null) {
            super.onDraw(canvas);
        } else {
            updateAnimationTime();
            drawMovieFrame(canvas);
            invalidateView();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMatchParentWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMatchParentHeight = MeasureSpec.getSize(heightMeasureSpec);


        if (mMovie != null) {
            determanationViewSize();
            determinationImageSize();

            setMeasuredDimension((int) mWidth, (int) mHeight);
        }
    }

    /**
     * 设置Gif图片资源
     *
     * @param resId
     */
    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mSrcResourceId = resId;
        InputStream inputStream = getResources().openRawResource(+mSrcResourceId);
        setImageStream(inputStream);
    }

    /**
     * 更新动画时间
     */
    private void updateAnimationTime() {
        long now = SystemClock.uptimeMillis();
        if (mMovieStart == 0) {
            mMovieStart = now;
        }
        int dur = mMovie.duration();

        if (dur == 0) {
            dur = 1000;
        }
        mMovieIndex = (int) (now - mMovieStart) % dur;
    }

    /**
     * 逐帧draw gif动画
     *
     * @param canvas
     */
    private void drawMovieFrame(Canvas canvas) {
        mMovie.setTime(mMovieIndex);

        canvas.save(Canvas.MATRIX_SAVE_FLAG);

        canvas.scale(mImageScaleX, mImageScaleY);

        mMovie.draw(canvas, mImageLeft, mImageTop);

        canvas.restore();
    }

    /**
     * 刷新View
     */
    private void invalidateView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }



    /**
     * 下载图片
     *
     * @param url
     */
    private void downLoadImage(String url) {
        InputStreamRequest request = new InputStreamRequest(url, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                InputStream inputStream = new ByteArrayInputStream(response);
                setImageStream(inputStream);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AfkHttpUtils2.addRequestQueue(getContext(), request);
    }

    /**==========================================================================================
     * =====================                                                                           =======================
     * =====================                              public                                    =======================
     * =====================                                                                           =======================
     * ===========================================================================================
     */

    /**
     * 设置gif图片流
     *
     * @param inputStream
     */
    public void setImageStream(InputStream inputStream) {
        if (inputStream == null)
            return;

        mMovie = Movie.decodeStream(inputStream);

        if (mMovie != null) {
            mImageOldWidth = mMovie.width();
            mImageOldHeight = mMovie.height();
        }

        determanationViewSize();
        determinationImageSize();

        invalidateView();
    }

    public void setImageUrl(String url) {
        downLoadImage(url);
    }

}
