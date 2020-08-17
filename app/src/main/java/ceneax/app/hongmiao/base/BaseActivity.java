package ceneax.app.hongmiao.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.util.AppUtil;
import ceneax.app.hongmiao.util.DateUtil;

/**
 * BaseActivity，所有Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUtil.setTransparentStatusBar(getWindow());

        // 夜间自动切换到暗黑主题
//        if(DateUtil.isNightNow())
//            setTheme(R.style.AppTheme_Night);
        setTheme(Config.currentTheme);

        setContentView(initLayoutResId()); // 设置Activity布局文件

        findViews(); // 获取View
        initVariable();
        initViews(savedInstanceState); // 初始化View
        initDatas(savedInstanceState); // 初始化数据
    }

    /**
     * 抽象方法，方法体中必须返回一个布局资源ID
     * @return int型的布局资源id
     */
    public abstract int initLayoutResId();

    /**
     * 获取View
     */
    public abstract void findViews();

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