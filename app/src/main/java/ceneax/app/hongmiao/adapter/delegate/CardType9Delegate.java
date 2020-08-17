package ceneax.app.hongmiao.adapter.delegate;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.bean.WeiboPicCateBean;
import ceneax.app.hongmiao.bean.WeiboTopicCardsBean;
import ceneax.app.hongmiao.listener.WeiboSpanClickListener;
import ceneax.app.hongmiao.mvp.view.ImageViewerDialog;
import ceneax.app.hongmiao.mvp.view.ProfileActivity;
import ceneax.app.hongmiao.mvp.view.WeiboDetailActivity;
import ceneax.app.hongmiao.util.DateUtil;
import ceneax.app.hongmiao.util.L;
import ceneax.app.hongmiao.util.WeiboRegUtil;
import ceneax.app.hongmiao.widget.NineGridImageLayout.NineGridImageLayout;
import ceneax.app.hongmiao.widget.NineGridImageLayout.RatioImageView;

public class CardType9Delegate implements ItemViewDelegate<WeiboTopicCardsBean>, NineGridImageLayout.OnItemClickListener {

    private Context context;

    public CardType9Delegate(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_fragment_index_temp_recyclerview;
    }

    @Override
    public boolean isForViewType(WeiboTopicCardsBean item, int position) {
        L.e("-----------------", position + "--------" + new Gson().toJson(item));
        return item.getCardType() == 9;
    }

    @Override
    public void convert(ViewHolder holder, WeiboTopicCardsBean weiboTopicCardsBean, final int position) {
        final WeiboBean weiboBean = weiboTopicCardsBean.getMblog();

        ImageView avatar = (ImageView) holder.getView(R.id.item_fragment_index_temp_recyclerview_avatar);
        Glide.with(context).load(weiboBean.getUser().getAvatarLarge())
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.circleCropTransform()).into(avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra(Config.GOTO_WEIBO_PROFILE_ACTIVITY, weiboBean.getUser());
                context.startActivity(intent);
            }
        });

        holder.setText(R.id.item_fragment_index_temp_recyclerview_username, weiboBean.getUser().getName());
        holder.setText(R.id.item_fragment_index_temp_recyclerview_date, DateUtil.format(new Date(weiboBean.getCreatedAt())));
        holder.setText(R.id.item_fragment_index_temp_recyclerview_repost, String.valueOf(weiboBean.getRepostsCount()));
        holder.setText(R.id.item_fragment_index_temp_recyclerview_comment, String.valueOf(weiboBean.getCommentsCount()));
        holder.setText(R.id.item_fragment_index_temp_recyclerview_thumb, String.valueOf(weiboBean.getAttitudesCount()));

        WeiboRegUtil.formatWeiboContent(context, weiboBean.getText(), (TextView) holder.getView(R.id.item_fragment_index_temp_recyclerview_content),
                position, weiboBean.getLongText(), true, new WeiboSpanClickListener(context));

        // 设置微博来源
        Matcher matcher = Pattern.compile("<a.*?href=\"(.*?)\".*?>(.*?)</a>").matcher(weiboBean.getSource());
        if(matcher.find())
            holder.setText(R.id.item_fragment_index_temp_recyclerview_source, matcher.group(2));

        holder.getView(R.id.item_fragment_index_temp_recyclerview_comment_wrap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommentClick(position, weiboBean);
            }
        });

        // 添加图片
        NineGridImageLayout imageLayout = holder.getView(R.id.item_fragment_index_temp_recyclerview_nine_image);
        if(weiboBean.getPicIds() != null && weiboBean.getPicIds().size() > 0) {
            List<WeiboPicCateBean> imgUrls = new ArrayList<>();

            for (int i = 0; i < weiboBean.getPicIds().size(); i++) {
                imgUrls.add(weiboBean.getPicInfos().get(weiboBean.getPicIds().get(i)));
            }

            imageLayout.setCorner(3);
            imageLayout.setUrlList(imgUrls);
            imageLayout.setOnOwnItemClickListener(this);
        } else {
            imageLayout.setUrlList(null);
        }

        //repost
        LinearLayout linearLayout = holder.getView(R.id.item_fragment_index_temp_recyclerview_repost_layout);
        if(weiboBean.getRetweetedStatus() != null) {
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRepostClick(position, weiboBean);
                }
            });

            WeiboRegUtil.formatWeiboContent(context, "@" + weiboBean.getRetweetedStatus().getUser().getScreenName() + "：" + weiboBean.getRetweetedStatus().getText(),
                    (TextView) holder.getView(R.id.item_fragment_index_temp_recyclerview_repost_content), position, weiboBean.getRetweetedStatus().getLongText(),
                    true, new WeiboSpanClickListener(context));

            // 添加图片
            NineGridImageLayout repoImageLayout = holder.getView(R.id.item_fragment_index_temp_recyclerview_repost_nine_image);
            if(weiboBean.getRetweetedStatus().getPicIds() != null && weiboBean.getRetweetedStatus().getPicIds().size() > 0) {
                List<WeiboPicCateBean> imgDatas = new ArrayList<>();

                for (int i = 0; i < weiboBean.getRetweetedStatus().getPicIds().size(); i++) {
                    imgDatas.add(weiboBean.getRetweetedStatus().getPicInfos().get(weiboBean.getRetweetedStatus().getPicIds().get(i)));
                }

                repoImageLayout.setCorner(3);
                repoImageLayout.setUrlList(imgDatas);
                repoImageLayout.setOnOwnItemClickListener(this);
            } else {
                repoImageLayout.setUrlList(null);
            }
        } else {
            linearLayout.setVisibility(View.GONE);
        }
    }

    private void onRepostClick(int position, WeiboBean weiboBean) {
        Intent intent = new Intent(context, WeiboDetailActivity.class);
        intent.putExtra(Config.GOTO_WEIBO_DETAIL_ACTIVITY, weiboBean.getRetweetedStatus());
        context.startActivity(intent);
    }

    private void onCommentClick(int position, WeiboBean weiboBean) {
        // 跟构造函数的方法同步
        Intent intent = new Intent(context, WeiboDetailActivity.class);
        intent.putExtra(Config.GOTO_WEIBO_DETAIL_ACTIVITY, weiboBean);
        context.startActivity(intent);
    }

    @Override
    public void onClickImage(RatioImageView imageView, int position, String url, ArrayList<WeiboPicCateBean> urlList) {
//        new ImageViewerDialog(position, urlList).show(, "ImageViewer");
    }

}
