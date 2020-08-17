package ceneax.app.hongmiao.mvp.model;

import java.io.IOException;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.bean.WeiboTopicResultBean;
import ceneax.app.hongmiao.mvp.contact.TopicActivityContact;
import ceneax.app.hongmiao.network.WeiboApi;

public class TopicActivityModel implements TopicActivityContact.IModel {

    private TopicActivityContact.IPresenter iPresenter;

    public TopicActivityModel(TopicActivityContact.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void getSearchAll(String kw) {
        WeiboApi.getInstance().getSearchAll(Config.weiboGsid.getGsid(), kw, 1, new WeiboApi.Listener<WeiboTopicResultBean>() {
            @Override
            public void success(WeiboTopicResultBean res) {
                iPresenter.onGetSearchAllSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetSearchAllFail(e);
            }
        });
    }

}
