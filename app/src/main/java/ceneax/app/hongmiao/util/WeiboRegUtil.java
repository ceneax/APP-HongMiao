package ceneax.app.hongmiao.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;

public class WeiboRegUtil {

    public static void formatWeiboContent(Context context, String source, TextView textView) {
        formatWeiboContent(context, source, textView, 0, false, false, null);
    }

    public static void formatWeiboContent(Context context, String source, TextView textView, final int position,
                                          boolean isLongText, boolean clickable, final SpanClick spanClick) {
        if(TextUtils.isEmpty(source))
            return;

        // 文本最后将空格替换成“...全文”
        SpannableString spannableString;
        if(isLongText) {
            String fullText = context.getString(R.string.util_weibo_reg_full_text);
            spannableString = new SpannableString(source + fullText);

            //  给“...全文”设置颜色
            int start = spannableString.toString().lastIndexOf(fullText);
            spannableString.setSpan(new ForegroundColorSpan(Color.GRAY), start, start + fullText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            spannableString = new SpannableString(source);
        }

        // 正则匹配
        Pattern pattern = Pattern.compile(Config.WEIBO_REG_ALL);
        Matcher matcher = pattern.matcher(spannableString);

        if(matcher.find()) {
            if(clickable) {
                // 实现文字的点击效果
                textView.setMovementMethod(MyLinkMovementMethod.getInstance());
                textView.setHighlightColor(Color.DKGRAY);
            }

            // 重置正则位置
            matcher.reset();
        }

        while (matcher.find()) {
            // 根据group的括号索引，可得出具体匹配哪个正则(0代表全部，1代表第一个括号)
            final String at = matcher.group(1);
            final String topic = matcher.group(2);
            final String superTopic = matcher.group(3);
            String emoji = matcher.group(4);
            final String url = matcher.group(5);

            // 处理@
            if(at != null) {
                int start = matcher.start(1);
                int end = start + at.length();

                spannableString.setSpan(new MyClickableSpan(spanClick, SpanClickType.WEIBO_AT, at.replace("@", ""), position), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            // 处理话题##
            if(topic != null) {
                int start = matcher.start(2);
                int end = start + topic.length();

                // topic.replace("#", "")
                spannableString.setSpan(new MyClickableSpan(spanClick, SpanClickType.WEIBO_TOPIC, topic, position), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            // 处理超话#[超话]#
            if(superTopic != null) {
                int start = matcher.start(3);
                int end = start + superTopic.length();

                spannableString.setSpan(new MyClickableSpan(spanClick, SpanClickType.WEIBO_SUPER_TOPIC, superTopic.replace("#", "").replace("[超话]", ""), position), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            // 处理emoji
            if(emoji != null) {
                int start = matcher.start(4);
                int end = start + emoji.length();

                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(context.getAssets().open(emoji + ".png"));
                    setEmojiSpan(context, spannableString, bitmap, textView, start, end);
                } catch (Exception e) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(context.getAssets().open(emoji + ".gif"));
                        setEmojiSpan(context, spannableString, bitmap, textView, start, end);
                    } catch (Exception ex) {
                        e.printStackTrace();
                    }
                }
            }

            // 处理url
            if(url != null) {
                int start = matcher.start(5);
                int end = start + url.length();

                spannableString.setSpan(new MyClickableSpan(spanClick, SpanClickType.WEIBO_URL, url, position), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        // 给textView设置span
        textView.setText(spannableString);
    }

    private static void setEmojiSpan(Context context, SpannableString spannableString, Bitmap bitmap, TextView textView, int start, int end) {
        if(bitmap == null)
            return;

        // 获取文本字体大小
        int size = (int) textView.getTextSize();
        // 压缩bitmap
        bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
        // 应用
        ImageSpan imageSpan = new ImageSpan(context, bitmap);
        spannableString.setSpan(imageSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    static class MyLinkMovementMethod extends LinkMovementMethod {

        private static MyLinkMovementMethod instance;

        public static MyLinkMovementMethod getInstance() {
            if(instance == null)
                return new MyLinkMovementMethod();
            return instance;
        }

        @Override
        public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
            boolean b = super.onTouchEvent(widget, buffer, event);

            // 解决点击事件冲突问题
            if(!b && event.getAction() == MotionEvent.ACTION_UP) {
                ViewParent parent = widget.getParent(); // 处理widget的父控件点击事件
                if(parent instanceof ViewGroup) {
                    return ((ViewGroup) parent).performClick();
                }
            }

            return b;
        }

    }

    static class MyClickableSpan extends ClickableSpan {

        private SpanClick spanClick;
        private SpanClickType spanClickType;
        private String content;
        private int position;

        public MyClickableSpan(SpanClick spanClick, SpanClickType spanClickType, String content, int position) {
            this.spanClick = spanClick;
            this.spanClickType = spanClickType;
            this.content = content;
            this.position = position;
        }

        @Override
        public void onClick(@NonNull View widget) {
            if(spanClick != null) {
//                ((TextView) widget).setMovementMethod(MyLinkMovementMethod.getInstance());
                spanClick.onClick(spanClickType, content, position);
            }
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.GRAY);
            ds.setUnderlineText(false);
        }

    }

    public interface SpanClick {
        void onClick(SpanClickType spanClickType, String content, int position);
    }

    public enum SpanClickType {
        WEIBO_AT,
        WEIBO_TOPIC,
        WEIBO_SUPER_TOPIC,
        WEIBO_URL
    }

}
