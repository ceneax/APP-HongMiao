package ceneax.app.hongmiao.bean;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboTopicResultBean extends BaseWeiboBean {

    private WeiboTopicCardListInfoBean cardlistInfo;
    private List<WeiboTopicCardsBean> cards;

    public List<WeiboTopicCardsBean> getCards() {
        return cards;
    }

    public void setCards(List<WeiboTopicCardsBean> cards) {
        this.cards = cards;
    }

    public WeiboTopicCardListInfoBean getCardlistInfo() {
        return cardlistInfo;
    }

    public void setCardlistInfo(WeiboTopicCardListInfoBean cardlistInfo) {
        this.cardlistInfo = cardlistInfo;
    }

}
