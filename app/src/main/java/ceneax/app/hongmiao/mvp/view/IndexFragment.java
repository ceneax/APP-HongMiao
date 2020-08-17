package ceneax.app.hongmiao.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.adapter.MyFragmentViewPagerAdapter;
import ceneax.app.hongmiao.base.BaseFragment;
import ceneax.app.hongmiao.bean.WeiboGroupBean;
import ceneax.app.hongmiao.bean.WeiboHotwordBean;
import ceneax.app.hongmiao.mvp.contact.IndexFragmentContact;
import ceneax.app.hongmiao.mvp.presenter.IndexFragmentPresenter;
import ceneax.app.hongmiao.util.AnimationUtil;
import ceneax.app.hongmiao.util.AppUtil;
import ceneax.app.hongmiao.util.DialogUtil;
import ceneax.app.hongmiao.util.L;
import ceneax.app.hongmiao.util.WeiboRegUtil;
import ceneax.app.hongmiao.widget.FixedBottomSheetBehavior;

public class IndexFragment extends BaseFragment implements IndexFragmentContact.IView, Toolbar.OnMenuItemClickListener {

    public static final String TAG = "IndexFragment";

    private IndexFragmentContact.IPresenter iPresenter;

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;
    private CommonAdapter<WeiboHotwordBean> recyclerViewAdapter;
    private List<WeiboHotwordBean> recyclerDatas = new ArrayList<>();

    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private MyFragmentViewPagerAdapter adapter;

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_index;
    }

    @Override
    public void initVariable() {
        // 初始化mvp的presenter
        iPresenter = new IndexFragmentPresenter(this);
    }

    @Override
    public void findViews(View view) {
        appBarLayout = view.findViewById(R.id.fragment_index_appbar_layout);
        toolbar = view.findViewById(R.id.fragment_index_toolbar);
        tabLayout = view.findViewById(R.id.fragment_index_tab_layout);
        swipeRefreshLayout = view.findViewById(R.id.fragment_index_refresh_layout);
        recyclerView = view.findViewById(R.id.fragment_index_recyclerview);
        viewPager = view.findViewById(R.id.fragment_index_view_pager);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        // 给appBarLayout设置padding，沉浸式
        appBarLayout.setPadding(0, AppUtil.getStatusBarHeight(context), 0, 0);

        // toolbar添加菜单
        toolbar.inflateMenu(R.menu.fragment_index_toolbar_menu);
        toolbar.setOnMenuItemClickListener(this);

        // 添加tab
        tabLayout.addTab(tabLayout.newTab().setText(R.string.fragment_index_tab_all));

        // 创建fragments，带参数
        IndexFragmentTemp indexFragmentTemp = new IndexFragmentTemp();
        Bundle bundle = new Bundle();
        bundle.putString(Config.GOTO_WEIBO_INDEX_FRAGMENT_TEMP, null);
        indexFragmentTemp.setArguments(bundle);
        fragments.add(indexFragmentTemp);

        // 初始化adapter
        adapter = new MyFragmentViewPagerAdapter(getChildFragmentManager(), fragments);

        // 设置viewpager
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        // 设置viewpager的behavior的最大高度
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        layoutParams.height = AppUtil.getRealScreenSize(getActivity().getWindowManager()).heightPixels - toolbar.getLayoutParams().height - AppUtil.getStatusBarHeight(context);
        viewPager.setLayoutParams(layoutParams);

        // tabLayout切换事件
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // 设置BottomSheetBehavior事件监听，并默认为展开
        FixedBottomSheetBehavior bottomSheetBehavior = FixedBottomSheetBehavior.from(viewPager);
        bottomSheetBehavior.addBottomSheetCallback(new FixedBottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                if(newState != BottomSheetBehavior.STATE_SETTLING) {
//                    ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
//                    layoutParams.height = AppUtil.getScreenSize(getActivity().getWindowManager()).heightPixels - toolbar.getLayoutParams().height;
//                    bottomSheet.setLayoutParams(layoutParams);
//                }
                switch (newState) {
                    case FixedBottomSheetBehavior.STATE_EXPANDED:
                        AnimationUtil.showViewAnimation(getActivity().findViewById(R.id.activity_main_nav_bar));
                        break;
                    case FixedBottomSheetBehavior.STATE_DRAGGING:
                        AnimationUtil.hideViewAnimation(getActivity().findViewById(R.id.activity_main_nav_bar));
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                toolbar.setAlpha(slideOffset);
            }
        });
        bottomSheetBehavior.setState(FixedBottomSheetBehavior.STATE_HALF_EXPANDED);

        // 设置recyclerview和adapt
        recyclerViewAdapter = new CommonAdapter<WeiboHotwordBean>(context, R.layout.item_fragment_index_recyclerview, recyclerDatas) {
            @Override
            protected void convert(ViewHolder holder, WeiboHotwordBean weiboHotwordBean, int position) {
                Glide.with(context).load(weiboHotwordBean.getPic())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into((ImageView) holder.getView(R.id.item_fragment_index_recyclerview_pic));
                Glide.with(context).load(weiboHotwordBean.getIcon())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into((ImageView) holder.getView(R.id.item_fragment_index_recyclerview_icon));
                WeiboRegUtil.formatWeiboContent(context, weiboHotwordBean.getDesc(), (TextView) holder.getView(R.id.item_fragment_index_recyclerview_title));
                WeiboRegUtil.formatWeiboContent(context, weiboHotwordBean.getDescExtr(), (TextView) holder.getView(R.id.item_fragment_index_recyclerview_num));
            }
        };
        recyclerViewAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                Intent topicIntent = new Intent(context, TopicActivity.class);
                topicIntent.putExtra(Config.GOTO_TOPIC_ACTIVITY, "#" + recyclerDatas.get(i).getDesc() + "#");
                context.startActivity(topicIntent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(recyclerViewAdapter);

        // 监听swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iPresenter.getWeiboHotword();
            }
        });
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
        swipeRefreshLayout.setRefreshing(true);
        iPresenter.getGroup();
        iPresenter.getWeiboHotword();
    }

    @Override
    public void onGetWeiboHotwordSuccess(List<WeiboHotwordBean> list) {
        recyclerDatas.clear();
        recyclerDatas.addAll(list);

        recyclerViewAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetWeiboHotwordFail(IOException e) {
        swipeRefreshLayout.setRefreshing(false);
        DialogUtil.showSnackBarKeep(getActivity(), e.getMessage());
    }

    @Override
    public void onGetGroupSuccess(final List<WeiboGroupBean> bean) {
        for(WeiboGroupBean groupBean : bean) {
            tabLayout.addTab(tabLayout.newTab().setText(groupBean.getName()));

            IndexFragmentTemp indexFragmentTemp = new IndexFragmentTemp();
            Bundle bundle = new Bundle();
            bundle.putString(Config.GOTO_WEIBO_INDEX_FRAGMENT_TEMP, groupBean.getIdstr());
            indexFragmentTemp.setArguments(bundle);
            fragments.add(indexFragmentTemp);

            adapter.notifyDataSetChanged();
        }
        viewPager.setOffscreenPageLimit(fragments.size());
    }

    @Override
    public void onGetGroupFail(IOException e) {
        // 暂时do nothing
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fragment_index_toolbar_menu_post:
                DialogUtil.showSnackBar(getActivity(), "发微博！");
                return true;
        }

        return false;
    }

}
