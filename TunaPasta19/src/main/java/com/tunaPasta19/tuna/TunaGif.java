package com.tunaPasta19.tuna;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;


import com.tunaPasta19.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

/**
 * @author Tunasashimi
 * @date 11/25/15 15:28
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaGif extends TunaView {

    private GifDrawable gifDrawable;


    private int tunaGifGraphicsSrc;


    private int tunaGifGraphicsSrcURLRequestWidth;

    public int getTunaGifGraphicsSrcURLRequestWidth() {
        return tunaGifGraphicsSrcURLRequestWidth;
    }

    public void setTunaGifGraphicsSrcURLRequestWidth(int tunaGifGraphicsSrcURLRequestWidth) {
        this.tunaGifGraphicsSrcURLRequestWidth = tunaGifGraphicsSrcURLRequestWidth;
        requestLayout();
    }

    private int tunaGifGraphicsSrcURLRequestHeight;

    public int getTunaGifGraphicsSrcURLRequestHeight() {
        return tunaGifGraphicsSrcURLRequestHeight;
    }

    public void setTunaGifGraphicsSrcURLRequestHeight(int tunaGifGraphicsSrcURLRequestHeight) {
        this.tunaGifGraphicsSrcURLRequestHeight = tunaGifGraphicsSrcURLRequestHeight;
        requestLayout();
    }

    public TunaGif(Context context) {
        super(context);
    }

    public TunaGif(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TunaGif(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        tunaTag = TunaGif.class.getSimpleName();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaGif);

//        tunaGifGraphicsSrc = typedArray.getResourceId(R.styleable.TunaGif_tunaGifGraphicsSrc, -1);
//        if (tunaGifGraphicsSrc == -1) {
//            throw new IllegalArgumentException("The content attribute tunaGifMarkGraphicsSrc must be given");
//        }
//        try {
//            gifDrawable = new GifDrawable(getResources(), R.drawable.tunagiftest_loading);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        tunaGifGraphicsSrcURLRequestWidth = typedArray.getInt(R.styleable.TunaGif_tunaGifGraphicsSrcURLRequestWidth, 0);
        tunaGifGraphicsSrcURLRequestHeight = typedArray.getInt(R.styleable.TunaGif_tunaGifGraphicsSrcURLRequestHeight, 0);

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
            if (tunaGifGraphicsSrcURLRequestWidth != 0 || tunaGifGraphicsSrcURLRequestHeight != 0) {
                float widthByHeightRatio = tunaGifGraphicsSrcURLRequestWidth * 1f / tunaGifGraphicsSrcURLRequestHeight;
                measuredHeight = (int) (measuredWidth / widthByHeightRatio);
            }
        } else if (specModeHeight == MeasureSpec.EXACTLY) {// match_parent
            measuredHeight = specSizeHeight;
        } else if (specModeHeight == MeasureSpec.UNSPECIFIED) {// unspecified
            if (tunaGifGraphicsSrcURLRequestWidth != 0 || tunaGifGraphicsSrcURLRequestHeight != 0) {
                float widthByHeightRatio = tunaGifGraphicsSrcURLRequestWidth * 1f / tunaGifGraphicsSrcURLRequestHeight;
                measuredHeight = (int) (measuredWidth / widthByHeightRatio);
            }
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (gifDrawable != null) {
            setBackground(gifDrawable);
        }
    }

    public void init(int id) {
        try {
            gifDrawable = new GifDrawable(getResources(), id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        postInvalidate();
    }
}
