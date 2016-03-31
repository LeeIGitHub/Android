package lee.afk.demo.fragment;

import android.content.Context;

import lee.afk.afkhttp.volley.Request;
import lee.afk.afkhttp.volley.VolleyError;
import lee.afk.afkhttp2.AfkHttp2;
import lee.afk.afkhttp2.AfkHttpListener2;
import lee.afk.afkutils.log.LeeLog;
import lee.afk.demo.bean.AHA;

/**
 * Created by Lee on 2016/3/8.
 */
public class BaseMyHttpTest2 extends AfkHttp2 {

    public static class MyHttpListener<T> implements AfkHttpListener2<T> {
        @Override
        public void onSuccess(T t) {
            String str = "";
            if (t != null)
                str = t.toString();
            LeeLog.p("onSuccess " + str);
        }

        @Override
        public void onFailed(VolleyError error) {
            LeeLog.p("onFailed " + error.toString());
        }

        @Override
        public void onFinish() {
            LeeLog.p("onFinish ");
        }
    }


    public static void getLKJASDLKJ(Context context, MyHttpListener listener){
        getData(context,"http://s.56wmm.com/AppBaiscService.asmx/GetBannerListV1?token=fcace0ec-b1d6-45c7-8e45-6536c8f68c1a&userId=300000352&",10,listener,AHA.class);
    }
}
