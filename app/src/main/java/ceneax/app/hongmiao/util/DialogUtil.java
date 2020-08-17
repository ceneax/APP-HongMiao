package ceneax.app.hongmiao.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ceneax.app.hongmiao.R;

public class DialogUtil {

    /**
     * 显示短时间的snackBar
     * @param activity 要显示在哪个activity上
     * @param content 内容
     */
    public static void showSnackBar(Activity activity, String content) {
        Snackbar.make(activity.findViewById(android.R.id.content), content, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 显示带按钮的snackBar
     * @param activity 要显示在哪个activity上
     * @param content 内容
     */
    public static void showSnackBarKeep(Activity activity, String content) {
        Snackbar.make(activity.findViewById(android.R.id.content), content, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.util_dialog_snack_bar_action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .show();
    }

    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

}
