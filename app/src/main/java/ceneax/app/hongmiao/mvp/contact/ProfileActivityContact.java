package ceneax.app.hongmiao.mvp.contact;

import java.io.IOException;

import ceneax.app.hongmiao.bean.WeiboUserResultBean;

public class ProfileActivityContact {

    public interface IView {
        void onGetProfileByNickSuccess(WeiboUserResultBean bean);
        void onGetProfileByNickFail(IOException e);
    }

    public interface IPresenter {
        void getProfile(String gsid, String nickname);
        void onGetProfileByNickSuccess(WeiboUserResultBean bean);
        void onGetProfileByNickFail(IOException e);
    }

    public interface IModel {
        void getProfile(String gsid, String nickname);
    }

}
