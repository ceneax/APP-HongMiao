package ceneax.app.hongmiao.mvp.model;

import java.io.IOException;

import ceneax.app.hongmiao.bean.WeiboGsidBean;
import ceneax.app.hongmiao.mvp.contact.LoginActivityContact;
import ceneax.app.hongmiao.network.WeiboApi;

public class LoginActivityModel implements LoginActivityContact.IModel {

    private LoginActivityContact.IPresenter iPresenter;

    public LoginActivityModel(LoginActivityContact.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void doLogin(String user, String pwd) {
        WeiboApi.getInstance().login(user, pwd, new WeiboApi.Listener<WeiboGsidBean>() {
            @Override
            public void success(WeiboGsidBean res) {
                iPresenter.onLoginSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onLoginFail(e);
            }
        });
    }

}
