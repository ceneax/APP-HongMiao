package ceneax.app.hongmiao.util;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class AppUtil {

    /**
     * 给状态栏设置为透明
     * @param window window参数
     */
    public static void setTransparentStatusBar(Window window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * 将dp转换为px单位
     * @param context 上下文
     * @param dp dp值
     * @return 返回int的px
     */
    public static int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 将px转换为dp单位
     * @param context 上下文
     * @param px px值
     * @return 返回int的dp
     */
    public static int px2dp(Context context, float px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取状态栏高度
     * @param context 上下文
     * @return 返回int值的px
     */
    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");

        if(resourceId > 0)
            return context.getApplicationContext().getResources().getDimensionPixelSize(resourceId);

        return 0;
    }

    /**
     * 获取可用屏幕宽高
     * @param windowManager 参数
     * @return 返回DisplayMetrics
     */
    public static DisplayMetrics getScreenSize(WindowManager windowManager) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }

    /**
     * 获取真实屏幕宽高
     * @param windowManager 参数
     * @return 返回DisplayMetrics
     */
    public static DisplayMetrics getRealScreenSize(WindowManager windowManager) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
        return outMetrics;
    }

}