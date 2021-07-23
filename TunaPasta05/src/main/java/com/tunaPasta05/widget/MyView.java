package com.tunaPasta05.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tunaPasta05.R;


public class MyView extends TextView {
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
		
		int textColor = typedArray.getColor(R.styleable.MyView_textColor, 0XFFFFFFFF);
		float textSize = typedArray.getDimension(R.styleable.MyView_textSize, 36);
		String mString = typedArray.getString(R.styleable.MyView_title);
		
		setText(mString);
		setTextSize(textSize);
		setTextColor(textColor);
	}
}
