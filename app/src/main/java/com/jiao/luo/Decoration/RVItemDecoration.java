package com.jiao.luo.Decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public  class RVItemDecoration extends RecyclerView.ItemDecoration {
    private final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;
    int intrinsicHeight;

    public RVItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        intrinsicHeight = mDivider.getIntrinsicHeight();
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        drawHorizontalLine(c, parent, state);
        drawVerticalLine(c, parent, state);

    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
            //Log.d("wnw", left + " " + top + " "+right+"   "+bottom+" "+i);
        }
    }

    //画竖线
    public void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 横向是否需要画分隔线
     *
     * @param parent
     * @param pos
     * @return
     */
    private boolean isColumnDrawDiv(RecyclerView parent, int pos) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutParam = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutParam.getSpanCount();
            int spanIndex = gridLayoutParam.getSpanSizeLookup().getSpanIndex(pos, spanCount);
            if (spanIndex == 0 || spanIndex == spanCount - 1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 水平方向是否要画分隔线
     *
     * @param parent
     * @param pos
     * @return
     */
    private boolean isRowDrawDiv(RecyclerView parent, int pos) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int itemCount = parent.getAdapter().getItemCount();
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            int lastLineIndex = itemCount % spanCount == 0 ? (itemCount / spanCount) : (itemCount / spanCount + 1);
            if (pos >= spanCount * (lastLineIndex - 1)) {
                return true;
            }
        }
        return true;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
        boolean isDrawRow = isRowDrawDiv(parent, itemPosition);
        boolean isDrawColumn = isColumnDrawDiv(parent, itemPosition);
        int rightPadding = 0, bottomPadding = 0;
        if (isDrawRow) {
            bottomPadding = intrinsicHeight;
        }
        if (isDrawColumn) {
            rightPadding = intrinsicHeight;
        }
        outRect.set(0, 0, rightPadding, bottomPadding);
    }
}