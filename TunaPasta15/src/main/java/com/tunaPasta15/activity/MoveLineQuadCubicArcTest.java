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

public class MoveLineQuadCubicArcTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new MoveLineQuadCubicArcView(this));
    }

    class MoveLineQuadCubicArcView extends View {
        Paint mPaint = new Paint();
        Path mPath = new Path();

        public MoveLineQuadCubicArcView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setTextSize(40);
            mPaint.setColor(Color.WHITE);

            canvas.drawText("moveTo 不会进行绘制，只用于移动移动画笔", 50, 50, mPaint);
            canvas.drawText("lineTo 用于进行直线绘制", 50, 100, mPaint);
            canvas.drawText("arcTo 用于绘制弧线（实际是截取圆或椭圆的一部分）", 50, 150, mPaint);
            canvas.drawText("quadTo 用于绘制圆滑曲线，即贝塞尔曲线", 50, 200, mPaint);
            canvas.drawText("cubicTo 比 quadTo 多了一个控制点", 50, 250, mPaint);

            mPath.moveTo(100, 300);
            mPath.lineTo(400, 300);
            mPath.arcTo(new RectF(500, 500, 800, 800), 0, 90);
            mPath.quadTo(700, 700, 600, 600);//x1,y1是控制位 x2,y2是目标位
			mPath.cubicTo(300, 300, 350, 350, 400, 400);

            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
    }
}
