package lee.afk.afkhttp2;

import lee.afk.afkhttp.volley.VolleyError;

/**
 * Created by dahan on 2016/3/8.
 */
public interface AfkHttpListener2<T> {
    void onSuccess(T t,String request);
    void onFailed(VolleyError error);
    void onFinish();
}
