package ceneax.app.hongmiao.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.base.BaseActivity;
import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.bean.WeiboCommentBean;
import ceneax.app.hongmiao.bean.WeiboCommentResultBean;
import ceneax.app.hongmiao.bean.WeiboPicCateBean;
import ceneax.app.hongmiao.listener.MyRecyclerViewScrollListener;
import ceneax.app.hongmiao.listener.WeiboSpanClickListener;
import ceneax.app.hongmiao.mvp.contact.WeiboDetailActivityContact;
import ceneax.app.hongmiao.mvp.presenter.WeiboDetailActivityPresenter;
import ceneax.app.hongmiao.util.AppUtil;
import ceneax.app.hongmiao.util.DateUtil;
import ceneax.app.hongmiao.util.DialogUtil;
import ceneax.app.hongmiao.util.L;
import ceneax.app.hongmiao.util.WeiboRegUtil;
import ceneax.app.hongmiao.widget.NineGridImageLayout.NineGridImageLayout;
import ceneax.app.hongmiao.widget.NineGridImageLayout.RatioImageView;
import ceneax.app.hongmiao.widget.SwipeRefreshLoadMore.SwipeRefreshLoadMore;

public class WeiboDetailActivity extends BaseActivity implements NineGridImageLayout.OnItemClickListener, WeiboDetailActivityContact.IView, View.OnClickListener {

    public static final String TAG = "WeiboDetailActivity";

    private WeiboDetailActivityContact.IPresenter iPresenter;

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private HeaderAndFooterWrapper adapter;
    private List<WeiboCommentBean> datas = new ArrayList();

    private SwipeRefreshLoadMore swipeRefreshLoadMore;

    private View headerView;

    private FloatingActionButton floatingActionButton;

    private WeiboBean weiboBean;
    private WeiboCommentResultBean commentResultBean;

    @Override
    public int initLayoutResId() {
        return R.layout.activity_weibo_detail;
    }

    @Override
    public void initVariable() {
        iPresenter = new WeiboDetailActivityPresenter(this);
    }

    @Override
    public void findViews() {
        appBarLayout = findViewById(R.id.activity_weibo_detail_appbar_layout);
        toolbar = findViewById(R.id.activity_weibo_detail_toolbar);
        swipeRefreshLoadMore = findViewById(R.id.activity_weibo_detail_swipe_refresh_loadmore);
        recyclerView = findViewById(R.id.activity_weibo_detail_recyclerview);
        floatingActionButton = findViewById(R.id.activity_weibo_detail_fb);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        // appBar设置padding
        appBarLayout.setPadding(0, AppUtil.getStatusBarHeight(this), 0, 0);

        // toolBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.activity_weibo_detail_title);

        // 挂载header
        headerView = LayoutInflater.from(this).inflate(R.layout.item_fragment_index_temp_recyclerview, null);
        initHeaderView();
        adapter = new HeaderAndFooterWrapper(new CommonAdapter<WeiboCommentBean>(this, R.layout.item_activity_weibo_detail_comment_recyclerview, datas) {
            @Override
            protected void convert(ViewHolder holder, final WeiboCommentBean bean, final int position) {
                ImageView avatar = (ImageView) holder.getView(R.id.item_activity_weibo_detail_comment_recyclerview_avatar);
                Glide.with(WeiboDetailActivity.this).load(bean.getUser().getAvatarLarge())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(RequestOptions.circleCropTransform()).into(avatar);
                avatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(WeiboDetailActivity.this, ProfileActivity.class);
                        intent.putExtra(Config.GOTO_WEIBO_PROFILE_ACTIVITY, bean.getUser());
                        startActivity(intent);
                    }
                });

                holder.setText(R.id.item_activity_weibo_detail_comment_recyclerview_username, bean.getUser().getScreenName());
                holder.setText(R.id.item_activity_weibo_detail_comment_recyclerview_date, DateUtil.format(new Date(bean.getCreatedAt())));
                holder.setText(R.id.item_activity_weibo_detail_comment_recyclerview_thumb_num, String.valueOf(bean.getLikeCounts()));

                WeiboRegUtil.formatWeiboContent(WeiboDetailActivity.this, bean.getText(), (TextView) holder.getView(R.id.item_activity_weibo_detail_comment_recyclerview_content), position,
                        false, true, new WeiboSpanClickListener(WeiboDetailActivity.this));

                TextView moreReplyBt = holder.getView(R.id.item_activity_weibo_detail_comment_recyclerview_more_reply_bt);
                if(bean.getTotalNumber() > 0) {
                    moreReplyBt.setText(getString(R.string.activity_weibo_detail_more_reply_bt_one) + bean.getTotalNumber() + getString(R.string.activity_weibo_detail_more_reply_bt_two));
                    moreReplyBt.setVisibility(View.VISIBLE);
                } else {
                    moreReplyBt.setVisibility(View.GONE);
                }
                moreReplyBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ReplyBottomDialog(datas.get(position - 1).getIdStr()).show(getSupportFragmentManager(), TAG);
                    }
                });

                holder.getView(R.id.item_activity_weibo_detail_comment_recyclerview_more_up_thumb).setVisibility(bean.getIsLikedByMblogAuthor() ? View.VISIBLE : View.GONE);
            }
        });
        adapter.addHeaderView(headerView);

        // 配置recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new MyRecyclerViewScrollListener() {
            @Override
            public void onScrollStateChanged(State state) {
                if(state == State.SCROLL_UP) {
                    floatingActionButton.hide();
                } else {
                    floatingActionButton.show();
                }
            }
        });

        floatingActionButton.setOnClickListener(this);

        swipeRefreshLoadMore.setOnRefreshListener(new SwipeRefreshLoadMore.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iPresenter.getComment(weiboBean.getIdstr());
            }
        });
        swipeRefreshLoadMore.setOnBottomRefreshListenrer(new SwipeRefreshLoadMore.OnBottomRefreshListener() {
            @Override
            public void onBottomRefresh() {
                if(commentResultBean != null)
                    iPresenter.loadMoreComment(weiboBean.getIdstr(), commentResultBean.getMaxIdStr());
                else
                    iPresenter.loadMoreComment(weiboBean.getIdstr(), null);
            }
        });
    }

    /**
     * 初始化header
     */
    private void initHeaderView() {
        Serializable serializable = getIntent().getSerializableExtra(Config.GOTO_WEIBO_DETAIL_ACTIVITY);

        if(!(serializable instanceof WeiboBean))
            return;

        weiboBean = (WeiboBean) serializable;

        headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_option).setVisibility(View.GONE);
        headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_operate).setVisibility(View.GONE);

        ImageView avatar = (ImageView) headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_avatar);
        Glide.with(this).load(weiboBean.getUser().getAvatarLarge())
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.circleCropTransform()).into(avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeiboDetailActivity.this, ProfileActivity.class);
                intent.putExtra(Config.GOTO_WEIBO_PROFILE_ACTIVITY, weiboBean.getUser());
                startActivity(intent);
            }
        });

        ((TextView) headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_username)).setText(weiboBean.getUser().getName());
        ((TextView) headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_date)).setText(DateUtil.format(new Date(weiboBean.getCreatedAt())));
        ((TextView) headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_repost)).setText(String.valueOf(weiboBean.getRepostsCount()));
        ((TextView) headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_comment)).setText(String.valueOf(weiboBean.getCommentsCount()));
        ((TextView) headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_thumb)).setText(String.valueOf(weiboBean.getAttitudesCount()));

        WeiboRegUtil.formatWeiboContent(this, weiboBean.getText(), (TextView) headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_content),
                0, weiboBean.getLongText(), true, new WeiboSpanClickListener(WeiboDetailActivity.this));

        // 设置微博来源
        Matcher matcher = Pattern.compile("<a.*?href=\"(.*?)\".*?>(.*?)</a>").matcher(weiboBean.getSource());
        if(matcher.find())
            ((TextView) headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_source)).setText(matcher.group(2));

        // 添加图片
        if(weiboBean.getPicIds() != null && weiboBean.getPicIds().size() > 0) {
            NineGridImageLayout imageLayout = headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_nine_image);
            List<WeiboPicCateBean> imgUrls = new ArrayList<>();

            for (int i = 0; i < weiboBean.getPicIds().size(); i++) {
                imgUrls.add(weiboBean.getPicInfos().get(weiboBean.getPicIds().get(i)));
            }

            imageLayout.setCorner(3);
            imageLayout.setUrlList(imgUrls);
            imageLayout.setOnOwnItemClickListener(this);
        }

        //repost
        if(weiboBean.getRetweetedStatus() != null) {
            LinearLayout linearLayout = headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_repost_layout);
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRepostClick();
                }
            });

            WeiboRegUtil.formatWeiboContent(this, "@" + weiboBean.getRetweetedStatus().getUser().getScreenName() + "：" + weiboBean.getRetweetedStatus().getText(),
                    (TextView) headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_repost_content), 0, weiboBean.getRetweetedStatus().getLongText(),
                    true, new WeiboSpanClickListener(WeiboDetailActivity.this));

            // 添加图片
            if(weiboBean.getRetweetedStatus().getPicIds() != null && weiboBean.getRetweetedStatus().getPicIds().size() > 0) {
                List<WeiboPicCateBean> imgUrls = new ArrayList<>();

                for (int i = 0; i < weiboBean.getRetweetedStatus().getPicIds().size(); i++) {
                    imgUrls.add(weiboBean.getRetweetedStatus().getPicInfos().get(weiboBean.getRetweetedStatus().getPicIds().get(i)));
                }

                NineGridImageLayout repoImageLayout = headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_repost_nine_image);
                repoImageLayout.setCorner(3);
                repoImageLayout.setUrlList(imgUrls);
                repoImageLayout.setOnOwnItemClickListener(this);
            }
        }
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
        if(weiboBean != null && weiboBean.getLongText()) {
            iPresenter.getLongText(weiboBean.getIdstr());
        }

        swipeRefreshLoadMore.setRefreshing(true);
        iPresenter.getComment(weiboBean.getIdstr());
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_weibo_detail_fb:
                break;
        }
    }

    @Override
    public void onClickImage(RatioImageView imageView, int position, String url, ArrayList<WeiboPicCateBean> urlList) {
        new ImageViewerDialog(position, urlList).show(getSupportFragmentManager(), "ImageViewer");
    }

    private void onRepostClick() {
        Intent intent = new Intent(this, WeiboDetailActivity.class);
        intent.putExtra(Config.GOTO_WEIBO_DETAIL_ACTIVITY, weiboBean.getRetweetedStatus());
        startActivity(intent);
    }

    @Override
    public void onGetLongTextSuccess(final WeiboBean bean) {
        WeiboRegUtil.formatWeiboContent(WeiboDetailActivity.this, bean.getLongFullText().getContent(), (TextView) headerView.findViewById(R.id.item_fragment_index_temp_recyclerview_content),
                0, false, true, new WeiboSpanClickListener(WeiboDetailActivity.this));
    }

    @Override
    public void onGetLongTextFail(IOException e) {
        DialogUtil.showSnackBarKeep(this, e.getMessage());
    }

    @Override
    public void onGetCommentSuccess(WeiboCommentResultBean bean) {
        commentResultBean = null;
        commentResultBean = bean;
        datas.clear();
        datas.addAll(commentResultBean.getComments());

        adapter.notifyDataSetChanged();
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
    }

    @Override
    public void onLoadMoreCommentSuccess(WeiboCommentResultBean bean) {
        commentResultBean = null;
        commentResultBean = bean;
        datas.addAll(commentResultBean.getComments());

        adapter.notifyDataSetChanged();
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
    }

    @Override
    public void onGetCommentFail(IOException e) {
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
        DialogUtil.showSnackBarKeep(this, e.getMessage());
    }

}
