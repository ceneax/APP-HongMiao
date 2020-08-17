package ceneax.app.hongmiao.mvp.contact;

import java.io.IOException;

import ceneax.app.hongmiao.bean.WeiboUserResultBean;

public class CommonContact {

    public interface IView {
        void onGetProfileSuccess(WeiboUserResultBean bean);
        void onGetProfileFail(IOException e);
    }

    public interface IPresenter {
        void getProfile(String gsid, long uid);
        void onGetProfileSuccess(WeiboUserResultBean bean);
        void onGetProfileFail(IOException e);
    }

    public interface IModel {
        void getProfile(String gsid, long uid);
    }

}
