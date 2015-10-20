package lee.afk.afkhttp.afkVolley;

import android.content.Context;

/**
 * Created by Lee on 2015/9/1.
 */
public class LeeHttpUtil {

    public static synchronized <T> void getData(Context context,String url,LeeHttpRequestListener listener){
        AfkHttpUtil.printHttpUrl(url);
        AfkHttpUtil.getData(context,url,listener);
    }


    public abstract static class LeeHttpRequestListener<T> extends AfkHttpUtil.AfkHttpRequestListener<T>{
        public LeeHttpRequestListener(){

        }
        @Override
        public void onSuccess(T var) {

        }
        @Override
        public void onFailed(AfkHttpError error) {

        }
        @Override
        public void onFinished() {

        }
    }
}
