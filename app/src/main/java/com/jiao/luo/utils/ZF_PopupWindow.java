package com.jiao.luo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.jiao.luo.R;

/**
 * 转发的popup
 */

public class ZF_PopupWindow  extends PopupWindow  {
    private final Context context;
    private View view;

    public ZF_PopupWindow(Context context){
        this.context = context;
        init();
    }
    private void init() {
        view = LayoutInflater.from(context).inflate(R.layout.popup_zf, null);
        this.setContentView(view);
        Button btn_cancle = (Button) view.findViewById(R.id.btn_cancle);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZF_PopupWindow.this.dismiss();
            }
        });
        this.setWidth(ScreenUtils.getScreenWidth(context));
        this.setHeight(ScreenUtils.getScreenHeight(context)/4);
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        this.setTouchable(true);
        this.setFocusable(true);
//        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y>height){
                        dismiss();
                    }
                }
                return true;
            }
        });

    }
    public  void show(View view){
        showAtLocation(view, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }

}
