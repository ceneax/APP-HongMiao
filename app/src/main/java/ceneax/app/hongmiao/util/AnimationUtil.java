package ceneax.app.hongmiao.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AnimationUtil {

    /**
     * 显示view附带动画
     * @param view 要执行动画的view
     */
    public static void showViewAnimation(View view)
    {
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(100);
        view.startAnimation(mShowAction);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏view附带动画
     * @param view 要执行动画的view
     */
    public static void hideViewAnimation(View view)
    {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(100);
        view.setAnimation(mHiddenAction);
        view.setVisibility(View.GONE);
    }

}