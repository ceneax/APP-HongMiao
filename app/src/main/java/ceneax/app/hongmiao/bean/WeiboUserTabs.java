package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboUserTabs extends BaseWeiboBean {

    private int id;
    private String tabKey;
    @SerializedName("must_show")
    private int mustShow;
    private int hidden;
    private String title;
    private String containerid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTabKey() {
        return tabKey;
    }

    public void setTabKey(String tabKey) {
        this.tabKey = tabKey;
    }

    public int getMustShow() {
        return mustShow;
    }

    public void setMustShow(int mustShow) {
        this.mustShow = mustShow;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContainerid() {
        return containerid;
    }

    public void setContainerid(String containerid) {
        this.containerid = containerid;
    }

}
