package com.tunaPasta12.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class PolyToPoly extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }

    private static class SampleView extends View {
        private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private Matrix mMatrix = new Matrix();
        private Paint.FontMetrics mFontMetrics;

        public SampleView(Context context) {
            super(context);

            // for when the style is STROKE
            mPaint.setStrokeWidth(4);
            // for when we draw text
            mPaint.setTextSize(40);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mFontMetrics = mPaint.getFontMetrics();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            canvas.save();
            canvas.translate(10, 10);
            // translate (1 point)
            doDraw(canvas, new float[]{0, 0}, new float[]{5, 5});
            canvas.restore();

            canvas.save();
            canvas.translate(160, 10);
            // rotate/uniform-scale (2 points)
            doDraw(canvas, new float[]{32, 32, 64, 32},
                    new float[]{32, 32, 64, 48});
            canvas.restore();

            canvas.save();
            canvas.translate(10, 110);
            // rotate/skew (3 points)
            doDraw(canvas, new float[]{0, 0, 64, 0, 0, 64},
                    new float[]{0, 0, 96, 0, 24, 64});
            canvas.restore();

            canvas.save();
            canvas.translate(160, 110);
            // perspective (4 points)
            doDraw(canvas, new float[]{0, 0, 64, 0, 64, 64, 0, 64},
                    new float[]{0, 0, 96, 0, 64, 96, 0, 64});
            canvas.restore();
        }

        private void doDraw(Canvas canvas, float src[], float dst[]) {
            canvas.save();
            mMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
            canvas.concat(mMatrix);

            mPaint.setColor(Color.GRAY);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(0, 0, 64, 64, mPaint);
            canvas.drawLine(0, 0, 64, 64, mPaint);
            canvas.drawLine(0, 64, 64, 0, mPaint);

            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.FILL);
            // how to draw the text center on our square
            // centering in X is easy... use alignment (and X at midpoint)
            float x = 64 / 2;
            // centering in Y, we need to measure ascent/descent first
            float y = 64 / 2 - (mFontMetrics.ascent + mFontMetrics.descent) / 2;
            canvas.drawText(src.length / 2 + "", x, y, mPaint);

            canvas.restore();
        }
    }
}

