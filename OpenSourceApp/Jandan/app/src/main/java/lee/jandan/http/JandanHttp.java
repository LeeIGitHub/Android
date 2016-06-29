package lee.jandan.http;

import android.content.Context;

import lee.afk.afkhttp.volley.VolleyError;
import lee.afk.afkhttp2.AfkHttp2;
import lee.afk.afkhttp2.AfkHttp2Request;
import lee.afk.afkhttp2.AfkHttpListener2;
import lee.afk.afkutils.log.LeeLog;
import lee.jandan.bean.JandanBean;

/**
 * Created by Lee on 2016/3/14.
 */
public class JandanHttp extends AfkHttp2 {

    public static <T> AfkHttp2Request getJandanHttpResponse(Context context, String url, final JandanHttpListener listener, final Class<T> classz, Object tag) {
        //loading progress
        LeeLog.p("Lee get http response " + url);
        return getData(context, url, 30, new AfkHttpListener2<T>() {
            @Override
            public void onSuccess(T t,String response) {
                if (listener != null) {
                    if (classz != null) {
                        JandanBean jandanBean = (JandanBean) t;
                        listener.onSuccess(jandanBean, response);
                    }
                    else{
                        listener.onSuccess(null,response);
                    }
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

//    public static <T> AfkHttp2Request getJandanHttpResponse2(Context context, String url, final AfkHttpListener2 listener, Class<T> classz, Object tag) {
//        //loading progress
//        LeeLog.p("Lee get http response " + url);
//        return getData(context, url, 30, new AfkHttpListener2<T>() {
//            @Override
//            public void onSuccess(T t,String response) {
//                if (listener != null) {
//                    listener.onSuccess(t, response);
//                }
//            }
//
//            @Override
//            public void onFailed(VolleyError error) {
//                if (listener != null) {
//                    listener.onFailed(error);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                if (listener != null) {
//                    listener.onFinish();
//                }
//                //loading progress finish
//            }
//        }, classz, tag);
//    }
}