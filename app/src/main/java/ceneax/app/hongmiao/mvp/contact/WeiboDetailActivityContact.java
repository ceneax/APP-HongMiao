package ceneax.app.hongmiao.mvp.contact;

import java.io.IOException;

import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.bean.WeiboCommentResultBean;

public class WeiboDetailActivityContact {

    public interface IView {
        void onGetLongTextSuccess(WeiboBean bean);
        void onGetLongTextFail(IOException e);

        void onGetCommentSuccess(WeiboCommentResultBean bean);
        void onLoadMoreCommentSuccess(WeiboCommentResultBean bean);
        void onGetCommentFail(IOException e);
    }

    public interface IPresenter {
        void getLongText(String id);
        void onGetLongTextSuccess(WeiboBean bean);
        void onGetLongTextFail(IOException e);

        void getComment(String id);
        void loadMoreComment(String id, String maxId);
        void onGetCommentSuccess(WeiboCommentResultBean bean);
        void onLoadMoreCommentSuccess(WeiboCommentResultBean bean);
        void onGetCommentFail(IOException e);
    }

    public interface IModel {
        void getLongText(String id);

        void getComment(String id);
        void loadMoreComment(String id, String maxId);
    }

}
