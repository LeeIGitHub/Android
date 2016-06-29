package lee.jandan.http;

/**
 * Created by Lee on 2016/6/13.
 */
public class HttpUrl {
    /**
     * 无聊图
     */
    public static final String URL_BORING_PICTURE = "http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_pic_comments&page=";
    /**
     * 妹子图
     */
    public static final String URL_SISTER = "http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments&page=";

    /**
     * 评论列表
     *
     * http://jandan.duoshuo.com/api/threads/listPosts.json?thread_key=comment-3167919
     *
     * comment-  comment_ID
     */
    public static final String URL_COMMENT_LIST = "http://jandan.duoshuo.com/api/threads/listPosts.json?thread_key=";
    /**
     * 发表评论
     */
    public static final String URL_PUSH_COMMENT = "http://jandan.duoshuo.com/api/posts/create.json";
}
