package lee.afk.view.afkimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.AttributeSet;
import android.view.ViewGroup;

import java.text.MessageFormat;

import lee.afk.afkhttp.volley.RequestQueue;
import lee.afk.afkhttp.volley.toolbox.ImageLoader;
import lee.afk.afkhttp.volley.toolbox.Volley;
import lee.afk.afkutils.log.LeeLog;

/**
 * Created by Lee on 2016/3/8.
 */
public class NetAfkImageView extends AfkImageView {
    RequestQueue requestQueue;
    ImageLoader imageLoader;

    public NetAfkImageView(Context context) {
        super(context, null);
    }

    public NetAfkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        requestQueue = Volley.newRequestQueue(getContext());
        imageLoader = new ImageLoader(requestQueue, new BitmapCache());
        addOnImageSetListener(onImageSetListener);
    }

    public void setImageUrl(String url) {
        setImageUrl(url, imageLoader);
    }

    private OnImageSetListener onImageSetListener = new OnImageSetListener() {
        @Override
        public Bitmap onImageSet(Bitmap bitmap, String url) {
            if (bitmap == null || bitmap.getHeight() <= 0 || bitmap.getWidth() <= 0)
                return bitmap;

            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = (bitmap.getWidth() / bitmap.getHeight() * params.width);
//            params.width = bitmap.getWidth();
//            params.height = bitmap.getHeight();
            setLayoutParams(params);
            LeeLog.p(MessageFormat.format("bitmap:width = {0},height = {1}, imageView:width={2}, height = {3}, url = {4}", bitmap.getWidth(), bitmap.getHeight(), params.width, params.height, url));
            return bitmap;
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
