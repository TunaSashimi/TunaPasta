package com.tunaPasta15.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class LinkMoveListView extends ListView {
	public boolean disableTouch = false;
	private LinkMoveListViewTest demo;

	public LinkMoveListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public LinkMoveListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.demo = ((LinkMoveListViewTest) context);
	}

	public LinkMoveListView(Context context) {
		super(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (ev.getAction() == 0) {
			demo.startTouch(this);
		}
		if (ev.getAction() == 3 && ev.getHistorySize() == 0) {
			demo.startTouch(this);
		}

		return super.onTouchEvent(ev);
	}
}
