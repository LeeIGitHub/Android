package lee.jandan.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dahan on 2016/3/17.
 */
public class JandanPic implements Serializable {


    /**
     * comment_ID : 3088366
     * comment_post_ID : 26402
     * comment_author : inf
     * comment_author_email : why313233@163.com
     * comment_author_url :
     * comment_author_IP : 49.64.200.28
     * comment_date : 2016-03-17 12:16:02
     * comment_date_gmt : 2016-03-17 04:16:02
     * comment_karma : 0
     * comment_approved : 1
     * comment_type :
     * comment_parent : 0
     * user_id : 0
     * comment_subscribe : N
     * comment_reply_ID : 0
     * vote_positive : 0
     * vote_negative : 0
     * vote_ip_pool :
     * text_content :  所谓九头身，一点都不违禾
     * pic :
     */

    private String comment_ID;
    private String comment_post_ID;
    private String comment_author;
    private String comment_author_email;
    private String comment_author_url;
    private String comment_author_IP;
    private String comment_date;
    private String comment_date_gmt;
    private String comment_karma;
    private String comment_approved;
    private String comment_type;
    private String comment_parent;
    private String user_id;
    private String comment_subscribe;
    private String comment_reply_ID;
    private String vote_positive;
    private String vote_negative;
    private String vote_ip_pool;
    private String text_content;
    private String pic;

    public void setComment_ID(String comment_ID) {
        this.comment_ID = comment_ID;
    }

    public void setComment_post_ID(String comment_post_ID) {
        this.comment_post_ID = comment_post_ID;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public void setComment_author_email(String comment_author_email) {
        this.comment_author_email = comment_author_email;
    }

    public void setComment_author_url(String comment_author_url) {
        this.comment_author_url = comment_author_url;
    }

    public void setComment_author_IP(String comment_author_IP) {
        this.comment_author_IP = comment_author_IP;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public void setComment_date_gmt(String comment_date_gmt) {
        this.comment_date_gmt = comment_date_gmt;
    }

    public void setComment_karma(String comment_karma) {
        this.comment_karma = comment_karma;
    }

    public void setComment_approved(String comment_approved) {
        this.comment_approved = comment_approved;
    }

    public void setComment_type(String comment_type) {
        this.comment_type = comment_type;
    }

    public void setComment_parent(String comment_parent) {
        this.comment_parent = comment_parent;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setComment_subscribe(String comment_subscribe) {
        this.comment_subscribe = comment_subscribe;
    }

    public void setComment_reply_ID(String comment_reply_ID) {
        this.comment_reply_ID = comment_reply_ID;
    }

    public void setVote_positive(String vote_positive) {
        this.vote_positive = vote_positive;
    }

    public void setVote_negative(String vote_negative) {
        this.vote_negative = vote_negative;
    }

    public void setVote_ip_pool(String vote_ip_pool) {
        this.vote_ip_pool = vote_ip_pool;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getComment_ID() {
        return comment_ID;
    }

    public String getComment_post_ID() {
        return comment_post_ID;
    }

    public String getComment_author() {
        return comment_author;
    }

    public String getComment_author_email() {
        return comment_author_email;
    }

    public String getComment_author_url() {
        return comment_author_url;
    }

    public String getComment_author_IP() {
        return comment_author_IP;
    }

    public String getComment_date() {
        return comment_date;
    }

    public String getComment_date_gmt() {
        return comment_date_gmt;
    }

    public String getComment_karma() {
        return comment_karma;
    }

    public String getComment_approved() {
        return comment_approved;
    }

    public String getComment_type() {
        return comment_type;
    }

    public String getComment_parent() {
        return comment_parent;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getComment_subscribe() {
        return comment_subscribe;
    }

    public String getComment_reply_ID() {
        return comment_reply_ID;
    }

    public String getVote_positive() {
        return vote_positive;
    }

    public String getVote_negative() {
        return vote_negative;
    }

    public String getVote_ip_pool() {
        return vote_ip_pool;
    }

    public String getText_content() {
        return text_content;
    }

    public String getPic() {
        return pic;
    }

    private static List<JandanPic> getJandan(WuliaoPic wuliaoPic) {
        List<String> pics = wuliaoPic.getPics();
        if (pics == null || pics.size() < 1)
            return null;

        List<JandanPic> result = new ArrayList<>(pics.size());

        for (String pic : pics) {
            JandanPic jandanPic = new JandanPic();
            jandanPic.setPic(pic);
            result.add(jandanPic);
        }
        return result;
    }

    public static List<JandanPic> getJandan(List<WuliaoPic> wuliaoPicList){
        if(wuliaoPicList == null || wuliaoPicList.size() < 1)
            return null;

        List<JandanPic> result = new ArrayList<>();

        for(WuliaoPic wuliaoPic : wuliaoPicList){
            List<JandanPic> jandanPicList = getJandan(wuliaoPic);
            result.addAll(result.size(),jandanPicList);
        }

        return result;
    }
}
