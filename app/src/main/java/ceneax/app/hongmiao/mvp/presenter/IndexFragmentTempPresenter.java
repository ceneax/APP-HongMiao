package ceneax.app.hongmiao.mvp.presenter;

import java.io.IOException;
import java.util.List;

import ceneax.app.hongmiao.App;
import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.mvp.contact.IndexFragmentTempContact;
import ceneax.app.hongmiao.mvp.model.IndexFragmentTempModel;

public class IndexFragmentTempPresenter implements IndexFragmentTempContact.IPresenter {

    private IndexFragmentTempContact.IView iView;
    private IndexFragmentTempContact.IModel iModel;

    public IndexFragmentTempPresenter(IndexFragmentTempContact.IView iView) {
        this.iView = iView;
        iModel = new IndexFragmentTempModel(this);
    }

    @Override
    public void getWeibo(String groupId) {
        // 判断是否登录
        if(Config.weiboGsid == null) {
            iView.onGetWeiboFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            iModel.getWeibo(groupId);
        }
    }

    @Override
    public void loadMoreWeibo(String groupId, int page) {
        // 判断是否登录
        if(Config.weiboGsid == null) {
            iView.onGetWeiboFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            iModel.loadMoreWeibo(groupId, page);
        }
    }

    @Override
    public void onGetWeiboSuccess(List<WeiboBean> res) {
        if(res != null && res.size() > 0) {
            iView.onGetWeiboSuccess(res);
        } else {
            iView.onGetWeiboFail(new IOException(App.getAppContext().getString(R.string.presenter_fragment_index_temp_get_weibo_none)));
        }
    }

    @Override
    public void onLoadMoreWeiboSuccess(List<WeiboBean> list) {
        if(list != null && list.size() > 0) {
            iView.onLoadMoreWeiboSuccess(list);
        } else {
            iView.onGetWeiboFail(new IOException(App.getAppContext().getString(R.string.presenter_fragment_index_temp_get_weibo_none_more)));
        }
    }

    @Override
    public void onGetWeiboFail(IOException e) {
        iView.onGetWeiboFail(new IOException(App.getAppContext().getString(R.string.presenter_fragment_index_temp_get_weibo_fail) + e.getMessage()));
    }

}
