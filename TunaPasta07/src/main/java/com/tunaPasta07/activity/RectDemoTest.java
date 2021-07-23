package com.tunaPasta07.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class RectDemoTest extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SimpleView(this));
    }

    private class SimpleView extends View {

        public SimpleView(Context context) {
            super(context);
        }

        protected void onDraw(android.graphics.Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Style.STROKE);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            canvas.drawRect(new RectF(10, 10, 300, 100), paint);
            paint.setStyle(Style.FILL);
            paint.setColor(Color.YELLOW);
            canvas.drawRect(10, 150, 300, 200, paint);
            paint.setStyle(Style.FILL_AND_STROKE);
            paint.setColor(Color.BLUE);
            canvas.drawRect(new Rect(10, 250, 300, 300), paint);

            canvas.drawRoundRect(new RectF(10, 350, 300, 450), 10, 10, paint);
        }
    }
}