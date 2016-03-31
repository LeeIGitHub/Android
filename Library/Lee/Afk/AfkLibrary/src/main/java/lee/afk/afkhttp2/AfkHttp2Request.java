package lee.afk.afkhttp2;

import lee.afk.afkhttp.volley.Request;

/**
 * Created by Lee on 2016/3/15.
 */
public class AfkHttp2Request {
    private Request request;

    public AfkHttp2Request(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void cancel() {
        if (request != null) {
            request.cancel();
        }
    }
}
