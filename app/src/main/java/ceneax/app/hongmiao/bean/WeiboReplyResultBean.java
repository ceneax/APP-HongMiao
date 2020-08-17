package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboReplyResultBean extends BaseWeiboBean {

    private List<WeiboCommentBean> comments;
    @SerializedName("max_id")
    private long maxId;
    @SerializedName("max_id_str")
    private String maxIdStr;
    @SerializedName("previous_cursor")
    private long previousCursor;
    @SerializedName("previous_cursor_str")
    private String previousCursorStr;
    @SerializedName("next_cursor")
    private long nextCursor;
    @SerializedName("next_cursor_str")
    private String nextCursorStr;
    @SerializedName("total_number")
    private int totalNumber;
    private WeiboBean status;
    @SerializedName("recommend_max_id")
    private long recommendMaxId;

    public List<WeiboCommentBean> getComments() {
        return comments;
    }

    public void setComments(List<WeiboCommentBean> comments) {
        this.comments = comments;
    }

    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public String getMaxIdStr() {
        return maxIdStr;
    }

    public void setMaxIdStr(String maxIdStr) {
        this.maxIdStr = maxIdStr;
    }

    public long getPreviousCursor() {
        return previousCursor;
    }

    public void setPreviousCursor(long previousCursor) {
        this.previousCursor = previousCursor;
    }

    public String getPreviousCursorStr() {
        return previousCursorStr;
    }

    public void setPreviousCursorStr(String previousCursorStr) {
        this.previousCursorStr = previousCursorStr;
    }

    public long getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(long nextCursor) {
        this.nextCursor = nextCursor;
    }

    public String getNextCursorStr() {
        return nextCursorStr;
    }

    public void setNextCursorStr(String nextCursorStr) {
        this.nextCursorStr = nextCursorStr;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public WeiboBean getStatus() {
        return status;
    }

    public void setStatus(WeiboBean status) {
        this.status = status;
    }

    public long getRecommendMaxId() {
        return recommendMaxId;
    }

    public void setRecommendMaxId(long recommendMaxId) {
        this.recommendMaxId = recommendMaxId;
    }

}
