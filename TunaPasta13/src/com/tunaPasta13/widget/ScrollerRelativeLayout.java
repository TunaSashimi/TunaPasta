package com.tunaPasta13.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.Scroller;

public class ScrollerRelativeLayout extends RelativeLayout {

    private boolean isScrolled;
    private Scroller mScroller;

    public ScrollerRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mScroller = new Scroller(context);
    }

    // @Override
    // public void computeScroll() {
    // //
    // 如果正处于滚动过程中（即已经调用过mScroller.startScroll()且滚动还未完成），computeScrollOffset()将会返回false。
    // if (mScroller.computeScrollOffset()) {
    // scrollTo(mScroller.getCurrX(), 0);
    // // 只要滚动没有完成，无条件地触发随后的滚动，即调用各个子View的computeScroll()函数：
    // postInvalidate();
    // }
    // }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int oldX = getScrollX();
            int oldY = getScrollY();
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
            if (oldX != currX || oldY != currY) {
                // 完成实际的滚动。
                scrollTo(currX, currY);
            }
            postInvalidate();
        }
    }

    public void beginScroll() {
        if (isScrolled) {
            mScroller.startScroll(0, 0, 0, 0, 1000);
            isScrolled = false;
        } else {
            // startX 水平方向滚动的偏移值，以像素为单位。正值表明滚动将向左滚动
            // startY 垂直方向滚动的偏移值，以像素为单位。正值表明滚动将向上滚动
            // dx 水平方向滑动的距离，正值会使滚动向左滚动
            // dy 垂直方向滑动的距离，正值会使滚动向上滚动
            // 第一个参数是起始移动的x坐标值，第二个是起始移动的y坐标值，第三个第四个参数都是移到某点的坐标值，
            mScroller.startScroll(-500, 0, -500, 0, 1000);
            isScrolled = true;
        }
        invalidate();
    }
}
