package ceneax.app.hongmiao.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ceneax.app.hongmiao.App;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.util.AppUtil;

public abstract class BaseBottomSheetDialog extends BottomSheetDialogFragment {

    public static final String TAG = "BaseBottomSheetDialog";

    public Context context;

    // 是否使用dialog默认不带圆角的背景
    private boolean useDefaultDialogBackground;
    private boolean useFullScreen;

    public BaseBottomSheetDialog() {
        this(false, false);
    }

    public BaseBottomSheetDialog(boolean useDefaultDialogBackground, boolean useFullScreen) {
        this.useDefaultDialogBackground = useDefaultDialogBackground;
        this.useFullScreen = useFullScreen;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(initLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        initVariable();
        initViews(savedInstanceState);
        initDatas(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        // 获取dialog实例
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();

        // 将dialog背景设置圆角
        if (!useDefaultDialogBackground)
            dialog.getWindow().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackground(context.getDrawable(R.drawable.shape_layout_corner));
        else
            dialog.getWindow().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundColor(Color.BLACK);

        // 获取dialog根布局
        FrameLayout rootLayout = dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if(rootLayout != null) {
            // 设置dialog最大高度
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) rootLayout.getLayoutParams();
            layoutParams.height = getDialogHeight();
            rootLayout.setLayoutParams(layoutParams);

            BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(rootLayout);
            behavior.setPeekHeight(getDialogHeight());
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    private int getDialogHeight() {
        if(useFullScreen)
            return AppUtil.getScreenSize(getActivity().getWindowManager()).heightPixels;
        else
            return AppUtil.getRealScreenSize(getActivity().getWindowManager()).heightPixels / 2;
    }

    /**
     * 抽象方法，方法体中必须返回一个布局资源ID
     * @return int型的布局资源id
     */
    public abstract int initLayoutResId();

    /**
     * 获取View
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
