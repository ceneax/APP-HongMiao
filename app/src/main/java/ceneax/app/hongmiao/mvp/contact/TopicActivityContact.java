package ceneax.app.hongmiao.mvp.contact;

import java.io.IOException;

import ceneax.app.hongmiao.bean.WeiboTopicResultBean;

public class TopicActivityContact {

    public interface IView {
        void onGetSearchAllSuccess(WeiboTopicResultBean bean);
        void onGetSearchAllFail(IOException e);
    }

    public interface IPresenter {
        void getSearchAll(String kw);
        void onGetSearchAllSuccess(WeiboTopicResultBean bean);
        void onGetSearchAllFail(IOException e);
    }

    public interface IModel {
        void getSearchAll(String kw);
    }

}
