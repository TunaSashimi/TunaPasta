package com.tunaPasta02.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class DrawOnPathView extends View {
    final String DRAW_STR = "金枪鱼刺身";
    Path[] paths = new Path[5];
    Paint paint;

    public DrawOnPathView(Context context, AttributeSet attrs) {
        // 注,这里的构造方法一定要带2参数的不然findViewbyId的时候找不到
        super(context, attrs);
        paths[0] = new Path();
        paths[0].moveTo(0, 0);
        for (int i = 1; i <= 7; i++) {
            // 生成7个点，随机生成它们的Y座标。并将它们连成一条Path
            paths[0].lineTo(i * 30, (float) Math.random() * 30);
        }

        paths[1] = new Path();
        RectF rectF = new RectF(0, 0, 200, 120);
        paths[1].addOval(rectF, Path.Direction.CCW);

        paths[2] = new Path();
        // 第一个参数是你弧形的画的矩形范围,就是你的圆弧画满后是内切的,第二个参数是起始的角度,第三个参数是移动的度数
        paths[2].addArc(rectF, 0, 90);


        paths[3] = new Path();
        RectF rectFo1 = new RectF(0, 0, 200, 200);//圆的直径是200,半径是100
        RectF rectFo2 = new RectF(300, 0, 500, 200);//圆的直径是200,半径是100
        RectF rectFo3 = new RectF(600, 0, 800, 200);//圆的直径是200,半径是100

        //默认给它30度
//		paths[3].lineTo(0, 200-offsetY);
        paths[3].addArc(rectFo1, 15, 360 - 30);

        //偏移量offsetY=斜边*sin15
        float offsetY = (float) (100 * Math.sin(Math.toRadians(15)));
        //偏移量offsetx=斜边-斜边*cos15
        float offsetX = (float) (100 - 100 * Math.cos(Math.toRadians(15)));

        paths[3].lineTo(300 + offsetX, 100 - offsetY);
        paths[3].addArc(rectFo2, 180 + 15, 180 - 30);

        paths[3].lineTo(600 + offsetX, 100 - offsetY);
        paths[3].addArc(rectFo3, 180 + 15, 360 - 30);

        paths[3].lineTo(500 - offsetX, 100 + offsetY);
        paths[3].addArc(rectFo2, 0 + 15, 180 - 30);

        paths[3].lineTo(200 - offsetX, 100 + offsetY);

//		paths[3].close();

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();

        paths[4] = new Path();
        paths[4].moveTo(0, 600);
        paths[4].lineTo(screenWidth - 120, 600);
        paths[4].lineTo(screenWidth - 100, 580);
        paths[4].lineTo(screenWidth - 80, 600);
        paths[4].lineTo(screenWidth, 600);

        // 初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(1);
    }

    public DrawOnPathView(Context context) {
        super(context);
        paths[0] = new Path();
        paths[0].moveTo(0, 0);
        for (int i = 1; i <= 7; i++) {
            // 生成7个点，随机生成它们的Y座标。并将它们连成一条Path
            paths[0].lineTo(i * 30, (float) Math.random() * 30);
        }

        paths[1] = new Path();
        RectF rectF = new RectF(0, 0, 200, 120);
        paths[1].addOval(rectF, Path.Direction.CCW);

        paths[2] = new Path();
        // 第一个参数是你弧形的画的矩形范围,就是你的圆弧画满后是内切的,第二个参数是起始的角度,第三个参数是移动的度数
        paths[2].addArc(rectF, 60, 180);

        // 初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.TRANSPARENT);

        canvas.translate(40, 40);
        // 设置从右边开始绘制（右对齐）
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextSize(20);
        // 绘制路径
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(paths[0], paint);
        // 沿着路径绘制一段文本。
        paint.setStyle(Paint.Style.FILL);
        canvas.drawTextOnPath(DRAW_STR, paths[0], -8, 20, paint);// 字符串,路径,水平偏移,垂直偏移

        // 画布下移60
        canvas.translate(0, 60);
        // 绘制路径
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(paths[1], paint);
        // 沿着路径绘制一段文本。
        paint.setStyle(Paint.Style.FILL);
        canvas.drawTextOnPath(DRAW_STR, paths[1], -20, 20, paint);

        // 画布下移120
        canvas.translate(0, 120);
        // 绘制路径
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(paths[2], paint);
        // 沿着路径绘制一段文本。
        paint.setStyle(Paint.Style.FILL);
        canvas.drawTextOnPath(DRAW_STR, paths[2], -10, 20, paint);

        canvas.translate(0, 120);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(paths[3], paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(paths[4], paint);
    }
}