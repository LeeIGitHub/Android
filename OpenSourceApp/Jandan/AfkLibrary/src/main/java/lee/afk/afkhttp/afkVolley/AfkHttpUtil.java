package lee.afk.afkhttp.afkVolley;

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
import lee.afk.afkutils.log.LeeLog;

/**
 * Created by Lee on 2015/9/1.
 */
public class AfkHttpUtil {

    private static RequestQueue mRequestQueue;

    public abstract static class AfkHttpRequestListener<T> extends TypeToken<T> implements AfkHttpRequest<T> {
        public AfkHttpRequestListener() {
        }
    }


    public interface AfkHttpRequest<T> {
        void onSuccess(T var);

        void onFailed(AfkHttpError error);

        void onFinished();
    }

    /**
     * 单例mRequestQuueu
     * @param context
     */
    public static synchronized void initRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
    }

    /**
     * 构造Request(Get)
     * @param context
     * @param url
     * @param requestListener
     * @param <T>
     * @return
     */
    public static <T> Request buildGetRequestTask(Context context, String url, final AfkHttpUtil.AfkHttpRequestListener<T> requestListener) {
        initRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                Gson gson = new Gson();
                T t = gson.fromJson(response, requestListener.getType());
                requestListener.onSuccess(t);
                requestListener.onFinished();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestListener.onFailed((AfkHttpError)error);
                requestListener.onFinished();
            }
        });

        DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(30 * 1000,0,1.0F);
        stringRequest.setRetryPolicy(retryPolicy);
        stringRequest.setShouldCache(false);
        stringRequest.setTag(context);
        return stringRequest;
    }

    /**
     * 放入队列（放入后会自动执行）
     * @param request
     * @return
     */
    public static synchronized Request<?> addToQueue(Request<?> request){
        return mRequestQueue.add(request);
    }

    /**
     * 获取数据
     * @param context
     * @param url
     * @param listener
     * @param <T>
     */
    public static synchronized <T> void getData(Context context,String url, AfkHttpRequestListener<T> listener){
        Request request = buildGetRequestTask(context,url,listener);
        addToQueue(request);
    }

    /**
     * 打印
     * @param url
     */
    public static synchronized void printHttpUrl(String url){
        LeeLog.p("AfkHttpUtil======> url= " + url);
    }
}
