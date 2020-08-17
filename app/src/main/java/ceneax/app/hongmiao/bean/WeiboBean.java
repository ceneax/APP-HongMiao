package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboBean extends BaseWeiboBean {

    // visible, geo, annotations, darwin_tags, hot_weibo_tags, text_tag_tips, comment_manage_info, alchemy_params

    @SerializedName("created_at")
    private String createdAt;
    private long id;
    private String idstr;
    private String mid;
    @SerializedName("can_edit")
    private boolean canEdit;
    private int version;
    @SerializedName("show_additional_indication")
    private int showAdditionalIndication;
    private String text;
    @SerializedName("source_allowclick")
    private int sourceAllowclick;
    @SerializedName("source_type")
    private int sourceType;
    private String source;
    private boolean favorited;
    private boolean truncated;
    @SerializedName("in_reply_to_status_id")
    private String inReplyToStatusId;
    @SerializedName("in_reply_to_user_id")
    private String inReplyToUserId;
    @SerializedName("in_reply_to_screen_name")
    private String inReplyToScreenName;
    @SerializedName("thumbnail_pic")
    private String thumbnailPic;
    @SerializedName("bmiddle_pic")
    private String bmiddlePic;
    @SerializedName("original_pic")
    private String originalPic;
    @SerializedName("is_paid")
    private boolean isPaid;
    @SerializedName("mblog_vip_type")
    private int mblogVipType;
    private WeiboUserBean user;
    @SerializedName("reposts_count")
    private int repostsCount;
    @SerializedName("comments_count")
    private int commentsCount;
    @SerializedName("attitudes_count")
    private int attitudesCount;
    @SerializedName("pending_approval_count")
    private int pendingApprovalCount;
    private boolean isLongText;
    @SerializedName("reward_exhibition_type")
    private int rewardExhibitionType;
    @SerializedName("reward_scheme")
    private String rewardScheme;
    @SerializedName("hide_flag")
    private int hideFlag;
    private int mlevel;
    @SerializedName("biz_feature")
    private long bizFeature;
    @SerializedName("topic_id")
    private String topicId;
    @SerializedName("sync_mblog")
    private boolean syncMblog;
    @SerializedName("is_imported_topic")
    private boolean isImportedTopic;
    @SerializedName("page_type")
    private int pageType;
    private int hasActionTypeCard;
    private int mblogtype;
    private String rid;
    private int userType;
    @SerializedName("more_info_type")
    private int moreInfoType;
    private String cardid;
    @SerializedName("positive_recom_flag")
    private int positiveRecomFlag;
    @SerializedName("enable_comment_guide")
    private boolean enableCommentGuide;
    @SerializedName("content_auth")
    private int contentAuth;
    @SerializedName("gif_ids")
    private String gifIds;
    @SerializedName("is_show_bulletin")
    private int isShowBulletin;
    @SerializedName("repost_type")
    private int repostType;
    @SerializedName("pic_num")
    private int picNum;
    @SerializedName("pic_ids")
    private List<String> picIds;
    @SerializedName("pic_infos")
    private Map<String, WeiboPicCateBean> picInfos;
    @SerializedName("retweeted_status")
    private WeiboBean retweetedStatus;
    @SerializedName("ad_state")
    private int adState;
    private WeiboLongTextBean longText;

    public WeiboLongTextBean getLongFullText() {
        return longText;
    }

    public void setLongFullText(WeiboLongTextBean longText) {
        this.longText = longText;
    }

    public List<String> getPicIds() {
        return picIds;
    }

    public void setPicIds(List<String> picIds) {
        this.picIds = picIds;
    }

    public Map<String, WeiboPicCateBean> getPicInfos() {
        return picInfos;
    }

    public void setPicInfos(Map<String, WeiboPicCateBean> picInfos) {
        this.picInfos = picInfos;
    }

    public int getAdState() {
        return adState;
    }

    public void setAdState(int adState) {
        this.adState = adState;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getShowAdditionalIndication() {
        return showAdditionalIndication;
    }

    public void setShowAdditionalIndication(int showAdditionalIndication) {
        this.showAdditionalIndication = showAdditionalIndication;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSourceAllowclick() {
        return sourceAllowclick;
    }

    public void setSourceAllowclick(int sourceAllowclick) {
        this.sourceAllowclick = sourceAllowclick;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean getTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public String getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public void setInReplyToStatusId(String inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    public String getInReplyToUserId() {
        return inReplyToUserId;
    }

    public void setInReplyToUserId(String inReplyToUserId) {
        this.inReplyToUserId = inReplyToUserId;
    }

    public String getInReplyToScreenName() {
        return inReplyToScreenName;
    }

    public void setInReplyToScreenName(String inReplyToScreenName) {
        this.inReplyToScreenName = inReplyToScreenName;
    }

    public String getThumbnailPic() {
        return thumbnailPic;
    }

    public void setThumbnailPic(String thumbnailPic) {
        this.thumbnailPic = thumbnailPic;
    }

    public String getBmiddlePic() {
        return bmiddlePic;
    }

    public void setBmiddlePic(String bmiddlePic) {
        this.bmiddlePic = bmiddlePic;
    }

    public String getOriginalPic() {
        return originalPic;
    }

    public void setOriginalPic(String originalPic) {
        this.originalPic = originalPic;
    }

    public boolean getPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getMblogVipType() {
        return mblogVipType;
    }

    public void setMblogVipType(int mblogVipType) {
        this.mblogVipType = mblogVipType;
    }

    public WeiboUserBean getUser() {
        return user;
    }

    public void setUser(WeiboUserBean user) {
        this.user = user;
    }

    public int getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(int repostsCount) {
        this.repostsCount = repostsCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getAttitudesCount() {
        return attitudesCount;
    }

    public void setAttitudesCount(int attitudesCount) {
        this.attitudesCount = attitudesCount;
    }

    public int getPendingApprovalCount() {
        return pendingApprovalCount;
    }

    public void setPendingApprovalCount(int pendingApprovalCount) {
        this.pendingApprovalCount = pendingApprovalCount;
    }

    public boolean getLongText() {
        return isLongText;
    }

    public void setLongText(boolean longText) {
        isLongText = longText;
    }

    public int getRewardExhibitionType() {
        return rewardExhibitionType;
    }

    public void setRewardExhibitionType(int rewardExhibitionType) {
        this.rewardExhibitionType = rewardExhibitionType;
    }

    public String getRewardScheme() {
        return rewardScheme;
    }

    public void setRewardScheme(String rewardScheme) {
        this.rewardScheme = rewardScheme;
    }

    public int getHideFlag() {
        return hideFlag;
    }

    public void setHideFlag(int hideFlag) {
        this.hideFlag = hideFlag;
    }

    public int getMlevel() {
        return mlevel;
    }

    public void setMlevel(int mlevel) {
        this.mlevel = mlevel;
    }

    public long getBizFeature() {
        return bizFeature;
    }

    public void setBizFeature(long bizFeature) {
        this.bizFeature = bizFeature;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public boolean getSyncMblog() {
        return syncMblog;
    }

    public void setSyncMblog(boolean syncMblog) {
        this.syncMblog = syncMblog;
    }

    public boolean getImportedTopic() {
        return isImportedTopic;
    }

    public void setImportedTopic(boolean importedTopic) {
        isImportedTopic = importedTopic;
    }

    public int getPageType() {
        return pageType;
    }

    public void setPageType(int pageType) {
        this.pageType = pageType;
    }

    public int getHasActionTypeCard() {
        return hasActionTypeCard;
    }

    public void setHasActionTypeCard(int hasActionTypeCard) {
        this.hasActionTypeCard = hasActionTypeCard;
    }

    public int getMblogtype() {
        return mblogtype;
    }

    public void setMblogtype(int mblogtype) {
        this.mblogtype = mblogtype;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getMoreInfoType() {
        return moreInfoType;
    }

    public void setMoreInfoType(int moreInfoType) {
        this.moreInfoType = moreInfoType;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public int getPositiveRecomFlag() {
        return positiveRecomFlag;
    }

    public void setPositiveRecomFlag(int positiveRecomFlag) {
        this.positiveRecomFlag = positiveRecomFlag;
    }

    public boolean getEnableCommentGuide() {
        return enableCommentGuide;
    }

    public void setEnableCommentGuide(boolean enableCommentGuide) {
        this.enableCommentGuide = enableCommentGuide;
    }

    public int getContentAuth() {
        return contentAuth;
    }

    public void setContentAuth(int contentAuth) {
        this.contentAuth = contentAuth;
    }

    public String getGifIds() {
        return gifIds;
    }

    public void setGifIds(String gifIds) {
        this.gifIds = gifIds;
    }

    public int getIsShowBulletin() {
        return isShowBulletin;
    }

    public void setIsShowBulletin(int isShowBulletin) {
        this.isShowBulletin = isShowBulletin;
    }

    public int getRepostType() {
        return repostType;
    }

    public void setRepostType(int repostType) {
        this.repostType = repostType;
    }

    public int getPicNum() {
        return picNum;
    }

    public void setPicNum(int picNum) {
        this.picNum = picNum;
    }

    public WeiboBean getRetweetedStatus() {
        return retweetedStatus;
    }

    public void setRetweetedStatus(WeiboBean retweetedStatus) {
        this.retweetedStatus = retweetedStatus;
    }

}
