package com.tunaPasta19.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.tunaPasta19.R;

public class SlidingMenuLinearLayout extends LinearLayout {
	
	private Scroller mScroller = null;
	public boolean isCover = true;
	private float x=0;
	
	public SlidingMenuLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		
//		这个类主要是支持view控件滑动，其实android很多可滑动的控件里面默认隐藏的就是这个类。
//		而且这个类没有进行实际的视图移动，当调用它的 startScroll()方法实际上只是为了在父类调用computeScroll()方法前开始动画，
//		也就是说这个类实际上就是相当于一个代理，值是 为了给后面视图移动添加一些动画效果。所以单独调用startScroll()而不重写computeScroll()方法是不会看到任何效果的。这两者 必须配合使用,才能有移动的时候的动画效果。
		
		mScroller = new Scroller(context);
		setLongClickable(true);//注释掉就不能实现拖动了
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//记录按下去时候的x坐标跟抬起来时候的做比较
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x=event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		//如果是向右滑动并且是覆盖状态	
		case MotionEvent.ACTION_UP:
			if(event.getX()>x && isCover){
				scrollToTarget();
			}else if(event.getX()<x && x > getWidth()*44/54 && !isCover){
				scrollToTarget();
			}
			break;
		}
		return super.onTouchEvent(event);
	}
	// 调用此方法滚动到目标位置
	public void scrollToTarget() {
		int fx=(int) (getWidth()*0.7);//移动的区域为总区域的70%
		if (!isCover) {
			setClick(true);
			mScroller.startScroll(-fx, 0, fx, 0, 1000);
			isCover = true;

		} else {
			setClick(false);
			mScroller.startScroll(0, 0, -fx, 0, 1000);
			isCover = false;
		}
		invalidate();// 刷新
	}

	public void setClick(boolean isClick) {
		
		findViewById(R.id.main_zjdh).setClickable(isClick);
		findViewById(R.id.main_zjt).setClickable(isClick);
		findViewById(R.id.main_zjzx).setClickable(isClick);

		findViewById(R.id.main_zjjy).setClickable(isClick);
		findViewById(R.id.main_zjjd).setClickable(isClick);
		findViewById(R.id.main_zjtj).setClickable(isClick);

		findViewById(R.id.main_zjzd).setClickable(isClick);
		findViewById(R.id.main_zjgef).setClickable(isClick);
		findViewById(R.id.main_jqqd).setClickable(isClick);

	}
	
	
//	为了易于控制滑屏控制，Android框架提供了 computeScroll()方法去控制这个流程。在绘制View时，会在draw()过程调用该 方法。
	@Override
	public void computeScroll() {
		// 先判断mScroller滚动是否完成
		if (mScroller.computeScrollOffset()) {
			// 这里调用View的scrollTo()完成实际的滚动
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			// 必须调用该方法，否则不一定能看到滚动效果
			postInvalidate();
		}
		super.computeScroll();
	}
}
