package com.tunaPasta15.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MoveLineQuadCubicArcToDiffence extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(new MoveLineQuadCubicArcToDiffenceView(this));
	}

	class MoveLineQuadCubicArcToDiffenceView extends View{
		Paint mPaint = new Paint();
		Path mPath = new Path();

		public MoveLineQuadCubicArcToDiffenceView(Context context){
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas){
			
			mPaint.setStyle(Paint.Style.FILL);
			mPaint.setTextSize(28);
			mPaint.setColor(Color.WHITE);
			
			canvas.drawText("moveTo 不会进行绘制，只用于移动移动画笔。", 50, 50, mPaint);
			canvas.drawText("lineTo 用于进行直线绘制。", 50, 100, mPaint);
			canvas.drawText("quadTo 用于绘制圆滑曲线，即贝塞尔曲线。", 50, 150, mPaint);
			canvas.drawText("cubicTo 同样是用来实现贝塞尔曲线的。", 50, 200, mPaint);
			canvas.drawText("cubicTo 和 quadTo 比多了一个控制点", 50, 250, mPaint);
			canvas.drawText("arcTo 用于绘制弧线（实际是截取圆或椭圆的一部分）。", 50, 300, mPaint);
			
			mPath.moveTo(50,400);
			mPath.lineTo(300, 400);
			mPath.quadTo(500,500,500, 600);
			mPath.cubicTo(450, 600, 600, 650, 600, 700);
			
			RectF rectF=new RectF(500,500,800,800);
			mPath.arcTo(rectF, 270, 180);
			
			mPath.close();
			canvas.drawPath(mPath, mPaint);
			
		}
	}
}
