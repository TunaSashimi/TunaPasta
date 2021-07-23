package com.tunaPasta09.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MyTextCenterTestView extends View {
	public MyTextCenterTestView(Context context) {
		super(context);
	}

	public MyTextCenterTestView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//super.onDraw(canvas);

		// 直接画比较难看
		// Rect targetRect = new Rect(50, 50, 1000, 200);
		// Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// paint.setStrokeWidth(3);
		// paint.setTextSize(80);
		// String testString = "测试：ijkJQKA:1234";
		// paint.setColor(Color.CYAN);
		// canvas.drawRect(targetRect, paint);
		// paint.setColor(Color.RED);
		// canvas.drawText(testString, targetRect.left, targetRect.bottom,
		// paint);

		canvas.drawColor(Color.WHITE);
		
	    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);  
	    paint.setStrokeWidth(3);  
	    paint.setTextSize(80);  
	    FontMetricsInt fmi = paint.getFontMetricsInt();  
	    String testString = "测试：ijkJQKA:1234";  
	    Rect bounds1 = new Rect();  
	    paint.getTextBounds("测", 0, 1, bounds1);  
	    Rect bounds2 = new Rect();  
	    paint.getTextBounds("测试：ijk", 0, 6, bounds2);  
	    
	    // 随意设一个位置作为baseline  
	    int x = 100;  
	    int y = 200;  
	    
	    // 把testString画在baseline上  
	    canvas.drawText(testString, x, y, paint);  
	    
	    // bounds1  
	    paint.setStyle(Style.STROKE);  // 画空心矩形  
	    canvas.save();  
	    canvas.translate(x, y);  // 注意这里有translate。getTextBounds得到的矩形也是以baseline为基准的  
	    paint.setColor(Color.GREEN);          
	    canvas.drawRect(bounds1, paint);  
	    canvas.restore();  
	    
	    // bounds2  
	    canvas.save();  
	    paint.setColor(Color.MAGENTA);  
	    canvas.translate(x, y);  
	    canvas.drawRect(bounds2, paint);  
	    canvas.restore();  
	    
	    // baseline  
	    paint.setColor(Color.RED);  
	    canvas.drawLine(x, y, 1024, y, paint);  
	    
	    // ascent  +
	    paint.setColor(Color.YELLOW);  
	    canvas.drawLine(x, y+fmi.ascent, 1024, y+fmi.ascent, paint);  
	    
	    // descent  
	    paint.setColor(Color.BLUE);  
	    canvas.drawLine(x, y+fmi.descent, 1024, y+fmi.descent, paint);  
	    
	    // top  
	    paint.setColor(Color.DKGRAY);  
	    canvas.drawLine(x, y+fmi.top, 1024, y+fmi.top, paint);  
	    
	    // bottom  
	    paint.setColor(Color.GREEN);  
	    canvas.drawLine(x, y+fmi.bottom, 1024, y+fmi.bottom, paint);  
		    
	}

}