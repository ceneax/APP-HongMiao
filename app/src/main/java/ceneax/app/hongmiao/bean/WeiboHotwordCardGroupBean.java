package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboHotwordCardGroupBean extends BaseWeiboBean {

    @SerializedName("card_group")
    private List<WeiboHotwordBean> cardGroup;

    public List<WeiboHotwordBean> getCardGroup() {
        return cardGroup;
    }

    public void setCardGroup(List<WeiboHotwordBean> cardGroup) {
        this.cardGroup = cardGroup;
    }

}
