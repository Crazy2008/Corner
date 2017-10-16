package cn.common;

import android.app.Application;

import cn.common.http2.HttpX;

/**
 * Created by Administrator on 2016/11/14.
 */

public class LibCommonUtils {
    public static boolean isDebug = false;
    public static String MainPackageName;

    public static void onCreate(Application context, boolean debug) {
        MainPackageName = context.getApplicationContext().getPackageName();
        isDebug = debug;
        HttpX.init(context);
    }
}
