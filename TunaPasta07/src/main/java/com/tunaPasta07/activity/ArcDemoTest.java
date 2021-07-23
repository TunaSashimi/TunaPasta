package com.tunaPasta07.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class ArcDemoTest extends Activity {
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
            canvas.drawArc(new RectF(100, 100, 300, 300), 0, -90, true, paint);

            paint.setStyle(Style.FILL);
            paint.setColor(Color.BLUE);
            canvas.drawArc(new RectF(100, 100, 300, 300), 90, 90, true, paint);

            paint.setStyle(Style.FILL);
            paint.setColor(Color.YELLOW);
            canvas.drawArc(new RectF(100, 100, 300, 300), 0, 90, false, paint);

            paint.setStyle(Style.FILL_AND_STROKE);
            paint.setColor(Color.BLACK);
            canvas.drawArc(new RectF(100, 100, 300, 300), 180, 90, false, paint);
        }
    }
}