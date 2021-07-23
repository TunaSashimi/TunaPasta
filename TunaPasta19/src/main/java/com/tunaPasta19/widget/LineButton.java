package com.tunaPasta19.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

public class LineButton extends Button{
	private Paint mPaint;
	public LineButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(Color.BLACK);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 画底线
		canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,this.getHeight() - 1, mPaint);
	}
}
