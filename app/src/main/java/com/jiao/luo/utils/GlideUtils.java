package com.jiao.luo.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiao.luo.R;

/**
 * Created by Administrator on 2017/8/17.
 */

public class GlideUtils {

    public static  void load(Context context,String url, ImageView iv){

        Glide.with(context).load(url).animate(R.anim.glide_pic).error(R.drawable.ic_default_image).placeholder(R.drawable.holderlit).into(iv);
    }


    public static  void load(Fragment fragment, String url, ImageView iv){

        Glide.with(fragment).load(url).animate(R.anim.glide_pic).error(R.drawable.ic_default_image).placeholder(R.drawable.holderlit).into(iv);
    }


    public static  void load(Activity activity, String url, ImageView iv){

        Glide.with(activity).load(url).animate(R.anim.glide_pic).error(R.drawable.ic_default_image).placeholder(R.drawable.holderlit).into(iv);
    }

}
