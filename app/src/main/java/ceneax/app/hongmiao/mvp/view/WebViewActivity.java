package ceneax.app.hongmiao.mvp.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import ceneax.app.hongmiao.Config;
import ceneax.app.hongmiao.R;
import ceneax.app.hongmiao.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    private static final String TAG = "WebViewActivity";

    private Toolbar toolbar;

    private WebView webView;

    @Override
    public int initLayoutResId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initVariable() {
    }

    @Override
    public void findViews() {
        toolbar = findViewById(R.id.activity_web_view_toolbar);
        webView = findViewById(R.id.activity_web_view_webview);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        // 初始化toolBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 初始化webView
        webView.getSettings().setJavaScriptEnabled(true);
        // Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP // 解决5.0以上不能同时加载http和https
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                getSupportActionBar().setTitle("稍等哦，马上就好！");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                getSupportActionBar().setTitle(view.getTitle());
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                getSupportActionBar().setTitle("稍等哦，马上就好！（" + newProgress + "%）");
            }
        });

        String url = getIntent().getStringExtra(Config.GOTO_WEB_VIEW_ACTIVITY);
        if(!TextUtils.isEmpty(url)) {
            webView.loadUrl(url);
        }
    }

    @Override
    public void initDatas(Bundle savedInstanceState) {
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
//        webView = null;
    }

}
