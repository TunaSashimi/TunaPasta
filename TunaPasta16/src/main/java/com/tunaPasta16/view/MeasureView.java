package com.tunaPasta16.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

public class MeasureView extends View {

    public MeasureView(Context context) {
        super(context);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        //
//        canvas.drawRect(795.75f, -58.25f, 1208.25f, 354.25f, paint);

        /**
         * 左上角顶点轨迹和边缘轨迹区别,右和下加了宽高
         */

        // 绘制左上角顶点轨迹
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, dpToPx(130), dpToPx(130), paint);

        // 绘制中心轨迹
        paint.setColor(Color.BLUE);
        canvas.drawRect(0 + dpToPx(32), 0 + dpToPx(32), dpToPx(130) + dpToPx(32), dpToPx(130) + dpToPx(32), paint);

        // 绘制边缘轨迹
        paint.setColor(Color.RED);
        canvas.drawRect(0, 0, dpToPx(130) + dpToPx(65), dpToPx(130) + dpToPx(65), paint);


    }

    //
    public float dpToPx(int dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()
        );
    }
}
