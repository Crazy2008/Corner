package com.jiao.luo.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 不能滑动的viewpager
 */

public class ControlScrollViewPager extends ViewPager {
    //为false是设置viewpager不能滑动
    private boolean scrollable = false;

    public ControlScrollViewPager(Context context) {
        super(context);
    }

    public ControlScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean enable) {
        scrollable = enable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (scrollable) {
            return super.onInterceptTouchEvent(event);
        } else {
            return false;
        }
    }
}
