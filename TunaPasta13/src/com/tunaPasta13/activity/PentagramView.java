package com.tunaPasta13.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.tunaPasta13.R;

public class PentagramView extends View {
    private final static float DEGREE = 36; // 五角星角度
    private float radius = 20;
    private int color = 0xff0000;
    public PentagramView(Context context) {
        super(context);
    }
    public PentagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PentagramView);
        this.color = a.getColor(R.styleable.PentagramView_color, color);
        this.radius = a.getFloat(R.styleable.PentagramView_radius, radius);// 中间五边形的边长 200.0
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        Path path = new Path();

        paint.setAntiAlias(true);

        float radian = (float) Math.toRadians(DEGREE);//36

        float radius_in = (float) (radius * Math.sin(radian / 2) / Math.cos(radian)); //内五边形的半径

        path.moveTo((float) (radius * Math.cos(radian / 2)), 0);//五角星的上顶点

        path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in * Math.sin(radian)), (float) (radius - radius * Math.sin(radian / 2)));//五角星的右一

        path.lineTo((float) (radius * Math.cos(radian / 2) * 2), (float) (radius - radius * Math.sin(radian / 2)));//五角星的最右

        path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in * Math.cos(radian / 2)), (float) (radius + radius_in * Math.sin(radian / 2)));//五角星的最右下面一个

        path.lineTo((float) (radius * Math.cos(radian / 2) + radius * Math.sin(radian)), (float) (radius + radius * Math.cos(radian)));//五角星的最右下

        path.lineTo((float) (radius * Math.cos(radian / 2)), (radius + radius_in));

        path.lineTo((float) (radius * Math.cos(radian / 2) - radius * Math.sin(radian)), (float) (radius + radius * Math.cos(radian)));

        path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in * Math.cos(radian / 2)), (float) (radius + radius_in * Math.sin(radian / 2)));

        path.lineTo(0, (float) (radius - radius * Math.sin(radian / 2)));

        path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in * Math.sin(radian)), (float) (radius - radius * Math.sin(radian / 2)));

        path.close();

        paint.setColor(this.color);
        canvas.drawPath(path, paint);
    }

}
