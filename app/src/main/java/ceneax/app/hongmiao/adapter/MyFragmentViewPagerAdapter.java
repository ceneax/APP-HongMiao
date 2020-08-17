package ceneax.app.hongmiao.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();

    /**
     * 构造方法
     * @param fm FragmentManager
     * @param fragmentList Fragment集合
     */
    public MyFragmentViewPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        this(fm, fragmentList, null);
    }

    /**
     * 构造方法
     * @param fm FragmentManager
     * @param fragmentList Fragment集合
     * @param tabItemTitles TabItemTitle数组
     */
    public MyFragmentViewPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList, String[] tabItemTitles) {
        super(fm);

        this.fragmentList = fragmentList;
//        this.tabItemTitles = tabItemTitles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

}
