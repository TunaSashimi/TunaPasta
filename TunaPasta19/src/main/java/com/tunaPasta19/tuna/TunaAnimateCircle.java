package com.tunaPasta19.tuna;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.tunaPasta19.R;


/**
 * @author Tunasashimi
 * @date 10/30/15 16:48
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaAnimateCircle extends TunaView {

    private int tunaAnimateCircleTotal;

    private float tunaAnimateCircleStrokeWidth;

    private int tunaAnimateCircleStrokeColor;
    private int tunaAnimateCircleSpreadColorBefore, tunaAnimateCircleSpreadColorAfter;

    private float tunaAnimateCircleRadiusBefore, tunaAnimateCircleRadiusAfter;

    private float tunaAnimateCircleNormalDeviation;
    private float tunaAnimateCircleSpreadDeciation;

    private float tunaAnimateCircleDx, tunaAnimateCircleDy;

    private boolean tunaAnimateCircleSpreadable;

    private float tunaAnimateCircleSpreadTurn;
    //
    private float[] tunaAnimateCircleRadiusCurrent;
    private int tunaAnimateCircleDelay;

    private float tunaAnimateCircleScreenRadius;
    private float tunaAnimateCircleSpreadCurrent;

    private TunaAnimateCircleAlign tunaAnimateCircleAlign;

    public enum TunaAnimateCircleAlign {
        CENTER(0), TOP(1), BOTTOM(2), LEFT(3), RIGHT(4),;
        final int nativeInt;

        TunaAnimateCircleAlign(int ni) {
            nativeInt = ni;
        }
    }

    private static final TunaAnimateCircleAlign[] tunaAnimateCircleAlignArray = {TunaAnimateCircleAlign.CENTER, TunaAnimateCircleAlign.TOP, TunaAnimateCircleAlign.BOTTOM,
            TunaAnimateCircleAlign.LEFT, TunaAnimateCircleAlign.RIGHT,};

    public TunaAnimateCircle(Context context) {
        this(context, null);
    }

    public TunaAnimateCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaAnimateCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        tunaTag = TunaAnimateCircle.class.getSimpleName();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaAnimateCircle);

        tunaAnimateCircleTotal = typedArray.getInt(R.styleable.TunaAnimateCircle_tunaAnimateCircleTotal, 0);
        if (tunaAnimateCircleTotal < 1) {
            throw new IndexOutOfBoundsException("The content attribute tunaDragArray length must be at least 1");
        } else {
            tunaAnimateCircleRadiusCurrent = new float[tunaAnimateCircleTotal];
        }

        int tunaAnimateCircleAlignIndex = typedArray.getInt(R.styleable.TunaAnimateCircle_tunaAnimateCircleAlign, -1);
        if (tunaAnimateCircleAlignIndex >= 0) {
            tunaAnimateCircleAlign = tunaAnimateCircleAlignArray[tunaAnimateCircleAlignIndex];
        } else {
            throw new IllegalArgumentException("The content attribute require a property named tunaAnimateCircleAlign");
        }

        tunaAnimateCircleStrokeWidth = typedArray.getDimension(R.styleable.TunaAnimateCircle_tunaAnimateCircleStrokeWidth, 0);
        tunaAnimateCircleStrokeColor = typedArray.getColor(R.styleable.TunaAnimateCircle_tunaAnimateCircleStrokeColor, Color.TRANSPARENT);

        tunaAnimateCircleSpreadColorBefore = typedArray.getColor(R.styleable.TunaAnimateCircle_tunaAnimateCircleSpreadColorBefore, Color.TRANSPARENT);
        tunaAnimateCircleSpreadColorAfter = typedArray.getColor(R.styleable.TunaAnimateCircle_tunaAnimateCircleSpreadColorAfter, Color.TRANSPARENT);

        tunaAnimateCircleRadiusBefore = typedArray.getDimension(R.styleable.TunaAnimateCircle_tunaAnimateCircleRadiusBefore, 0);
        tunaAnimateCircleRadiusAfter = typedArray.getDimension(R.styleable.TunaAnimateCircle_tunaAnimateCircleRadiusAfter, 0);

        tunaAnimateCircleNormalDeviation = typedArray.getDimension(R.styleable.TunaAnimateCircle_tunaAnimateCircleNormalDeviation, 0);
        tunaAnimateCircleSpreadDeciation = typedArray.getDimension(R.styleable.TunaAnimateCircle_tunaAnimateCircleSpreadDeciation, 0);

        tunaAnimateCircleDx = typedArray.getDimension(R.styleable.TunaAnimateCircle_tunaAnimateCircleDx, 0);
        tunaAnimateCircleDy = typedArray.getDimension(R.styleable.TunaAnimateCircle_tunaAnimateCircleDy, 0);

        tunaAnimateCircleSpreadable = typedArray.getBoolean(R.styleable.TunaAnimateCircle_tunaAnimateCircleSpreadable, false);

        tunaAnimateCircleDelay = typedArray.getInt(R.styleable.TunaAnimateCircle_tunaAnimateCircleDelay, 0);

        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        tunaSurplus = tunaAnimateCircleRadiusAfter - tunaAnimateCircleRadiusBefore;

        tunaShare = tunaSurplus / tunaAnimateCircleTotal;
        // Small to large
        for (int i = 0; i < tunaAnimateCircleTotal; i++) {
            tunaAnimateCircleRadiusCurrent[i] = tunaAnimateCircleRadiusBefore + tunaShare * i;
        }

        tunaAnimateCircleScreenRadius = (float) Math.hypot((tunaWidth >> 1) + tunaAnimateCircleDx, tunaHeight + tunaAnimateCircleDy);

        switch (tunaAnimateCircleAlign) {
            case CENTER:
                tunaCenterX = (tunaWidth >> 1) + tunaAnimateCircleDx;
                tunaCenterY = (tunaHeight >> 1) + tunaAnimateCircleDy;
                break;
            case TOP:
                tunaCenterX = (tunaWidth >> 1) + tunaAnimateCircleDx;
                tunaCenterY = tunaAnimateCircleDy;
                break;
            case BOTTOM:
                tunaCenterX = (tunaWidth >> 1) + tunaAnimateCircleDx;
                tunaCenterY = tunaHeight + tunaAnimateCircleDy;
                break;
            case LEFT:
                tunaCenterX = tunaAnimateCircleDx;
                tunaCenterY = (tunaHeight >> 1) + tunaAnimateCircleDy;
                break;
            case RIGHT:
                tunaCenterX = tunaWidth + tunaAnimateCircleDx;
                tunaCenterY = (tunaHeight >> 1) + tunaAnimateCircleDy;
                break;
            default:
                break;
        }

        //
        tunaAnimateCircleSpreadTurn = (tunaAnimateCircleRadiusBefore + tunaAnimateCircleScreenRadius) * 0.5f;

        tunaAnimateCircleSpreadCurrent = tunaAnimateCircleRadiusBefore;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!tunaAnimateCircleSpreadable) {
            //

            for (int i = 0; i < tunaAnimateCircleTotal; i++) {
                int alpha = (int) ((tunaAnimateCircleRadiusAfter - tunaAnimateCircleRadiusCurrent[i]) / (tunaAnimateCircleRadiusAfter - tunaAnimateCircleRadiusBefore) * 255);
                canvas.drawCircle(tunaCenterX, tunaCenterY, tunaAnimateCircleRadiusCurrent[i],
                        initTunaPaint(Paint.Style.STROKE, tunaAnimateCircleStrokeColor, tunaAnimateCircleStrokeWidth, alpha));
                tunaAnimateCircleRadiusCurrent[i] += tunaAnimateCircleNormalDeviation;
                if (tunaAnimateCircleRadiusCurrent[i] >= tunaAnimateCircleRadiusAfter) {
                    tunaAnimateCircleRadiusCurrent[i] = tunaAnimateCircleRadiusBefore;
                }
            }

            if (tunaAnimationable) {
                if (tunaAnimateCircleDelay == 0) {
                    invalidate();
                } else {
                    postInvalidateDelayed(tunaAnimateCircleDelay);
                }
            }

        } else {

            int alpha = (int) (Math.abs(tunaAnimateCircleSpreadCurrent - tunaAnimateCircleSpreadTurn) / tunaAnimateCircleSpreadTurn * 255);
            canvas.drawCircle(
                    (tunaWidth >> 1) + tunaAnimateCircleDx,
                    tunaHeight + tunaAnimateCircleDy,
                    tunaAnimateCircleSpreadCurrent,
                    initTunaPaint(Paint.Style.FILL, tunaAnimateCircleSpreadCurrent <= tunaAnimateCircleSpreadTurn ? tunaAnimateCircleSpreadColorBefore
                            : tunaAnimateCircleSpreadColorAfter, 0, alpha));

            if (tunaAnimateCircleSpreadCurrent < tunaAnimateCircleScreenRadius) {
                tunaAnimateCircleSpreadCurrent += tunaAnimateCircleSpreadDeciation;
                if (tunaAnimationable) {
                    if (tunaAnimateCircleDelay == 0) {
                        invalidate();
                    } else {
                        postInvalidateDelayed(tunaAnimateCircleDelay);
                    }
                }
            } else {
                if (tunaSubAnimateEndListener != null) {
                    tunaSubAnimateEndListener.tunaSubAnimateEnd(this);
                }
            }
        }
    }

    public boolean isTunaAnimateCircleSpreadable() {
        return tunaAnimateCircleSpreadable;
    }

    //
    public void setTunaAnimateCircleSpreadable(boolean tunaAnimateCircleSpreadable) {
        this.tunaAnimateCircleSpreadable = tunaAnimateCircleSpreadable;
        if (tunaAnimateCircleSpreadable) {
            tunaAnimateCircleSpreadCurrent = tunaAnimateCircleRadiusBefore;
            invalidate();
        }
    }

}
