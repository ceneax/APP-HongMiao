package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboProfileStatusesCardBean extends BaseWeiboBean {

    @SerializedName("card_type")
    private int cardType;
    private WeiboBean mblog;

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public WeiboBean getMblog() {
        return mblog;
    }

    public void setMblog(WeiboBean mblog) {
        this.mblog = mblog;
    }

}
