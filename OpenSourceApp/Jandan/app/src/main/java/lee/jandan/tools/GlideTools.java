package lee.jandan.tools;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import lee.jandan.R;

/**
 * Created by Lee on 2016/6/17.
 */
public class GlideTools {
    public static void show(Context context, ImageView imageView, String url) {
        show(getRequestManager(null, null, context), imageView, url);
    }

    public static void show(Activity activity, ImageView imageView, String url) {
        show(getRequestManager(activity, null, null), imageView, url);
    }

    public static void show(Fragment fragment, ImageView imageView, String url) {
        show(getRequestManager(null, fragment, null), imageView, url);
    }

    private static void show(RequestManager requestManager, ImageView imageView, String url) {
        DrawableTypeRequest drawableTypeRequest = requestManager
                .load(url);

        String str = url.substring(url.length() - 3, url.length() - 0);

        if (url.substring(url.length() - 3, url.length()).equals("gif")) {
            BitmapTypeRequest bitmapTypeRequest = drawableTypeRequest.asBitmap();
            bitmapTypeRequest
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .thumbnail(0.1f)
                    .placeholder(R.mipmap.chuyin)
                    .into(imageView);
        } else {
            drawableTypeRequest
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .thumbnail(0.1f)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }

    private static RequestManager getRequestManager(Activity activity, Fragment fragment, Context context) {
        if (activity != null) {
            return Glide.with(activity);
        } else if (fragment != null) {
            return Glide.with(fragment);
        } else if (context != null) {
            return Glide.with(context);
        }

        return null;
    }
}
