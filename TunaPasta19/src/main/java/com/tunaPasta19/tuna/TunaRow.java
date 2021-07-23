package com.tunaPasta19.tuna;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

import com.tunaPasta19.R;

/**
 * @author Tunasashimi
 * @date 10/30/15 16:57
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaRow extends TunaView {

    private int tunaRowBackgroundNormal;
    public int getTunaRowBackgroundNormal() {
        return tunaRowBackgroundNormal;
    }
    public void setTunaRowBackgroundNormal(int tunaRowBackgroundNormal) {
        this.tunaRowBackgroundNormal = tunaRowBackgroundNormal;
    }

    private int tunaRowDuraction;
    public int getTunaRowDuraction() {
        return tunaRowDuraction;
    }
    public void setTunaRowDuraction(int tunaRowDuraction) {
        this.tunaRowDuraction = tunaRowDuraction;
    }

    private TunaRowDirection tunaRowDirection;
    public enum TunaRowDirection {
        TOP(0),
        BOTTOM(1),;
        final int nativeInt;

        TunaRowDirection(int ni) {
            nativeInt = ni;
        }
    }

    private static final TunaRowDirection[] tunaRowDirectionArray = {
            TunaRowDirection.TOP,
            TunaRowDirection.BOTTOM,
    };

    private int tunaRowStopY;
    //
    private AnimatorSet tunaRowAnimatorSet;

    public AnimatorSet getTunaRowAnimatorSet() {
        return tunaRowAnimatorSet;
    }

    public void setTunaRowAnimatorSet(AnimatorSet tunaRowAnimatorSet) {
        this.tunaRowAnimatorSet = tunaRowAnimatorSet;
    }

    private Property<TunaRow, Integer> tunaRowStopYProperty = new Property<TunaRow, Integer>(Integer.class, "tunaRowStopYProperty") {
        @Override
        public Integer get(TunaRow object) {
            return object.tunaRowStopY;
        }

        @Override
        public void set(TunaRow object, Integer value) {
            object.tunaRowStopY = value;
            invalidate();
        }
    };


    public TunaRow(Context context) {
        this(context, null);
    }

    public TunaRow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaRow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        tunaTag = TunaRow.class.getSimpleName();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaRow);

        tunaRowBackgroundNormal = typedArray.getColor(R.styleable.TunaRow_tunaRowBackgroundNormal, Color.TRANSPARENT);
        tunaRowDuraction = typedArray.getInt(R.styleable.TunaRow_tunaRowDuraction, 1000);

        int tunaRowDirectionIndex = typedArray.getInt(R.styleable.TunaRow_tunaRowDirection, 0);
        if (tunaRowDirectionIndex >= 0) {
            tunaRowDirection = tunaRowDirectionArray[tunaRowDirectionIndex];
        }
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        typedArray.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initTunaPaint(Paint.Style.FILL, tunaRowBackgroundNormal, tunaWidth);

        //
        switch (tunaRowDirection) {
            case TOP:
                tunaRowStopY = 0;
                break;
            case BOTTOM:
                tunaRowStopY = tunaHeight;
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //
        switch (tunaRowDirection) {
            case TOP:
                canvas.drawLine(tunaWidth >> 1, 0, tunaWidth >> 1, tunaRowStopY, tunaPaint);
                break;
            case BOTTOM:
                canvas.drawLine(tunaWidth >> 1, tunaHeight, tunaWidth >> 1, tunaRowStopY, tunaPaint);
                break;
        }
    }

    public void play() {
        tunaRowAnimatorSet = new AnimatorSet();
        ObjectAnimator tunaRowStopYObjectAnimator = null;
        //
        switch (tunaRowDirection) {
            case TOP:
                tunaRowStopYObjectAnimator = ObjectAnimator.ofInt(this, tunaRowStopYProperty, 0, tunaHeight);
                break;
            case BOTTOM:
                tunaRowStopYObjectAnimator = ObjectAnimator.ofInt(this, tunaRowStopYProperty, tunaHeight, 0);
                break;
        }
        tunaRowStopYObjectAnimator.setDuration(tunaRowDuraction);
        tunaRowAnimatorSet.playTogether(tunaRowStopYObjectAnimator);
        tunaRowAnimatorSet.start();
    }
}