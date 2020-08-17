package ceneax.app.hongmiao.mvp.model;

import java.io.IOException;
import java.util.List;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.mvp.contact.IndexFragmentTempContact;
import ceneax.app.hongmiao.network.WeiboApi;

public class IndexFragmentTempModel implements IndexFragmentTempContact.IModel {

    private IndexFragmentTempContact.IPresenter iPresenter;

    public IndexFragmentTempModel(IndexFragmentTempContact.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void getWeibo(String groupId) {
        WeiboApi.getInstance().getWeibo(Config.weiboGsid.getGsid(), groupId, 1, new WeiboApi.Listener<List<WeiboBean>>() {
            @Override
            public void success(List<WeiboBean> res) {
                iPresenter.onGetWeiboSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetWeiboFail(e);
            }
        });
    }

    @Override
    public void loadMoreWeibo(String groupId, int page) {
        WeiboApi.getInstance().getWeibo(Config.weiboGsid.getGsid(), groupId, page, new WeiboApi.Listener<List<WeiboBean>>() {
            @Override
            public void success(List<WeiboBean> res) {
                iPresenter.onLoadMoreWeiboSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetWeiboFail(e);
            }
        });
    }

}
