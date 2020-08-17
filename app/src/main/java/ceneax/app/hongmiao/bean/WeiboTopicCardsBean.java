package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboTopicCardsBean extends BaseWeiboBean {

    /**
     * type:
     * 6.desc，更多热门微博
     * 7.desc，导语
     * 9.mblog，微博
     * 11.card_group，前面是正常微博，最后是6
     * 42.小标题，desc
     * 52.items
     * 10.话题贡献榜
     * 24.话题贡献榜
     */
    @SerializedName("card_type")
    private int cardType;
    private String desc;
    private String itemid;
    private WeiboBean mblog;
    @SerializedName("display_followbtn")
    private int displayFollowbtn;
    @SerializedName("display_arrow")
    private int displayArrow;
    @SerializedName("show_type")
    private int showType;
    @SerializedName("is_hotweibo")
    private int isHotweibo;
    @SerializedName("display_socialtitle")
    private int displaySocialtitle;
    @SerializedName("card_group")
    private List<WeiboTopicCardsBean> cardGroup;
    private String title;

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public WeiboBean getMblog() {
        return mblog;
    }

    public void setMblog(WeiboBean mblog) {
        this.mblog = mblog;
    }

    public int getDisplayFollowbtn() {
        return displayFollowbtn;
    }

    public void setDisplayFollowbtn(int displayFollowbtn) {
        this.displayFollowbtn = displayFollowbtn;
    }

    public int getDisplayArrow() {
        return displayArrow;
    }

    public void setDisplayArrow(int displayArrow) {
        this.displayArrow = displayArrow;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getIsHotweibo() {
        return isHotweibo;
    }

    public void setIsHotweibo(int isHotweibo) {
        this.isHotweibo = isHotweibo;
    }

    public int getDisplaySocialtitle() {
        return displaySocialtitle;
    }

    public void setDisplaySocialtitle(int displaySocialtitle) {
        this.displaySocialtitle = displaySocialtitle;
    }

    public List<WeiboTopicCardsBean> getCardGroup() {
        return cardGroup;
    }

    public void setCardGroup(List<WeiboTopicCardsBean> cardGroup) {
        this.cardGroup = cardGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
