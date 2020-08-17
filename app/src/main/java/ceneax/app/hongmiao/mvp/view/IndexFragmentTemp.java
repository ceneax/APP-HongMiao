package ceneax.app.hongmiao.mvp.view;

import android.os.Bundle;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.adapter.WeiboListAdapter;
import ceneax.app.hongmiao.base.BaseFragment;
import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.listener.MyRecyclerViewScrollListener;
import ceneax.app.hongmiao.mvp.contact.IndexFragmentTempContact;
import ceneax.app.hongmiao.mvp.presenter.IndexFragmentTempPresenter;
import ceneax.app.hongmiao.util.AnimationUtil;
import ceneax.app.hongmiao.util.DialogUtil;
import ceneax.app.hongmiao.widget.SwipeRefreshLoadMore.SwipeRefreshLoadMore;

public class IndexFragmentTemp extends BaseFragment implements IndexFragmentTempContact.IView, View.OnClickListener {

    public static final String TAG = "IndexFragmentTemp";

    private IndexFragmentTempContact.IPresenter iPresenter;

    private RecyclerView recyclerView;
    private List<WeiboBean> datas = new ArrayList();
    private WeiboListAdapter adapter;

    private SwipeRefreshLoadMore swipeRefreshLoadMore;

    private FloatingActionButton floatingActionButton;

    private int page = 1;
    private String groupId;

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_index_temp;
    }

    @Override
    public void initVariable() {
        groupId = getArguments().getString(Config.GOTO_WEIBO_INDEX_FRAGMENT_TEMP);

        // 创建mvp的presenter
        iPresenter = new IndexFragmentTempPresenter(this);
    }

    @Override
    public void findViews(View view) {
        recyclerView = view.findViewById(R.id.fragment_index_temp_recyclerview);
        floatingActionButton = view.findViewById(R.id.fragment_index_temp_fb);
        swipeRefreshLoadMore = view.findViewById(R.id.fragment_index_temp_swipe_refresh_loadmore);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        floatingActionButton.setOnClickListener(this);

        // 初始化adapter
        adapter = new WeiboListAdapter(context, R.layout.item_fragment_index_temp_recyclerview, datas, getChildFragmentManager());

        // 配置recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(new MyRecyclerViewScrollListener() {
            @Override
            public void onScrollStateChanged(State state) {
                switch (state) {
                    case SCROLL_UP:
                        AnimationUtil.hideViewAnimation(getActivity().findViewById(R.id.activity_main_nav_bar));
                        floatingActionButton.hide();
                        swipeRefreshLoadMore.setNestedScrollingEnabled(false);
                        break;
                    case SCROLL_DOWN:
                        AnimationUtil.showViewAnimation(getActivity().findViewById(R.id.activity_main_nav_bar));
                        floatingActionButton.show();
                        break;
                    case SCROLL_TOP:
                        swipeRefreshLoadMore.setNestedScrollingEnabled(true);
                        break;
                }
            }
        });

        swipeRefreshLoadMore.setOnRefreshListener(new SwipeRefreshLoadMore.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iPresenter.getWeibo(groupId);
            }
        });
        swipeRefreshLoadMore.setOnBottomRefreshListenrer(new SwipeRefreshLoadMore.OnBottomRefreshListener() {
            @Override
            public void onBottomRefresh() {
                iPresenter.loadMoreWeibo(groupId, page + 1);
            }
        });
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
        swipeRefreshLoadMore.setRefreshing(true);
        iPresenter.getWeibo(groupId);
    }

    @Override
    public void onGetWeiboSuccess(List<WeiboBean> list) {
        datas.clear();
        datas.addAll(list);
        page = 1;

        adapter.notifyDataSetChanged();
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
    }

    @Override
    public void onLoadMoreWeiboSuccess(List<WeiboBean> list) {
        datas.addAll(list);
        page ++;

        adapter.notifyDataSetChanged();
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
    }

    @Override
    public void onGetWeiboFail(final IOException e) {
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
        DialogUtil.showSnackBarKeep(getActivity(), e.getMessage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_index_temp_fb:
                iPresenter.getWeibo(groupId);
                recyclerView.scrollToPosition(0);
                break;
        }
    }

}