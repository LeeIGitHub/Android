package lee.afk.afkhttp;

import lee.afk.afkhttp.volley.VolleyError;

/**
 * Created by Lee on 2015/11/4.
 */
public abstract class AfkHttp<T>{
    abstract AfkHttpUtils.AfkHttpRequestListener<T> getListener();
    abstract void run();
    abstract void cancel();
    abstract void onSuccess(T t);
    abstract void onFailed(VolleyError error);
    abstract void onFinish();
}
