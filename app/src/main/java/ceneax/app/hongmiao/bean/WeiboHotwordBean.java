package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboHotwordBean extends BaseWeiboBean {

    private String icon;
    @SerializedName("desc_extr")
    private String descExtr;
    private String desc;
    private String pic;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescExtr() {
        return descExtr;
    }

    public void setDescExtr(String descExtr) {
        this.descExtr = descExtr;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
