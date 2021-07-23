package com.tunaPasta07.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;

public class LinesDemoTest extends Activity {
    @Override
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
            float[] fl = new float[]{0, 200, 150, 200, 0, 250, 150, 200};

            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Style.STROKE);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);

            canvas.drawLine(10, 10, 150, 150, paint);

            canvas.drawLines(fl, 0, fl.length, paint);

            float[] fl1 = new float[]{10, 300, 250, 300, 30, 400, 200, 400};

            paint.setStrokeWidth(10);
            canvas.drawLines(fl1, paint);
        }
    }
}