package ceneax.app.hongmiao.mvp.model;

import java.io.IOException;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.bean.WeiboReplyResultBean;
import ceneax.app.hongmiao.mvp.contact.ReplyBottomDialogContact;
import ceneax.app.hongmiao.network.WeiboApi;

public class ReplyBottomDialogModel implements ReplyBottomDialogContact.IModel {

    private ReplyBottomDialogContact.IPresenter iPresenter;

    public ReplyBottomDialogModel(ReplyBottomDialogContact.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void getReply(String id) {
        WeiboApi.getInstance().getWeiboReply(Config.weiboGsid.getGsid(), id, "0", new WeiboApi.Listener<WeiboReplyResultBean>() {
            @Override
            public void success(WeiboReplyResultBean res) {
                iPresenter.onGetReplySuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetReplyFail(e);
            }
        });
    }

    @Override
    public void loadMoreReply(String id, String maxId) {
        WeiboApi.getInstance().getWeiboReply(Config.weiboGsid.getGsid(), id, maxId, new WeiboApi.Listener<WeiboReplyResultBean>() {
            @Override
            public void success(WeiboReplyResultBean res) {
                iPresenter.onLoadMoreReplySuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetReplyFail(e);
            }
        });
    }

}
