package com.tunaPasta06.widget;
import java.util.TimerTask;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

import com.tunaPasta06.activity.GalleryAutoChangeTest;
public class GuideGallery extends Gallery {
	private GalleryAutoChangeTest m_iact;
	public GuideGallery(Context context) {
		super(context);
	}
	public GuideGallery(Context context,AttributeSet attrs) {
		super(context, attrs);
	}
	public GuideGallery(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}
	public void setImageActivity(GalleryAutoChangeTest iact){
		m_iact = iact;
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		new java.util.Timer().schedule(new TimerTask() {
			public void run() {
				m_iact.timeFlag = false;
				this.cancel();
			}
		}, 5000);
		int kEvent;
		if (isScrollingLeft(e1, e2)) { // Check if scrolling left
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		} else { // Otherwise scrolling right
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(kEvent, null);
		if (this.getSelectedItemPosition() == 0)
			this.setSelection(m_iact.urls.size());
		return true;

	}
	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2){
        return e2.getX() > e1.getX();
    }
	
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		m_iact.timeTaks.timeCondition = false;
		return super.onScroll(e1, e2, distanceX, distanceY);
	}

}
