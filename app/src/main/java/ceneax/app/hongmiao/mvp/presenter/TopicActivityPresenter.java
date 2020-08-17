package ceneax.app.hongmiao.mvp.presenter;

import android.text.TextUtils;

import java.io.IOException;

import ceneax.app.hongmiao.App;
import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.bean.WeiboTopicResultBean;
import ceneax.app.hongmiao.mvp.contact.TopicActivityContact;
import ceneax.app.hongmiao.mvp.model.TopicActivityModel;

public class TopicActivityPresenter implements TopicActivityContact.IPresenter {

    private TopicActivityContact.IView iView;
    private TopicActivityContact.IModel iModel;

    public TopicActivityPresenter(TopicActivityContact.IView iView) {
        this.iView = iView;
        iModel = new TopicActivityModel(this);
    }

    @Override
    public void getSearchAll(String kw) {
        if(Config.weiboGsid == null) {
            iView.onGetSearchAllFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else if(TextUtils.isEmpty(kw)) {
            iView.onGetSearchAllFail(new IOException("kw为空"));
        } else {
            iModel.getSearchAll(kw);
        }
    }

    @Override
    public void onGetSearchAllSuccess(WeiboTopicResultBean bean) {
        iView.onGetSearchAllSuccess(bean);
    }

    @Override
    public void onGetSearchAllFail(IOException e) {
        iView.onGetSearchAllFail(new IOException("获取失败！" + e.getMessage()));
    }

}
