package com.jiao.luo.utils;

import android.app.Activity;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Administrator on 2017/8/23.
 */
    // 设置popupwindow 显示后背景变灰色
public class PopupUtils {

    public static  void setBg(final Activity activity,PopupWindow popupWindow){
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.3f;
        activity.getWindow().setAttributes(lp);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }
}
