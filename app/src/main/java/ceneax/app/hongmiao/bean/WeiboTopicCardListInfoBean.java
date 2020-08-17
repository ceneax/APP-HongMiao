package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboTopicCardListInfoBean extends BaseWeiboBean {

    @SerializedName("v_p")
    private String vP;
    private String containerid;
    @SerializedName("title_top")
    private String titleTop;
    private int total;
    @SerializedName("show_style")
    private int showStyle;
    private long starttime;
    @SerializedName("can_shared")
    private int canShared;
    @SerializedName("cardlist_head_cards")
    private List<WeiboTopicCardListHeadCardsBean> cardlistHeadCards;

    public String getvP() {
        return vP;
    }

    public void setvP(String vP) {
        this.vP = vP;
    }

    public String getContainerid() {
        return containerid;
    }

    public void setContainerid(String containerid) {
        this.containerid = containerid;
    }

    public String getTitleTop() {
        return titleTop;
    }

    public void setTitleTop(String titleTop) {
        this.titleTop = titleTop;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getShowStyle() {
        return showStyle;
    }

    public void setShowStyle(int showStyle) {
        this.showStyle = showStyle;
    }

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public int getCanShared() {
        return canShared;
    }

    public void setCanShared(int canShared) {
        this.canShared = canShared;
    }

    public List<WeiboTopicCardListHeadCardsBean> getCardlistHeadCards() {
        return cardlistHeadCards;
    }

    public void setCardlistHeadCards(List<WeiboTopicCardListHeadCardsBean> cardlistHeadCards) {
        this.cardlistHeadCards = cardlistHeadCards;
    }

}
