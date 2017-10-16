package com.jiao.luo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * 搜索界面显示的记录或者  感兴趣内容展示的容器
 */

public class MyFlowLayout extends ViewGroup {
    private Line mLine;
    private ArrayList<Line> mLines;
    private int mUsedWidth;
    private int mAvailableWidthSize;

    public MyFlowLayout(Context context) {
        super(context);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //创建一个行对象
        mLine = new Line();
        //创建一个集合保存所有的行对象
        mLines = new ArrayList<>();
        //创建一个变量记录当前行已经用过的宽度
        mUsedWidth = 0;


        //测量模式：三种
        //MeasureSpec.EXACTLY:精确的测量模式，100dp,match_parent
        //MeasureSpec.AT_MOST:至多的测量模式，不超过，wrap_content
        //MeasureSpec.UNSPECIFIED:未定义的测量模式
        //widthMeasureSpec 32位数，前两是测量模式，后30是具体的大小
        //获取测量模式
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        //获取具体大小
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        //给子控件的可用宽度
        mAvailableWidthSize = widthSize - getPaddingLeft() - getPaddingRight();

        int heigthMode = View.MeasureSpec.getMode(heightMeasureSpec);
        //获取具体大小
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        //给子控件的可用高度
        int availableHeightSize = heightSize - getPaddingTop() - getPaddingBottom();

        //约束所有的子控件不能比父控件宽
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            //获取每一个子控件
            View child = getChildAt(i);
            //重新定义一下子控件的宽高
            //计算新测量规则
            int wSpec = View.MeasureSpec.makeMeasureSpec(mAvailableWidthSize, MeasureSpec.AT_MOST);
            int hSpec = MeasureSpec.makeMeasureSpec(availableHeightSize, MeasureSpec.AT_MOST);
            //新测量规则生效
            child.measure(wSpec, hSpec);

            //获取子控件的宽度
            int childWidth = child.getMeasuredWidth();
            //获取子控件的高度
            int childHeight = child.getMeasuredHeight();
            //获取子控件间的间距
            ViewGroup.MarginLayoutParams mp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();

            //如何换行，把每一行看作一个对象
            if (mUsedWidth + childWidth + mp.leftMargin + mp.rightMargin > mAvailableWidthSize) {
                //换行
                mLines.add(mLine);//将当前行加入到行集合中
                //添加当前的宽度
                mLine.mLineWidth = mUsedWidth;

                //创建一个新行对象
                mLine = new Line();
                //因为是新的行，所以已经用过宽度置0
                mUsedWidth = 0;
            }

            //累加已用的宽度
            mUsedWidth += childWidth + mp.leftMargin + mp.rightMargin;
            //当前子控件加入到行中
            mLine.addView(child);

            //最后一行时，没有摆满，或刚刚摆满,需要将此对象加入到行集合中
            if (i == count - 1) {
                //添加当前的宽度
                mLine.mLineWidth = mUsedWidth;
                mLines.add(mLine);
            }

        }
        Log.i("tag", mLines.size() + "");
        //修改一下父控件的宽高
        //父控件的高度=所有的行高+getPaddingTop() + getPaddingBottom()
        int newHeight = getPaddingTop() + getPaddingBottom();
        int lineSize = mLines.size();
        for (int i = 0; i < lineSize; i++) {
            //获取每一个行对象
            Line line = mLines.get(i);
            newHeight += line.mLineHeight;
        }
        //定义父控件的宽度的测量规则
        widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(widthSize, View.MeasureSpec.EXACTLY);
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(newHeight, View.MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //代表每一行的对象
    class Line {
        //行高
        public int mLineHeight = 0;
        public int mLineWidth = 0;

        //保存行中所有view的集合
        private List<View> mViewList = new ArrayList<>();

        //添加行中的子view
        public void addView(View v) {
            //计算行高 = 行中高度最大的控件的高度
            int childWidth = v.getMeasuredWidth();

            //获取子view高度
            int childHeight = v.getMeasuredHeight();
            //上下的间距
            ViewGroup.MarginLayoutParams mp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();

            mLineHeight = Math.max(childHeight + mp.topMargin + mp.bottomMargin, mLineHeight);

            //计算已用的宽度
            //mLineWidth += childWidth + mp.rightMargin + mp.leftMargin;

            mViewList.add(v);
        }

        public void onLayout(int left, int top) {
            //获取所有子view
            int size = mViewList.size();
            for (int i = 0; i < size; i++) {
                //获取到每一个view
                View child = mViewList.get(i);
                //获取子view宽高
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                //获取子view的间距
                ViewGroup.MarginLayoutParams mp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();

                //将空白区域平分给当前行所有的子view
                //计算空白区域的大小
                int paddding = (mAvailableWidthSize - mLineWidth) / size;

                //将子控件的宽高重新定义一下
                int newWidth = width + paddding;
                int wSpec = View.MeasureSpec.makeMeasureSpec(newWidth, View.MeasureSpec.EXACTLY);
                int hSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

                child.measure(wSpec, hSpec);

                //获取新的宽高
                width = child.getMeasuredWidth();
                height = child.getMeasuredHeight();

                //让行中的view居中显示
                int verPadding = (mLineHeight - height - mp.topMargin - mp.bottomMargin) / 2;

                int l = left + mp.leftMargin;
                int t = top + mp.topMargin + verPadding;
                int r = l + width ;
                int b = t + height ;
                //摆放此view
                child.layout(l, t, r, b);
                //左边的位置不断右移
                left += mp.leftMargin + width + mp.rightMargin;
            }

        }

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //遍历所有的行，传入左边和上边的值
        int size = mLines.size();
        int left = getPaddingLeft();
        int top = getPaddingTop();

        for (int i = 0; i < size; i++) {
            //获取每一行
            Line line = mLines.get(i);
            line.onLayout(left, top);
            //每一行的top值不断的变化 = 行高的累加
            top += line.mLineHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        //返回子控件的LayoutParams
        MarginLayoutParams mp = new MarginLayoutParams(getContext(), attrs);
        return mp;
    }
}
