package ceneax.app.hongmiao.bean;

import com.google.gson.annotations.SerializedName;

import ceneax.app.hongmiao.base.BaseWeiboBean;

public class WeiboUserBean extends BaseWeiboBean {

    // status, insecurity

    private long id;
    @SerializedName("idstr")
    private String idStr;
    @SerializedName("class")
    private int classField;
    @SerializedName("screen_name")
    private String screenName;
    private String name;
    private String province;
    private String city;
    private String location;
    private String description;
    private String url;
    @SerializedName("profile_image_url")
    private String profileImageUrl;
    @SerializedName("cover_image")
    private String coverImage;
    @SerializedName("cover_image_phone")
    private String coverImagePhone;
    @SerializedName("profile_url")
    private String profileUrl;
    private String domain;
    private String weihao;
    private String gender;
    @SerializedName("followers_count")
    private int followersCount;
    @SerializedName("friends_count")
    private int friendsCount;
    @SerializedName("pagefriends_count")
    private int pagefriendsCount;
    @SerializedName("statuses_count")
    private int statusesCount;
    @SerializedName("video_status_count")
    private int videoStatusCount;
    @SerializedName("video_play_count")
    private int videoPlayCount;
    @SerializedName("favourites_count")
    private int favouritesCount;
    @SerializedName("created_at")
    private String createdAt;
    private boolean following;
    @SerializedName("allow_all_act_msg")
    private boolean allowAllActMsg;
    @SerializedName("geo_enabled")
    private boolean geoEnabled;
    private boolean verified;
    @SerializedName("verified_type")
    private int verifiedType;
    private String remark;
    private String email;
    private int ptype;
    @SerializedName("allow_all_comment")
    private boolean allowAllComment;
    @SerializedName("avatar_large")
    private String avatarLarge;
    @SerializedName("avatar_hd")
    private String avatarHd;
    @SerializedName("verified_reason")
    private String verifiedReason;
    @SerializedName("verified_trade")
    private String verifiedTrade;
    @SerializedName("verified_reason_url")
    private String verifiedReasonUrl;
    @SerializedName("verified_source")
    private String verifiedSource;
    @SerializedName("verified_source_url")
    private String verifiedSourceUrl;
    @SerializedName("follow_me")
    private boolean followMe;
    private boolean like;
    @SerializedName("like_me")
    private boolean likeMe;
    @SerializedName("online_status")
    private int onlineStatus;
    @SerializedName("bi_followers_count")
    private int biFollowersCount;
    private String lang;
    private int star;
    private int mbtype;
    private int mbrank;
    @SerializedName("block_word")
    private int blockWord;
    @SerializedName("block_app")
    private int blockApp;
    @SerializedName("credit_score")
    private int creditScore;
    @SerializedName("user_ability")
    private int userAbility;
    private String cardid;
    @SerializedName("avatargj_id")
    private String avatargjId;
    private int urank;
    @SerializedName("story_read_state")
    private int storyReadState;
    @SerializedName("vclub_member")
    private int vclubMember;
    @SerializedName("is_teenager")
    private int isTeenager;
    @SerializedName("is_guardian")
    private int isGuardian;
    @SerializedName("is_teenager_list")
    private int isTeenagerList;
    @SerializedName("pc_new")
    private int pcNew;
    @SerializedName("special_follow")
    private boolean specialFollow;
    @SerializedName("planet_video")
    private int planetVideo;
    @SerializedName("video_mark")
    private int videoMark;
    @SerializedName("live_status")
    private int liveStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public int getClassField() {
        return classField;
    }

    public void setClassField(int classField) {
        this.classField = classField;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCoverImagePhone() {
        return coverImagePhone;
    }

    public void setCoverImagePhone(String coverImagePhone) {
        this.coverImagePhone = coverImagePhone;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getWeihao() {
        return weihao;
    }

    public void setWeihao(String weihao) {
        this.weihao = weihao;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public int getPagefriendsCount() {
        return pagefriendsCount;
    }

    public void setPagefriendsCount(int pagefriendsCount) {
        this.pagefriendsCount = pagefriendsCount;
    }

    public int getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(int statusesCount) {
        this.statusesCount = statusesCount;
    }

    public int getVideoStatusCount() {
        return videoStatusCount;
    }

    public void setVideoStatusCount(int videoStatusCount) {
        this.videoStatusCount = videoStatusCount;
    }

    public int getVideoPlayCount() {
        return videoPlayCount;
    }

    public void setVideoPlayCount(int videoPlayCount) {
        this.videoPlayCount = videoPlayCount;
    }

    public int getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(int favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean getFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean getAllowAllActMsg() {
        return allowAllActMsg;
    }

    public void setAllowAllActMsg(boolean allowAllActMsg) {
        this.allowAllActMsg = allowAllActMsg;
    }

    public boolean getGeoEnabled() {
        return geoEnabled;
    }

    public void setGeoEnabled(boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getVerifiedType() {
        return verifiedType;
    }

    public void setVerifiedType(int verifiedType) {
        this.verifiedType = verifiedType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
    }

    public boolean getAllowAllComment() {
        return allowAllComment;
    }

    public void setAllowAllComment(boolean allowAllComment) {
        this.allowAllComment = allowAllComment;
    }

    public String getAvatarLarge() {
        return avatarLarge;
    }

    public void setAvatarLarge(String avatarLarge) {
        this.avatarLarge = avatarLarge;
    }

    public String getAvatarHd() {
        return avatarHd;
    }

    public void setAvatarHd(String avatarHd) {
        this.avatarHd = avatarHd;
    }

    public String getVerifiedReason() {
        return verifiedReason;
    }

    public void setVerifiedReason(String verifiedReason) {
        this.verifiedReason = verifiedReason;
    }

    public String getVerifiedTrade() {
        return verifiedTrade;
    }

    public void setVerifiedTrade(String verifiedTrade) {
        this.verifiedTrade = verifiedTrade;
    }

    public String getVerifiedReasonUrl() {
        return verifiedReasonUrl;
    }

    public void setVerifiedReasonUrl(String verifiedReasonUrl) {
        this.verifiedReasonUrl = verifiedReasonUrl;
    }

    public String getVerifiedSource() {
        return verifiedSource;
    }

    public void setVerifiedSource(String verifiedSource) {
        this.verifiedSource = verifiedSource;
    }

    public String getVerifiedSourceUrl() {
        return verifiedSourceUrl;
    }

    public void setVerifiedSourceUrl(String verifiedSourceUrl) {
        this.verifiedSourceUrl = verifiedSourceUrl;
    }

    public boolean getFollowMe() {
        return followMe;
    }

    public void setFollowMe(boolean followMe) {
        this.followMe = followMe;
    }

    public boolean getLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean getLikeMe() {
        return likeMe;
    }

    public void setLikeMe(boolean likeMe) {
        this.likeMe = likeMe;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public int getBiFollowersCount() {
        return biFollowersCount;
    }

    public void setBiFollowersCount(int biFollowersCount) {
        this.biFollowersCount = biFollowersCount;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getMbtype() {
        return mbtype;
    }

    public void setMbtype(int mbtype) {
        this.mbtype = mbtype;
    }

    public int getMbrank() {
        return mbrank;
    }

    public void setMbrank(int mbrank) {
        this.mbrank = mbrank;
    }

    public int getBlockWord() {
        return blockWord;
    }

    public void setBlockWord(int blockWord) {
        this.blockWord = blockWord;
    }

    public int getBlockApp() {
        return blockApp;
    }

    public void setBlockApp(int blockApp) {
        this.blockApp = blockApp;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public int getUserAbility() {
        return userAbility;
    }

    public void setUserAbility(int userAbility) {
        this.userAbility = userAbility;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getAvatargjId() {
        return avatargjId;
    }

    public void setAvatargjId(String avatargjId) {
        this.avatargjId = avatargjId;
    }

    public int getUrank() {
        return urank;
    }

    public void setUrank(int urank) {
        this.urank = urank;
    }

    public int getStoryReadState() {
        return storyReadState;
    }

    public void setStoryReadState(int storyReadState) {
        this.storyReadState = storyReadState;
    }

    public int getVclubMember() {
        return vclubMember;
    }

    public void setVclubMember(int vclubMember) {
        this.vclubMember = vclubMember;
    }

    public int getIsTeenager() {
        return isTeenager;
    }

    public void setIsTeenager(int isTeenager) {
        this.isTeenager = isTeenager;
    }

    public int getIsGuardian() {
        return isGuardian;
    }

    public void setIsGuardian(int isGuardian) {
        this.isGuardian = isGuardian;
    }

    public int getIsTeenagerList() {
        return isTeenagerList;
    }

    public void setIsTeenagerList(int isTeenagerList) {
        this.isTeenagerList = isTeenagerList;
    }

    public int getPcNew() {
        return pcNew;
    }

    public void setPcNew(int pcNew) {
        this.pcNew = pcNew;
    }

    public boolean getIsSpecialFollow() {
        return specialFollow;
    }

    public void setSpecialFollow(boolean specialFollow) {
        this.specialFollow = specialFollow;
    }

    public int getPlanetVideo() {
        return planetVideo;
    }

    public void setPlanetVideo(int planetVideo) {
        this.planetVideo = planetVideo;
    }

    public int getVideoMark() {
        return videoMark;
    }

    public void setVideoMark(int videoMark) {
        this.videoMark = videoMark;
    }

    public int getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(int liveStatus) {
        this.liveStatus = liveStatus;
    }

}
