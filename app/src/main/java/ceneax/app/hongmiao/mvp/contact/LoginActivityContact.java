package ceneax.app.hongmiao.mvp.contact;

import java.io.IOException;

import ceneax.app.hongmiao.bean.WeiboGsidBean;

public class LoginActivityContact {

    public interface IView {
        void onLoginSuccess(WeiboGsidBean bean);
        void onLoginFail(IOException e);
    }

    public interface IPresenter {
        void doLogin(String user, String pwd);
        void onLoginSuccess(WeiboGsidBean bean);
        void onLoginFail(IOException e);
    }

    public interface IModel {
        void doLogin(String user, String pwd);
    }

}
