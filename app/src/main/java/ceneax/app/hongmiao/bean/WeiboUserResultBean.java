package ceneax.app.hongmiao.bean;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboUserResultBean extends BaseWeiboBean {

    private WeiboUserBean userInfo;
    private WeiboUserTabInfo tabsInfo;

    public WeiboUserTabInfo getTabsInfo() {
        return tabsInfo;
    }

    public void setTabsInfo(WeiboUserTabInfo tabsInfo) {
        this.tabsInfo = tabsInfo;
    }

    public WeiboUserBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WeiboUserBean userInfo) {
        this.userInfo = userInfo;
    }

}
