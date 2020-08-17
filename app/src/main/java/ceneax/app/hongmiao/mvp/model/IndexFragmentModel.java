package ceneax.app.hongmiao.mvp.model;

import java.io.IOException;
import java.util.List;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.bean.WeiboGroupBean;
import ceneax.app.hongmiao.bean.WeiboHotwordBean;
import ceneax.app.hongmiao.mvp.contact.IndexFragmentContact;
import ceneax.app.hongmiao.network.WeiboApi;

public class IndexFragmentModel implements IndexFragmentContact.IModel {

    private IndexFragmentContact.IPresenter iPresenter;

    public IndexFragmentModel(IndexFragmentContact.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void getWeiboHotword() {
        WeiboApi.getInstance().getHotword(Config.weiboGsid.getGsid(), new WeiboApi.Listener<List<WeiboHotwordBean>>() {
            @Override
            public void success(List<WeiboHotwordBean> res) {
                iPresenter.onGetWeiboHotwordSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetWeiboHotwordFail(e);
            }
        });
    }

    @Override
    public void getGroup() {
        WeiboApi.getInstance().getGroup(Config.weiboGsid.getGsid(), new WeiboApi.Listener<List<WeiboGroupBean>>() {
            @Override
            public void success(List<WeiboGroupBean> res) {
                iPresenter.onGetGroupSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetGroupFail(e);
            }
        });
    }

}
