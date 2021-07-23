package com.tunaPasta11.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

public class MeasureTextTest extends GraphicsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int STRIDE = 64;   // must be >= WIDTH

    private static int[] createColors() {
        int[] colors = new int[STRIDE * HEIGHT];
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int r = x * 255 / (WIDTH - 1);
                int g = y * 255 / (HEIGHT - 1);
                int b = 255 - Math.min(r, g);
                int a = Math.max(r, g);
                colors[y * STRIDE + x] = (a << 24) | (r << 16) | (g << 8) | b;
            }
        }
        return colors;
    }

    private static class SampleView extends View {
        private Paint mPaint;
        private float mOriginX = 10;
        private float mOriginY = 80;

        public SampleView(Context context) {
            super(context);
            setFocusable(true);

            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStrokeWidth(5);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setTextSize(64);
            mPaint.setTypeface(Typeface.create(Typeface.SERIF,
                    Typeface.ITALIC));
        }

        private void showText(Canvas canvas, String text, Paint.Align align) {
            //   mPaint.setTextAlign(align);

            Rect bounds = new Rect();
            float[] widths = new float[text.length()];

            int count = mPaint.getTextWidths(text, 0, text.length(), widths);
            float w = mPaint.measureText(text, 0, text.length());
            mPaint.getTextBounds(text, 0, text.length(), bounds);

            mPaint.setColor(0xFF88FF88);
            canvas.drawRect(bounds, mPaint);
            mPaint.setColor(Color.BLACK);
            canvas.drawText(text, 0, 0, mPaint);

            float[] pts = new float[2 + count * 2];
            float x = 0;
            float y = 0;
            pts[0] = x;
            pts[1] = y;
            for (int i = 0; i < count; i++) {
                x += widths[i];
                pts[2 + i * 2] = x;
                pts[2 + i * 2 + 1] = y;
            }
            mPaint.setColor(Color.RED);
            mPaint.setStrokeWidth(0);
            canvas.drawLine(0, 0, w, 0, mPaint);
            mPaint.setStrokeWidth(5);
            canvas.drawPoints(pts, 0, (count + 1) << 1, mPaint);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            canvas.translate(mOriginX, mOriginY);

            showText(canvas, "Measure", Paint.Align.LEFT);
            canvas.translate(0, 80);
            showText(canvas, "wiggy!", Paint.Align.CENTER);
            canvas.translate(0, 80);
            showText(canvas, "Text", Paint.Align.RIGHT);
        }
    }
}

