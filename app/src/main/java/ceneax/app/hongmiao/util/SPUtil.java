package ceneax.app.hongmiao.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import ceneax.app.hongmiao.App;
import ceneax.app.hongmiao.bean.WeiboGsidBean;

public class SPUtil {

    private static final String WEIBO_GSID = "WeiboGsid";

    public static WeiboGsidBean getWeiboGsid() {
        SharedPreferences sp = App.getAppContext().getSharedPreferences(WEIBO_GSID, Context.MODE_PRIVATE);
        String gsid = sp.getString(WEIBO_GSID, null);

        return gsid == null ? null : new Gson().fromJson(gsid, WeiboGsidBean.class);
    }

    public static void setWeiboGsid(WeiboGsidBean bean) {
        SharedPreferences.Editor editor = App.getAppContext().getSharedPreferences(WEIBO_GSID, Context.MODE_PRIVATE).edit();

        if(bean != null) {
            editor.putString(WEIBO_GSID, new Gson().toJson(bean));
            editor.apply();
        }
    }

}
