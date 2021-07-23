package com.tunaPasta06.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;

import com.tunaPasta06.activity.ViewBase;

public class PieView extends ViewBase {

    int areaX = 1;
    int areaY = 1;
    int areaWidth;
    int areaHight;
    int colors[];
    int shade_colors[];
    int percent[];
    private int thickness = 0;

    public PieView(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param colors
     * @param shade_colors
     * @param percent
     */
    public PieView(Context context, int[] colors, int[] shade_colors, int[] percent) {
        super(context);
        this.colors = colors;
        this.shade_colors = shade_colors;
        this.percent = percent;
    }

    //设置厚度
    public void setThickness(int thickness) {
        this.thickness = thickness;
        areaY = thickness + 2;
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        areaWidth = width - 2;
        areaHight = height - 2;
        Paint paint = new Paint();
        paint.setColor(0xFFFFFF);
        paint.setStyle(Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1);
        for (int i = 0; i <= thickness; i++) {
            int tempAngle = 270;
            for (int j = 0; j < percent.length; j++) {
                paint.setColor(shade_colors[j]);
                canvas.drawArc(new RectF(areaX, areaY - i, areaX + areaWidth, areaHight - i), tempAngle, percent[j], true, paint);
                tempAngle += percent[j];
            }
            if (i == thickness) {
                for (int j = 0; j < percent.length; j++) {
//					paint.setColor(colors[j]);
                    paint.setStyle(j % 2 == 0 ? Style.STROKE : Style.FILL);
                    canvas.drawArc(new RectF(areaX, areaY - i, areaX + areaWidth, areaHight - i), tempAngle, percent[j], true, paint);
                    tempAngle += percent[j];
                }
            }
        }
    }
}
