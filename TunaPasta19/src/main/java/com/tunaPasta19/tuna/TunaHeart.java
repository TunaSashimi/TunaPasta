package com.tunaPasta19.tuna;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * @author Tunasashimi
 * @date 10/30/15 16:54
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaHeart extends TunaView {
    public TunaHeart(Context context) {
        this(context, null);
    }

    public TunaHeart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaHeart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        tunaTag = TunaHeart.class.getSimpleName();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(Color.YELLOW);
        initTunaPaint(Paint.Style.FILL, Color.RED);

        int i, j;
        double x, y, r;
        for (i = 0; i <= 90; i++) {
            for (j = 0; j <= 90; j++) {
                r = Math.PI / 45 * i * (1 - Math.sin(Math.PI / 45 * j)) * 20;
                x = r * Math.cos(Math.PI / 45 * j) * Math.sin(Math.PI / 45 * i) + 320 / 2;
                y = -r * Math.sin(Math.PI / 45 * j) + 400 / 4;
                canvas.drawPoint((float) x, (float) y, tunaPaint);
            }
        }
    }

}
