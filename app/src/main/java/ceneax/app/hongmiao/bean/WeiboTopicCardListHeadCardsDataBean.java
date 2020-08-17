package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboTopicCardListHeadCardsDataBean extends BaseWeiboBean {

    @SerializedName("data_type")
    private int dataType;
    @SerializedName("show_blur_background")
    private int showBlurBackground;
    private int width;
    private int height;
    @SerializedName("background_url")
    private String backgroundUrl;
    @SerializedName("portrait_url")
    private String portraitUrl;
    @SerializedName("portrait_sub_text")
    private String portraitSubText;
    private String title;
    private String midtext;
    private String downtext;
    @SerializedName("tag_text")
    private String tagText;

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getShowBlurBackground() {
        return showBlurBackground;
    }

    public void setShowBlurBackground(int showBlurBackground) {
        this.showBlurBackground = showBlurBackground;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getPortraitSubText() {
        return portraitSubText;
    }

    public void setPortraitSubText(String portraitSubText) {
        this.portraitSubText = portraitSubText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMidtext() {
        return midtext;
    }

    public void setMidtext(String midtext) {
        this.midtext = midtext;
    }

    public String getDowntext() {
        return downtext;
    }

    public void setDowntext(String downtext) {
        this.downtext = downtext;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

}
