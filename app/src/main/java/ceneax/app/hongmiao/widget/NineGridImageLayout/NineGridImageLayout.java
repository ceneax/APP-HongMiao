package ceneax.app.hongmiao.widget.NineGridImageLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

import ceneax.app.hongmiao.bean.WeiboPicCateBean;
import ceneax.app.hongmiao.widget.transform.GlideRoundTransform;

public class NineGridImageLayout extends BaseNineGridImageLayout {

    protected static final int MAX_W_H_RATIO = 3;

    private OnItemClickListener onItemClickListener;

    private Context mContext;

    private int corner = 0;

    public NineGridImageLayout(Context context) {
        super(context);
        mContext = context;
    }

    public NineGridImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected boolean displayOneImage(final RatioImageView imageView, String url, final int parentWidth) {
        Glide.with(mContext).asBitmap()
                .load(url)
                .transition(BitmapTransitionOptions.withCrossFade())
                .transform(new GlideRoundTransform(corner))
                .into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                int w = resource.getWidth();
                int h = resource.getHeight();

                int newW;
                int newH;

                if (h > w * MAX_W_H_RATIO) {//h:w = 5:3
                    newW = parentWidth / 2;
                    newH = newW * 5 / 3;
                } else if (h < w) {//h:w = 2:3
                    newW = parentWidth * 2 / 3;
                    newH = newW * 2 / 3;
                } else {//newH:h = newW :w
                    newW = parentWidth / 2;
                    newH = h * newW / w;
                }
                setOneImageLayoutParams(imageView, newW, newH);

                imageView.setImageBitmap(resource);
            }
        });
        return false;
    }

    @Override
    protected void displayImage(RatioImageView imageView, String url) {
        Glide.with(mContext).load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(new GlideRoundTransform(corner)).into(imageView);
    }

    @Override
    protected void onClickImage(RatioImageView imageView, int position, String url, ArrayList<WeiboPicCateBean> urlList) {
        if(onItemClickListener != null)
            onItemClickListener.onClickImage(imageView, position, url, urlList);
    }

    public void setOnOwnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setCorner(int value) {
        corner = value;
    }

    public interface OnItemClickListener {
        void onClickImage(RatioImageView imageView, int position, String url, ArrayList<WeiboPicCateBean> urlList);
    }

}
