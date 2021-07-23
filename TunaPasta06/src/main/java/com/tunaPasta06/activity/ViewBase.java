package com.tunaPasta06.activity;

import android.content.Context;
import android.view.View;

public class ViewBase extends View{

	public int width;
	public int height;
	
	public ViewBase(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		height = View.MeasureSpec.getSize(heightMeasureSpec); 
		width = View.MeasureSpec.getSize(widthMeasureSpec); 
		setMeasuredDimension(width,height); 
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
