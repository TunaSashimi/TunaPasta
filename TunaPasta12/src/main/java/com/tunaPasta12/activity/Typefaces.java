package com.tunaPasta12.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

public class Typefaces extends GraphicsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }

    private static class SampleView extends View {
        private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private Typeface mFace;

        public SampleView(Context context) {
            super(context);

            mFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/computerfont.ttf");
            mPaint.setTextSize(64);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            mPaint.setTypeface(null);
            canvas.drawText("Default", 10, 100, mPaint);
            mPaint.setTypeface(mFace);
            canvas.drawText("Custom", 10, 200, mPaint);
        }
    }
}

