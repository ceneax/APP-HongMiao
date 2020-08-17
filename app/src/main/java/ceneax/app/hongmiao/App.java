package ceneax.app.hongmiao;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

/**
 * 自定义Application
 */
public class App extends Application {

    public static final String TAG = "App";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    /**
     * 获取全局context
     * @return 返回AppContext
     */
    public static Context getAppContext() {
        return context;
    }

}
