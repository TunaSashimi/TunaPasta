package com.tunaPasta19.tuna;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

/**
 * @author Tunasashimi
 * @date 11/2/15 16:36
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaBeta extends TunaView {
    public TunaBeta(Context context) {
        this(context, null);
    }

    public TunaBeta(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaBeta(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        tunaTag = TunaBubble.class.getSimpleName();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specSizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(specSizeWidth, specSizeWidth * 7);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
    }


}
