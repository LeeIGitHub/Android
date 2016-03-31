package lee.afk.afkhttp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lee.afk.afkhttp.volley.DefaultRetryPolicy;
import lee.afk.afkhttp.volley.Request;
import lee.afk.afkhttp.volley.RequestQueue;
import lee.afk.afkhttp.volley.Response;
import lee.afk.afkhttp.volley.VolleyError;
import lee.afk.afkhttp.volley.toolbox.StringRequest;
import lee.afk.afkhttp.volley.toolbox.Volley;

/**
 * Created by Lee on 2015/11/4.
 */
public class AfkHttpUtils {

    private static RequestQueue mRequestQueue;

    private interface AfkHttpListener<T>{
        void onSuccess(T t);
        void onFailed(VolleyError error);
        void onFinish();
    }

    public static abstract class AfkHttpRequestListener<T> extends TypeToken<T> implements AfkHttpListener<T>{
        public AfkHttpRequestListener(){}
    }


    private static void init(Context context){
        if(mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(context);
    }

    public static void addRequestQueue(Context context,Request request){
        init(context);

        mRequestQueue.add(request);
    }
    public static void cancelRequestQueue(Request request){
        request.cancel();
    }



    private static <T> Request getHttpRequest(Context context,int method,String url, final AfkHttpRequestListener<T> listener){
        StringRequest request = new StringRequest
        (
                method,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(listener == null)
                            return ;

                        T t = new Gson().fromJson(response,listener.getType());
                        listener.onSuccess(t);
                        listener.onFinish();
                    }
               },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onFailed(error);
                        listener.onFinish();
                    }
                }
        );

        DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(30 * 1000,0,1.0F);
        request.setRetryPolicy(retryPolicy);
        request.setShouldCache(false);
        request.setTag(context);

        return request;
    }

    public static synchronized <T> Request get(Context context, String url, AfkHttpRequestListener<T> listener){
        Request request = getHttpRequest(context,Request.Method.GET,url,listener);
        addRequestQueue(context,request);
        return request;
    }

}
