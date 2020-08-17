package ceneax.app.hongmiao.mvp.presenter;

import java.io.IOException;

import ceneax.app.hongmiao.App;
import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.bean.WeiboUserBean;
import ceneax.app.hongmiao.bean.WeiboUserResultBean;
import ceneax.app.hongmiao.mvp.contact.CommonContact;
import ceneax.app.hongmiao.mvp.model.CommonModel;

public class CommonPresenter implements CommonContact.IPresenter {

    private CommonContact.IView iView;
    private CommonContact.IModel iModel;

    public CommonPresenter(CommonContact.IView iView) {
        this.iView = iView;
        iModel = new CommonModel(this);
    }

    @Override
    public void getProfile(String gsid, long uid) {
        if(Config.weiboGsid == null) {
            iView.onGetProfileFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            iModel.getProfile(gsid, uid);
        }
    }

    @Override
    public void onGetProfileSuccess(WeiboUserResultBean bean) {
        iView.onGetProfileSuccess(bean);
    }

    @Override
    public void onGetProfileFail(IOException e) {
        iView.onGetProfileFail(new IOException(App.getAppContext().getString(R.string.presenter_common_get_profile_fail) + e.getMessage()));
    }

}
