package lee.afk.afkhttp2;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import lee.afk.afkhttp.volley.Request;

/**
 * Created by Lee on 2016/3/7.
 */
public abstract class AfkHttp2 {

    private static Map<String, AfkHttp2Request> mRequestMap;

    public static Map<String, AfkHttp2Request> getRequestMap() {
        if (mRequestMap == null)
            mRequestMap = new HashMap<>();
        return mRequestMap;
    }

    public static void cancel(Object tag) {
        if (getRequestMap().size() < 1)
            return;

        AfkHttp2Request request = getRequestMap().get(tag.getClass().toString());
        request.cancel();
        getRequestMap().remove(request);
    }

    protected static <T> AfkHttp2Request getData(Context context, String url, int timeOut, AfkHttpListener2<T> listener, Class<T> c, Object tag) {
        Request request = AfkHttpUtils2.getHttpRequest(context, Request.Method.GET, url, timeOut, listener, c);
        AfkHttpUtils2.addRequestQueue(context, request);

        AfkHttp2Request request2 = new AfkHttp2Request(request);
        if (tag != null) {
            getRequestMap().put(tag.getClass().toString(), request2);
        }
        return request2;
    }

}