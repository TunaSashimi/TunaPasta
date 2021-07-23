package com.tunaPasta06.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class PreparingMeasureImageView extends ImageView {

	public PreparingMeasureImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public PreparingMeasureImageView(Context context) {
		super(context);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		System.out.println("onMeasure 我被调用了"+System.currentTimeMillis());
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		System.out.println("onDraw 我被调用了"+System.currentTimeMillis());
	}

}