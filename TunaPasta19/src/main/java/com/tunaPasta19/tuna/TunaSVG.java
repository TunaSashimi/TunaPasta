package com.tunaPasta19.tuna;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;

import com.tunaPasta19.R;


/**
 * @author Tunasashimi
 * @date 10/30/15 16:52
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaSVG extends TunaView {

    //
    private Bitmap tunaSVGSrcBitmap;

    public Bitmap getTunaSVGSrcBitmap() {
        return tunaSVGSrcBitmap;
    }

    public void setTunaSVGSrcBitmap(Bitmap tunaSVGSrcBitmap) {
        this.tunaSVGSrcBitmap = tunaSVGSrcBitmap;
    }

    //
    protected Matrix tunaSVGMatrix;

    public Matrix getTunaSVGMatrix() {
        return tunaSVGMatrix;
    }

    public void setTunaSVGMatrix(Matrix tunaSVGMatrix) {
        this.tunaSVGMatrix = tunaSVGMatrix;
    }

    protected Matrix initTunaSVGMatrix(float sx, float sy) {
        if (tunaSVGMatrix == null) {
            tunaSVGMatrix = new Matrix();
        }
        tunaSVGMatrix.reset();
        tunaSVGMatrix.setScale(sx, sy);
        return tunaSVGMatrix;
    }

    private TunaSVGType tunaSVGType;

    public enum TunaSVGType {
        CIRCLE(0),
        STAR(1),
        HEART(2),
        FLOWER(3),
        PENTAGON(4),
        SIXTEENEDGE(5),
        FORTYEDGE(6),
        SNAIL(7),
        ;
        final int nativeInt;

        TunaSVGType(int ni) {
            nativeInt = ni;
        }
    }

    private static final TunaSVGType[] tunaScaleTypeArray = {
            TunaSVGType.CIRCLE,
            TunaSVGType.STAR,
            TunaSVGType.HEART,
            TunaSVGType.FLOWER,
            TunaSVGType.PENTAGON,
            TunaSVGType.SIXTEENEDGE,
            TunaSVGType.FORTYEDGE,
            TunaSVGType.SNAIL,
    };


    public TunaSVG(Context context) {
        this(context, null);
    }

    public TunaSVG(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaSVG(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        tunaTag = TunaSVG.class.getSimpleName();

        //
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaSVG);
        int tunaBitmapId = typedArray.getResourceId(R.styleable.TunaSVG_tunaSVGSrc, -1);
        if (tunaBitmapId != -1) {
            tunaSrcBitmap = BitmapFactory.decodeResource(getResources(), tunaBitmapId);
        }


        int tunaSVGTypeIndex = typedArray.getInt(R.styleable.TunaSVG_tunaSVGType, 0);
        if (tunaSVGTypeIndex >= 0) {
            tunaSVGType = tunaScaleTypeArray[tunaSVGTypeIndex];
        }
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //
        int specSizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specModeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int specSizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        int measuredWidth = specSizeWidth;
        int measuredHeight = specSizeWidth;

        if (specModeHeight == MeasureSpec.AT_MOST) {//wrap_content
            measuredHeight = measuredWidth;
        } else if (specModeHeight == MeasureSpec.EXACTLY) {// match_parent
            measuredHeight = specSizeHeight;
        } else if (specModeHeight == MeasureSpec.UNSPECIFIED) {// unspecified
            measuredHeight = measuredWidth;
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        tunaScaleSx = tunaWidth * 1f / tunaSrcBitmap.getWidth();
        tunaScaleSy = tunaHeight * 1f / tunaSrcBitmap.getHeight();
        initTunaMatrix(tunaScaleSx, tunaScaleSy);

        int shortSide = tunaWidth >= tunaHeight ? tunaHeight : tunaWidth;
        initTunaSVGMatrix(tunaWidth * 1f / shortSide, tunaHeight * 1f / shortSide);

        if (isInEditMode()) {
            tunaSVGSrcBitmap = TunaView.getCircleBitmap(shortSide);
        } else {
            switch (tunaSVGType) {
                case CIRCLE:
                    tunaSVGSrcBitmap = TunaView.getCircleBitmap(shortSide);
                    break;
                case STAR:
                    tunaSVGSrcBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_star);
                    break;
                case HEART:
                    tunaSVGSrcBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_heart);
                    break;
                case FLOWER:
                    tunaSVGSrcBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_flower);
                    break;
                case PENTAGON:
                    tunaSVGSrcBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_pentagon);
                    break;
                case SIXTEENEDGE:
                    tunaSVGSrcBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_sixteenedge);
                    break;
                case FORTYEDGE:
                    tunaSVGSrcBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_fortyedge);
                    break;
                case SNAIL:
                    tunaSVGSrcBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_snail);
                    break;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.saveLayer(0, 0, tunaWidth, tunaHeight, null, Canvas.ALL_SAVE_FLAG);

        //
        canvas.drawBitmap(tunaSVGSrcBitmap, tunaSVGMatrix, initTunaPaint());

        //
        tunaPaint.setXfermode(tunaPorterDuffXfermode);
        canvas.drawBitmap(tunaSrcBitmap, tunaMatrix, tunaPaint);

        tunaPaint.setXfermode(null);
    }
}
