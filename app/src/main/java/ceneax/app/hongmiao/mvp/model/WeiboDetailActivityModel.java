package ceneax.app.hongmiao.mvp.model;

import java.io.IOException;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.bean.WeiboCommentResultBean;
import ceneax.app.hongmiao.mvp.contact.WeiboDetailActivityContact;
import ceneax.app.hongmiao.network.WeiboApi;

public class WeiboDetailActivityModel implements WeiboDetailActivityContact.IModel {

    private WeiboDetailActivityContact.IPresenter iPresenter;

    public WeiboDetailActivityModel(WeiboDetailActivityContact.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void getLongText(String id) {
        WeiboApi.getInstance().getWeiboLongText(Config.weiboGsid.getGsid(), id, new WeiboApi.Listener<WeiboBean>() {
            @Override
            public void success(WeiboBean res) {
                iPresenter.onGetLongTextSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetLongTextFail(e);
            }
        });
    }

    @Override
    public void getComment(String id) {
        WeiboApi.getInstance().getWeiboComment(Config.weiboGsid.getGsid(), id, "0", new WeiboApi.Listener<WeiboCommentResultBean>() {
            @Override
            public void success(WeiboCommentResultBean res) {
                iPresenter.onGetCommentSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetCommentFail(e);
            }
        });
    }

    @Override
    public void loadMoreComment(String id, String maxId) {
        WeiboApi.getInstance().getWeiboComment(Config.weiboGsid.getGsid(), id, maxId, new WeiboApi.Listener<WeiboCommentResultBean>() {
            @Override
            public void success(WeiboCommentResultBean res) {
                iPresenter.onLoadMoreCommentSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetCommentFail(e);
            }
        });
    }

}
