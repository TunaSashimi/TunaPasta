package com.tunaPasta10.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class BezierCurveView extends View {

	private Paint paint;
	private Path path;

	public BezierCurveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BezierCurveView(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.RED);
		paint.setStrokeWidth(1);

		path = new Path();
	}

	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);

		int halfGraphStartX = 395;
		int halfGraphEndX = 405;
		int halfGraphEndY = 60;
		for (int i = 0; i < 20; i++) {
			path.moveTo(halfGraphStartX, 0);
			path.cubicTo(halfGraphStartX, 0, (halfGraphStartX + halfGraphEndX) / 2, halfGraphEndY, halfGraphEndX, 0);
			halfGraphStartX -= 5;
			halfGraphEndY += 10;
			halfGraphEndX += 5;
		}

		int diamondCircleX = 100;
		int diamondCircleY = 400;
		int diamondOffsetX = 4;
		int diamondOffsetY = 10;
		for (int i = 0; i < 20; i++) {
			path.moveTo(diamondCircleX - diamondOffsetX, diamondCircleY);
			path.lineTo(diamondCircleX, diamondCircleY + diamondOffsetY);
			path.lineTo(diamondCircleX + diamondOffsetX, diamondCircleY);
			path.lineTo(diamondCircleX, diamondCircleY - diamondOffsetY);
			path.close();
			diamondOffsetX += 4;
			diamondOffsetY += 8;
		}

		int bezierCircleX = 400;
		int bezierCircley = 400;
		int deviationX = 4;
		int deviationtY = 10;
		int controlX = 60;
		int controlY = 60;
		for (int i = 0; i < 30; i++) {
			path.moveTo(bezierCircleX - deviationX, bezierCircley);
			path.quadTo((bezierCircleX - deviationX + bezierCircleX) / 2
					- controlX, (bezierCircley + bezierCircley + deviationtY)
					/ 2 + controlY, bezierCircleX, bezierCircley + deviationtY);
			path.quadTo((bezierCircleX + bezierCircleX + deviationX) / 2
					+ controlX, (bezierCircley + deviationtY + bezierCircley)
					/ 2 + controlY, bezierCircleX + deviationX, bezierCircley);
			path.quadTo((bezierCircleX + deviationX + bezierCircleX) / 2
					+ controlX, (bezierCircley + bezierCircley - deviationtY)
					/ 2 - controlY, bezierCircleX, bezierCircley - deviationtY);
			path.quadTo((bezierCircleX + bezierCircleX - deviationX) / 2
					- controlX, (bezierCircley - deviationtY + bezierCircley)
					/ 2 - controlY, bezierCircleX - deviationX, bezierCircley);
			deviationX += 4;
			deviationtY += 7;
		}

		// 左下的图形圆心
		float bezierOvalX = 110;
		float bezierOvalY = 820;
		
		//左下的图形宽度的一半,高度的一半
		float deviationOvalX = 4;
		float deviationOvalY = 20;
		//宽度的一半,高度的一般的变动量
		float deviationOvalXOffset = 4;
		float deviationOvalYOffset = 4;
		
		//左下的图形拉伸距离的宽度,高度
		float controlOvalX = 4;
		float controlOvalY = 16;
		//拉伸距离的宽度,高度的变动量
		float controlOvalXOffset = 3;
		float controlOvalYOffset = 4;
		
		int cyclesNum=5;
		//
		paint.setColor(Color.BLUE);
		canvas.drawRect(bezierOvalX-(deviationOvalX+(cyclesNum-1)*(deviationOvalXOffset+controlOvalXOffset)), 
				bezierOvalY-(deviationOvalY+(cyclesNum-1)*(deviationOvalYOffset+controlOvalYOffset)), 
				bezierOvalX+(deviationOvalX+(cyclesNum-1)*(deviationOvalXOffset+controlOvalXOffset)), 
				bezierOvalY+(deviationOvalY+(cyclesNum-1)*(deviationOvalYOffset+controlOvalYOffset)), paint);
		//
		
		for (int i = 0; i < cyclesNum; i++) {
			if (i == 0) {
				//中间段曲线
				path.moveTo(bezierOvalX, bezierOvalY + 15);
				path.lineTo(bezierOvalX + 2, bezierOvalY + 2);
			}
			path.quadTo(bezierOvalX, bezierOvalY - deviationOvalY - controlOvalY, 
					bezierOvalX + deviationOvalX, bezierOvalY - deviationOvalY);// 右上
			
			path.quadTo(bezierOvalX + deviationOvalX + controlOvalX, bezierOvalY, 
					bezierOvalX + deviationOvalX, bezierOvalY + deviationOvalY);// 右下
			
			path.quadTo(bezierOvalX, bezierOvalY + deviationOvalY + controlOvalY, 
					bezierOvalX - deviationOvalX, bezierOvalY + deviationOvalY);// 左下
			
			path.quadTo(bezierOvalX - deviationOvalX - controlOvalX, bezierOvalY, 
					bezierOvalX - deviationOvalX, bezierOvalY - deviationOvalY);// 左上

			deviationOvalX += deviationOvalXOffset;
			deviationOvalY += deviationOvalYOffset;

			controlOvalX += controlOvalXOffset;
			controlOvalY += controlOvalYOffset;
		}
		
		paint.setColor(Color.RED);
		canvas.drawPath(path, paint);
		
		// 右下的椭圆
		int ovalCircleX = 360;
		int ovalCircleY = 800;
		int ovalOffseyX = 7;
		int ovalOffseyY = 15;
		
		
		for (int i = 0; i < 10; i++) {
			RectF rectOval = new RectF(ovalCircleX - ovalOffseyX, ovalCircleY
					- ovalOffseyY, ovalCircleX + ovalOffseyX, ovalCircleY
					+ ovalOffseyY);
			canvas.drawOval(rectOval, paint);
			ovalOffseyX += 7;
			ovalOffseyY += 10;
		}

		
		
	}

}
