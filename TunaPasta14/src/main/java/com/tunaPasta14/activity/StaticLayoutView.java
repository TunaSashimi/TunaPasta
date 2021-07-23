package com.tunaPasta14.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;

import com.tunaPasta14.R;

public class StaticLayoutView extends View{

	TextPaint textPaint = null;
	StaticLayout staticLayout = null;
	Paint paint = null;
	int width = 150;
	int height = 0;
	String txt = null;
	boolean running = false;

	public StaticLayoutView(Context context){
		super(context);
		textPaint = new TextPaint();
		textPaint.setAntiAlias(true);
		textPaint.setTextSize(36);
		txt = getResources().getString(R.string.static_layout_text);
		staticLayout = new StaticLayout(txt, textPaint, width, Alignment.ALIGN_NORMAL, 1, 0, false);
		height = staticLayout.getHeight();
		paint = new Paint();
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.RED);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				running = !running;
				if (running) {
					new Thread(){
						public void run(){
							while (running) {
								width++;
								staticLayout = new StaticLayout(txt, textPaint, width, Alignment.ALIGN_NORMAL, 1, 0, false);
								height = staticLayout.getHeight();
								postInvalidate();
								try {
									Thread.sleep(50);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								if (width >= 500) {
									width = 50;
								}
							}
						};
					}.start();
				}
				break;
			default:
				break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.WHITE);
		canvas.translate(20, 20);
		staticLayout.draw(canvas);
		canvas.drawRect(0, 0, width, height, paint);
		super.onDraw(canvas);
	}

}
