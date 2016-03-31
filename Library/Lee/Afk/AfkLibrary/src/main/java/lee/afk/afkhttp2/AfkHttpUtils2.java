package lee.afk.afkhttp2;

import android.content.Context;

import com.google.gson.Gson;

import lee.afk.afkhttp.volley.DefaultRetryPolicy;
import lee.afk.afkhttp.volley.Request;
import lee.afk.afkhttp.volley.RequestQueue;
import lee.afk.afkhttp.volley.Response;
import lee.afk.afkhttp.volley.VolleyError;
import lee.afk.afkhttp.volley.toolbox.StringRequest;
import lee.afk.afkhttp.volley.toolbox.Volley;

/**
 * Created by Lee on 2016/3/4.
 */
public class AfkHttpUtils2 {
    private static RequestQueue mRequestQueue;

    private static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(context);

        return mRequestQueue;
    }

    public static void addRequestQueue(Context context, Request request) {
        getRequestQueue(context).add(request);
    }

    public static synchronized <T> Request getHttpRequest(Context context, int method, String url, int timeOut, final AfkHttpListener2<T> listener2, final Class<T> c) {
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                T t = new Gson().fromJson(response, c);
                listener2.onSuccess(t);
                listener2.onFinish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener2.onFailed(error);
                listener2.onFinish();
            }
        });

        DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(timeOut * 1000, 0, 1.0F);
        request.setRetryPolicy(retryPolicy);
        request.setShouldCache(false);
        request.setTag(context);

        return request;
    }

    public static synchronized void cancel(Request request) {
        request.cancel();
    }

    public static synchronized void cancelAll(Context context, Object object) {
        getRequestQueue(context).cancelAll(object);
    }
}
