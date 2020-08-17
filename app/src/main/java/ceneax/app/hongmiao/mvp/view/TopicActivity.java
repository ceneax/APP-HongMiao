package ceneax.app.hongmiao.mvp.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.adapter.delegate.CardType6Delegate;
import ceneax.app.hongmiao.adapter.delegate.CardType9Delegate;
import ceneax.app.hongmiao.adapter.delegate.CardTypeNoneDelegate;
import ceneax.app.hongmiao.base.BaseActivity;
import ceneax.app.hongmiao.bean.WeiboTopicCardListHeadCardsChannelBean;
import ceneax.app.hongmiao.bean.WeiboTopicCardsBean;
import ceneax.app.hongmiao.bean.WeiboTopicResultBean;
import ceneax.app.hongmiao.mvp.contact.TopicActivityContact;
import ceneax.app.hongmiao.mvp.presenter.TopicActivityPresenter;
import ceneax.app.hongmiao.util.AppUtil;
import ceneax.app.hongmiao.util.DialogUtil;
import ceneax.app.hongmiao.util.L;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class TopicActivity extends BaseActivity implements TopicActivityContact.IView {

    private static final String TAG = "TopicActivity";

    private TopicActivityContact.IPresenter iPresenter;

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;

    private LinearLayout topicInfoLayout;
    private ImageView topicInfoImg;
    private TextView topicInfoTitle;
    private TextView topicInfoSubTitle;
    private TextView topicInfoDownText;

    private RecyclerView recyclerView;
    private MultiItemTypeAdapter<WeiboTopicCardsBean> adapter;
    private List<WeiboTopicCardsBean> datas = new ArrayList<>();

    private String topicContent;
    private WeiboTopicResultBean resultBean;

    @Override
    public int initLayoutResId() {
        return R.layout.activity_topic;
    }

    @Override
    public void findViews() {
        appBarLayout = findViewById(R.id.activity_topic_appbar);
        toolbar = findViewById(R.id.activity_topic_toolbar);
        tabLayout = findViewById(R.id.activity_topic_tablayout);
        topicInfoLayout = findViewById(R.id.activity_topic_info_layout);
        topicInfoImg = findViewById(R.id.activity_topic_info_layout_img);
        topicInfoTitle = findViewById(R.id.activity_topic_info_layout_title);
        topicInfoSubTitle = findViewById(R.id.activity_topic_info_layout_sub_title);
        topicInfoDownText = findViewById(R.id.activity_topic_info_layout_down_text);
        recyclerView = findViewById(R.id.activity_topic_recyclerview);
    }

    @Override
    public void initVariable() {
        topicContent = getIntent().getStringExtra(Config.GOTO_TOPIC_ACTIVITY);
        topicContent = URLEncoder.encode(topicContent);
        iPresenter = new TopicActivityPresenter(this);

        adapter = new MultiItemTypeAdapter<>(this, datas);
        adapter.addItemViewDelegate(new CardType9Delegate(this));
        adapter.addItemViewDelegate(new CardType6Delegate());
        adapter.addItemViewDelegate(new CardTypeNoneDelegate());
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        // 设置appbar
        appBarLayout.setPadding(0, AppUtil.getStatusBarHeight(this), 0, 0);

        // 配置toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
        iPresenter.getSearchAll(topicContent);
    }

    /**
     * 设置话题的tabBar
     */
    private void setTopicTabBar() {
        for(int i = 0; i < resultBean.getCardlistInfo().getCardlistHeadCards().size(); i ++) {
            if(resultBean.getCardlistInfo().getCardlistHeadCards().get(i).getHeadData() != null) {
                topicInfoTitle.setText(resultBean.getCardlistInfo().getCardlistHeadCards().get(i).getHeadData().getTitle());
                topicInfoSubTitle.setText(resultBean.getCardlistInfo().getCardlistHeadCards().get(i).getHeadData().getMidtext());
                Glide.with(this).load(resultBean.getCardlistInfo().getCardlistHeadCards().get(i).getHeadData().getPortraitUrl())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(topicInfoImg);

                if(!TextUtils.isEmpty(resultBean.getCardlistInfo().getCardlistHeadCards().get(i).getHeadData().getDowntext())) {
                    topicInfoDownText.setText(resultBean.getCardlistInfo().getCardlistHeadCards().get(i).getHeadData().getDowntext());
                    topicInfoDownText.setVisibility(View.VISIBLE);
                }

                topicInfoLayout.setVisibility(View.VISIBLE);

                Glide.with(this).load(resultBean.getCardlistInfo().getCardlistHeadCards().get(i).getHeadData().getPortraitUrl())
                        .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3)))
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                appBarLayout.setBackground(resource);
                            }
                        });
            }

            if(resultBean.getCardlistInfo().getCardlistHeadCards().get(i).getChannelList() != null) {
                for(WeiboTopicCardListHeadCardsChannelBean channelBean : resultBean.getCardlistInfo().getCardlistHeadCards().get(i).getChannelList()) {
                    tabLayout.addTab(tabLayout.newTab().setText(channelBean.getName()));
                }
            }
        }
    }

    /**
     * 设置话题items
     */
    private void setTopicItems() {
        datas.clear();
        datas.addAll(resultBean.getCards());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetSearchAllSuccess(WeiboTopicResultBean bean) {
        resultBean = bean;
        setTopicTabBar();
        setTopicItems();
    }

    @Override
    public void onGetSearchAllFail(IOException e) {
        DialogUtil.showSnackBarKeep(this, e.getMessage());
    }

}
