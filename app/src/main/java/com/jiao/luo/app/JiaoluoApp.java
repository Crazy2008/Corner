package com.jiao.luo.app;


import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.StrictMode;
import android.widget.ImageView;

import com.jiao.luo.BuildConfig;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.GlideUtils;
import com.lzy.ninegrid.NineGridView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import cn.common.LibCommonUtils;
import cn.common.utils.PhoneInfo;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by xin on 2017/6/29.
 */

public class JiaoluoApp extends Application {


    private static String registrationId;

    //static 代码段可以防止内存泄露  下拉刷新
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(android.R.color.white, android.R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();


        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        Constant.eqid = JPushInterface.getRegistrationID(this);


        //九宫格的初始化
        NineGridView.setImageLoader(new PicassoImageLoader());

        if (BuildConfig.APPLICATION_ID.equals(PhoneInfo.getProcessName(this))) {
            initCreate();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }


    }

    private void initCreate() {
        LibCommonUtils.onCreate(this, BuildConfig.DEBUG);
    }




    /** Picasso 加载 */
    private class PicassoImageLoader implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
          /*  Glide.with(context).load(url)//
                    .placeholder(R.drawable.ic_default_image)//
                    .error(R.drawable.ic_default_image)//
                    .into(imageView);*/

            GlideUtils.load(context,url,imageView);
        }
        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }


}
