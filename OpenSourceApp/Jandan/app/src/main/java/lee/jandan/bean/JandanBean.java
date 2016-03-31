package lee.jandan.bean;

import java.io.Serializable;

/**
 * Created by Lee on 2016/3/17.
 */
public abstract class JandanBean<T> implements Serializable {

    /**
     * status : ok
     * current_page : 1
     * total_comments : 216282
     * page_count : 8652
     * count : 25
     * comments : null
     */

    private String status;
    private int current_page;
    private int total_comments;
    private int page_count;
    private int count;
    private T comments;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public void setTotal_comments(int total_comments) {
        this.total_comments = total_comments;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setComments(T comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public int getTotal_comments() {
        return total_comments;
    }

    public int getPage_count() {
        return page_count;
    }

    public int getCount() {
        return count;
    }

    public T getComments() {
        return comments;
    }

    public class Bean extends JandanBean<T>{}
}
