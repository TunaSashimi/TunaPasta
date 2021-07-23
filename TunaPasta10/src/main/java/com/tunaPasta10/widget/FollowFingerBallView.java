/**
 * 
 */
package com.tunaPasta10.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class FollowFingerBallView extends View {
	public float currentX = 40;
	public float currentY = 50;

	public FollowFingerBallView(Context context) {
		super(context);
	}

	public FollowFingerBallView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public FollowFingerBallView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 创建画笔
		Paint p = new Paint();
		// 设置画笔的颜色
		p.setColor(Color.CYAN);
		// 绘制一个小圆（作为小球）
		canvas.drawCircle(currentX, currentY, 80, p);
	}
}
