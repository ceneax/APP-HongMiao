package ceneax.app.hongmiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ceneax.app.hongmiao.bean.WeiboPicCateBean;

public class ImageViewerPagerAdapter extends PagerAdapter {

    private Context context;

    private List<WeiboPicCateBean> datas = new ArrayList<>();

    public ImageViewerPagerAdapter(Context context, List<WeiboPicCateBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setOnClickListener(getOnClick());

        // 加载图片
        Glide.with(context).load(datas.get(position).getLarge().getUrl()).centerInside().into(imageView);

        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public View.OnClickListener getOnClick() {
        return null;
    }

}
