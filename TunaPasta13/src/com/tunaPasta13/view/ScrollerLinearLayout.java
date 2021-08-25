package com.tunaPasta13.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class ScrollerLinearLayout extends LinearLayout {
    private Scroller mScroller;
    private int actionDownY;

    public ScrollerLinearLayout(Context context) {
        this(context, null);
    }

    public ScrollerLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
        setLongClickable(true);
        mScroller = new Scroller(context);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int oldX = getScrollX();
            int oldY = getScrollY();
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
            if (oldX != currX || oldY != currY) {
                scrollTo(currX, currY);
            }
            postInvalidate();
        }
    }

    // 调用此方法滚动到目标位置
    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }

    // 调用此方法设置滚动的相对偏移
    public void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionDownY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float eventY = event.getY();
                int dis = (int) ((actionDownY - eventY) / 2);
                smoothScrollBy(0, dis);
                break;
            case MotionEvent.ACTION_UP:
                smoothScrollTo(0, 0);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

}