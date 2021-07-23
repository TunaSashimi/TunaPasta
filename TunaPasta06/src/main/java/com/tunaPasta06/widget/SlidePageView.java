package com.tunaPasta06.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

import com.tunaPasta06.tool.ComponentTool;

public class SlidePageView extends CanGetSizeLinearLayout {
	private Scroller mScroller;
	private int currPagePosition = 0;

	private int scrollToPositionX = 0;

	private int scrollToPositionY = 0;

	private boolean allowMoveTouchScroll = true;

	private boolean allowThrowTouchScroll = true;
	private OnPageViewChangedListener onPageViewChangedListener;
	private VelocityTracker mVelocityTracker;
	private float mLastMotionX;

	public int getScrollToPositionX() {
		return this.scrollToPositionX;
	}

	public int getScrollToPositionY() {
		return this.scrollToPositionY;
	}

	public boolean isAllowMoveTouchScroll() {
		return this.allowMoveTouchScroll;
	}

	public void setAllowMoveTouchScroll(boolean allowMoveTouchScroll) {
		this.allowMoveTouchScroll = allowMoveTouchScroll;
	}

	public boolean isAllowThrowTouchScroll() {
		return this.allowThrowTouchScroll;
	}

	public void setAllowThrowTouchScroll(boolean allowThrowTouchScroll) {
		this.allowThrowTouchScroll = allowThrowTouchScroll;
	}

	public int getCurrPagePosition() {
		return this.currPagePosition;
	}

	public void setCurrPagePosition(int currPagePosition) {
		this.currPagePosition = currPagePosition;
	}

	public void setOnPageViewChangedListener(
			OnPageViewChangedListener onPageViewChangedListener) {
		this.onPageViewChangedListener = onPageViewChangedListener;
	}

	public SlidePageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		this.mScroller = new Scroller(context);
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		for (int i = 0; i < getChildCount(); i++) {
			View childView = getChildAt(i);
			if (childView.getMeasuredWidth() <= 0)
				childView.measure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		jumpToAppointCurrPage();
	}

	public void setScrollToPosition() {
		View currPageView = getChildAt(getCurrPagePosition());
		ViewSizeAndPosition currPageViewSizeAndPosition = ComponentTool
				.getViewSizeAndPosition(currPageView);
		ViewSizeAndPosition screenSizeAndPosition = ComponentTool
				.getScreenSizeAndPosition((Activity) getContext());
		int gap = (screenSizeAndPosition.getWidth() - currPageViewSizeAndPosition
				.getWidth()) / 2;
		this.scrollToPositionX = (currPageViewSizeAndPosition.getLeft() - gap);
	}

	public void jumpToAppointCurrPage() {
		setScrollToPosition();
		scrollTo(this.scrollToPositionX, this.scrollToPositionY);
	}

	public void computeScroll() {
		if (this.mScroller.computeScrollOffset()) {
			this.scrollToPositionX = this.mScroller.getCurrX();

			scrollTo(this.scrollToPositionX, this.scrollToPositionY);
			postInvalidate();
		}
	}

	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
//		float y = event.getY();
		switch (event.getAction()) {
		case 0:
			if (this.mVelocityTracker == null) {
				this.mVelocityTracker = VelocityTracker.obtain();
				this.mVelocityTracker.addMovement(event);
			}
			if (!this.mScroller.isFinished()) {
				this.mScroller.abortAnimation();
			}
			this.mLastMotionX = x;
			break;
		case 2:
			if (this.mVelocityTracker != null) {
				this.mVelocityTracker.addMovement(event);
			}
			int deltaX = (int) (this.mLastMotionX - x);
			int deltaY = 0;
			if (isAllowMoveTouchScroll())
				scrollBy(deltaX, deltaY);
			this.mLastMotionX = x;
			break;
		case 1:
			Log.i("", "onTouchEvent  ACTION_UP");

			int velocityX = 0;
			if ((isAllowThrowTouchScroll()) && (this.mVelocityTracker != null)) {
				this.mVelocityTracker.addMovement(event);
				this.mVelocityTracker.computeCurrentVelocity(1000);
				velocityX = (int) this.mVelocityTracker.getXVelocity();
			}

			if ((velocityX > 600) && (getCurrPagePosition() > 0)) {
				setCurrPagePosition(getCurrPagePosition() - 1);
				CurrPageScrollToScreenCenter();
			} else if ((velocityX < -600)
					&& (getCurrPagePosition() < getChildCount() - 1)) {
				setCurrPagePosition(getCurrPagePosition() + 1);
				CurrPageScrollToScreenCenter();
			} else {
				accordingToTouchPositionToComputerCurrPagePosition();
				CurrPageScrollToScreenCenter();
			}

			if (this.mVelocityTracker != null) {
				this.mVelocityTracker.recycle();
				this.mVelocityTracker = null;
			}

			if (this.onPageViewChangedListener == null)
				break;
			this.onPageViewChangedListener.OnPageViewChanged(
					this.currPagePosition, getChildAt(this.currPagePosition));
		}

		return true;
	}

	private void accordingToTouchPositionToComputerCurrPagePosition() {
		ViewSizeAndPosition screenSizeAndPosition = ComponentTool
				.getScreenSizeAndPosition((Activity) getContext());
		int screenCenterX = screenSizeAndPosition.getWidth() / 2;

		int childViewNum = getChildCount();
		for (int i = 0; i < childViewNum; i++) {
			View childView = getChildAt(i);
			ViewSizeAndPosition childViewSizeAndPosition = ComponentTool
					.getViewSizeAndPosition(childView);
			if ((childViewSizeAndPosition.getLeft() - getScrollX() > screenCenterX)
					|| (childViewSizeAndPosition.getRight() - getScrollX() <= screenCenterX))
				continue;
			this.currPagePosition = i;
			return;
		}
	}

	public void CurrPageScrollToScreenCenter() {
		View currChildView = getChildAt(this.currPagePosition);

		ViewSizeAndPosition screenSizeAndPosition = ComponentTool
				.getScreenSizeAndPosition((Activity) getContext());
		int screenCenterX = screenSizeAndPosition.getWidth() / 2;
		Point currChildViewCenterPoint = ComponentTool
				.getViewCenterPoint(currChildView);
		int deltaX = -(getScrollX() - currChildViewCenterPoint.x + screenCenterX);
		int deltaY = 0;
		this.mScroller.startScroll(getScrollX(), this.scrollToPositionY,
				deltaX, deltaY, Math.abs(2 * deltaX));
		invalidate();
	}

	public static abstract interface OnPageViewChangedListener {
		public abstract void OnPageViewChanged(int paramInt, View paramView);
	}
}