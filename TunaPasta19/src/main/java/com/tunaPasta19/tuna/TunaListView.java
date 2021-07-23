package com.tunaPasta19.tuna;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author Tunasashimi
 * @date 11/2/15 11:07
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaListView extends ListView {
    public TunaListView(Context context) {
        super(context);

//        setWillNotDraw(false);
    }

    public TunaListView(Context context, AttributeSet attrs) {
        super(context, attrs);

//        setWillNotDraw(false);
    }

    public TunaListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        System.out.println("onMeasure");

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

//        System.out.println("onLayout");

        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        System.out.println("onDraw");

        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

//        System.out.println("onDispatchDraw");

        super.dispatchDraw(canvas);
    }
}
