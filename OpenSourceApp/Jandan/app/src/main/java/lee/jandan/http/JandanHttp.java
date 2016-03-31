package lee.jandan.http;

import android.content.Context;

import lee.afk.afkhttp.volley.VolleyError;
import lee.afk.afkhttp2.AfkHttp2;
import lee.afk.afkhttp2.AfkHttp2Request;
import lee.afk.afkhttp2.AfkHttpListener2;
import lee.jandan.bean.JandanBean;
import lee.jandan.bean.WuliaoPic;

/**
 * Created by Lee on 2016/3/14.
 */
public class JandanHttp extends AfkHttp2 {

    public static <T> AfkHttp2Request getJandanHttpResponse(Context context, String url, final JandanHttpListener listener,Class<T> classz, Object tag) {
        //loading progress
        return getData(context, url, 30, new AfkHttpListener2<T>() {
            @Override
            public void onSuccess(T t) {
                if (listener != null) {
                    JandanBean jandanBean = (JandanBean)t;
                    listener.onSuccess(jandanBean);
                }
            }

            @Override
            public void onFailed(VolleyError error) {
                if (listener != null) {
                    listener.onFailed(error);
                }
            }

            @Override
            public void onFinish() {
                if (listener != null) {
                    listener.onFinish();
                }
                //loading progress finish
            }
        }, classz, tag);
    }
}