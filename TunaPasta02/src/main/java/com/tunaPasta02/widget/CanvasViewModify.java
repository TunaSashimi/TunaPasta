package com.tunaPasta02.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.tunaPasta02.R;


public class CanvasViewModify extends View {
    public CanvasViewModify(Context context) {
        super(context);
    }

    public CanvasViewModify(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.FILL);

        paint.setColor(Color.parseColor("#ff0000"));

        int triangle_picture_width = getResources().getDimensionPixelSize(R.dimen.triangle_picture_width);
        int triangle_picture_height = getResources().getDimensionPixelSize(R.dimen.triangle_picture_height);

        Path path = new Path();
        path.moveTo(triangle_picture_width / 2, 0);
        path.lineTo(0, triangle_picture_height);
        path.lineTo(triangle_picture_width, triangle_picture_height);
        path.close();

        canvas.drawPath(path, paint);
    }
}