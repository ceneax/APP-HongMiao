package ceneax.app.hongmiao.mvp.model;

import java.io.IOException;

import ceneax.app.hongmiao.bean.WeiboUserResultBean;
import ceneax.app.hongmiao.mvp.contact.ProfileActivityContact;
import ceneax.app.hongmiao.network.WeiboApi;

public class ProfileActivityModel implements ProfileActivityContact.IModel {

    private ProfileActivityContact.IPresenter iPresenter;

    public ProfileActivityModel(ProfileActivityContact.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void getProfile(String gsid, String nickname) {
        WeiboApi.getInstance().getProfile(gsid, nickname, new WeiboApi.Listener<WeiboUserResultBean>() {
            @Override
            public void success(WeiboUserResultBean res) {
                iPresenter.onGetProfileByNickSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetProfileByNickFail(e);
            }
        });
    }

}
