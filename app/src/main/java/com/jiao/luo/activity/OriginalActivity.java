package com.jiao.luo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.Response.ResPub;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.IntentUtils;
import com.jiao.luo.utils.PopupUtils;
import com.jiao.luo.utils.ToastUtils;
import com.jiao.luo.utils.ZF_PopupWindow;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.common.base.ActivityCommBase;
import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 跳转的web原文界面
 */
public class OriginalActivity extends ActivityCommBase {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.iv_web_back)
    ImageView ivWebBack;
    @BindView(R.id.iv_web_go)
    ImageView ivWebGo;
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @BindView(R.id.iv_web_fav)
    ImageView ivWebFav;
    @BindView(R.id.iv_web_comment)
    ImageView ivWebComment;
    @BindView(R.id.tv_fav_count)
    TextView tv_fav_count;

    @BindView(R.id.ll_foot)
    LinearLayout ll_foot;
    //收藏数量
    private String fav_count;
    //评论数量
    private String comment_count;
    private int newsId;


    private boolean isFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original);
        ButterKnife.bind(this);
        setWebArg(webView);
        Intent intent = getIntent();
        String url = intent.getStringExtra(Constant.URL);
        fav_count = intent.getStringExtra(Constant.FAV_COUNT);
        comment_count = intent.getStringExtra(Constant.COMMENT_COUNT);
        newsId = intent.getIntExtra(Constant.NEWSID, -1);

        tv_fav_count.setText(fav_count);
        tvCommentCount.setText(comment_count);


        webView.loadUrl(url);
    }

    private void setWebArg(WebView webView) {
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setting.setDatabaseEnabled(true);
        setting.setUseWideViewPort(true); /*加载手机版的 */
        setting.setLoadWithOverviewMode(true);
        setting.setSupportZoom(true);
        /*webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });*/
    }


    @OnClick({R.id.iv_back, R.id.iv_share, R.id.iv_web_back, R.id.iv_web_go, R.id.iv_web_comment, R.id.tv_comment_count, R.id.iv_web_fav})
    public void onViewClicked(View view) {

     /*   mWebView.goBack();//跳到上个页面
        mWebView.goForward();//跳到下个页面
        mWebView.canGoBack();//是否可以跳到上一页(如果返回false,说明已经是第一页)
        mWebView.canGoForward();//是否可以跳到下一页(如果返回false,说明已经是最后一页)*/

        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                ZF_PopupWindow window = new ZF_PopupWindow(this);
                window.show(ll_foot);
                PopupUtils.setBg(this, window);
                break;
            case R.id.iv_web_back:
                if (webView.canGoBack()) {
                    webView.goBack();
                }
                break;
            case R.id.iv_web_go:
                if (webView.canGoForward()) {
                    webView.goForward();
                }
                break;
            case R.id.iv_web_comment:
                IntentUtils.intentComment(this, String.valueOf(newsId));

                break;
            case R.id.tv_comment_count:
                break;
            case R.id.iv_web_fav:
                int count = Integer.parseInt(fav_count);
                if (!isFav) {
                    ivWebFav.setImageResource(R.drawable.content_set_like);
                    tv_fav_count.setText(String.valueOf(count + 1));
                    isFav = !isFav;
                    fav_count = String.valueOf(count + 1);
                } else {
                    ivWebFav.setImageResource(R.drawable.content_like);
                    if (Integer.parseInt(fav_count) > 0) {
                        tv_fav_count.setText(String.valueOf(count - 1));
                        fav_count = String.valueOf(count - 1);
                    } else {
                        tv_fav_count.setText("0");
                        fav_count = "0";
                    }
                    isFav = !isFav;
                }
                FavForNet();
                break;
        }
    }

    //收藏话题内容
    private void FavForNet() {
        if(!Constant.ISLOGIN){
            ToastUtils.showToast(this,"请先登录");
            return;
        }
        OkGo.post(Constant.NEWSFAV).headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("newsid", newsId).execute(new SimpleCommonCallback<ResPub>(this) {
            @Override
            public void onSuccess(ResPub resPub, Call call, Response response) {
                if (resPub.getReturnCode() == 1) {
                    toast(resPub.getMessage());
                } else {
                    toast(resPub.getMessage());
                }
            }
        });

    }
}
