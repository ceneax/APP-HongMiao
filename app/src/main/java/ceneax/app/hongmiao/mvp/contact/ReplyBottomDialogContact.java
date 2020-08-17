package ceneax.app.hongmiao.mvp.contact;

import java.io.IOException;

import ceneax.app.hongmiao.bean.WeiboReplyResultBean;

public class ReplyBottomDialogContact {

    public interface IView {
        void onGetReplySuccess(WeiboReplyResultBean bean);
        void onLoadMoreReplySuccess(WeiboReplyResultBean bean);
        void onGetReplyFail(IOException e);
    }

    public interface IPresenter {
        void getReply(String id);
        void loadMoreReply(String id, String maxId);
        void onGetReplySuccess(WeiboReplyResultBean bean);
        void onLoadMoreReplySuccess(WeiboReplyResultBean bean);
        void onGetReplyFail(IOException e);
    }

    public interface IModel {
        void getReply(String id);
        void loadMoreReply(String id, String maxId);
    }

}
