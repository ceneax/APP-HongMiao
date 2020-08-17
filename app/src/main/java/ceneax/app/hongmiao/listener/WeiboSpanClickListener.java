package ceneax.app.hongmiao.listener;

import android.content.Context;
import android.content.Intent;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.mvp.view.ProfileActivity;
import ceneax.app.hongmiao.mvp.view.TopicActivity;
import ceneax.app.hongmiao.mvp.view.WebViewActivity;
import ceneax.app.hongmiao.util.DialogUtil;
import ceneax.app.hongmiao.util.WeiboRegUtil;

public class WeiboSpanClickListener implements WeiboRegUtil.SpanClick {

    private Context context;

    public WeiboSpanClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(WeiboRegUtil.SpanClickType spanClickType, String content, int position) {
        switch (spanClickType) {
            case WEIBO_AT:
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra(Config.GOTO_WEIBO_PROFILE_ACTIVITY, content);
                context.startActivity(intent);
                break;
            case WEIBO_URL:
                Intent webViewIntent = new Intent(context, WebViewActivity.class);
                webViewIntent.putExtra(Config.GOTO_WEB_VIEW_ACTIVITY, content);
                context.startActivity(webViewIntent);
                break;
            case WEIBO_SUPER_TOPIC:
                DialogUtil.showToast(context, "暂时还不支持查看超话！等等哦~");
                break;
            case WEIBO_TOPIC:
                Intent topicIntent = new Intent(context, TopicActivity.class);
                topicIntent.putExtra(Config.GOTO_TOPIC_ACTIVITY, content);
                context.startActivity(topicIntent);
                break;
        }
    }

}
