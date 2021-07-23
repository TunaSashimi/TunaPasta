package com.tunaPasta12.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class DrawPoints extends GraphicsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }

    private static class SampleView extends View {
        private Paint mPaint = new Paint();
        private float[] mPts;

        private static float SIZE = 300;
        private static float SEGS = 32;
        private static final int X = 0;
        private static final int Y = 1;

        private void buildPoints() {
            final int ptCount = (int) ((SEGS + 1) * 2);
            mPts = new float[ptCount * 2];

            float value = 0;
            final float delta = SIZE / SEGS;
            for (int i = 0; i <= SEGS; i++) {
                mPts[i * 4 + X] = SIZE - value;
                mPts[i * 4 + Y] = 0;
                mPts[i * 4 + X + 2] = 0;
                mPts[i * 4 + Y + 2] = value;
                value += delta;
            }
        }

        float displayDensity;

        public SampleView(Context context) {
            super(context);


            displayDensity = getResources().getDisplayMetrics().density;

            SIZE = SIZE * displayDensity + 0.5f;
            SEGS = SEGS * displayDensity + 0.5f;

            buildPoints();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint = mPaint;

            canvas.translate(10, 10);

            canvas.drawColor(Color.WHITE);

            paint.setColor(Color.RED);
            paint.setStrokeWidth(0);
            canvas.drawLines(mPts, paint);

            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(3);
            canvas.drawPoints(mPts, paint);
        }
    }
}

