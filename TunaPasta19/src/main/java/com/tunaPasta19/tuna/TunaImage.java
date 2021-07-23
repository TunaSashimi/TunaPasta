package com.tunaPasta19.tuna;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.tunaPasta19.R;


/**
 * @author Tunasashimi
 * @date 11/20/15 10:46
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaImage extends TunaView {


    private float tunaImageRadius;

    public float getTunaImageRadius() {
        return tunaImageRadius;
    }

    public void setTunaImageRadius(float tunaImageRadius) {
        this.tunaImageRadius = tunaImageRadius;
    }

    private float tunaImageAlpha;

    public float getTunaImageAlpha() {
        return tunaImageAlpha;
    }

    public void setTunaImageAlpha(float tunaImageAlpha) {
        this.tunaImageAlpha = tunaImageAlpha;
    }

    private boolean tunaImageSepia;

    public boolean isTunaImageSepia() {
        return tunaImageSepia;
    }

    public void setTunaImageSepia(boolean tunaImageSepia) {
        this.tunaImageSepia = tunaImageSepia;
    }

    private boolean tunaImageEmboss;

    public boolean isTunaImageEmboss() {
        return tunaImageEmboss;
    }

    public void setTunaImageEmboss(boolean tunaImageEmboss) {
        this.tunaImageEmboss = tunaImageEmboss;
    }

    private boolean tunaImageBacksheet;

    public boolean isTunaImageBacksheet() {
        return tunaImageBacksheet;
    }

    public void setTunaImageBacksheet(boolean tunaImageBacksheet) {
        this.tunaImageBacksheet = tunaImageBacksheet;
    }

    private boolean tunaImageSketch;

    public boolean isTunaImageSketch() {
        return tunaImageSketch;
    }

    public void setTunaImageSketch(boolean tunaImageSketch) {
        this.tunaImageSketch = tunaImageSketch;
    }

    private float tunaImageSunshineFractionX;

    public float getTunaImageSunshineFractionX() {
        return tunaImageSunshineFractionX;
    }

    public void setTunaImageSunshineFractionX(float tunaImageSunshineFractionX) {
        this.tunaImageSunshineFractionX = tunaImageSunshineFractionX;
    }

    private float tunaImageSunshineFractionY;

    public float getTunaImageSunshineFractionY() {
        return tunaImageSunshineFractionY;
    }

    public void setTunaImageSunshineFractionY(float tunaImageSunshineFractionY) {
        this.tunaImageSunshineFractionY = tunaImageSunshineFractionY;
    }

    private float tunaImageBright;
    public float getTunaImageBright() {
        return tunaImageBright;
    }

    public void setTunaImageBright(float tunaImageBright) {
        this.tunaImageBright = tunaImageBright;
        invalidate();
    }

    private float tunaImageHue;
    public float getTunaImageHue() {
        return tunaImageHue;
    }
    public void setTunaImageHue(float tunaImageHue) {
        this.tunaImageHue = tunaImageHue;
        invalidate();
    }

    private float tunaImageSaturation;
    public float getTunaImageSaturation() {
        return tunaImageSaturation;
    }

    public void setTunaImageSaturation(float tunaImageSaturation) {
        this.tunaImageSaturation = tunaImageSaturation;
        invalidate();
    }

    private TunaImageReverse tunaImageReverse;

    public enum TunaImageReverse {
        HORIZONTAL(0),
        VERTICAL(1),;
        final int nativeInt;

        TunaImageReverse(int ni) {
            nativeInt = ni;
        }
    }

    public TunaImageReverse getTunaImageReverse() {
        return tunaImageReverse;
    }

    public void setTunaImageReverse(TunaImageReverse tunaImageReverse) {
        this.tunaImageReverse = tunaImageReverse;
    }

    private static final TunaImageReverse[] tunaImageReverseArray = {TunaImageReverse.HORIZONTAL, TunaImageReverse.VERTICAL,};

    private Bitmap tunaImageSrcBitmap;

    public Bitmap getTunaImageSrcBitmap() {
        return tunaImageSrcBitmap;
    }

    public void setTunaImageSrcBitmap(Bitmap tunaImageSrcBitmap) {
        this.tunaImageSrcBitmap = tunaImageSrcBitmap;
    }

    public TunaImage(Context context) {
        this(context, null);
    }

    public TunaImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        tunaTag = TunaImage.class.getSimpleName();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaImage);

        int tunaImageSrcIndex = typedArray.getResourceId(R.styleable.TunaImage_tunaImageSrc, -1);
        if (tunaImageSrcIndex >= 0) {
            tunaSrcBitmap = BitmapFactory.decodeResource(getResources(), tunaImageSrcIndex);
        } else {
            throw new IllegalArgumentException("The content attribute require a property named tunaImageSrc");
        }

        tunaImageRadius = typedArray.getDimension(R.styleable.TunaImage_tunaImageRadius, 0);
        tunaImageAlpha = typedArray.getFraction(R.styleable.TunaImage_tunaImageAlpha, 1, 1, 1f);

        tunaImageSepia = typedArray.getBoolean(R.styleable.TunaImage_tunaImageSepia, false);
        tunaImageEmboss = typedArray.getBoolean(R.styleable.TunaImage_tunaImageEmboss, false);
        tunaImageBacksheet = typedArray.getBoolean(R.styleable.TunaImage_tunaImageBacksheet, false);
        tunaImageSketch = typedArray.getBoolean(R.styleable.TunaImage_tunaImageSketch, false);

        tunaImageSunshineFractionX = typedArray.getFraction(R.styleable.TunaImage_tunaImageSunshineFractionX, 1, 1, 0);
        tunaImageSunshineFractionY = typedArray.getFraction(R.styleable.TunaImage_tunaImageSunshineFractionY, 1, 1, 0);

        tunaImageBright = typedArray.getDimension(R.styleable.TunaImage_tunaImageBright, 1f);
        tunaImageHue = typedArray.getDimension(R.styleable.TunaImage_tunaImageHue, 0f);
        tunaImageSaturation = typedArray.getDimension(R.styleable.TunaImage_tunaImageSaturation, 1f);

        int tunaImageReverseIndex = typedArray.getInt(R.styleable.TunaImage_tunaImageReverse, -1);
        if (tunaImageReverseIndex >= 0) {
            tunaImageReverse = tunaImageReverseArray[tunaImageReverseIndex];
        }

        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        tunaScaleSx = tunaWidth * 1f / tunaSrcBitmap.getWidth();
        tunaScaleSy = tunaHeight * 1f / tunaSrcBitmap.getHeight();

        initTunaMatrix(tunaScaleSx, tunaScaleSy);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //
        tunaImageSrcBitmap = tunaSrcBitmap;

        if (tunaImageRadius > 0) {
            tunaImageSrcBitmap = TunaView.getClassicRoundBitmap(tunaSrcBitmap, tunaWidth);
        }

        if (tunaImageAlpha != 1f) {
            if (tunaImageSrcBitmap == null) {
                tunaImageSrcBitmap = TunaView.getAlphaBitmap(tunaSrcBitmap, tunaImageAlpha);
            } else {
                tunaImageSrcBitmap = TunaView.getAlphaBitmap(tunaImageSrcBitmap, tunaImageAlpha);
            }
        }

        if (tunaImageSepia) {
            if (tunaImageSrcBitmap == null) {
                tunaImageSrcBitmap = TunaView.getSepiaBitmap(tunaSrcBitmap);
            } else {
                tunaImageSrcBitmap = TunaView.getSepiaBitmap(tunaImageSrcBitmap);
            }
        }

        if (tunaImageEmboss) {
            if (tunaImageSrcBitmap == null) {
                tunaImageSrcBitmap = TunaView.getEmbossBitmap(tunaSrcBitmap);
            } else {
                tunaImageSrcBitmap = TunaView.getEmbossBitmap(tunaImageSrcBitmap);
            }
        }

        if (tunaImageBacksheet) {
            if (tunaImageSrcBitmap == null) {
                tunaImageSrcBitmap = TunaView.getBacksheetBitmap(tunaSrcBitmap);
            } else {
                tunaImageSrcBitmap = TunaView.getBacksheetBitmap(tunaImageSrcBitmap);
            }
        }

        if (tunaImageSketch) {
            if (tunaImageSrcBitmap == null) {
                tunaImageSrcBitmap = TunaView.getSketchBitmap(tunaSrcBitmap);
            } else {
                tunaImageSrcBitmap = TunaView.getSketchBitmap(tunaImageSrcBitmap);
            }
        }

        if (tunaImageSunshineFractionX != 0 || tunaImageSunshineFractionY != 0) {
            if (tunaImageSrcBitmap == null) {
                tunaImageSrcBitmap = TunaView.getSunshineBitmap(
                        tunaSrcBitmap,
                        tunaWidth * tunaImageSunshineFractionX,
                        tunaHeight * tunaImageSunshineFractionY);
                ;
            } else {
                tunaImageSrcBitmap = TunaView.getSunshineBitmap(
                        tunaImageSrcBitmap,
                        tunaWidth * tunaImageSunshineFractionX,
                        tunaHeight * tunaImageSunshineFractionY);
                ;
            }
        }

        if (tunaImageReverse != null) {
            if (tunaImageSrcBitmap == null) {
                tunaImageSrcBitmap = TunaView.getReverseBitmap(tunaSrcBitmap, tunaImageReverse == TunaImageReverse.HORIZONTAL ? 0 : 1);
            } else {
                tunaImageSrcBitmap = TunaView.getReverseBitmap(tunaImageSrcBitmap, tunaImageReverse == TunaImageReverse.HORIZONTAL ? 0 : 1);
            }
        }

        if (tunaImageBright != 1f || tunaImageHue != 0f || tunaImageSaturation != 1f) {
            if (tunaImageSrcBitmap == null) {
                tunaImageSrcBitmap = TunaView.processBitmap(tunaSrcBitmap, tunaImageBright, tunaImageHue, tunaImageSaturation);
            } else {
                tunaImageSrcBitmap = TunaView.processBitmap(tunaImageSrcBitmap, tunaImageBright, tunaImageHue, tunaImageSaturation);
            }
        }
        canvas.drawBitmap(tunaImageSrcBitmap, tunaMatrix, null);
    }
}
