package ceneax.app.hongmiao.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.base.BaseActivity;
import ceneax.app.hongmiao.bean.WeiboGsidBean;
import ceneax.app.hongmiao.bean.WeiboUserBean;
import ceneax.app.hongmiao.bean.WeiboUserResultBean;
import ceneax.app.hongmiao.mvp.contact.CommonContact;
import ceneax.app.hongmiao.mvp.contact.LoginActivityContact;
import ceneax.app.hongmiao.mvp.presenter.CommonPresenter;
import ceneax.app.hongmiao.mvp.presenter.LoginActivityPresenter;
import ceneax.app.hongmiao.util.DialogUtil;
import ceneax.app.hongmiao.util.L;
import ceneax.app.hongmiao.util.SPUtil;

public class LoginActivity extends BaseActivity implements LoginActivityContact.IView, CommonContact.IView {

    public static final String TAG = "LoginActivity";

    private LoginActivityContact.IPresenter iPresenter;
    private CommonContact.IPresenter iCommonPresenter;

    private Button loginButton;
    private EditText userEditText;
    private EditText pwdEditText;

    @Override
    public int initLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initVariable() {
        iPresenter = new LoginActivityPresenter(this);
        iCommonPresenter = new CommonPresenter(this);
    }

    @Override
    public void findViews() {
        loginButton = findViewById(R.id.activity_login_bt_login);
        userEditText = findViewById(R.id.activity_login_edit_user);
        pwdEditText = findViewById(R.id.activity_login_edit_pwd);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        // 开始登录
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.doLogin(userEditText.getText().toString(), pwdEditText.getText().toString());
            }
        });
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
    }

    @Override
    public void onLoginSuccess(WeiboGsidBean bean) {
        Config.weiboGsid = bean;
        SPUtil.setWeiboGsid(bean);
        iCommonPresenter.getProfile(bean.getGsid(), Long.parseLong(bean.getUid()));
    }

    @Override
    public void onLoginFail(IOException e) {
        DialogUtil.showSnackBarKeep(this, e.getMessage());
    }

    @Override
    public void onGetProfileSuccess(WeiboUserResultBean bean) {
        Config.weiboUserResultBean = bean;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onGetProfileFail(IOException e) {
        DialogUtil.showSnackBarKeep(this, e.getMessage());
    }
}
