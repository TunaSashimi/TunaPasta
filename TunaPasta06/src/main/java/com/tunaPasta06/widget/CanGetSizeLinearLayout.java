package com.tunaPasta06.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public abstract class CanGetSizeLinearLayout extends LinearLayout implements
		CommonViewQuality {
	public CanGetSizeLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ViewSizeAndPosition getViewSizeAndPosition() {
		int width = getRight() - getLeft();
		int height = getBottom() - getTop();
		ViewSizeAndPosition vsp = new ViewSizeAndPosition();
		vsp.setWidth(width);
		vsp.setHeight(height);
		vsp.setLeft(getLeft());
		vsp.setTop(getTop());
		vsp.setRight(getRight());
		vsp.setBottom(getBottom());
		return vsp;
	}
}