package com.tunaPasta05.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View{
	Integer color[]={
			Color.YELLOW,Color.BLUE,Color.RED,
			Color.CYAN,Color.GREEN,Color.LTGRAY,
			Color.DKGRAY,Color.WHITE,Color.GRAY
	};
	public CustomView(Context c){
		super(c);
	}
	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint=new Paint();
		paint.setColor(Color.YELLOW);
		for(int i=0;i<9;i++){
			paint.setColor(color[i]);
			canvas.drawCircle(240, 300, 100-10*i, paint);
		}
		paint.setColor(Color.RED);
		paint.setTextSize(30);
		paint.setColor(Color.WHITE);
		canvas.drawText("我的自定义View", 100, 450, paint);
	}
}
