package com.jiao.luo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class BitmapUtils {

    public static  Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }
    public static  Bitmap getZipBitmap(String url, Context context) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);


            BitmapFactory.Options opts = new BitmapFactory.Options();
            //只有添加了该属性，才不会将jpg转换为Bitmap而是仅仅获取jpg的宽和高信息
            opts.inJustDecodeBounds = true;
            //1. 先去获取一下图片的元数据，获取图片的宽（width）和高（height）属性
		/*
		 * 注意：如果设置了opts.inJustDecodeBounds = true;属性，那么返回值为null
		 */
           BitmapFactory.decodeStream(bis,null,opts);
            //获取到图片的宽度和高度
            int width = opts.outWidth;
            int height = opts.outHeight;
            Log.d("tag", "width="+width+"\nheight"+height);
            //2. 获取手机屏幕的宽（screenWidth）和高（screenHeight）
            //获取屏幕的显示器对象

            //获取显示器的宽和高
            int screenWidth = ScreenUtils.getScreenWidth(context);
            int screenHeight = ScreenUtils.getScreenHeight(context);
            Log.d("tag", "screenWidth="+screenWidth+"\nscreenHeight"+screenHeight);
            //3. 计算宽度的比值 width/screenWidth = widthSample,计算高度的比值：height/screenHeight=heightSample
            int widthSample = width/screenWidth;
            int heightSample = height/screenHeight;
            //4. 通常情况下widthSample ！= heightSample,取其中最大的值最为比值（sample）。
            int sample = Math.max(widthSample, heightSample);
            //5. 将图片的宽和高都进行sample倍的压缩（取样）。
            opts.inJustDecodeBounds = false;
            //设置压缩比
            opts.inSampleSize = 1;
            Rect rect = new Rect();
            bm = BitmapFactory.decodeStream(bis,rect,opts);

            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }


}