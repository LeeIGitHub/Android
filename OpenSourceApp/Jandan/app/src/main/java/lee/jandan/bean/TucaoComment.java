package lee.jandan.bean;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import lee.afk.afkutils.log.LeeLog;
import lee.afk.afkutils.string.AfkString;

/**
 * Created by Lee on 2016/6/13.
 */
public class TucaoComment implements Serializable {

    /**
     * formPosition : bottom
     * order : asc
     * limit : 50
     * hot_posts : 3
     * max_depth : 1
     * show_context : false
     * like_thread_enabled : true
     * parse_html_enabled : true
     * show_weibo : false
     * show_qqt : false
     * show_reposts : true
     * deny_anonymous : false
     * auth_in_win : true
     * require_guest_email : false
     * require_guest_url : false
     * use_smilies : false
     * use_images : true
     * poweredby_text : 多说
     * mzadx_id : null
     * message :
     */

    private OptionsBean options;
    private Object parentPosts;
    private UsersBean users;
    /**
     * thread_id : 6295102025122185986
     * site_id : 1077916
     * title :
     * created_at : 2016-06-12T08:50:56+08:00
     * thread_key : comment-3167919
     * url : http://jandan.net/pic/page-9119#comment-3167919
     * meta : []
     * agent : Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36
     * source : duoshuo
     * author_id : 0
     * comments : 12
     * dislikes : 0
     * likes : 0
     * reposts : 0
     * views : 0
     * post_enable : 1
     */

    private ThreadBean thread;
    /**
     * total : 12
     * pages : 1
     */

    private CursorBean cursor;
    /**
     * options : {"formPosition":"bottom","order":"asc","limit":50,"hot_posts":3,"max_depth":1,"show_context":false,"like_thread_enabled":true,"parse_html_enabled":true,"show_weibo":false,"show_qqt":false,"show_reposts":true,"deny_anonymous":false,"auth_in_win":true,"require_guest_email":false,"require_guest_url":false,"use_smilies":false,"use_images":true,"poweredby_text":"多说","mzadx_id":null,"message":""}
     * response : ["6295103370189341441","6295106195866780417","6295107433689776897","6295109362046206722","6295111274283926274","6295114386608489218","6295115203872817922","6295131007771214593","6295199535094301442","6295205129624748801","6295251032486183681","6295271162117096193"]
     * hotPosts : ["6295103370189341441"]
     * parentPosts : {}
     * users : {}
     * thread : {"thread_id":"6295102025122185986","site_id":1077916,"title":"","created_at":"2016-06-12T08:50:56+08:00","thread_key":"comment-3167919","url":"http://jandan.net/pic/page-9119#comment-3167919","meta":[],"agent":"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36","source":"duoshuo","author_id":"0","comments":12,"dislikes":0,"likes":0,"reposts":0,"views":0,"post_enable":1}
     * cursor : {"total":12,"pages":1}
     * code : 0
     */

    private int code;
    private List<String> response;
    private List<String> hotPosts;
    private List<Tucao> tucaoList;

    public List<Tucao> getTucaoList() {
        return tucaoList;
    }

    public void setTucaoList(List<Tucao> tucaoList) {
        this.tucaoList = tucaoList;
    }

    public OptionsBean getOptions() {
        return options;
    }

    public void setOptions(OptionsBean options) {
        this.options = options;
    }


    public Object getParentPosts() {
        return parentPosts;
    }

    public void setParentPosts(Object parentPosts) {
        this.parentPosts = parentPosts;
    }
//    public String getParentPosts() {
//        return parentPosts;
//    }
//
//    public void setParentPosts(String parentPosts) {
//        this.parentPosts = parentPosts;
//    }

    public UsersBean getUsers() {
        return users;
    }

    public void setUsers(UsersBean users) {
        this.users = users;
    }

    public ThreadBean getThread() {
        return thread;
    }

    public void setThread(ThreadBean thread) {
        this.thread = thread;
    }

    public CursorBean getCursor() {
        return cursor;
    }

    public void setCursor(CursorBean cursor) {
        this.cursor = cursor;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

    public List<String> getHotPosts() {
        return hotPosts;
    }

    public void setHotPosts(List<String> hotPosts) {
        this.hotPosts = hotPosts;
    }

    public static class OptionsBean {
        private String formPosition;
        private String order;
        private int limit;
        private int hot_posts;
        private int max_depth;
        private boolean show_context;
        private boolean like_thread_enabled;
        private boolean parse_html_enabled;
        private boolean show_weibo;
        private boolean show_qqt;
        private boolean show_reposts;
        private boolean deny_anonymous;

        public String getFormPosition() {
            return formPosition;
        }

        public void setFormPosition(String formPosition) {
            this.formPosition = formPosition;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getHot_posts() {
            return hot_posts;
        }

        public void setHot_posts(int hot_posts) {
            this.hot_posts = hot_posts;
        }

        public int getMax_depth() {
            return max_depth;
        }

        public void setMax_depth(int max_depth) {
            this.max_depth = max_depth;
        }

        public boolean isShow_context() {
            return show_context;
        }

        public void setShow_context(boolean show_context) {
            this.show_context = show_context;
        }

        public boolean isLike_thread_enabled() {
            return like_thread_enabled;
        }

        public void setLike_thread_enabled(boolean like_thread_enabled) {
            this.like_thread_enabled = like_thread_enabled;
        }

        public boolean isParse_html_enabled() {
            return parse_html_enabled;
        }

        public void setParse_html_enabled(boolean parse_html_enabled) {
            this.parse_html_enabled = parse_html_enabled;
        }

        public boolean isShow_weibo() {
            return show_weibo;
        }

        public void setShow_weibo(boolean show_weibo) {
            this.show_weibo = show_weibo;
        }

        public boolean isShow_qqt() {
            return show_qqt;
        }

        public void setShow_qqt(boolean show_qqt) {
            this.show_qqt = show_qqt;
        }

        public boolean isShow_reposts() {
            return show_reposts;
        }

        public void setShow_reposts(boolean show_reposts) {
            this.show_reposts = show_reposts;
        }

        public boolean isDeny_anonymous() {
            return deny_anonymous;
        }

        public void setDeny_anonymous(boolean deny_anonymous) {
            this.deny_anonymous = deny_anonymous;
        }
    }

    public static class ParentPostsBean {
    }

    public static class UsersBean {
    }

    public static class ThreadBean {
        private String thread_id;
        private int site_id;
        private String title;
        private String created_at;
        private String thread_key;
        private String url;
        private String agent;
        private String source;
        private String author_id;
        private int comments;
        private int dislikes;
        private int likes;
        private int reposts;
        private int views;
        private int post_enable;
        private List<?> meta;

        public String getThread_id() {
            return thread_id;
        }

        public void setThread_id(String thread_id) {
            this.thread_id = thread_id;
        }

        public int getSite_id() {
            return site_id;
        }

        public void setSite_id(int site_id) {
            this.site_id = site_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getThread_key() {
            return thread_key;
        }

        public void setThread_key(String thread_key) {
            this.thread_key = thread_key;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public int getDislikes() {
            return dislikes;
        }

        public void setDislikes(int dislikes) {
            this.dislikes = dislikes;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getReposts() {
            return reposts;
        }

        public void setReposts(int reposts) {
            this.reposts = reposts;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getPost_enable() {
            return post_enable;
        }

        public void setPost_enable(int post_enable) {
            this.post_enable = post_enable;
        }

        public List<?> getMeta() {
            return meta;
        }

        public void setMeta(List<?> meta) {
            this.meta = meta;
        }
    }

    public static class CursorBean {
        private int total;
        private int pages;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }
    }

    public static class Tucao {

        /**
         * post_id : 6295103370189341441
         * thread_id : 6295102025122185986
         * status : approved
         * source : duoshuo
         * type : duoshuo
         * message : 这就是为什么骑车要戴头盔
         * created_at : 2016-06-12T08:56:09+08:00
         * privileges : []
         * parent_id : 0
         * root_id : 0
         * reposts : 0
         * comments : 0
         * author_id : 11853821
         * author_key : 0
         * agent : Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.2 Safari/537.36
         * likes : 25
         * dislikes : 0
         * reports : 0
         * parents : []
         * author : {"user_id":"11853821","name":"猪皮","avatar_url":"http://tp4.sinaimg.cn/1407700875/50/1282559007/1","url":"http://weibo.com/otakuchen","threads":"","comments":"16","weibo_uid":"1407700875","qq_uid":"","renren_uid":"","kaixin_uid":"","douban_uid":"","netease_uid":"","sohu_uid":"","baidu_uid":"","msn_uid":"","google_uid":"","taobao_uid":""}
         * ip : 117.158.152.71
         * iplocation : 中国
         * is_top : 0
         */

        private String post_id;
        private String thread_id;
        private String status;
        private String source;
        private String type;
        private String message;
        private String created_at;
        private int parent_id;
        private int root_id;
        private int reposts;
        private int comments;
        private String author_id;
        private int author_key;
        private String agent;
        private int likes;
        private int dislikes;
        private int reports;
        /**
         * user_id : 11853821
         * name : 猪皮
         * avatar_url : http://tp4.sinaimg.cn/1407700875/50/1282559007/1
         * url : http://weibo.com/otakuchen
         * threads :
         * comments : 16
         * weibo_uid : 1407700875
         * qq_uid :
         * renren_uid :
         * kaixin_uid :
         * douban_uid :
         * netease_uid :
         * sohu_uid :
         * baidu_uid :
         * msn_uid :
         * google_uid :
         * taobao_uid :
         */

        private AuthorBean author;
        private String ip;
        private String iplocation;
        private int is_top;
        private List<?> privileges;
        private List<?> parents;

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getThread_id() {
            return thread_id;
        }

        public void setThread_id(String thread_id) {
            this.thread_id = thread_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public int getRoot_id() {
            return root_id;
        }

        public void setRoot_id(int root_id) {
            this.root_id = root_id;
        }

        public int getReposts() {
            return reposts;
        }

        public void setReposts(int reposts) {
            this.reposts = reposts;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public int getAuthor_key() {
            return author_key;
        }

        public void setAuthor_key(int author_key) {
            this.author_key = author_key;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getDislikes() {
            return dislikes;
        }

        public void setDislikes(int dislikes) {
            this.dislikes = dislikes;
        }

        public int getReports() {
            return reports;
        }

        public void setReports(int reports) {
            this.reports = reports;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getIplocation() {
            return iplocation;
        }

        public void setIplocation(String iplocation) {
            this.iplocation = iplocation;
        }

        public int getIs_top() {
            return is_top;
        }

        public void setIs_top(int is_top) {
            this.is_top = is_top;
        }

        public List<?> getPrivileges() {
            return privileges;
        }

        public void setPrivileges(List<?> privileges) {
            this.privileges = privileges;
        }

        public List<?> getParents() {
            return parents;
        }

        public void setParents(List<?> parents) {
            this.parents = parents;
        }

        public static class AuthorBean {
            private String user_id;
            private String name;
            private String avatar_url;
            private String url;
            private String threads;
            private String comments;
            private String weibo_uid;
            private String qq_uid;
            private String renren_uid;
            private String kaixin_uid;
            private String douban_uid;
            private String netease_uid;
            private String sohu_uid;
            private String baidu_uid;
            private String msn_uid;
            private String google_uid;
            private String taobao_uid;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThreads() {
                return threads;
            }

            public void setThreads(String threads) {
                this.threads = threads;
            }

            public String getComments() {
                return comments;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public String getWeibo_uid() {
                return weibo_uid;
            }

            public void setWeibo_uid(String weibo_uid) {
                this.weibo_uid = weibo_uid;
            }

            public String getQq_uid() {
                return qq_uid;
            }

            public void setQq_uid(String qq_uid) {
                this.qq_uid = qq_uid;
            }

            public String getRenren_uid() {
                return renren_uid;
            }

            public void setRenren_uid(String renren_uid) {
                this.renren_uid = renren_uid;
            }

            public String getKaixin_uid() {
                return kaixin_uid;
            }

            public void setKaixin_uid(String kaixin_uid) {
                this.kaixin_uid = kaixin_uid;
            }

            public String getDouban_uid() {
                return douban_uid;
            }

            public void setDouban_uid(String douban_uid) {
                this.douban_uid = douban_uid;
            }

            public String getNetease_uid() {
                return netease_uid;
            }

            public void setNetease_uid(String netease_uid) {
                this.netease_uid = netease_uid;
            }

            public String getSohu_uid() {
                return sohu_uid;
            }

            public void setSohu_uid(String sohu_uid) {
                this.sohu_uid = sohu_uid;
            }

            public String getBaidu_uid() {
                return baidu_uid;
            }

            public void setBaidu_uid(String baidu_uid) {
                this.baidu_uid = baidu_uid;
            }

            public String getMsn_uid() {
                return msn_uid;
            }

            public void setMsn_uid(String msn_uid) {
                this.msn_uid = msn_uid;
            }

            public String getGoogle_uid() {
                return google_uid;
            }

            public void setGoogle_uid(String google_uid) {
                this.google_uid = google_uid;
            }

            public String getTaobao_uid() {
                return taobao_uid;
            }

            public void setTaobao_uid(String taobao_uid) {
                this.taobao_uid = taobao_uid;
            }
        }
    }

    public static TucaoComment parse(String result) {
        if (AfkString.isEmpty(result))
            return null;

        TucaoComment tucaoComment = null;

        Gson gson = new Gson();

        /*
        try {
            tucaoComment = gson.fromJson(result, TucaoComment.class);
        } catch (JsonSyntaxException e) {
            LeeLog.e(result);
            LeeLog.e(e);
        }*/
        LeeLog.e(result);
        tucaoComment = gson.fromJson(result, TucaoComment.class);

        if (tucaoComment == null || tucaoComment.getResponse() == null || tucaoComment.getResponse().size() < 1)
            return null;

        List<Tucao> tucaoList = new ArrayList<>(tucaoComment.getResponse().size());

        try {
            Tucao tucao = null;

            if (tucaoComment.getParentPosts() instanceof LinkedTreeMap) {
                LinkedTreeMap<String, LinkedTreeMap<String, String>> map = (LinkedTreeMap) tucaoComment.getParentPosts();
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    LinkedTreeMap<String, String> tmap = (LinkedTreeMap<String, String>) entry.getValue();
                    tucao = new Tucao();
                    String message = tmap.get("message");
                    tucao.setMessage(message);
                    tucaoList.add(tucao);
                }
            }
            tucaoComment.setTucaoList(tucaoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tucaoComment;
    }
}
