package com.jiao.luo.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.jiao.luo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.common.base.ActivityCommBase;

/**
 * 跳转的web原文界面
 */
public class ProtocolActivity extends ActivityCommBase {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol);
        ButterKnife.bind(this);
        setWebArg(webView);

        webView.loadUrl("file:///android_asset/test.html");
    }

    private void setWebArg(WebView webView) {
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setting.setDatabaseEnabled(true);
        setting.setUseWideViewPort(true); /*加载手机版的 */
        setting.setLoadWithOverviewMode(true);
        //支持屏幕缩放
        setting.setSupportZoom(true);
        setting.setBuiltInZoomControls(false);
        /*webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });*/
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
