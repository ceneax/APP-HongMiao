package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboGroupResultBean extends BaseWeiboBean {

    @SerializedName("total_number")
    private int totalNumber;
    private List<WeiboGroupBean> lists;

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<WeiboGroupBean> getLists() {
        return lists;
    }

    public void setLists(List<WeiboGroupBean> lists) {
        this.lists = lists;
    }

}
