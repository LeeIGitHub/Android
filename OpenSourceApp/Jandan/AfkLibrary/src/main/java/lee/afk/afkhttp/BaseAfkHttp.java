package lee.afk.afkhttp;

import lee.afk.afkhttp.volley.VolleyError;

/**
 * Created by Lee on 2015/11/4.
 */
public class BaseAfkHttp<T> extends AfkHttp<T> {
    @Override
    AfkHttpUtils.AfkHttpRequestListener<T> getListener() {
        AfkHttpUtils.AfkHttpRequestListener listener =  new AfkHttpUtils.AfkHttpRequestListener<T>(){
            @Override
            public void onSuccess(T test) {
                BaseAfkHttp.this.onSuccess(test);
            }

            @Override
            public void onFailed(VolleyError error) {
                BaseAfkHttp.this.onFailed(error);
            }

            @Override
            public void onFinish() {
                BaseAfkHttp.this.onFinish();
            }
        };

        return listener;
    }

    @Override
    void run() {

    }

    @Override
    void cancel() {

    }

    @Override
    void onSuccess(T o) {

    }

    @Override
    void onFailed(VolleyError error) {

    }

    @Override
    void onFinish() {

    }
}
