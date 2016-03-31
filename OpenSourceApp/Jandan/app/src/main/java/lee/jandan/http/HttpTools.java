package lee.jandan.http;

import android.content.Context;

import lee.afk.afkhttp2.AfkHttpListener2;
import lee.jandan.bean.WuliaoPic;

/**
 * Created by Lee on 2016/3/14.
 */
public class HttpTools {

    public static void getWuliaoPic(Context context, JandanHttpListener listener, Object tag) {
        JandanHttp.getJandanHttpResponse(context, "http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_pic_comments&page=1", listener, WuliaoPic.Bean.class,tag);
    }

}
