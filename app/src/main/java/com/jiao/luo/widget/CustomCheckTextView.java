package com.jiao.luo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiao.luo.R;

/**
 * Created by Administrator on 2017/8/22.
 */

public class CustomCheckTextView  extends LinearLayout implements Checkable {
    private TextView titleView;
    private CheckBox mCheckBox;

    public CustomCheckTextView(Context context) {
        this(context, null);
    }

    public CustomCheckTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCheckTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        //将加载出来的View添加到当前View层级中去。
        //有两种方案，一种是加载布局时将rootView传进去，或直接使用addView添加进去
        //View v = mLayoutInflater.inflate(R.layout.layout_custom_ctv, null);
        View v = mLayoutInflater.inflate(R.layout.activity_vote_item, this, true);
//        mCheckBox = (CheckBox)v.findViewById(R.id.cb);
        titleView = (TextView)v.findViewById(R.id.tv_content);
        //this.addView(v);
    }

    @Override
    public void setChecked(boolean checked) {
        mCheckBox.setChecked(checked);
    }

    @Override
    public boolean isChecked() {
        return mCheckBox.isChecked();
    }

    @Override
    public void toggle() {
        mCheckBox.toggle();
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }

    @Override
    public void setActivated(boolean activated) {
        super.setActivated(activated);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        toggle();
    }
}
