package ceneax.app.hongmiao.mvp.presenter;

import android.text.TextUtils;

import java.io.IOException;

import ceneax.app.hongmiao.App;
import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.bean.WeiboUserResultBean;
import ceneax.app.hongmiao.mvp.contact.ProfileActivityContact;
import ceneax.app.hongmiao.mvp.model.ProfileActivityModel;

public class ProfileActivityPresenter implements ProfileActivityContact.IPresenter {

    private ProfileActivityContact.IView iView;
    private ProfileActivityContact.IModel iModel;

    public ProfileActivityPresenter(ProfileActivityContact.IView iView) {
        this.iView = iView;
        iModel = new ProfileActivityModel(this);
    }

    @Override
    public void getProfile(String gsid, String nickname) {
        if(gsid == null || TextUtils.isEmpty(nickname)) {
            iView.onGetProfileByNickFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            iModel.getProfile(gsid, nickname);
        }
    }

    @Override
    public void onGetProfileByNickSuccess(WeiboUserResultBean bean) {
        iView.onGetProfileByNickSuccess(bean);
    }

    @Override
    public void onGetProfileByNickFail(IOException e) {
        iView.onGetProfileByNickFail(new IOException(App.getAppContext().getString(R.string.presenter_common_get_profile_fail) + e.getMessage()));
    }

}
