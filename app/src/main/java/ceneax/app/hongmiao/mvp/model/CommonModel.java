package ceneax.app.hongmiao.mvp.model;

import java.io.IOException;

import ceneax.app.hongmiao.bean.WeiboUserBean;
import ceneax.app.hongmiao.bean.WeiboUserResultBean;
import ceneax.app.hongmiao.mvp.contact.CommonContact;
import ceneax.app.hongmiao.network.WeiboApi;

public class CommonModel implements CommonContact.IModel {

    private CommonContact.IPresenter iPresenter;

    public CommonModel(CommonContact.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void getProfile(String gsid, long uid) {
        WeiboApi.getInstance().getProfile(gsid, uid, new WeiboApi.Listener<WeiboUserResultBean>() {
            @Override
            public void success(WeiboUserResultBean res) {
                iPresenter.onGetProfileSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetProfileFail(e);
            }
        });
    }

}
