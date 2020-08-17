package ceneax.app.hongmiao.bean;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboHotwordCardBean extends BaseWeiboBean {

    private List<WeiboHotwordCardGroupBean> cards;

    public List<WeiboHotwordCardGroupBean> getCards() {
        return cards;
    }

    public void setCards(List<WeiboHotwordCardGroupBean> cards) {
        this.cards = cards;
    }

}
