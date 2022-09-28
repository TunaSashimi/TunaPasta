package com.tunaPasta13.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundImageView extends ImageView {
    private float radius = 16;
    private final RectF roundRect = new RectF();
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundImageView(Context context) {
        super(context);
        init();
    }

    private void init() {
        zonePaint.setAntiAlias(true);

        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        float density = getResources().getDisplayMetrics().density;
        radius = radius * density;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        roundRect.set(0, 0, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(roundRect, radius, radius, zonePaint);
        //
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);

        super.draw(canvas);
        canvas.restore();
    }
}