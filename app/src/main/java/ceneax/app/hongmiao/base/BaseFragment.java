package ceneax.app.hongmiao.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * BaseFragment，所有Fragment的基类
 */
public abstract class BaseFragment extends Fragment {

    public static final String TAG = "BaseFragment";

    public Context context;

    private boolean isViewPrepared = false;
    private boolean isFirstVisible = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(initLayoutResId(), null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view); // 获取View
        initVariable();
        initViews(savedInstanceState); // 初始化View

        isViewPrepared = true;
        if(getUserVisibleHint() && isFirstVisible) {
            initDatas(null);
            isFirstVisible = false;
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isViewPrepared && isFirstVisible && isVisibleToUser) {
            initDatas(null);
            isFirstVisible = false;
        }
    }

    /**
     * 抽象方法，返回一个布局资源ID
     * @return int型布局资源ID
     */
    public abstract int initLayoutResId();

    /**
     * 获取View
     * @param view 传递一个已经创建好的View对象
     */
    public abstract void findViews(View view);

    /**
     * 初始化变量
     */
    public abstract void initVariable();

    /**
     * 获取View之后，用来初始化的方法体，此方法传递了savedInstanceState参数，用于恢复数据
     * @param savedInstanceState 存放数据的对象
     */
    public abstract void initViews(Bundle savedInstanceState);

    /**
     * 初始化一些本地数据和网络请求，此方法传递了savedInstanceState参数，用于恢复数据
     * @param savedInstanceState 存放数据的对象
     */
    public abstract void initDatas(Bundle savedInstanceState);

}