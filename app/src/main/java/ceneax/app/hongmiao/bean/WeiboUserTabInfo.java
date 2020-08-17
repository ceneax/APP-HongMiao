package ceneax.app.hongmiao.bean;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboUserTabInfo extends BaseWeiboBean {

    private List<WeiboUserTabs> tabs;

    public List<WeiboUserTabs> getTabs() {
        return tabs;
    }

    public void setTabs(List<WeiboUserTabs> tabs) {
        this.tabs = tabs;
    }

}
