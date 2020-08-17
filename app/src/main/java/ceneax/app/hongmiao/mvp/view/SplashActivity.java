package ceneax.app.hongmiao.mvp.view;

import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.base.BaseActivity;
import ceneax.app.hongmiao.bean.WeiboGsidBean;
import ceneax.app.hongmiao.bean.WeiboUserBean;
import ceneax.app.hongmiao.bean.WeiboUserResultBean;
import ceneax.app.hongmiao.mvp.contact.CommonContact;
import ceneax.app.hongmiao.mvp.presenter.CommonPresenter;
import ceneax.app.hongmiao.util.SPUtil;

public class SplashActivity extends BaseActivity implements CommonContact.IView {

    public static final String TAG = "SplashActivity";

    private CommonContact.IPresenter iCommonPresenter;

    @Override
    public int initLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initVariable() {
        iCommonPresenter = new CommonPresenter(this);
    }

    @Override
    public void findViews() {
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
        WeiboGsidBean weiboGsidBean = SPUtil.getWeiboGsid();

        if(weiboGsidBean == null) {
            jumpActivity(LoginActivity.class);
        } else {
            Config.weiboGsid = weiboGsidBean;
            iCommonPresenter.getProfile(weiboGsidBean.getGsid(), Long.parseLong(weiboGsidBean.getUid()));
        }
    }

    /**
     * 跳转到指定activity，并关闭当前activity
     * @param cls class
     */
    private void jumpActivity(Class<?> cls) {
        startActivity(new Intent(SplashActivity.this, cls));
        finish();
    }

    @Override
    public void onGetProfileSuccess(WeiboUserResultBean bean) {
        Config.weiboUserResultBean = bean;
        jumpActivity(MainActivity.class);
    }

    @Override
    public void onGetProfileFail(IOException e) {
        jumpActivity(LoginActivity.class);
    }

}
