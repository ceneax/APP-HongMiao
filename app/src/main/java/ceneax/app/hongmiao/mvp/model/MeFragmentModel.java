package ceneax.app.hongmiao.mvp.model;

import java.io.IOException;
import java.util.List;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.mvp.contact.MeFragmentContact;
import ceneax.app.hongmiao.network.WeiboApi;

public class MeFragmentModel implements MeFragmentContact.IModel {

    private MeFragmentContact.IPresenter iPresenter;

    public MeFragmentModel(MeFragmentContact.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void getProfileStatuses(String uid, String containerid) {
        WeiboApi.getInstance().getProfileStatuses(Config.weiboGsid.getGsid(), uid, containerid, 1, new WeiboApi.Listener<List<WeiboBean>>() {
            @Override
            public void success(List<WeiboBean> res) {
                iPresenter.onGetProfileStatusesSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetProfileStatusesFail(e);
            }
        });
    }

    @Override
    public void loadMoreProfileStatuses(String uid, String containerid, int page) {
        WeiboApi.getInstance().getProfileStatuses(Config.weiboGsid.getGsid(), uid, containerid, page, new WeiboApi.Listener<List<WeiboBean>>() {
            @Override
            public void success(List<WeiboBean> res) {
                iPresenter.onLoadMoreProfileStatusesSuccess(res);
            }

            @Override
            public void fail(IOException e) {
                iPresenter.onGetProfileStatusesFail(e);
            }
        });
    }

}
