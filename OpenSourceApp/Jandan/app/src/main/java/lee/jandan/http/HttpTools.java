package lee.jandan.http;

import android.content.Context;

import lee.afk.afkhttp2.AfkHttp2;
import lee.afk.afkhttp2.AfkHttpListener2;
import lee.jandan.bean.TucaoComment;
import lee.jandan.bean.WuliaoPic;

/**
 * Created by Lee on 2016/3/14.
 */
public class HttpTools {

    public static void getWuliaoPic(Context context, JandanHttpListener listener, int page, Object tag) {
        JandanHttp.getJandanHttpResponse(context, "http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_pic_comments&page=" + page, listener, WuliaoPic.Bean.class, tag);
    }

    public static void getTucao(Context context, JandanHttpListener listener, Object tag, String tucaoId) {
        JandanHttp.getJandanHttpResponse(context, HttpUrl.URL_COMMENT_LIST + "comment-" + tucaoId, listener, null, tag);
    }
}
