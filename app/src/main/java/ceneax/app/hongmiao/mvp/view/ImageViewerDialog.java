package ceneax.app.hongmiao.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.adapter.ImageViewerPagerAdapter;
import ceneax.app.hongmiao.base.BaseBottomSheetDialog;
import ceneax.app.hongmiao.bean.WeiboPicCateBean;
import ceneax.app.hongmiao.bean.WeiboPicCateInfoBean;

public class ImageViewerDialog extends BaseBottomSheetDialog {

    private static final String TAG = "ImageViewerDialog";

    private ViewPager viewPager;
    private List<WeiboPicCateBean> datas = new ArrayList<>();

    private TextView indicator;

    private int position;

    public ImageViewerDialog(String url) {
        super(true, true);

        WeiboPicCateBean picCateBean = new WeiboPicCateBean();
        WeiboPicCateInfoBean picCateInfoBean = new WeiboPicCateInfoBean();
        List<WeiboPicCateBean> tmp = new ArrayList<>();
        picCateInfoBean.setUrl(url);
        picCateBean.setLarge(picCateInfoBean);
        tmp.add(picCateBean);

        this.position = 0;
        this.datas = tmp;
    }

    public ImageViewerDialog(int position, List<WeiboPicCateBean> datas) {
        super(true, true);

        this.position = position;
        this.datas = datas;
    }

    @Override
    public int initLayoutResId() {
        return R.layout.dialog_image_viewer;
    }

    @Override
    public void findViews(View view) {
        viewPager = view.findViewById(R.id.dialog_image_viewwe_viewpager);
        indicator = view.findViewById(R.id.dialog_image_viewwe_indicator);
    }

    @Override
    public void initVariable() {
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        viewPager.setAdapter(new ImageViewerPagerAdapter(context, datas) {
            @Override
            public View.OnClickListener getOnClick() {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                };
            }
        });
//        viewPager.setOffscreenPageLimit(datas.size());
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int pos) {
                super.onPageSelected(pos);
                indicator.setText((pos + 1) + "/" + datas.size());
            }
        });

        indicator.setText((position + 1) + "/" + datas.size());
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
    }

}
