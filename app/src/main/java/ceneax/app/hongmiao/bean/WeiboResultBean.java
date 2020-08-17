package ceneax.app.hongmiao.bean;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboResultBean extends BaseWeiboBean {

    private List<WeiboBean> statuses;

    public List<WeiboBean> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<WeiboBean> statuses) {
        this.statuses = statuses;
    }

}
