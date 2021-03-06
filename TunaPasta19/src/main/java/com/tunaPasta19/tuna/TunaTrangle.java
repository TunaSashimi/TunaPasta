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
 * @date 10/30/15 16:59
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaTrangle extends TunaView {

    //tunaTrangleStrokeWidth default 0
    private float tunaTrangleStrokeWidth;

    //tunaTrangleStrokeColor default transparent
    private int tunaTrangleStrokeColor;

    // tunaTrangleBackgroundNormal default Color.WHITE,tunaTrangleBackgroundPress default tunaTrangleBackgroundNormal,tunaTrangleBackgroundSelect default tunaTrangleBackgroundNormal
    private int tunaTrangleBackgroundNormal, tunaTrangleBackgroundPress, tunaTrangleBackgroundSelect;

    private TunaTrangleTowardType tunaTrangleTowardType;

    public enum TunaTrangleTowardType {
        TOP(0),
        BOTTOM(1),
        LEFT(2),
        RIGHT(3),;
        final int nativeInt;

        TunaTrangleTowardType(int ni) {
            nativeInt = ni;
        }
    }

    private static final TunaTrangleTowardType[] tunaTowardTypeArray = {
            TunaTrangleTowardType.TOP,
            TunaTrangleTowardType.BOTTOM,
            TunaTrangleTowardType.LEFT,
            TunaTrangleTowardType.RIGHT,
    };

    //when draw a triangle possible need hide Maximum border line default false
    private boolean tunaTrangleHideEdge;

    //some draw variables
    private double halfTopCornerDadian;
    private float topCornerDistance;
    private float bottomCornerInternalDirectionDistance;
    private float boundaryLineInterceptionDistance;

    public TunaTrangle(Context context) {
        this(context, null);
    }

    public TunaTrangle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaTrangle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        tunaTag = TunaTrangle.class.getSimpleName();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaTrangle);

        int tunaTrangleTowardTypeIndex = typedArray.getInt(R.styleable.TunaTrangle_tunaTrangleTowardType, -1);
        if (tunaTrangleTowardTypeIndex >= 0) {
            tunaTrangleTowardType = tunaTowardTypeArray[tunaTrangleTowardTypeIndex];
        } else {
            throw new IllegalArgumentException("The content attribute tunaTrangleTowardType type must be given");
        }

        tunaTrangleBackgroundNormal = typedArray.getColor(R.styleable.TunaTrangle_tunaTrangleBackgroundNormal, Color.TRANSPARENT);
        tunaTrangleBackgroundPress = typedArray.getColor(R.styleable.TunaTrangle_tunaTrangleBackgroundPress, tunaTrangleBackgroundNormal);
        tunaTrangleBackgroundSelect = typedArray.getColor(R.styleable.TunaTrangle_tunaTrangleBackgroundSelect, tunaTrangleBackgroundNormal);

        tunaTrangleStrokeWidth = typedArray.getDimension(R.styleable.TunaTrangle_tunaTrangleStrokeWidth, 0);
        tunaTrangleStrokeColor = typedArray.getColor(R.styleable.TunaTrangle_tunaTrangleStrokeColor, Color.TRANSPARENT);

        tunaTrangleHideEdge = typedArray.getBoolean(R.styleable.TunaTrangle_tunaTrangleHideEdge, false);

        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (tunaTrangleTowardType == TunaTrangleTowardType.TOP || tunaTrangleTowardType == TunaTrangleTowardType.BOTTOM) {
            //tan(halfTopCornerDadian)=(1/2tunaWidth)/tunaHeight
            halfTopCornerDadian = Math.atan((tunaWidth * 0.5f) / tunaHeight);
        } else {
            //tan(halfTopCornerDadian)=(1/2tunaHeight)/tunaWidth
            halfTopCornerDadian = Math.atan((tunaHeight * 0.5f) / tunaWidth);
        }

        //sin(halfTopCornerDadian)=tunaTrangleStrokeWidth/topCornerDistance
        topCornerDistance = (float) (tunaTrangleStrokeWidth / Math.sin(halfTopCornerDadian));
        //cos(halfTopCornerDadian)=tunaTrangleStrokeWidth/bottomCornerOneCalDirectionDistance
        bottomCornerInternalDirectionDistance = (float) (tunaTrangleStrokeWidth / Math.cos(halfTopCornerDadian));
        //tan(halfTopCornerDadian)=boundaryLineInterceptionDistance/tunaTrangleStrokeWidth
        boundaryLineInterceptionDistance = (float) (Math.tan(halfTopCornerDadian) * tunaTrangleStrokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //start drawing from the outside triangle topCorner and draw clockwise
        //attention! direct coverage on some models will be the sideline, the way to avoid the use of painted area
        switch (tunaTrangleTowardType) {
            case TOP:
                initTunaPathMoveTo(tunaWidth >> 1, 0);
                //both requirements tunaTrangleStrokeWidth != 0 and tunaTrangleHideHypotenuse=true will cut edge
                if (tunaTrangleStrokeWidth != 0 && tunaTrangleHideEdge) {
                    tunaPath.lineTo(tunaWidth - boundaryLineInterceptionDistance, tunaHeight - tunaTrangleStrokeWidth);
                    tunaPath.lineTo(tunaWidth - (boundaryLineInterceptionDistance + bottomCornerInternalDirectionDistance), tunaHeight - tunaTrangleStrokeWidth);
                    tunaPath.lineTo(tunaWidth >> 1, topCornerDistance);
                    tunaPath.lineTo(boundaryLineInterceptionDistance + bottomCornerInternalDirectionDistance, tunaHeight - tunaTrangleStrokeWidth);
                    tunaPath.lineTo(boundaryLineInterceptionDistance, tunaHeight - tunaTrangleStrokeWidth);
                } else {
                    tunaPath.lineTo(tunaWidth, tunaHeight);
                    tunaPath.lineTo(0, tunaHeight);
                }
                break;
            case BOTTOM:
                initTunaPathMoveTo(tunaWidth >> 1, tunaHeight);
                if (tunaTrangleStrokeWidth != 0 && tunaTrangleHideEdge) {
                    tunaPath.lineTo(boundaryLineInterceptionDistance, tunaTrangleStrokeWidth);
                    tunaPath.lineTo(boundaryLineInterceptionDistance + bottomCornerInternalDirectionDistance, tunaTrangleStrokeWidth);
                    tunaPath.lineTo(tunaWidth >> 1, tunaHeight - topCornerDistance);
                    tunaPath.lineTo(tunaWidth - (boundaryLineInterceptionDistance + bottomCornerInternalDirectionDistance), tunaTrangleStrokeWidth);
                    tunaPath.lineTo(tunaWidth - boundaryLineInterceptionDistance, tunaTrangleStrokeWidth);
                } else {
                    tunaPath.lineTo(0, 0);
                    tunaPath.lineTo(tunaWidth, 0);
                }
                break;
            case LEFT:
                initTunaPathMoveTo(0, tunaHeight >> 1);
                if (tunaTrangleStrokeWidth != 0 && tunaTrangleHideEdge) {
                    tunaPath.lineTo(tunaWidth - tunaTrangleStrokeWidth, boundaryLineInterceptionDistance);
                    tunaPath.lineTo(tunaWidth - tunaTrangleStrokeWidth, boundaryLineInterceptionDistance + bottomCornerInternalDirectionDistance);
                    tunaPath.lineTo(topCornerDistance, tunaHeight >> 1);
                    tunaPath.lineTo(tunaWidth - tunaTrangleStrokeWidth, tunaHeight - (boundaryLineInterceptionDistance + bottomCornerInternalDirectionDistance));
                    tunaPath.lineTo(tunaWidth - tunaTrangleStrokeWidth, tunaHeight - boundaryLineInterceptionDistance);
                } else {
                    tunaPath.lineTo(tunaWidth, 0);
                    tunaPath.lineTo(tunaWidth, tunaHeight);
                }
                break;
            case RIGHT:
                initTunaPathMoveTo(tunaWidth, tunaHeight >> 1);
                if (tunaTrangleStrokeWidth != 0 && tunaTrangleHideEdge) {
                    tunaPath.lineTo(tunaTrangleStrokeWidth, tunaHeight - boundaryLineInterceptionDistance);
                    tunaPath.lineTo(tunaTrangleStrokeWidth, tunaHeight - (boundaryLineInterceptionDistance + bottomCornerInternalDirectionDistance));
                    tunaPath.lineTo(tunaWidth - topCornerDistance, tunaHeight >> 1);
                    tunaPath.lineTo(tunaTrangleStrokeWidth, boundaryLineInterceptionDistance + bottomCornerInternalDirectionDistance);
                    tunaPath.lineTo(tunaTrangleStrokeWidth, boundaryLineInterceptionDistance);
                } else {
                    tunaPath.lineTo(0, tunaHeight);
                    tunaPath.lineTo(0, 0);
                }
                break;
        }

        tunaPath.close();
        canvas.drawPath(tunaPath,
                initTunaPaint(Paint.Style.FILL, tunaTrangleStrokeWidth != 0 ?
                        tunaTrangleStrokeColor : tunaSelect ? tunaTrangleBackgroundSelect : tunaPress ? tunaTrangleBackgroundPress : tunaTrangleBackgroundNormal));

        //drawing  the inside triangle topCorner and draw clockwise
        switch (tunaTrangleTowardType) {
            case TOP:
                if (tunaTrangleStrokeWidth != 0) {
                    tunaPaint.setColor(tunaSelect ? tunaTrangleBackgroundSelect : tunaPress ? tunaTrangleBackgroundPress : tunaTrangleBackgroundNormal);
                    tunaPath.reset();
                    tunaPath.moveTo(tunaWidth >> 1, topCornerDistance);
                    tunaPath.lineTo(tunaWidth - (bottomCornerInternalDirectionDistance + boundaryLineInterceptionDistance), tunaHeight - tunaTrangleStrokeWidth);
                    tunaPath.lineTo(bottomCornerInternalDirectionDistance + boundaryLineInterceptionDistance, tunaHeight - tunaTrangleStrokeWidth);
                    tunaPath.close();
                    canvas.drawPath(tunaPath, tunaPaint);
                }
                break;
            case BOTTOM:
                if (tunaTrangleStrokeWidth != 0) {
                    tunaPaint.setColor(tunaSelect ? tunaTrangleBackgroundSelect : tunaPress ? tunaTrangleBackgroundPress : tunaTrangleBackgroundNormal);
                    tunaPath.reset();
                    tunaPath.moveTo(tunaWidth >> 1, tunaHeight - topCornerDistance);
                    tunaPath.lineTo(bottomCornerInternalDirectionDistance + boundaryLineInterceptionDistance, tunaTrangleStrokeWidth);
                    tunaPath.lineTo(tunaWidth - (bottomCornerInternalDirectionDistance + boundaryLineInterceptionDistance), tunaTrangleStrokeWidth);
                    tunaPath.close();
                    canvas.drawPath(tunaPath, tunaPaint);
                }
                break;
            case LEFT:
                if (tunaTrangleStrokeWidth != 0) {
                    tunaPaint.setColor(tunaSelect ? tunaTrangleBackgroundSelect : tunaPress ? tunaTrangleBackgroundPress : tunaTrangleBackgroundNormal);
                    tunaPath.reset();
                    tunaPath.moveTo(topCornerDistance, tunaHeight >> 1);
                    tunaPath.lineTo(tunaWidth - tunaTrangleStrokeWidth, bottomCornerInternalDirectionDistance + boundaryLineInterceptionDistance);
                    tunaPath.lineTo(tunaWidth - tunaTrangleStrokeWidth, tunaHeight - (bottomCornerInternalDirectionDistance + boundaryLineInterceptionDistance));
                    tunaPath.close();
                    canvas.drawPath(tunaPath, tunaPaint);
                }
                break;
            case RIGHT:
                if (tunaTrangleStrokeWidth != 0) {
                    tunaPaint.setColor(tunaSelect ? tunaTrangleBackgroundSelect : tunaPress ? tunaTrangleBackgroundPress : tunaTrangleBackgroundNormal);
                    tunaPath.reset();
                    tunaPath.moveTo(tunaWidth - topCornerDistance, tunaHeight >> 1);
                    tunaPath.lineTo(tunaTrangleStrokeWidth, tunaHeight - (bottomCornerInternalDirectionDistance + boundaryLineInterceptionDistance));
                    tunaPath.lineTo(tunaTrangleStrokeWidth, bottomCornerInternalDirectionDistance + boundaryLineInterceptionDistance);
                    tunaPath.close();
                    canvas.drawPath(tunaPath, tunaPaint);
                }
                break;
        }
    }
}
