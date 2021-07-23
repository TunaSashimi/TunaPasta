package com.tunaPasta06.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;

public class PreparingMeasureButton extends Button {

	public PreparingMeasureButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public PreparingMeasureButton(Context context) {
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