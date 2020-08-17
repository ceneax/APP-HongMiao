package ceneax.app.hongmiao.mvp.presenter;

import android.text.TextUtils;

import java.io.IOException;

import ceneax.app.hongmiao.App;
import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.bean.WeiboReplyResultBean;
import ceneax.app.hongmiao.mvp.contact.ReplyBottomDialogContact;
import ceneax.app.hongmiao.mvp.model.ReplyBottomDialogModel;

public class ReplyBottomDialogPresenter implements ReplyBottomDialogContact.IPresenter {

    private ReplyBottomDialogContact.IView iView;
    private ReplyBottomDialogContact.IModel iModel;

    public ReplyBottomDialogPresenter(ReplyBottomDialogContact.IView iView) {
        this.iView = iView;
        iModel = new ReplyBottomDialogModel(this);
    }

    @Override
    public void getReply(String id) {
        if(Config.weiboGsid == null) {
            iView.onGetReplyFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            if(TextUtils.isEmpty(id)) {
                iView.onGetReplyFail(new IOException(App.getAppContext().getString(R.string.presenter_reply_bottom_dialog_get_reply_none)));
            } else {
                iModel.getReply(id);
            }
        }
    }

    @Override
    public void loadMoreReply(String id, String maxId) {
        if(Config.weiboGsid == null) {
            iView.onGetReplyFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            if(TextUtils.isEmpty(id) || TextUtils.isEmpty(maxId) || maxId.equals("0")) {
                iView.onGetReplyFail(new IOException(App.getAppContext().getString(R.string.presenter_reply_bottom_dialog_get_reply_none)));
            } else {
                iModel.loadMoreReply(id, maxId);
            }
        }
    }

    @Override
    public void onGetReplySuccess(WeiboReplyResultBean bean) {
        if(bean.getComments() != null && bean.getComments().size() > 0) {
            iView.onGetReplySuccess(bean);
        } else {
            iView.onGetReplyFail(new IOException(App.getAppContext().getString(R.string.presenter_reply_bottom_dialog_get_reply_none)));
        }
    }

    @Override
    public void onLoadMoreReplySuccess(WeiboReplyResultBean bean) {
        if(bean.getComments() != null && bean.getComments().size() > 0) {
            iView.onLoadMoreReplySuccess(bean);
        } else {
            iView.onGetReplyFail(new IOException(App.getAppContext().getString(R.string.presenter_reply_bottom_dialog_get_reply_none)));
        }
    }

    @Override
    public void onGetReplyFail(IOException e) {
        iView.onGetReplyFail(new IOException(App.getAppContext().getString(R.string.presenter_reply_bottom_dialog_get_reply_fail) + e.getMessage()));
    }

}
