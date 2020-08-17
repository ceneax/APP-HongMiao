package ceneax.app.hongmiao.mvp.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.adapter.WeiboListAdapter;
import ceneax.app.hongmiao.base.BaseFragment;
import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.listener.MyRecyclerViewScrollListener;
import ceneax.app.hongmiao.mvp.contact.MeFragmentContact;
import ceneax.app.hongmiao.mvp.presenter.MeFragmentPresenter;
import ceneax.app.hongmiao.util.AnimationUtil;
import ceneax.app.hongmiao.util.AppUtil;
import ceneax.app.hongmiao.util.DialogUtil;
import ceneax.app.hongmiao.util.L;
import ceneax.app.hongmiao.widget.SwipeRefreshLoadMore.SwipeRefreshLoadMore;

public class MeFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener, MeFragmentContact.IView {

    private static final String TAG = "MeFragment";

    private MeFragmentContact.IPresenter iPresenter;

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

    private ImageView avatarImageView;
    private TextView userNameText;
    private TextView followerText;
    private TextView followText;
    private TextView sexText;
    private TextView descText;

    private SwipeRefreshLoadMore swipeRefreshLoadMore;

    private RecyclerView recyclerView;
    private WeiboListAdapter adapter;
    private List<WeiboBean> datas = new ArrayList<>();

    private int page = 1;

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initVariable() {
        iPresenter = new MeFragmentPresenter(this);
    }

    @Override
    public void findViews(View view) {
        appBarLayout = view.findViewById(R.id.fragment_me_appbar_layout);
        toolbar = view.findViewById(R.id.fragment_me_toolbar);
        avatarImageView = view.findViewById(R.id.fragment_me_avatar);
        userNameText = view.findViewById(R.id.fragment_me_username);
        followerText = view.findViewById(R.id.fragment_me_follower);
        followText = view.findViewById(R.id.fragment_me_follow);
        sexText = view.findViewById(R.id.fragment_me_sex);
        descText = view.findViewById(R.id.fragment_me_desc);
        swipeRefreshLoadMore = view.findViewById(R.id.fragment_me_swipe_refresh_loadmore);
        recyclerView = view.findViewById(R.id.fragment_me_recyclerview);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        // appBar添加padding
        appBarLayout.setPadding(0, AppUtil.getStatusBarHeight(context), 0, 0);

        // toolBar添加menu
        toolbar.inflateMenu(R.menu.fragment_me_toolbar_menu);
        toolbar.setOnMenuItemClickListener(this);

        // 设置recyclerview的adapter
        adapter = new WeiboListAdapter(context, R.layout.item_fragment_index_temp_recyclerview, datas, getChildFragmentManager());

        // 配置recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new MyRecyclerViewScrollListener() {
            @Override
            public void onScrollStateChanged(State state) {
                switch (state) {
                    case SCROLL_UP:
                        AnimationUtil.hideViewAnimation(getActivity().findViewById(R.id.activity_main_nav_bar));
                        break;
                    case SCROLL_DOWN:
                        AnimationUtil.showViewAnimation(getActivity().findViewById(R.id.activity_main_nav_bar));
                        break;
                }
            }
        });

        swipeRefreshLoadMore.setOnRefreshListener(new SwipeRefreshLoadMore.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(Config.weiboUserResultBean == null && Config.weiboUserResultBean.getUserInfo() == null) {
                    iPresenter.getProfileStatuses(null, null);
                } else {
                    iPresenter.getProfileStatuses(Config.weiboUserResultBean.getUserInfo().getIdStr(), iPresenter.getContainerId(Config.weiboUserResultBean));
                }
            }
        });
        swipeRefreshLoadMore.setOnBottomRefreshListenrer(new SwipeRefreshLoadMore.OnBottomRefreshListener() {
            @Override
            public void onBottomRefresh() {
                if(Config.weiboUserResultBean == null && Config.weiboUserResultBean.getUserInfo() == null) {
                    iPresenter.loadMoreProfileStatuses(null, null, page);
                } else {
                    iPresenter.loadMoreProfileStatuses(Config.weiboUserResultBean.getUserInfo().getIdStr(), iPresenter.getContainerId(Config.weiboUserResultBean), page + 1);
                }
            }
        });

        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Config.weiboUserResultBean != null && Config.weiboUserResultBean.getUserInfo() != null)
                    new ImageViewerDialog(Config.weiboUserResultBean.getUserInfo().getAvatarHd()).show(getChildFragmentManager(), "ImageViewer");
            }
        });
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
        setUserInfo();
    }

    /**
     * 设置用户信息
     */
    private void setUserInfo() {
        if(Config.weiboUserResultBean != null && Config.weiboUserResultBean.getUserInfo() != null && Config.weiboGsid != null) {
            userNameText.setText(Config.weiboUserResultBean.getUserInfo().getScreenName());
            followerText.setText(getString(R.string.fragment_me_follower) + Config.weiboUserResultBean.getUserInfo().getFollowersCount());
            followText.setText(getString(R.string.fragment_me_follow) + Config.weiboUserResultBean.getUserInfo().getFriendsCount());
            descText.setText(getString(R.string.fragment_me_desc) + Config.weiboUserResultBean.getUserInfo().getDescription());

            String sex;
            switch (Config.weiboUserResultBean.getUserInfo().getGender()) {
                case "m":
                    sex = getString(R.string.fragment_me_sex_male);
                    break;
                case "f":
                    sex = getString(R.string.fragment_me_sex_female);
                    break;
                default:
                    sex = getString(R.string.fragment_me_sex_unknown);
                    break;
            }
            sexText.setText(sex);

            Glide.with(context).load(Config.weiboUserResultBean.getUserInfo().getAvatarLarge())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.circleCropTransform())
                    .into(avatarImageView);

            // 获取微博
            swipeRefreshLoadMore.setRefreshing(true);
            iPresenter.getProfileStatuses(Config.weiboUserResultBean.getUserInfo().getIdStr(), iPresenter.getContainerId(Config.weiboUserResultBean));
        } else {
            DialogUtil.showSnackBarKeep(getActivity(), getString(R.string.not_login));
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fragment_me_toolbar_menu_setting:
                DialogUtil.showSnackBar(getActivity(), "打开设置界面");
                return true;
            case R.id.fragment_me_toolbar_menu_theme:
                Config.currentTheme = Config.currentTheme == R.style.AppTheme ? R.style.AppTheme_Night : R.style.AppTheme;
                getActivity().recreate();
                return true;
        }

        return false;
    }

    @Override
    public void onGetProfileStatusesSuccess(List<WeiboBean> list) {
        datas.clear();
        datas.addAll(list);
        page = 1;

        adapter.notifyDataSetChanged();
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
    }

    @Override
    public void onLoadMoreProfileStatusesSuccess(List<WeiboBean> list) {
        datas.addAll(list);
        page ++;

        adapter.notifyDataSetChanged();
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
    }

    @Override
    public void onGetProfileStatusesFail(IOException e) {
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
        DialogUtil.showSnackBarKeep(getActivity(), e.getMessage());
    }

}
