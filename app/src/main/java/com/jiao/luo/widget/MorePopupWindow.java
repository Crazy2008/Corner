package com.jiao.luo.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.jiao.luo.R;
import com.jiao.luo.activity.HomeActivity;
import com.jiao.luo.utils.Constant;

/**
 * 转发的popup
 */

public class MorePopupWindow extends PopupWindow  {
    private final Context context;
    private View view;

    public MorePopupWindow(Context context){
        this.context = context;
        init();
    }
    private void init() {
        view = LayoutInflater.from(context).inflate(R.layout.popup_more_item, null);
        this.setContentView(view);

        view.findViewById(R.id.tv_find).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,HomeActivity.class);
                intent.putExtra(Constant.PAGE,0);
                context.startActivity(intent);

            }
        });
        view.findViewById(R.id.tv_sport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,HomeActivity.class);
                intent.putExtra(Constant.PAGE,1);
                context.startActivity(intent);
            }
        });
        view.findViewById(R.id.tv_my).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,HomeActivity.class);
                intent.putExtra(Constant.PAGE,2);
                context.startActivity(intent);
            }
        });


        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        this.setTouchable(true);
        this.setFocusable(true);
//        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));

       /* view.setOnTouchListener(new View.OnTouchListener() {
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
        });*/

    }
    public  void show(View view){
        showAtLocation(view, Gravity.TOP|Gravity.RIGHT,0, 150);
    }

}
