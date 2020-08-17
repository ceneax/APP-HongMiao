package ceneax.app.hongmiao.mvp.contact;

import java.io.IOException;
import java.util.List;

import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.bean.WeiboUserResultBean;

public class MeFragmentContact {

    public interface IView {
        void onGetProfileStatusesSuccess(List<WeiboBean> list);
        void onLoadMoreProfileStatusesSuccess(List<WeiboBean> list);
        void onGetProfileStatusesFail(IOException e);
    }

    public interface IPresenter {
        void getProfileStatuses(String uid, String containerid);
        void loadMoreProfileStatuses(String uid, String containerid, int page);
        void onGetProfileStatusesSuccess(List<WeiboBean> list);
        void onLoadMoreProfileStatusesSuccess(List<WeiboBean> list);
        void onGetProfileStatusesFail(IOException e);

        String getContainerId(WeiboUserResultBean weiboUserResultBean);
    }

    public interface IModel {
        void getProfileStatuses(String uid, String containerid);
        void loadMoreProfileStatuses(String uid, String containerid, int page);
    }

}
