package ceneax.app.hongmiao.mvp.presenter;

import java.io.IOException;
import java.util.List;

import ceneax.app.hongmiao.App;
import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.bean.WeiboGroupBean;
import ceneax.app.hongmiao.bean.WeiboHotwordBean;
import ceneax.app.hongmiao.mvp.contact.IndexFragmentContact;
import ceneax.app.hongmiao.mvp.model.IndexFragmentModel;

public class IndexFragmentPresenter implements IndexFragmentContact.IPresenter {

    private IndexFragmentContact.IView iView;
    private IndexFragmentContact.IModel iModel;

    public IndexFragmentPresenter(IndexFragmentContact.IView iView) {
        this.iView = iView;
        iModel = new IndexFragmentModel(this);
    }

    @Override
    public void getWeiboHotword() {
        if(Config.weiboGsid == null) {
            iView.onGetWeiboHotwordFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            iModel.getWeiboHotword();
        }
    }

    @Override
    public void onGetWeiboHotwordSuccess(List<WeiboHotwordBean> list) {
        if (list.size() > 0) {
            iView.onGetWeiboHotwordSuccess(list);
        } else {
            iView.onGetWeiboHotwordFail(new IOException(App.getAppContext().getString(R.string.presenter_fragment_index_hot_word_fail)));
        }
    }

    @Override
    public void onGetWeiboHotwordFail(IOException e) {
        iView.onGetWeiboHotwordFail(new IOException(App.getAppContext().getString(R.string.presenter_fragment_index_hot_word_fail) + e.getMessage()));
    }

    @Override
    public void getGroup() {
        if(Config.weiboGsid == null) {
            iView.onGetGroupFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            iModel.getGroup();
        }
    }

    @Override
    public void onGetGroupSuccess(List<WeiboGroupBean> bean) {
        if(bean != null && bean.size() > 0) {
            iView.onGetGroupSuccess(bean);
        } else {
            // 暂时do nothing
        }
    }

    @Override
    public void onGetGroupFail(IOException e) {
        // 暂时do nothing
    }

}
