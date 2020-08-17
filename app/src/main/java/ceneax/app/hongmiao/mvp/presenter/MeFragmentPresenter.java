package ceneax.app.hongmiao.mvp.presenter;

import android.text.TextUtils;

import java.io.IOException;
import java.util.List;

import ceneax.app.hongmiao.App;
import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.bean.WeiboUserResultBean;
import ceneax.app.hongmiao.mvp.contact.MeFragmentContact;
import ceneax.app.hongmiao.mvp.model.MeFragmentModel;

public class MeFragmentPresenter implements MeFragmentContact.IPresenter {

    private MeFragmentContact.IView iView;
    private MeFragmentContact.IModel iModel;

    public MeFragmentPresenter(MeFragmentContact.IView iView) {
        this.iView = iView;
        iModel = new MeFragmentModel(this);
    }

    public String getContainerId(WeiboUserResultBean weiboUserResultBean) {
        String containerid = null;

        for(int i = 0; i < weiboUserResultBean.getTabsInfo().getTabs().size(); i ++) {
            if(weiboUserResultBean.getTabsInfo().getTabs().get(i).getTabKey().equals("weibo")) {
                containerid = weiboUserResultBean.getTabsInfo().getTabs().get(i).getContainerid();
            }
        }

        return containerid;
    }

    @Override
    public void getProfileStatuses(String uid, String containerid) {
        if(Config.weiboGsid == null) {
            iView.onGetProfileStatusesFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            if(TextUtils.isEmpty(uid) || TextUtils.isEmpty(containerid)) {
                iView.onGetProfileStatusesFail(new IOException(App.getAppContext().getString(R.string.presenter_fragment_index_temp_get_weibo_none)));
            } else {
                iModel.getProfileStatuses(uid, containerid);
            }
        }
    }

    @Override
    public void loadMoreProfileStatuses(String uid, String containerid, int page) {
        if(Config.weiboGsid == null) {
            iView.onGetProfileStatusesFail(new IOException(App.getAppContext().getString(R.string.not_login)));
        } else {
            if(TextUtils.isEmpty(uid) || TextUtils.isEmpty(containerid)) {
                iView.onGetProfileStatusesFail(new IOException(App.getAppContext().getString(R.string.presenter_fragment_index_temp_get_weibo_none)));
            } else {
                iModel.loadMoreProfileStatuses(uid, containerid, page);
            }
        }
    }

    @Override
    public void onGetProfileStatusesSuccess(List<WeiboBean> list) {
        if(list.size() > 0) {
            iView.onGetProfileStatusesSuccess(list);
        } else {
            iView.onGetProfileStatusesFail(new IOException(App.getAppContext().getString(R.string.presenter_fragment_index_temp_get_weibo_none)));
        }
    }

    @Override
    public void onLoadMoreProfileStatusesSuccess(List<WeiboBean> list) {
        if(list.size() > 0) {
            iView.onLoadMoreProfileStatusesSuccess(list);
        } else {
            iView.onGetProfileStatusesFail(new IOException(App.getAppContext().getString(R.string.presenter_fragment_index_temp_get_weibo_none)));
        }
    }

    @Override
    public void onGetProfileStatusesFail(IOException e) {
        iView.onGetProfileStatusesFail(new IOException(App.getAppContext().getString(R.string.presenter_fragment_index_temp_get_weibo_fail) + e.getMessage()));
    }

}