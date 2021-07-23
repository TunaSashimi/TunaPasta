package com.tunaPasta19.tuna;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.tunaPasta19.R;


/**
 * @author Tunasashimi
 * @date 11/15/15 16:17
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaDraw extends TunaView {

    //
    private int tunaPaintingColor;

    public int getTunaPaintingColor() {
        return tunaPaintingColor;
    }

    public void setTunaPaintingColor(int tunaPaintingColor) {
        this.tunaPaintingColor = tunaPaintingColor;
    }

    private float tunaPaintingWidth;

    public float getTunaPaintingWidth() {
        return tunaPaintingWidth;
    }

    public void setTunaPaintingWidth(float tunaPaintingWidth) {
        this.tunaPaintingWidth = tunaPaintingWidth;
    }

    //
    private Bitmap tunaPaintingSrc;

    public Bitmap getTunaPaintingSrc() {
        return tunaPaintingSrc;
    }

    public void setTunaPaintingSrc(int id) {
        setTunaPaintingSrc(decodeBitmapResource(id));
    }

    public void setTunaPaintingSrc(Bitmap tunaPaintingSrc) {
        this.tunaPaintingSrc = tunaPaintingSrc;
    }


    private TunaPaintingType tunaPaintingType;
    public enum TunaPaintingType {
        CIRCLE(0),
        STAR(1),
        HEART(2),
        FLOWER(3),
        PENTAGON(4),
        SIXTEENEDGE(5),
        FORTYEDGE(6),
        SNAIL(7),;
        final int nativeInt;

        TunaPaintingType(int ni) {
            nativeInt = ni;
        }
    }

    private static final TunaPaintingType[] tunaScaleTypeArray = {
            TunaPaintingType.CIRCLE,
            TunaPaintingType.STAR,
            TunaPaintingType.HEART,
            TunaPaintingType.FLOWER,
            TunaPaintingType.PENTAGON,
            TunaPaintingType.SIXTEENEDGE,
            TunaPaintingType.FORTYEDGE,
            TunaPaintingType.SNAIL,
    };

    private Bitmap tunaPaintingDstBitmap;
    protected Matrix tunaPaintingDstMatrix;

    public Matrix getTunaPaintingDstMatrix() {
        return tunaPaintingDstMatrix;
    }

    public void setTunaPaintingDstMatrix(Matrix tunaPaintingDstMatrix) {
        this.tunaPaintingDstMatrix = tunaPaintingDstMatrix;
    }

    protected Matrix initTunaPaintingDstMatrix(float sx, float sy) {
        if (tunaPaintingDstMatrix == null) {
            tunaPaintingDstMatrix = new Matrix();
        }
        tunaPaintingDstMatrix.reset();
        tunaPaintingDstMatrix.setScale(sx, sy);
        return tunaPaintingDstMatrix;
    }


    //
    private Paint mBitmapPaint;
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    public Paint getTunaPaintingPaint() {
        return tunaPaint;
    }

    public TunaDraw(Context context) {
        this(context, null);
    }

    public TunaDraw(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaDraw(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        tunaTag = TunaDraw.class.getSimpleName();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaDraw);

        tunaPaintingColor = typedArray.getColor(R.styleable.TunaDraw_tunaPaintingColor, Color.RED);
        tunaPaintingWidth = typedArray.getDimension(R.styleable.TunaDraw_tunaPaintingWidth, 12);


        int tunaPaintingSrcId = typedArray.getResourceId(R.styleable.TunaDraw_tunaPaintingSrc, -1);
        if (tunaPaintingSrcId != -1) {
            tunaPaintingSrc = BitmapFactory.decodeResource(getResources(), tunaPaintingSrcId);
        }

        //
        int tunaPaintingTypeIndex = typedArray.getInt(R.styleable.TunaDraw_tunaPaintingType, -1);
        if (tunaPaintingTypeIndex >= 0) {
            tunaPaintingType = tunaScaleTypeArray[tunaPaintingTypeIndex];
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

        //When the parent class in onMeasure initialized  tunaPaint in TunaView
        initTunaPaint(Paint.Style.STROKE, tunaPaintingColor, tunaPaintingWidth);
        tunaPaint.setAntiAlias(true);
        tunaPaint.setDither(true);
        tunaPaint.setStrokeJoin(Paint.Join.ROUND);
        tunaPaint.setStrokeCap(Paint.Cap.ROUND);


        //
        tunaSrcBitmap = Bitmap.createBitmap(tunaWidth, tunaHeight, Bitmap.Config.ARGB_8888);
        tunaCanvas = new Canvas(tunaSrcBitmap);

        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        //
        if (tunaPaintingSrc != null) {
            tunaScaleSx = tunaWidth * 1f / tunaPaintingSrc.getWidth();
            tunaScaleSy = tunaHeight * 1f / tunaPaintingSrc.getHeight();
            initTunaMatrix(tunaScaleSx, tunaScaleSy);
        }

        //
        if (tunaPaintingType != null) {
            int shortSide = tunaWidth >= tunaHeight ? tunaHeight : tunaWidth;
            initTunaPaintingDstMatrix(tunaWidth * 1f / shortSide, tunaHeight * 1f / shortSide);

            if (isInEditMode()) {
                tunaPaintingDstBitmap = TunaView.getCircleBitmap(shortSide);
            } else {
                switch (tunaPaintingType) {
                    case CIRCLE:
                        tunaPaintingDstBitmap = TunaView.getCircleBitmap(shortSide);
                        break;
                    case STAR:
                        tunaPaintingDstBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_star);
                        break;
                    case HEART:
                        tunaPaintingDstBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_heart);
                        break;
                    case FLOWER:
                        tunaPaintingDstBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_flower);
                        break;
                    case PENTAGON:
                        tunaPaintingDstBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_pentagon);
                        break;
                    case SIXTEENEDGE:
                        tunaPaintingDstBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_sixteenedge);
                        break;
                    case FORTYEDGE:
                        tunaPaintingDstBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_fortyedge);
                        break;
                    case SNAIL:
                        tunaPaintingDstBitmap = TunaView.getSVGBitmap(getContext(), shortSide, shortSide, R.raw.svg_snail);
                        break;
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (tunaPaintingType == null) {
            if (tunaPaintingSrc != null) {
                canvas.drawBitmap(tunaPaintingSrc, tunaMatrix, null);
            }
            canvas.drawBitmap(tunaSrcBitmap, 0, 0, mBitmapPaint);
            if (tunaPath != null) {
                canvas.drawPath(tunaPath, tunaPaint);
            }
        } else {

            //
            if (tunaPaintingSrc != null) {
                canvas.saveLayer(0, 0, tunaWidth, tunaHeight, null, Canvas.ALL_SAVE_FLAG);
                canvas.drawBitmap(tunaPaintingDstBitmap, tunaPaintingDstMatrix, tunaPaint);
                tunaPaint.setXfermode(tunaPorterDuffXfermode);
                canvas.drawBitmap(tunaPaintingSrc, tunaMatrix, tunaPaint);
                tunaPaint.setXfermode(null);
                canvas.restore();
            }

            //
            canvas.saveLayer(0, 0, tunaWidth, tunaHeight, null, Canvas.ALL_SAVE_FLAG);
            canvas.drawBitmap(tunaPaintingDstBitmap, tunaPaintingDstMatrix, mBitmapPaint);
            mBitmapPaint.setXfermode(tunaPorterDuffXfermode);
            canvas.drawBitmap(tunaSrcBitmap, 0, 0, mBitmapPaint);
            mBitmapPaint.setXfermode(null);
            canvas.restore();

            //
            if (tunaPath != null) {
                canvas.drawPath(tunaPath, tunaPaint);
            }
        }
    }

    public void touchDown(float x, float y) {
        initTunaPathMoveTo(x, y);
        mX = x;
        mY = y;
        invalidate();
    }

    public void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            tunaPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
        invalidate();
    }

    public void touchUp() {
        tunaPath.lineTo(mX, mY);
        tunaCanvas.drawPath(tunaPath, tunaPaint);
        invalidate();
    }

    //
    public void setTunaPaintingListener() {
        setTunaTouchDownListener(new TunaView.TunaTouchDownListener() {
            @Override
            public void tunaTouchDown(View v) {
                touchDown(getTunaTouchEventX(), getTunaTouchEventY());
            }
        });
        setTunaTouchMoveListener(new TunaView.TunaTouchMoveListener() {
            @Override
            public void tunaTouchMove(View v) {
                touchMove(getTunaTouchEventX(), getTunaTouchEventY());

            }
        });
        setTunaTouchUpListener(new TunaView.TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                touchUp();
            }
        });
    }
}