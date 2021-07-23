package com.tunaPasta09.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.tunaPasta09.R;


public class ThreePaintTypesMyDrawView extends View {
	public ThreePaintTypesMyDrawView(Context context) {
		super(context);
	}

	public ThreePaintTypesMyDrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint paint = new Paint();

		paint.setStyle(Paint.Style.FILL);
		
		paint.setColor(Color.WHITE);
		
		int triangle_picture_width=getResources().getDimensionPixelSize(R.dimen.activity_three_types);
		
		// 绘制矩形
		canvas.drawRect(0, 0, triangle_picture_width, triangle_picture_width, paint);
		
	}
}