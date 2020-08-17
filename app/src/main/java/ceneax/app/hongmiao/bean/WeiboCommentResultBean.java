package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboCommentResultBean extends BaseWeiboBean {

    private long id;
    private String idStr;
    private WeiboBean status;
    @SerializedName("root_comments")
    private List<WeiboCommentBean> comments;
    @SerializedName("max_id")
    private long maxId;
    @SerializedName("max_id_str")
    private String maxIdStr;

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

    public WeiboBean getStatus() {
        return status;
    }

    public void setStatus(WeiboBean status) {
        this.status = status;
    }

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

}
