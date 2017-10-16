package com.jiao.luo.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jiao.luo.R;

/**
 * Created by Administrator on 2017/8/23.
 */

public class SortPopupWindow extends PopupWindow {


    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    private LinearLayout ll_1,ll_2;
    private TextView tv_sort_one,tv_sort_time;
    private ImageView iv_1,iv_2;


    private View mMenuView;

    public SortPopupWindow(Activity context, final View.OnClickListener listener, boolean isOne) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.follow_popup_item, null);
        ll_1 = (LinearLayout) mMenuView.findViewById(R.id.ll_1);
        ll_2 = (LinearLayout) mMenuView.findViewById(R.id.ll_2);
        tv_sort_one = (TextView) mMenuView.findViewById(R.id.tv_sort_one);
        tv_sort_time = (TextView) mMenuView.findViewById(R.id.tv_sort_time);
        iv_1 = (ImageView) mMenuView.findViewById(R.id.iv_1);
        iv_2 = (ImageView) mMenuView.findViewById(R.id.iv_2);
        setToggle(isOne);

        ll_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              setToggle(true);
                listener.onClick(v);
            }
        });
        ll_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToggle(false);
                listener.onClick(v);
            }
        });
//        btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
        //取消按钮
      /*  btn_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });*/
        //设置按钮监听
     /*   btn_pick_photo.setOnClickListener(itemsOnClick);
        btn_take_photo.setOnClickListener(itemsOnClick);*/
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.selelct_popup_anim_style);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#ffffff"));
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

    private void setToggle(boolean isOne) {
        if(isOne){
            tv_sort_one.setTextColor(Color.parseColor("#01CFFF"));
            iv_1.setVisibility(View.VISIBLE);
        }else{
            tv_sort_time.setTextColor(Color.parseColor("#01CFFF"));
            iv_2.setVisibility(View.VISIBLE);
        }

    }

}