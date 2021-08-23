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
        paint.setColor(Color.BLUE);
        // 绘制正方形
//        canvas.drawRect(795.75f, -58.25f, 1208.25f, 354.25f, paint);
        canvas.drawRect(0, 0, dpToPx(130), dpToPx(130), paint);
    }

    //
    public  float dpToPx(int dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()
        );
    }
}
