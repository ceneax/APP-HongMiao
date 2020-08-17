package ceneax.app.hongmiao.mvp.contact;

import java.io.IOException;
import java.util.List;

import ceneax.app.hongmiao.bean.WeiboBean;

public class IndexFragmentTempContact {

    public interface IView {
        void onGetWeiboSuccess(List<WeiboBean> list);
        void onLoadMoreWeiboSuccess(List<WeiboBean> list);
        void onGetWeiboFail(IOException e);
    }

    public interface IPresenter {
        void getWeibo(String groupId);
        void loadMoreWeibo(String groupId, int page);
        void onGetWeiboSuccess(List<WeiboBean> res);
        void onLoadMoreWeiboSuccess(List<WeiboBean> list);
        void onGetWeiboFail(IOException e);
    }

    public interface IModel {
        void getWeibo(String groupId);
        void loadMoreWeibo(String groupId, int page);
    }

}
