package ceneax.app.hongmiao.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.adapter.MyFragmentViewPagerAdapter;
import ceneax.app.hongmiao.base.BaseActivity;

public class MainActivity extends BaseActivity {

    public static final String TAG = "MainActivity";

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public int initLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initVariable() {
    }

    @Override
    public void findViews() {
        viewPager = findViewById(R.id.activity_main_viewpager);
        bottomNavigationView = findViewById(R.id.activity_main_nav_bar);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        // 创建fragments
        fragments.add(new IndexFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MeFragment());

        // 设置adapter和事件
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(new MyFragmentViewPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(position).getItemId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // 给navView添加点击事件
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.activity_main_nav_menu_index:
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.activity_main_nav_menu_message:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.activity_main_nav_menu_me:
                        viewPager.setCurrentItem(2, false);
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
    }

}