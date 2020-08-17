package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboTopicCardListHeadCardsChannelBean extends BaseWeiboBean {

    private String id;
    private String name;
    @SerializedName("default_add")
    private int defaultAdd;
    @SerializedName("must_show")
    private int mustShow;
    private String containerid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefaultAdd() {
        return defaultAdd;
    }

    public void setDefaultAdd(int defaultAdd) {
        this.defaultAdd = defaultAdd;
    }

    public int getMustShow() {
        return mustShow;
    }

    public void setMustShow(int mustShow) {
        this.mustShow = mustShow;
    }

    public String getContainerid() {
        return containerid;
    }

    public void setContainerid(String containerid) {
        this.containerid = containerid;
    }

}
