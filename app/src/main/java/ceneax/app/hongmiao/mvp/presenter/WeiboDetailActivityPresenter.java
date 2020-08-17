package ceneax.app.hongmiao.mvp.presenter;

import android.text.TextUtils;

import java.io.IOException;

import ceneax.app.hongmiao.App;
import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.bean.WeiboCommentResultBean;
import ceneax.app.hongmiao.mvp.contact.WeiboDetailActivityContact;
import ceneax.app.hongmiao.mvp.model.WeiboDetailActivityModel;

public class WeiboDetailActivityPresenter implements WeiboDetailActivityContact.IPresenter {

    private WeiboDetailActivityContact.IView iView;
    private WeiboDetailActivityContact.IModel iModel;

    public WeiboDetailActivityPresenter(WeiboDetailActivityContact.IView iView) {
        this.iView = iView;
        iModel = new WeiboDetailActivityModel(this);
    }

    @Override
    public void getLongText(String id) {
        if(Config.weiboGsid == null) {
            iView.onGetLongTextFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            iModel.getLongText(id);
        }
    }

    @Override
    public void onGetLongTextSuccess(WeiboBean bean) {
        iView.onGetLongTextSuccess(bean);
    }

    @Override
    public void onGetLongTextFail(IOException e) {
        iView.onGetLongTextFail(new IOException(App.getAppContext().getString(R.string.presenter_fragment_index_temp_get_weibo_fail) + e.getMessage()));
    }

    @Override
    public void getComment(String id) {
        if(Config.weiboGsid == null) {
            iView.onGetCommentFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            iModel.getComment(id);
        }
    }

    @Override
    public void loadMoreComment(String id, String maxId) {
        if(Config.weiboGsid == null) {
            iView.onGetCommentFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            if(TextUtils.isEmpty(maxId) || maxId.equals("0")) {
                iView.onGetCommentFail(new IOException(App.getAppContext().getString(R.string.presenter_activity_weibo_detail_get_comment_none_more)));
            } else {
                iModel.loadMoreComment(id, maxId);
            }
        }
    }

    @Override
    public void onGetCommentSuccess(WeiboCommentResultBean bean) {
        if(bean.getComments() != null && bean.getComments().size() > 0) {
            iView.onGetCommentSuccess(bean);
        } else {
            iView.onGetCommentFail(new IOException(App.getAppContext().getString(R.string.presenter_activity_weibo_detail_get_comment_none)));
        }
    }

    @Override
    public void onLoadMoreCommentSuccess(WeiboCommentResultBean bean) {
        if(bean.getComments() != null && bean.getComments().size() > 0) {
            iView.onLoadMoreCommentSuccess(bean);
        } else {
            iView.onGetCommentFail(new IOException(App.getAppContext().getString(R.string.presenter_activity_weibo_detail_get_comment_none_more)));
        }
    }

    @Override
    public void onGetCommentFail(IOException e) {
        iView.onGetCommentFail(new IOException(App.getAppContext().getString(R.string.presenter_activity_weibo_detail_get_comment_fail) + e.getMessage()));
    }

}
