package ceneax.app.hongmiao.mvp.presenter;

import android.text.TextUtils;

import java.io.IOException;

import ceneax.app.hongmiao.App;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.bean.WeiboGsidBean;
import ceneax.app.hongmiao.mvp.contact.LoginActivityContact;
import ceneax.app.hongmiao.mvp.model.LoginActivityModel;

public class LoginActivityPresenter implements LoginActivityContact.IPresenter {

    private LoginActivityContact.IView iView;
    private LoginActivityContact.IModel iModel;

    public LoginActivityPresenter(LoginActivityContact.IView iView) {
        this.iView = iView;
        iModel = new LoginActivityModel(this);
    }

    @Override
    public void doLogin(String user, String pwd) {
        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)) {
            iView.onLoginFail(new IOException(App.getAppContext().getString(R.string.activity_login_fail_user_pwd_null)));
        } else {
            iModel.doLogin(user, pwd);
        }
    }

    @Override
    public void onLoginSuccess(WeiboGsidBean bean) {
        iView.onLoginSuccess(bean);
    }

    @Override
    public void onLoginFail(IOException e) {
        iView.onLoginFail(new IOException(App.getAppContext().getString(R.string.activity_login_fail_text) + e.getMessage()));
    }

}
