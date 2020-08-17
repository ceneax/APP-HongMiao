package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboTopicCardListHeadCardsBean extends BaseWeiboBean {

    @SerializedName("menu_scheme")
    private String menuScheme;
    @SerializedName("title_top")
    private String titleTop;
    @SerializedName("head_type")
    private int headType;
    @SerializedName("head_type_name")
    private String headTypeName;
    @SerializedName("head_data")
    private WeiboTopicCardListHeadCardsDataBean headData;
    @SerializedName("channel_list")
    private List<WeiboTopicCardListHeadCardsChannelBean> channelList;

    public String getMenuScheme() {
        return menuScheme;
    }

    public void setMenuScheme(String menuScheme) {
        this.menuScheme = menuScheme;
    }

    public String getTitleTop() {
        return titleTop;
    }

    public void setTitleTop(String titleTop) {
        this.titleTop = titleTop;
    }

    public int getHeadType() {
        return headType;
    }

    public void setHeadType(int headType) {
        this.headType = headType;
    }

    public String getHeadTypeName() {
        return headTypeName;
    }

    public void setHeadTypeName(String headTypeName) {
        this.headTypeName = headTypeName;
    }

    public WeiboTopicCardListHeadCardsDataBean getHeadData() {
        return headData;
    }

    public void setHeadData(WeiboTopicCardListHeadCardsDataBean headData) {
        this.headData = headData;
    }

    public List<WeiboTopicCardListHeadCardsChannelBean> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<WeiboTopicCardListHeadCardsChannelBean> channelList) {
        this.channelList = channelList;
    }

}
