package ceneax.app.hongmiao.mvp.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.adapter.WeiboListAdapter;
import ceneax.app.hongmiao.base.BaseActivity;
import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.bean.WeiboUserBean;
import ceneax.app.hongmiao.bean.WeiboUserResultBean;
import ceneax.app.hongmiao.mvp.contact.CommonContact;
import ceneax.app.hongmiao.mvp.contact.MeFragmentContact;
import ceneax.app.hongmiao.mvp.contact.ProfileActivityContact;
import ceneax.app.hongmiao.mvp.presenter.CommonPresenter;
import ceneax.app.hongmiao.mvp.presenter.MeFragmentPresenter;
import ceneax.app.hongmiao.mvp.presenter.ProfileActivityPresenter;
import ceneax.app.hongmiao.util.AppUtil;
import ceneax.app.hongmiao.util.DialogUtil;
import ceneax.app.hongmiao.widget.SwipeRefreshLoadMore.SwipeRefreshLoadMore;

public class ProfileActivity extends BaseActivity implements MeFragmentContact.IView, CommonContact.IView, ProfileActivityContact.IView {

    private static final String TAG = "ProfileActivity";

    private MeFragmentContact.IPresenter iPresenter;
    private CommonContact.IPresenter iCommonPresenter;
    private ProfileActivityContact.IPresenter iThisPresenter;

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

    private ImageView avatarImageView;
    private TextView userNameText;
    private TextView followerText;
    private TextView followText;
    private TextView sexText;
    private TextView descText;
    private Button followButton;

    private SwipeRefreshLoadMore swipeRefreshLoadMore;

    private RecyclerView recyclerView;
    private WeiboListAdapter adapter;
    private List<WeiboBean> datas = new ArrayList<>();

    private int page = 1;

    private String nickName;
    private WeiboUserBean userBean;
    private WeiboUserResultBean resultBean;

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initVariable() {
        // 获取传递过来的数据
        Serializable serializable = getIntent().getSerializableExtra(Config.GOTO_WEIBO_PROFILE_ACTIVITY);
        if(serializable instanceof WeiboUserBean) {
            userBean = (WeiboUserBean) serializable;
        } else {
            nickName = (String) serializable;
        }

        iPresenter = new MeFragmentPresenter(this);
        iCommonPresenter = new CommonPresenter(this);
        iThisPresenter = new ProfileActivityPresenter(this);
    }

    @Override
    public void findViews() {
        appBarLayout = findViewById(R.id.fragment_me_appbar_layout);
        toolbar = findViewById(R.id.fragment_me_toolbar);
        avatarImageView = findViewById(R.id.fragment_me_avatar);
        userNameText = findViewById(R.id.fragment_me_username);
        followerText = findViewById(R.id.fragment_me_follower);
        followText = findViewById(R.id.fragment_me_follow);
        sexText = findViewById(R.id.fragment_me_sex);
        descText = findViewById(R.id.fragment_me_desc);
        swipeRefreshLoadMore = findViewById(R.id.fragment_me_swipe_refresh_loadmore);
        recyclerView = findViewById(R.id.fragment_me_recyclerview);
        followButton = findViewById(R.id.fragment_me_follow_bt);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        // appBar添加padding
        appBarLayout.setPadding(0, AppUtil.getStatusBarHeight(this), 0, 0);

        // 设置toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        // 关注按钮可见
        followButton.setVisibility(View.VISIBLE);

        // 设置recyclerview的adapter
        adapter = new WeiboListAdapter(this, R.layout.item_fragment_index_temp_recyclerview, datas, getSupportFragmentManager());

        // 配置recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefreshLoadMore.setOnRefreshListener(new SwipeRefreshLoadMore.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(resultBean == null) {
                    iPresenter.getProfileStatuses(null, null);
                } else {
                    iPresenter.getProfileStatuses(userBean.getIdStr(), iPresenter.getContainerId(resultBean));
                }
            }
        });
        swipeRefreshLoadMore.setOnBottomRefreshListenrer(new SwipeRefreshLoadMore.OnBottomRefreshListener() {
            @Override
            public void onBottomRefresh() {
                if(resultBean == null) {
                    iPresenter.loadMoreProfileStatuses(null, null, page);
                } else {
                    iPresenter.loadMoreProfileStatuses(userBean.getIdStr(), iPresenter.getContainerId(resultBean), page + 1);
                }
            }
        });

        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userBean != null)
                    new ImageViewerDialog(userBean.getAvatarHd()).show(getSupportFragmentManager(), "ImageViewer");
            }
        });
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
        if(nickName == null) {
            setUserInfo();
        } else {
            swipeRefreshLoadMore.setRefreshing(true);
            if(Config.weiboGsid == null) {
                iThisPresenter.getProfile(null, null);
            } else {
                iThisPresenter.getProfile(Config.weiboGsid.getGsid(), nickName);
            }
        }
    }

    /**
     * 设置用户信息
     */
    private void setUserInfo() {
        if(userBean != null && Config.weiboGsid != null) {
            userNameText.setText(userBean.getScreenName());
            followerText.setText(getString(R.string.fragment_me_follower) + userBean.getFollowersCount());
            followText.setText(getString(R.string.fragment_me_follow) + userBean.getFriendsCount());
            descText.setText(getString(R.string.fragment_me_desc) + userBean.getDescription());

            String sex;
            switch (userBean.getGender()) {
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

            Glide.with(this).load(userBean.getAvatarLarge())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.circleCropTransform())
                    .into(avatarImageView);

            if(userBean.getFollowing()) {
                followButton.setText(R.string.activity_profile_follow_bt_cancel);
            } else {
                followButton.setText(R.string.activity_profile_follow_bt_follow);
            }

            if(nickName == null) {
                // 获取微博
                swipeRefreshLoadMore.setRefreshing(true);
                iCommonPresenter.getProfile(Config.weiboGsid.getGsid(), Long.parseLong(userBean.getIdStr()));
            }
        } else {
            DialogUtil.showSnackBarKeep(this, getString(R.string.not_login));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
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
        DialogUtil.showSnackBarKeep(this, e.getMessage());
    }

    @Override
    public void onGetProfileSuccess(WeiboUserResultBean bean) {
        resultBean =  bean;
        iPresenter.getProfileStatuses(userBean.getIdStr(), iPresenter.getContainerId(resultBean));
    }

    @Override
    public void onGetProfileFail(IOException e) {
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
        DialogUtil.showSnackBarKeep(this, e.getMessage());
    }

    @Override
    public void onGetProfileByNickSuccess(WeiboUserResultBean bean) {
        resultBean =  bean;
        userBean = bean.getUserInfo();
        setUserInfo();
        iPresenter.getProfileStatuses(userBean.getIdStr(), iPresenter.getContainerId(resultBean));
    }

    @Override
    public void onGetProfileByNickFail(IOException e) {
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
        DialogUtil.showSnackBarKeep(this, e.getMessage());
    }

}
