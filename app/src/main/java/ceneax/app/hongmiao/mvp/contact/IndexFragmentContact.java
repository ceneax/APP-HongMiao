package ceneax.app.hongmiao.mvp.contact;

import java.io.IOException;
import java.util.List;

import ceneax.app.hongmiao.bean.WeiboGroupBean;
import ceneax.app.hongmiao.bean.WeiboHotwordBean;

public class IndexFragmentContact {

    public interface IView {
        void onGetWeiboHotwordSuccess(List<WeiboHotwordBean> list);
        void onGetWeiboHotwordFail(IOException e);

        void onGetGroupSuccess(List<WeiboGroupBean> bean);
        void onGetGroupFail(IOException e);
    }

    public interface IPresenter {
        void getWeiboHotword();
        void onGetWeiboHotwordSuccess(List<WeiboHotwordBean> list);
        void onGetWeiboHotwordFail(IOException e);

        void getGroup();
        void onGetGroupSuccess(List<WeiboGroupBean> bean);
        void onGetGroupFail(IOException e);
    }

    public interface IModel {
        void getWeiboHotword();
        void getGroup();
    }

}
