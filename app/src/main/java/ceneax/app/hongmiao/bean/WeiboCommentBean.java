package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboCommentBean extends BaseWeiboBean {

    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("rootid")
    private long id;
    @SerializedName("rootidstr")
    private String idStr;
    @SerializedName("floor_number")
    private int floorNumber;
    private String text;
    @SerializedName("disable_reply")
    private int disableReply;
    private WeiboUserBean user;
    private boolean liked;
    @SerializedName("total_number")
    private int totalNumber;
    private boolean isLikedByMblogAuthor;
    @SerializedName("like_counts")
    private long likeCounts;
    private List<WeiboCommentBean> comments;

    public List<WeiboCommentBean> getComments() {
        return comments;
    }

    public void setComments(List<WeiboCommentBean> comments) {
        this.comments = comments;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDisableReply() {
        return disableReply;
    }

    public void setDisableReply(int disableReply) {
        this.disableReply = disableReply;
    }

    public WeiboUserBean getUser() {
        return user;
    }

    public void setUser(WeiboUserBean user) {
        this.user = user;
    }

    public boolean getIsLiked() {
        return liked;
    }

    public void setIsLiked(boolean liked) {
        this.liked = liked;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public boolean getIsLikedByMblogAuthor() {
        return isLikedByMblogAuthor;
    }

    public void setIsLikedByMblogAuthor(boolean likedByMblogAuthor) {
        isLikedByMblogAuthor = likedByMblogAuthor;
    }

    public long getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(long likeCounts) {
        this.likeCounts = likeCounts;
    }

}
