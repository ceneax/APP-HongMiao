package ceneax.app.hongmiao;

import ceneax.app.hongmiao.bean.WeiboGsidBean;
import ceneax.app.hongmiao.bean.WeiboUserBean;
import ceneax.app.hongmiao.bean.WeiboUserResultBean;

public class Config {

    // 全局常量
    public static final String WEIBO_REG_AT = "@[\u4e00-\u9fa5\\w]+";
    public static final String WEIBO_REG_TOPIC = "#[\u4e00-\u9fa5\\w]+#";
    public static final String WEIBO_REG_SUPER_TOPIC = "#[\u4e00-\u9fa5\\w]+\\[超话\\]#";
    public static final String WEIBO_REG_EMOJI = "\\[[\u4e00-\u9fa5\\w]+\\]";
    public static final String WEIBO_REG_URL = "http://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public static final String WEIBO_REG_ALL = "(" + WEIBO_REG_AT + ")|(" + WEIBO_REG_TOPIC + ")|(" + WEIBO_REG_SUPER_TOPIC + ")|(" + WEIBO_REG_EMOJI + ")|(" + WEIBO_REG_URL + ")";

    public static final String GOTO_WEIBO_PROFILE_ACTIVITY = "weiboUserInfo";
    public static final String GOTO_WEIBO_DETAIL_ACTIVITY = "weiboDetail";
    public static final String GOTO_WEIBO_INDEX_FRAGMENT_TEMP = "groupId";
    public static final String GOTO_WEB_VIEW_ACTIVITY = "webView";
    public static final String GOTO_TOPIC_ACTIVITY = "topicActivity";

    // 全局变量
    public static WeiboGsidBean weiboGsid = null;
    public static WeiboUserResultBean weiboUserResultBean = null;

    public static int currentTheme = R.style.AppTheme;

}
