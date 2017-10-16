package com.jiao.luo.utils;

import android.content.Context;
import android.content.Intent;

import com.jiao.luo.activity.CateDetailActivity;
import com.jiao.luo.activity.LoginActivity;
import com.jiao.luo.activity.OriginalActivity;
import com.jiao.luo.activity.PublicCommentActivity;
import com.jiao.luo.activity.WriteActivity;

/**
 * 跳转到webView的工具类
 */

public class IntentUtils {

    /**
     * @param context       上下文
     * @param url           地址
     * @param fav_count     收藏数量
     * @param comment_count 评论数量
     */
    public static void intentWeb(Context context, String url, int fav_count, int comment_count,int newsId) {
        Intent intent = new Intent(context, OriginalActivity.class);
        intent.putExtra(Constant.FAV_COUNT, String.valueOf(fav_count));
        intent.putExtra(Constant.COMMENT_COUNT, String.valueOf(comment_count));
        intent.putExtra(Constant.NEWSID,newsId);
        intent.putExtra(Constant.URL, url);
        context.startActivity(intent);
    }


    public static void intentDetail(Context context, String typeid) {
        Intent intent = new Intent(context, CateDetailActivity.class);
        intent.putExtra(Constant.TYPEID, typeid);
        context.startActivity(intent);
    }


    public static void intentComment(Context context, String newsid) {

        Intent intent = new Intent(context, PublicCommentActivity.class);
        intent.putExtra(Constant.NEWID, newsid);
        context.startActivity(intent);
    }

    public static void intentWrite(Context context, int  sid) {
        Intent intent = new Intent(context, WriteActivity.class);
        intent.putExtra(Constant.SID, sid);
        context.startActivity(intent);
    }


    public static  void intentLogin(Context context){
        Constant.clearLoginData();

        context.startActivity(new Intent(context, LoginActivity.class));
    }


}
