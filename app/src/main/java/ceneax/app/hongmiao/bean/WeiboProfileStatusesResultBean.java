package ceneax.app.hongmiao.bean;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboProfileStatusesResultBean extends BaseWeiboBean {

    private List<WeiboProfileStatusesCardBean> cards;

    public List<WeiboProfileStatusesCardBean> getCards() {
        return cards;
    }

    public void setCards(List<WeiboProfileStatusesCardBean> cards) {
        this.cards = cards;
    }

}
