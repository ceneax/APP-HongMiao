package ceneax.app.hongmiao.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.base.BaseBottomSheetDialog;
import ceneax.app.hongmiao.bean.WeiboCommentBean;
import ceneax.app.hongmiao.bean.WeiboReplyResultBean;
import ceneax.app.hongmiao.listener.MyRecyclerViewScrollListener;
import ceneax.app.hongmiao.listener.WeiboSpanClickListener;
import ceneax.app.hongmiao.mvp.contact.ReplyBottomDialogContact;
import ceneax.app.hongmiao.mvp.presenter.ReplyBottomDialogPresenter;
import ceneax.app.hongmiao.util.AnimationUtil;
import ceneax.app.hongmiao.util.DateUtil;
import ceneax.app.hongmiao.util.DialogUtil;
import ceneax.app.hongmiao.util.WeiboRegUtil;
import ceneax.app.hongmiao.widget.SwipeRefreshLoadMore.SwipeRefreshLoadMore;

public class ReplyBottomDialog extends BaseBottomSheetDialog implements ReplyBottomDialogContact.IView {

    public static final String TAG = "ReplyBottomDialog";

    private ReplyBottomDialogContact.IPresenter iPresenter;

    private RecyclerView recyclerView;
    private CommonAdapter<WeiboCommentBean> adapter;
    private List<WeiboCommentBean> datas = new ArrayList<>();

    private SwipeRefreshLoadMore swipeRefreshLoadMore;

    private WeiboReplyResultBean replyResultBean;
    private String commentId;

    public ReplyBottomDialog(String commentId) {
        this.commentId = commentId;
    }

    @Override
    public int initLayoutResId() {
        return R.layout.dialog_reply_bottom;
    }

    @Override
    public void initVariable() {
        iPresenter = new ReplyBottomDialogPresenter(this);
    }

    @Override
    public void findViews(View view) {
        recyclerView = view.findViewById(R.id.dialog_reply_bottom_recyclerview);
        swipeRefreshLoadMore = view.findViewById(R.id.dialog_reply_bottom_swipe_refresh_layout);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        // 设置adapter
        adapter = new CommonAdapter<WeiboCommentBean>(context, R.layout.item_dialog_reply_bottom_recyclerview, datas) {
            @Override
            protected void convert(ViewHolder holder, final WeiboCommentBean bean, int position) {
                ImageView avatar = (ImageView) holder.getView(R.id.item_dialog_reply_bottom_recyclerview_avatar);
                Glide.with(context).load(bean.getUser().getAvatarLarge())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(RequestOptions.circleCropTransform()).into(avatar);
                avatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ProfileActivity.class);
                        intent.putExtra(Config.GOTO_WEIBO_PROFILE_ACTIVITY, bean.getUser());
                        startActivity(intent);
                    }
                });

                holder.setText(R.id.item_dialog_reply_bottom_recyclerview_username, bean.getUser().getScreenName());
                holder.setText(R.id.item_dialog_reply_bottom_recyclerview_date, DateUtil.format(new Date(bean.getCreatedAt())));
                holder.setText(R.id.item_dialog_reply_bottom_recyclerview_thumb_num, String.valueOf(bean.getLikeCounts()));

                WeiboRegUtil.formatWeiboContent(context, bean.getText(), (TextView) holder.getView(R.id.item_dialog_reply_bottom_recyclerview_content),
                        position, false, true, new WeiboSpanClickListener(context));
            }
        };

        // 配置recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new MyRecyclerViewScrollListener() {
            @Override
            public void onScrollStateChanged(State state) {
                switch (state) {
                    case SCROLL_UP:
                        swipeRefreshLoadMore.setNestedScrollingEnabled(false);
                        break;
                    case SCROLL_TOP:
                        swipeRefreshLoadMore.setNestedScrollingEnabled(true);
                        break;
                }
            }
        });

        swipeRefreshLoadMore.setOnRefreshListener(new SwipeRefreshLoadMore.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iPresenter.getReply(commentId);
            }
        });
        swipeRefreshLoadMore.setOnBottomRefreshListenrer(new SwipeRefreshLoadMore.OnBottomRefreshListener() {
            @Override
            public void onBottomRefresh() {
                if (replyResultBean == null)
                    iPresenter.loadMoreReply(commentId, null);
                else
                    iPresenter.loadMoreReply(commentId, replyResultBean.getMaxIdStr());
            }
        });
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
        swipeRefreshLoadMore.setRefreshing(true);
        iPresenter.getReply(commentId);
    }

    @Override
    public void onGetReplySuccess(WeiboReplyResultBean bean) {
        replyResultBean = null;
        replyResultBean = bean;
        datas.clear();
        datas.addAll(replyResultBean.getComments());

        adapter.notifyDataSetChanged();
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
    }

    @Override
    public void onLoadMoreReplySuccess(WeiboReplyResultBean bean) {
        replyResultBean = null;
        replyResultBean = bean;
        datas.addAll(replyResultBean.getComments());

        adapter.notifyDataSetChanged();
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);
    }

    @Override
    public void onGetReplyFail(final IOException e) {
        swipeRefreshLoadMore.setRefreshing(false);
        swipeRefreshLoadMore.setBottomRefreshing(false);

        DialogUtil.showToast(context, e.getMessage());
    }

}
