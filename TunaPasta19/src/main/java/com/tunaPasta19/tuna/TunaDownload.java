package com.tunaPasta19.tuna;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;


import com.tunaPasta19.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import pl.droidsonroids.gif.GifDrawable;
/**
 * @author Tunasashimi
 * @date 11/3/15 18:11
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaDownload extends TunaView {

    private float tunaDownloadPercent;
    public float getTunaDownloadPercent() {
        return tunaDownloadPercent;
    }

    public void setTunaDownloadPercent(float tunaDownloadPercent) {
        this.tunaDownloadPercent = tunaDownloadPercent;
        if (tunaDownloadGraphicsSrcFrontId != -1) {
            invalidate();
        }
    }

    private String tunaDownloadCacheFolder;

    public String getTunaDownloadCacheFolder() {
        return tunaDownloadCacheFolder;
    }

    public void setTunaDownloadCacheFolder(String tunaDownloadCacheFolder) {
        this.tunaDownloadCacheFolder = tunaDownloadCacheFolder;
    }

    private String tunaDownloadCacheFolderDirectory;

    private boolean tunaDownloadCacheCheck;

    public boolean isTunaDownloadCacheCheck() {
        return tunaDownloadCacheCheck;
    }

    public void setTunaDownloadCacheCheck(boolean tunaDownloadCacheCheck) {
        this.tunaDownloadCacheCheck = tunaDownloadCacheCheck;
    }

    private static final TunaDownloadType[] tunaDownloadTypeArray = {TunaDownloadType.TENSILE, TunaDownloadType.ORIGINAL,};

    private float tunaDownloadRadius;
    public float getTunaDownloadRadius() {
        return tunaDownloadRadius;
    }
    public void setTunaDownloadRadius(float tunaDownloadRadius) {
        setTunaDownloadRadius(TypedValue.COMPLEX_UNIT_DIP, tunaDownloadRadius);
    }
    public void setTunaDownloadRadius(int unit, float tunaDownloadRadius) {
        setTunaDownloadRadiusRaw(applyDimension(unit, tunaDownloadRadius, getViewDisplayMetrics(this)));
    }
    private void setTunaDownloadRadiusRaw(float tunaDownloadRadius) {
        if (this.tunaDownloadRadius != tunaDownloadRadius) {
            this.tunaDownloadRadius = tunaDownloadRadius;
            invalidate();
        }
    }


    private float tunaDownloadRadiusLeftTop;
    public float getTunaDownloadRadiusLeftTop() {
        return tunaDownloadRadiusLeftTop;
    }
    public void setTunaDownloadRadiusLeftTop(float tunaDownloadRadiusLeftTop) {
        setTunaDownloadRadiusLeftTop(TypedValue.COMPLEX_UNIT_DIP, tunaDownloadRadiusLeftTop);
    }

    public void setTunaDownloadRadiusLeftTop(int unit, float tunaDownloadRadiusLeftTop) {
        setTunaDownloadRadiusLeftTopRaw(applyDimension(unit, tunaDownloadRadiusLeftTop, getViewDisplayMetrics(this)));
    }

    private void setTunaDownloadRadiusLeftTopRaw(float tunaDownloadRadiusLeftTop) {
        if (this.tunaDownloadRadiusLeftTop != tunaDownloadRadiusLeftTop) {
            this.tunaDownloadRadiusLeftTop = tunaDownloadRadiusLeftTop;
            invalidate();
        }
    }

    private float tunaDownloadRadiusLeftBottom;
    public float getTunaDownloadRadiusLeftBottom() {
        return tunaDownloadRadiusLeftBottom;
    }
    public void setTunaRadiusLeftBottom(float tunaDownloadRadiusLeftBottom) {
        setTunaDownloadRadiusLeftBottom(TypedValue.COMPLEX_UNIT_DIP, tunaDownloadRadiusLeftBottom);
    }

    public void setTunaDownloadRadiusLeftBottom(int unit, float tunaDownloadRadiusLeftBottom) {
        setTunaDownloadRadiusLeftBottomRaw(applyDimension(unit, tunaDownloadRadiusLeftBottom, getViewDisplayMetrics(this)));
    }

    private void setTunaDownloadRadiusLeftBottomRaw(float tunaDownloadRadiusLeftBottom) {
        if (this.tunaDownloadRadiusLeftBottom != tunaDownloadRadiusLeftBottom) {
            this.tunaDownloadRadiusLeftBottom = tunaDownloadRadiusLeftBottom;
            invalidate();
        }
    }

    private float tunaDownloadRadiusRightTop;
    public float getTunaDownloadRadiusRightTop() {
        return tunaDownloadRadiusRightTop;
    }
    public void setDownloadRadiusRightTop(float tunaDownloadRadiusRightTop) {
        setTunaDownloadRadiusRightTop(TypedValue.COMPLEX_UNIT_DIP, tunaDownloadRadiusRightTop);
    }

    public void setTunaDownloadRadiusRightTop(int unit, float tunaDownloadRadiusRightTop) {
        setTunaDownloadRadiusRightTopRaw(applyDimension(unit, tunaDownloadRadiusRightTop, getViewDisplayMetrics(this)));
    }

    private void setTunaDownloadRadiusRightTopRaw(float tunaDownloadRadiusRightTop) {
        if (this.tunaDownloadRadiusRightTop != tunaDownloadRadiusRightTop) {
            this.tunaDownloadRadiusRightTop = tunaDownloadRadiusRightTop;
            invalidate();
        }
    }

    private float tunaDownloadRadiusRightBottom;
    public float getTunaDownloadRadiusRightBottom() {
        return tunaDownloadRadiusRightBottom;
    }
    public void setTunaDownloadRadiusRightBottom(float tunaDownloadRadiusRightBottom) {
        setTunaDownloadRadiusRightBottom(TypedValue.COMPLEX_UNIT_DIP, tunaDownloadRadiusRightBottom);
    }

    public void setTunaDownloadRadiusRightBottom(int unit, float tunaDownloadRadiusRightBottom) {
        setTunaDownloadRadiusRightBottomRaw(applyDimension(unit, tunaDownloadRadiusRightBottom, getViewDisplayMetrics(this)));
    }

    private void setTunaDownloadRadiusRightBottomRaw(float tunaDownloadRadiusRightBottom) {
        if (this.tunaDownloadRadiusRightBottom != tunaDownloadRadiusRightBottom) {
            this.tunaDownloadRadiusRightBottom = tunaDownloadRadiusRightBottom;
            invalidate();
        }
    }

    private boolean tunaDownloadError;
    public boolean isTunaDownloadError() {
        return tunaDownloadError;
    }

    public void setTunaDownloadError(boolean tunaDownloadError) {
        this.tunaDownloadError = tunaDownloadError;
        invalidate();
    }

    private boolean tunaDownInterceptFlag;
    public boolean isTunaDownInterceptFlag() {
        return tunaDownInterceptFlag;
    }
    public void setTunaDownInterceptFlag(boolean tunaDownInterceptFlag) {
        this.tunaDownInterceptFlag = tunaDownInterceptFlag;
    }

    private static final int BUF_SIZE = 1024;
    private static final int TIMEOUT = 10000;

    private static final String TUNADOWN_LOADSUFFIX = ".tmp";

    private static final int DOWN_START = 0;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_COMPLETE = 2;
    private static final int DOWN_EXIST = 3;
    private static final int DOWN_ERROR = 4;

    private File tunaDownloadfile;

    private int tunaDownloadBackgroundNormal;

    private int tunaDownloadGraphicsSrcFillId;

    private float tunaDownloadGraphicsSrcFillFractionTop;

    public float getTunaDownloadGraphicsSrcFillFractionTop() {
        return tunaDownloadGraphicsSrcFillFractionTop;
    }

    public void setTunaDownloadGraphicsSrcFillFractionTop(float tunaDownloadGraphicsSrcFillFractionTop) {
        this.tunaDownloadGraphicsSrcFillFractionTop = tunaDownloadGraphicsSrcFillFractionTop;
        invalidate();
    }

    private float tunaDownloadGraphicsSrcFillFractionBottom;

    public float getTunaDownloadGraphicsSrcFillFractionBottom() {
        return tunaDownloadGraphicsSrcFillFractionBottom;
    }

    public void setTunaDownloadGraphicsSrcFillFractionBottom(float tunaDownloadGraphicsSrcFillFractionBottom) {
        this.tunaDownloadGraphicsSrcFillFractionBottom = tunaDownloadGraphicsSrcFillFractionBottom;
        invalidate();
    }


    //
    private boolean tunaDownloadMark;
    public boolean isTunaDownloadMark() {
        return tunaDownloadMark;
    }
    public void setTunaDownloadMark(boolean tunaDownloadMark) {
        this.tunaDownloadMark = tunaDownloadMark;
        invalidate();
    }

    //
    private int tunaDownloadMarkGraphicsId;
    private float tunaDownloadMarkGraphicsSrcWidth;

    //
    public static final int LEFT = 0x00000000;
    public static final int CENTER_HORIZONTAL = 0x00000001;
    public static final int RIGHT = CENTER_HORIZONTAL << 1;
    public static final int TOP = 0x00000000;
    public static final int CENTER_VERTICAL = RIGHT << 1;
    public static final int BOTTOM = CENTER_VERTICAL << 1;
    public static final int CENTER = CENTER_HORIZONTAL | CENTER_VERTICAL;
    public static final int GRAVITY_MASK = CENTER_HORIZONTAL | RIGHT | CENTER_VERTICAL | BOTTOM;

    //
    private int tunaDownloadMarkGravity;
    public int getTunaDownloadMarkGravity(){
        return tunaDownloadMarkGravity;
    }
    public void setSrcAnchorGravity(int tunaSrcAnchorGravity){
        this.tunaDownloadMarkGravity = tunaSrcAnchorGravity;
        invalidate();
    }

    //
    private int tunaDownloadGraphicsSrcFrontId, tunaDownloadGraphicsSrcBackId;


    private float tunaDownloadGraphicsSrcFrontFractionTop;

    public float getTunaDownloadGraphicsSrcFrontFractionTop() {
        return tunaDownloadGraphicsSrcFrontFractionTop;
    }

    public void setTunaDownloadGraphicsSrcFrontFractionTop(float tunaDownloadGraphicsSrcFrontFractionTop) {
        this.tunaDownloadGraphicsSrcFrontFractionTop = tunaDownloadGraphicsSrcFrontFractionTop;
        invalidate();
    }

    private float tunaDownloadGraphicsSrcFrontFractionBottom;

    public float getTunaDownloadGraphicsSrcFrontFractionBottom() {
        return tunaDownloadGraphicsSrcFrontFractionBottom;
    }

    public void setTunaDownloadGraphicsSrcFrontFractionBottom(float tunaDownloadGraphicsSrcFrontFractionBottom) {
        this.tunaDownloadGraphicsSrcFrontFractionBottom = tunaDownloadGraphicsSrcFrontFractionBottom;
        invalidate();
    }


    //
    private String tunaDownloadTitleTextValue;

    private float tunaDownloadTitleTextSize;

    private int tunaDownloadTitleTextColorNormal;

    private float tunaDownloadTitleFractionVertical;

    public float getTunaDownloadTitleFractionVertical() {
        return tunaDownloadTitleFractionVertical;
    }

    public void setTunaDownloadTitleFractionVertical(float tunaDownloadTitleFractionVertical) {
        this.tunaDownloadTitleFractionVertical = tunaDownloadTitleFractionVertical;
        invalidate();
    }

    //
    private String tunaDownloadContentTextValue;
    private float tunaDownloadContentTextSize;
    private int tunaDownloadContentTextColorNormal;

    private float tunaDownloadContentFractionVertical;

    public float getTunaDownloadContentFractionVertical() {
        return tunaDownloadContentFractionVertical;
    }

    public void setTunaDownloadContentFractionVertical(float tunaDownloadContentFractionVertical) {
        this.tunaDownloadContentFractionVertical = tunaDownloadContentFractionVertical;
        invalidate();
    }

    //
    private String tunaDownloadContentMarkTextValue;
    public String getTunaDownloadContentMarkTextValue() {
        return tunaDownloadContentMarkTextValue;
    }
    public void setTunaDownloadContentMarkTextValue(String tunaDownloadContentMarkTextValue) {
        this.tunaDownloadContentMarkTextValue = tunaDownloadContentMarkTextValue;
        invalidate();
    }

    private float tunaDownloadContentMarkTextSize;

    private int tunaDownloadContentMarkTextColorNormal;

    private float tunaDownloadContentMarkFractionVertical;

    public float getTunaDownloadContentMarkFractionVertical() {
        return tunaDownloadContentMarkFractionVertical;
    }

    public void setTunaDownloadContentMarkFractionVertical(float tunaDownloadContentMarkFractionVertical) {
        this.tunaDownloadContentMarkFractionVertical = tunaDownloadContentMarkFractionVertical;
        invalidate();
    }

    private float tunaDownloadContentMarkRadius;

    private int tunaDownloadContentMarkBackgroundNormal;

    private float tunaDownloadContentMarkStrokeWidth;

    //
    private String tunaDownloadGraphicsSrcURL;
    private String tunaDownloadGraphicsSrcURLIndex;

    private int tunaDownloadGraphicsSrcURLRequestWidth;
    public int getTunaDownloadGraphicsSrcURLRequestWidth() {
        return tunaDownloadGraphicsSrcURLRequestWidth;
    }
    public void setTunaDownloadGraphicsSrcURLRequestWidth(int tunaDownloadGraphicsSrcURLRequestWidth) {
        this.tunaDownloadGraphicsSrcURLRequestWidth = tunaDownloadGraphicsSrcURLRequestWidth;
        requestLayout();
    }

    private int tunaDownloadGraphicsSrcURLRequestHeight;
    public int getTunaDownloadGraphicsSrcURLRequestHeight() {
        return tunaDownloadGraphicsSrcURLRequestHeight;
    }
    public void setTunaDownloadGraphicsSrcURLRequestHeight(int tunaDownloadGraphicsSrcURLRequestHeight) {
        this.tunaDownloadGraphicsSrcURLRequestHeight = tunaDownloadGraphicsSrcURLRequestHeight;
        requestLayout();
    }

    public void setTunaDownloadGraphicsSrcURLRequestWidthHeight(int tunaDownloadGraphicsSrcURLRequestWidth,int tunaDownloadGraphicsSrcURLRequestHeight) {
        this.tunaDownloadGraphicsSrcURLRequestWidth = tunaDownloadGraphicsSrcURLRequestWidth;
        this.tunaDownloadGraphicsSrcURLRequestHeight = tunaDownloadGraphicsSrcURLRequestHeight;
        requestLayout();
    }

    private int tunaDownloadGraphicsSrcDownloadWidth;
    private int tunaDownloadGraphicsSrcDownloadHeight;

    // other members of the variables
    private int tunaDownloadGraphicsSrcFillWidth, tunaDownloadGraphicsSrcFillHeight;
    private int tunaDownloadGraphicsSrcFrontWidth, tunaDownloadGraphicsSrcFrontHeight;

    private float tunaDownloadGraphicsScaleX, tunaDownloadGraphicsScaleY;

    private TunaDownloadType tunaDownloadType;

    public enum TunaDownloadType {
        TENSILE(0), ORIGINAL(1),;
        final int nativeInt;

        TunaDownloadType(int ni) {
            nativeInt = ni;
        }
    }

    // attention tunaPorterDuffXfermode default 0 instead of -1!
    protected PorterDuffXfermode tunaDownloadPorterDuffXfermode;

    public enum TunaDownloadPorterDuffXfermode {
        SRC_IN(0), SRC_OUT(1),;
        final int nativeInt;

        TunaDownloadPorterDuffXfermode(int ni) {
            nativeInt = ni;
        }
    }

    private static final Mode[] tunaDownloadPorterDuffXfermodeArray = {PorterDuff.Mode.SRC_IN, PorterDuff.Mode.SRC_OUT,};

    public PorterDuffXfermode getTunaDownloadPorterDuffXfermode() {
        return tunaPorterDuffXfermode;
    }

    public void setTunaDownloadPorterDuffXfermode(TunaDownloadPorterDuffXfermode tunaDownloadPorterDuffXfermode) {
        this.tunaDownloadPorterDuffXfermode = new PorterDuffXfermode(tunaDownloadPorterDuffXfermodeArray[tunaDownloadPorterDuffXfermode.nativeInt]);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_START:
                    tunaDownloadError = false;
                    getTunaDownloadGraphicsSrc();
                    break;
                case DOWN_UPDATE:
                    setTunaDownloadPercent(tunaDownloadPercent);
                    break;
                case DOWN_COMPLETE:
                    if ( tunaDownloadCompleteListener != null) {
                        tunaDownloadCompleteListener.tunaDownloadComplete();
                    }
                    invalidate();
                    break;
                case DOWN_EXIST:
                    if ( tunaDownloadCompleteListener != null) {
                        tunaDownloadCompleteListener.tunaDownloadComplete();
                    }
                    break;
                case DOWN_ERROR:
                    setTunaDownloadError(true);
                    break;
            }
        }
    };

    private String graphicsType;
    private GifDrawable gifDrawable;


    //
    protected TunaDownloadCompleteListener tunaDownloadCompleteListener;

    public interface TunaDownloadCompleteListener {
        void tunaDownloadComplete();
    }

    public TunaDownloadCompleteListener getTunaDownloadCompleteListener() {
        return tunaDownloadCompleteListener;
    }

    public void setTunaDownloadCompleteListener(TunaDownloadCompleteListener tunaDownloadCompleteListener) {
        this.tunaDownloadCompleteListener = tunaDownloadCompleteListener;
    }

    public TunaDownload(Context context) {
        this(context, null);
    }

    public TunaDownload(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaDownload(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        tunaTag = TunaDownload.class.getSimpleName();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaDownload);

        tunaDownloadCacheFolder = typedArray.getString(R.styleable.TunaDownload_tunaDownloadCacheFolder);
        tunaDownloadCacheCheck = typedArray.getBoolean(R.styleable.TunaDownload_tunaDownloadCacheCheck, false);

        int tunaDownloadTypeIndex = typedArray.getInt(R.styleable.TunaDownload_tunaDownloadType, 0);
        if (tunaDownloadTypeIndex >= 0) {
            tunaDownloadType = tunaDownloadTypeArray[tunaDownloadTypeIndex];
        }

        tunaDownloadBackgroundNormal = typedArray.getColor(R.styleable.TunaDownload_tunaDownloadBackgroundNormal, Color.TRANSPARENT);

        tunaDownloadGraphicsSrcFillId = typedArray.getResourceId(R.styleable.TunaDownload_tunaDownloadGraphicsSrcFill, -1);

        tunaDownloadGraphicsSrcFillFractionTop = typedArray.getFraction(R.styleable.TunaDownload_tunaDownloadGraphicsSrcFillFractionTop, 1, 1, 0);
        tunaDownloadGraphicsSrcFillFractionBottom = typedArray.getFraction(R.styleable.TunaDownload_tunaDownloadGraphicsSrcFillFractionBottom, 1, 1, 1);
        if (tunaDownloadGraphicsSrcFillFractionBottom - tunaDownloadGraphicsSrcFillFractionTop <= 0){
            throw new IllegalArgumentException("The content attribute tunaDownloadGraphicsSrcFillFractionBottom must be greater than tunaDownloadGraphicsSrcFillFractionTop");
        }

        int tunaDownloadMarkGraphicsSrcId = typedArray.getResourceId(R.styleable.TunaDownload_tunaDownloadMarkGraphicsSrc, -1);
        if (tunaDownloadMarkGraphicsSrcId != -1) {
            this.tunaDownloadMarkGraphicsId = tunaDownloadMarkGraphicsSrcId;
            tunaDownloadMark = typedArray.getBoolean(R.styleable.TunaDownload_tunaDownloadMark, false);
            tunaDownloadMarkGraphicsSrcWidth = typedArray.getDimension(R.styleable.TunaDownload_tunaDownloadMarkGraphicsSrcWidth, 0);
            //
            tunaDownloadMarkGravity = typedArray.getInt(R.styleable.TunaDownload_tunaDownloadMarkGravity, 0);
        }

        tunaDownloadGraphicsSrcFrontId = typedArray.getResourceId(R.styleable.TunaDownload_tunaDownloadGraphicsSrcFront, -1);
        tunaDownloadGraphicsSrcBackId = typedArray.getResourceId(R.styleable.TunaDownload_tunaDownloadGraphicsSrcBack, -1);

        tunaDownloadGraphicsSrcFrontFractionTop = typedArray.getFraction(R.styleable.TunaDownload_tunaDownloadGraphicsSrcFrontFractionTop, 1, 1, 0);
        tunaDownloadGraphicsSrcFrontFractionBottom = typedArray.getFraction(R.styleable.TunaDownload_tunaDownloadGraphicsSrcFrontFractionBottom, 1, 1, 1);
        if (tunaDownloadGraphicsSrcFrontFractionBottom - tunaDownloadGraphicsSrcFrontFractionTop <= 0){
            throw new IllegalArgumentException("The content attribute tunaDownloadGraphicsSrcFrontFractionBottom must be greater than tunaDownloadGraphicsSrcFrontFractionTop");
        }


        tunaDownloadTitleTextValue = typedArray.getString(R.styleable.TunaDownload_tunaDownloadTitleTextValue);
        tunaDownloadTitleTextSize = typedArray.getDimension(R.styleable.TunaDownload_tunaDownloadTitleTextSize, 0);
        tunaDownloadTitleTextColorNormal = typedArray.getColor(R.styleable.TunaDownload_tunaDownloadTitleTextColorNormal, Color.TRANSPARENT);
        tunaDownloadTitleFractionVertical = typedArray.getFraction(R.styleable.TunaDownload_tunaDownloadTitleFractionVertical, 1, 1, 0);

        tunaDownloadContentTextValue = typedArray.getString(R.styleable.TunaDownload_tunaDownloadContentTextValue);
        tunaDownloadContentTextSize = typedArray.getDimension(R.styleable.TunaDownload_tunaDownloadContentTextSize, 0);
        tunaDownloadContentTextColorNormal = typedArray.getColor(R.styleable.TunaDownload_tunaDownloadContentTextColorNormal, Color.TRANSPARENT);
        tunaDownloadContentFractionVertical = typedArray.getFraction(R.styleable.TunaDownload_tunaDownloadContentFractionVertical, 1, 1, 0);


        tunaDownloadContentMarkTextValue = typedArray.getString(R.styleable.TunaDownload_tunaDownloadContentMarkTextValue);
        tunaDownloadContentMarkTextSize = typedArray.getDimension(R.styleable.TunaDownload_tunaDownloadContentMarkTextSize, 0);
        tunaDownloadContentMarkTextColorNormal = typedArray.getColor(R.styleable.TunaDownload_tunaDownloadContentMarkTextColorNormal, Color.TRANSPARENT);
        tunaDownloadContentMarkFractionVertical = typedArray.getFraction(R.styleable.TunaDownload_tunaDownloadContentMarkFractionVertical, 1, 1, 0);

        tunaDownloadContentMarkBackgroundNormal = typedArray.getColor(R.styleable.TunaDownload_tunaDownloadContentMarkBackgroundNormal, Color.TRANSPARENT);
        tunaDownloadContentMarkStrokeWidth = typedArray.getDimension(R.styleable.TunaDownload_tunaDownloadContentMarkStrokeWidth, 0);
        tunaDownloadContentMarkRadius = typedArray.getDimension(R.styleable.TunaDownload_tunaDownloadContentMarkRadius, 0);

        tunaDownloadGraphicsSrcURLRequestWidth = typedArray.getInt(R.styleable.TunaDownload_tunaDownloadGraphicsSrcURLRequestWidth, 0);
        tunaDownloadGraphicsSrcURLRequestHeight = typedArray.getInt(R.styleable.TunaDownload_tunaDownloadGraphicsSrcURLRequestHeight, 0);

        tunaDownloadRadius = typedArray.getDimension(R.styleable.TunaDownload_tunaDownloadRadius, 0);
        tunaDownloadRadiusLeftTop = typedArray.getDimension(R.styleable.TunaDownload_tunaDownloadRadiusLeftTop, 0);
        tunaDownloadRadiusLeftBottom = typedArray.getDimension(R.styleable.TunaDownload_tunaDownloadRadiusLeftBottom, 0);
        tunaDownloadRadiusRightTop = typedArray.getDimension(R.styleable.TunaDownload_tunaDownloadRadiusRightTop, 0);
        tunaDownloadRadiusRightBottom = typedArray.getDimension(R.styleable.TunaDownload_tunaDownloadRadiusRightBottom, 0);

        if (tunaDownloadRadius > 0 || tunaDownloadRadiusLeftTop >0 || tunaDownloadRadiusLeftBottom > 0 || tunaDownloadRadiusRightTop > 0 || tunaDownloadRadiusRightBottom > 0) {
            // tunaPorterDuffXfermodeIndex default PorterDuff.Mode.SRC_IN
            int tunaDownloadPorterDuffXfermodeIndex = typedArray.getInt(R.styleable.TunaDownload_tunaDownloadPorterDuffXfermode, 0);
            tunaDownloadPorterDuffXfermode = new PorterDuffXfermode(tunaDownloadPorterDuffXfermodeArray[tunaDownloadPorterDuffXfermodeIndex]);
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
            if (tunaDownloadGraphicsSrcURLRequestWidth != 0 || tunaDownloadGraphicsSrcURLRequestHeight != 0) {
                float widthByHeightRatio = tunaDownloadGraphicsSrcURLRequestWidth * 1f / tunaDownloadGraphicsSrcURLRequestHeight;
                measuredHeight = (int) (measuredWidth / widthByHeightRatio / (tunaDownloadGraphicsSrcFillFractionBottom - tunaDownloadGraphicsSrcFillFractionTop));
            }
        } else if (specModeHeight == MeasureSpec.EXACTLY) {// match_parent
            measuredHeight = specSizeHeight;
        } else if (specModeHeight == MeasureSpec.UNSPECIFIED) {// unspecified
            if (tunaDownloadGraphicsSrcURLRequestWidth != 0 || tunaDownloadGraphicsSrcURLRequestHeight != 0) {
                float widthByHeightRatio = tunaDownloadGraphicsSrcURLRequestWidth * 1f / tunaDownloadGraphicsSrcURLRequestHeight;
                measuredHeight = (int) (measuredWidth / widthByHeightRatio / (tunaDownloadGraphicsSrcFillFractionBottom - tunaDownloadGraphicsSrcFillFractionTop));
            }
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //setLayerType (View.LAYER_TYPE_SOFTWARE, null) confilct with dynamically onMeasure!
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (GRAPHICSTYPE_GIF.equals(graphicsType)) {
            setBackground(gifDrawable);
        } else {

            boolean needSaveLayer = (tunaDownloadRadius > 0 || tunaDownloadRadiusLeftTop >0 || tunaDownloadRadiusLeftBottom > 0 || tunaDownloadRadiusRightTop > 0 || tunaDownloadRadiusRightBottom > 0);
            if (needSaveLayer) {
                tunaLayer = canvas.saveLayer(0, 0, tunaWidth, tunaHeight, null);
                // If not, then a given color can not be displayed after the image corners interception
                if (tunaDownloadBackgroundNormal == Color.TRANSPARENT) {
                    tunaDownloadBackgroundNormal = Color.WHITE;
                }

                tunaClassic = (tunaDownloadRadius == tunaDownloadRadiusLeftTop && tunaDownloadRadiusLeftTop == tunaDownloadRadiusLeftBottom &&
                        tunaDownloadRadiusLeftBottom == tunaDownloadRadiusRightTop && tunaDownloadRadiusRightTop == tunaDownloadRadiusRightBottom);

                if (tunaClassic) {
                    drawTunaRectClassic(canvas, tunaWidth, tunaHeight, tunaDownloadBackgroundNormal, tunaDownloadRadius);
                } else {
                    drawTunaRectCustom(canvas, tunaWidth, tunaHeight, tunaDownloadBackgroundNormal,
                            tunaDownloadRadiusLeftTop, tunaDownloadRadiusLeftBottom, tunaDownloadRadiusRightTop, tunaDownloadRadiusRightBottom);
                }

            } else if (tunaDownloadBackgroundNormal != Color.TRANSPARENT) {
                canvas.drawColor(tunaDownloadBackgroundNormal);
            }
            if (needSaveLayer) {
                tunaPaint.setXfermode(tunaDownloadPorterDuffXfermode);
            }
            Bitmap tunaDownloadBitmapSrcFromURL = null;
            if (GRAPHICSTYPE_PNG.equals(graphicsType) || GRAPHICSTYPE_JPG.equals(graphicsType)) {
                tunaDownloadBitmapSrcFromURL = decodeBitmapFile(tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex, tunaDownloadGraphicsSrcURLRequestWidth,
                        tunaDownloadGraphicsSrcURLRequestHeight);
                if (tunaDownloadBitmapSrcFromURL == null) {
                    File delectFile = new File(tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex);
                    delectFile.delete();
                    graphicsType = null;
                    // mHandler.sendEmptyMessage(DOWN_START);
                }
            } else if (tunaDownloadGraphicsSrcURLIndex != null) {

                tunaDownloadBitmapSrcFromURL = decodeBitmapFile(tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex, tunaDownloadGraphicsSrcURLRequestWidth,
                        tunaDownloadGraphicsSrcURLRequestHeight);

                if (tunaDownloadBitmapSrcFromURL == null) {
                    File delectFile = new File(tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex);
                    delectFile.delete();
                    graphicsType = null;
                    // mHandler.sendEmptyMessage(DOWN_START);
                }
            }

            if (tunaDownloadBitmapSrcFromURL != null) {

                mHandler.sendEmptyMessage(DOWN_EXIST);

                tunaDownloadGraphicsSrcDownloadWidth = tunaDownloadBitmapSrcFromURL.getWidth();
                tunaDownloadGraphicsSrcDownloadHeight = tunaDownloadBitmapSrcFromURL.getHeight();

                //
                tunaDownloadGraphicsScaleY = tunaHeight * (tunaDownloadGraphicsSrcFillFractionBottom - tunaDownloadGraphicsSrcFillFractionTop)
                        / tunaDownloadGraphicsSrcDownloadHeight;
                float dy = tunaHeight * tunaDownloadGraphicsSrcFillFractionTop;

                //
                switch (tunaDownloadType) {
                    case TENSILE:
                        tunaDownloadGraphicsScaleX = tunaWidth * 1f / tunaDownloadGraphicsSrcDownloadWidth;

                        canvas.translate(0, dy);
                        canvas.drawBitmap(tunaDownloadBitmapSrcFromURL, initTunaMatrix(tunaDownloadGraphicsScaleX, tunaDownloadGraphicsScaleY), tunaPaint);
                        canvas.translate(-0, -dy);

                        break;
                    case ORIGINAL:
                        float dx = (tunaWidth - tunaDownloadGraphicsSrcDownloadWidth * tunaDownloadGraphicsScaleY) * 0.5f;

                        canvas.translate(dx, dy);
                        canvas.drawBitmap(tunaDownloadBitmapSrcFromURL, initTunaMatrix(tunaDownloadGraphicsScaleY, tunaDownloadGraphicsScaleY), tunaPaint);
                        canvas.translate(-dx, -dy);

                        break;
                    default:
                        break;
                }
            } else {
                Bitmap tunaDownloadGraphicsSrcFill = null;
                if (tunaDownloadGraphicsSrcFillId != -1) {
                    tunaDownloadGraphicsSrcFill = decodeBitmapResource(tunaDownloadGraphicsSrcFillId);
                }
                if (tunaDownloadGraphicsSrcFill != null) {
                    tunaDownloadGraphicsSrcFillWidth = tunaDownloadGraphicsSrcFill.getWidth();
                    tunaDownloadGraphicsSrcFillHeight = tunaDownloadGraphicsSrcFill.getHeight();
                    float fillScaleX = tunaWidth * 1f / tunaDownloadGraphicsSrcFillWidth;
                    float fillScaleY = tunaHeight * (tunaDownloadGraphicsSrcFillFractionBottom - tunaDownloadGraphicsSrcFillFractionTop) / tunaDownloadGraphicsSrcFillHeight;
                    float fillMarginLeft = 0;
                    float fillMarginTop = tunaHeight * tunaDownloadGraphicsSrcFillFractionTop;
                    canvas.save();
                    canvas.translate(fillMarginLeft, fillMarginTop);

                    canvas.drawBitmap(tunaDownloadGraphicsSrcFill, initTunaMatrix(fillScaleX, fillScaleY), tunaPaint);
                    // canvas.drawBitmap(tunaDownloadGraphicsSrcFill,
                    // initTunaMatrix(fillScaleX, fillScaleY),
                    // initTunaPaint(Paint.Style.FILL, Color.RED));

                    canvas.restore();
                }

                Bitmap tunaDownloadGraphicsSrcFront = null;
                Bitmap tunaDownloadGraphicsSrcBack = null;

                if (tunaDownloadGraphicsSrcFrontId != -1) {
                    tunaDownloadGraphicsSrcFront = decodeBitmapResource(tunaDownloadGraphicsSrcFrontId);
                }

                if (tunaDownloadGraphicsSrcBackId != -1) {
                    tunaDownloadGraphicsSrcBack = decodeBitmapResource(tunaDownloadGraphicsSrcBackId);
                }

                if (tunaDownloadGraphicsSrcFront != null && tunaDownloadGraphicsSrcBack != null) {

                    tunaDownloadGraphicsSrcFrontWidth = tunaDownloadGraphicsSrcFront.getWidth();
                    tunaDownloadGraphicsSrcFrontHeight = tunaDownloadGraphicsSrcFront.getHeight();

                    float frontScale = tunaHeight * (tunaDownloadGraphicsSrcFrontFractionBottom - tunaDownloadGraphicsSrcFrontFractionTop) / tunaDownloadGraphicsSrcFrontHeight;
                    float frontMarginLeft = (tunaWidth - tunaDownloadGraphicsSrcFrontWidth * frontScale) * 0.5f;
                    float frontMarginTop = tunaHeight * tunaDownloadGraphicsSrcFrontFractionTop;

                    canvas.save();
                    canvas.translate(frontMarginLeft, frontMarginTop);
                    canvas.clipRect(initTunaRect(0, 0, (int) (tunaDownloadGraphicsSrcFrontWidth * frontScale),
                            (int) (tunaDownloadGraphicsSrcFrontHeight * frontScale * (1 - tunaDownloadPercent))));

                    canvas.drawBitmap(tunaDownloadGraphicsSrcBack, initTunaMatrix(frontScale, frontScale), null);
                    canvas.restore();

                    canvas.save();
                    canvas.translate(frontMarginLeft, frontMarginTop);
                    canvas.clipRect(initTunaRect(0, (int) (tunaDownloadGraphicsSrcFrontHeight * frontScale * (1 - tunaDownloadPercent)),
                            (int) (tunaDownloadGraphicsSrcFrontWidth * frontScale), (int) (tunaDownloadGraphicsSrcFrontHeight * frontScale)));
                    canvas.drawBitmap(tunaDownloadGraphicsSrcFront, tunaMatrix, null);
                    canvas.restore();

                }
                // if (!tunaDownloadError) {
                // }else {
                // }
            }

            if (tunaDownloadMark) {
                Bitmap tunaDownloadGraphicsMark = null;
                if (tunaDownloadMarkGraphicsId != -1) {
                    tunaDownloadGraphicsMark = decodeBitmapResource(tunaDownloadMarkGraphicsId);
                }
                if (tunaDownloadGraphicsMark != null) {

                    //
                    float tunaDownloadGraphicsMarkScaleX = tunaDownloadMarkGraphicsSrcWidth / (tunaDownloadGraphicsMark.getWidth());
                    float tunaDownloadMarkGraphicsSrcHeight = tunaDownloadGraphicsMark.getWidth() * tunaDownloadGraphicsMarkScaleX;
                    float radiusArrary[] = {0, 0, 0, 0};

                    float anchorDx = 0, anchorDy = 0;
                    switch (tunaDownloadMarkGravity & GRAVITY_MASK) {
                        case LEFT | TOP:
                            radiusArrary[0] = tunaDownloadRadius >= tunaDownloadRadiusLeftTop ? tunaDownloadRadius : tunaDownloadRadiusLeftTop;
                            break;

                        case LEFT | BOTTOM:
                            anchorDy = tunaHeight - tunaDownloadMarkGraphicsSrcHeight;
                            radiusArrary[1] = tunaDownloadRadius >= tunaDownloadRadiusLeftBottom ? tunaDownloadRadius : tunaDownloadRadiusLeftBottom;
                            break;

                        case CENTER_HORIZONTAL:
                            anchorDx = (tunaWidth >> 1) - tunaDownloadMarkGraphicsSrcWidth * 0.5f;
                            break;
                        case RIGHT | TOP:
                            anchorDx = tunaWidth - tunaDownloadMarkGraphicsSrcWidth;
                            radiusArrary[2] = tunaDownloadRadius >= tunaDownloadRadiusRightTop ? tunaDownloadRadius : tunaDownloadRadiusRightTop;
                            break;
                        case RIGHT | BOTTOM:
                            anchorDx = tunaWidth - tunaDownloadMarkGraphicsSrcWidth;
                            anchorDy = tunaHeight - tunaDownloadMarkGraphicsSrcHeight;
                            radiusArrary[3] = tunaDownloadRadius >= tunaDownloadRadiusRightBottom ? tunaDownloadRadius : tunaDownloadRadiusRightBottom;
                            break;
                        case CENTER_VERTICAL:
                            anchorDy = (tunaHeight >> 1) - tunaDownloadMarkGraphicsSrcHeight * 0.5f;
                            break;
                        case LEFT | CENTER:
                            anchorDx = (tunaWidth >> 1) - tunaDownloadMarkGraphicsSrcWidth * 0.5f;
                            anchorDy = (tunaHeight >> 1) - tunaDownloadMarkGraphicsSrcHeight * 0.5f;
                            break;
                        case RIGHT | CENTER_VERTICAL:
                            anchorDx = tunaWidth - tunaDownloadMarkGraphicsSrcWidth;
                            anchorDy = (tunaHeight >> 1) - tunaDownloadMarkGraphicsSrcHeight * 0.5f;
                            break;
                        case BOTTOM | CENTER_HORIZONTAL:
                            anchorDx = (tunaWidth >> 1) - tunaDownloadMarkGraphicsSrcWidth * 0.5f;
                            anchorDy = tunaHeight - tunaDownloadMarkGraphicsSrcHeight;
                            break;
                        default:
                            break;
                    }

                    canvas.translate(anchorDx, anchorDy);
                    if (tunaDownloadRadius > 0) {
                        canvas.drawBitmap(getCustomRoundBitmap(getScaleBitmap(tunaDownloadGraphicsMark, tunaDownloadGraphicsMarkScaleX), radiusArrary[0], radiusArrary[1], radiusArrary[2], radiusArrary[3]), 0, 0, null);
                    } else {
                        canvas.drawBitmap(tunaDownloadGraphicsMark, initTunaMatrix(tunaDownloadGraphicsMarkScaleX, tunaDownloadGraphicsMarkScaleX), new Paint(Paint.ANTI_ALIAS_FLAG));
                    }
                    canvas.translate(-anchorDx, -anchorDy);

                }
            }

            //
            if (tunaDownloadTitleTextValue != null) {
                drawTunaText(canvas, tunaDownloadTitleTextValue, tunaWidth, tunaWidth >> 1, tunaHeight * tunaDownloadTitleFractionVertical, 0, 0,
                        initTunaTextPaint(Paint.Style.FILL, tunaDownloadTitleTextColorNormal, tunaDownloadTitleTextSize, Paint.Align.CENTER));
            }

            //
            if (tunaDownloadContentTextValue != null) {
                drawTunaText(canvas, tunaDownloadContentTextValue, tunaWidth, tunaWidth >> 1, tunaHeight * tunaDownloadContentFractionVertical, 0, 0,
                        initTunaTextPaint(Paint.Style.FILL, tunaDownloadContentTextColorNormal, tunaDownloadContentTextSize, Paint.Align.CENTER));
            }

            //
            if (tunaDownloadContentMarkTextValue != null) {
                float centerY = tunaHeight * tunaDownloadContentMarkFractionVertical;
                float markLeftX = (tunaWidth >> 1) - initTunaTextPaint(tunaDownloadContentTextSize).measureText(tunaDownloadContentTextValue);
                float markRightX = markLeftX + initTunaTextPaint(tunaDownloadContentMarkTextSize).measureText(tunaDownloadContentMarkTextValue);

                canvas.drawRoundRect(
                        initTunaRectF(markLeftX - tunaDownloadContentMarkStrokeWidth,
                                centerY - tunaDownloadContentMarkTextSize * 0.5f - tunaDownloadContentMarkStrokeWidth,
                                markRightX + tunaDownloadContentMarkStrokeWidth,
                                centerY + tunaDownloadContentMarkTextSize * 0.5f + tunaDownloadContentMarkStrokeWidth),
                        tunaDownloadContentMarkRadius,
                        tunaDownloadContentMarkRadius,
                        initTunaTextPaint(Paint.Style.FILL, tunaDownloadContentMarkBackgroundNormal, tunaDownloadContentMarkTextSize, Paint.Align.LEFT));

                drawTunaText(canvas, tunaDownloadContentMarkTextValue, tunaWidth, markLeftX, centerY, 0, 0,
                        initTunaTextPaint(Paint.Style.FILL, tunaDownloadContentMarkTextColorNormal, tunaDownloadContentMarkTextSize, Paint.Align.LEFT));
            }
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    public void init(HashMap<String, Object> tunaDownloadGraphicsMap, String tunaDownloadGraphicsSrcURL) {
        init(tunaDownloadGraphicsMap, tunaDownloadGraphicsSrcURL, false, null, null, null);
    }

    public void init(HashMap<String, Object> tunaDownloadGraphicsMap, String tunaDownloadGraphicsSrcURL, boolean tunaDownloadMark) {
        init(tunaDownloadGraphicsMap, tunaDownloadGraphicsSrcURL, tunaDownloadMark, null, null, null);
    }

    public void init(HashMap<String, Object> tunaDownloadGraphicsMap, String tunaDownloadGraphicsSrcURL, String tunaDownloadTitleTextValue) {
        init(tunaDownloadGraphicsMap, tunaDownloadGraphicsSrcURL, false, tunaDownloadTitleTextValue, null, null);
    }

    public void init(HashMap<String, Object> tunaDownloadGraphicsMap, String tunaDownloadGraphicsSrcURL, String tunaDownloadTitleTextValue, String tunaDownloadContentTextValue) {
        init(tunaDownloadGraphicsMap, tunaDownloadGraphicsSrcURL, false, tunaDownloadTitleTextValue, null, tunaDownloadContentTextValue);
    }

    public void init(HashMap<String, Object> tunaDownloadGraphicsMap, String tunaDownloadGraphicsSrcURL, String tunaDownloadTitleTextValue, String tunaDownloadContentMarkTextValue,
                     String tunaDownloadContentTextValue) {
        init(tunaDownloadGraphicsMap, tunaDownloadGraphicsSrcURL, false, tunaDownloadTitleTextValue, tunaDownloadContentMarkTextValue, tunaDownloadContentTextValue);
    }

    public void init(HashMap<String, Object> tunaDownloadGraphicsMap, String tunaDownloadGraphicsSrcURL, boolean tunaDownloadMark, String tunaDownloadTitleTextValue,
                     String tunaDownloadContentMarkTextValue, String tunaDownloadContentTextValue) {

        tunaGraphicsMap = tunaDownloadGraphicsMap;

        //
        if (tunaDownloadCacheFolder == null) {
            throw new IllegalArgumentException("The content attribute tunaDownloadCacheFolder type must be given");
        } else {
            tunaDownloadCacheFolderDirectory = Environment.getExternalStorageDirectory() + "/" + tunaDownloadCacheFolder + "/";
        }

        if (tunaWidth == 0 || tunaHeight == 0) {

            this.tunaDownloadGraphicsSrcURL = tunaDownloadGraphicsSrcURL;
            this.tunaDownloadMark = tunaDownloadMark;

            this.tunaDownloadTitleTextValue = tunaDownloadTitleTextValue;
            this.tunaDownloadContentTextValue = tunaDownloadContentTextValue;
            this.tunaDownloadContentMarkTextValue = tunaDownloadContentMarkTextValue;

            mHandler.sendEmptyMessageDelayed(DOWN_START, 0);

        } else if (graphicsType == null || !this.tunaDownloadGraphicsSrcURL.equals(tunaDownloadGraphicsSrcURL)) {

            this.tunaDownloadGraphicsSrcURL = tunaDownloadGraphicsSrcURL;
            this.tunaDownloadMark = tunaDownloadMark;

            this.tunaDownloadTitleTextValue = tunaDownloadTitleTextValue;
            this.tunaDownloadContentTextValue = tunaDownloadContentTextValue;
            this.tunaDownloadContentMarkTextValue = tunaDownloadContentMarkTextValue;

            mHandler.sendEmptyMessage(DOWN_START);
        }
    }

    private void getTunaDownloadGraphicsSrc() {
        // if there SD card, then the precedence established cache Pictures
        // folder to avoid ImageLoadTask the folder does not exist
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File fileCacheFolderDirectory = new File(tunaDownloadCacheFolderDirectory);
            if (!fileCacheFolderDirectory.exists()) {
                fileCacheFolderDirectory.mkdir();
            }
        }

        // File name can not bring inside, and: when will complain otherwise
        // created
        if (tunaDownloadGraphicsSrcURL != null) {
            tunaDownloadGraphicsSrcURLIndex = tunaDownloadGraphicsSrcURL.replaceAll("/", "").replaceAll(":", "");
        }

        tunaDownloadfile = new File(tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex);

        if (tunaDownloadCacheCheck) {
            new Thread() {
                public void run() {

                    InputStream is = null;
                    FileOutputStream fos = null;

                    try {
                        URL url = new URL(tunaDownloadGraphicsSrcURL);
                        HttpURLConnection httpURLConn = (HttpURLConnection) url.openConnection();

                        httpURLConn.setConnectTimeout(TIMEOUT);
                        httpURLConn.setReadTimeout(TIMEOUT);

                        httpURLConn.setRequestProperty("Accept-Encoding", "identity");

                        int contentLength = httpURLConn.getContentLength();
                        if (tunaDownloadfile.exists() && tunaDownloadfile.length() == contentLength) {
                            graphicsType = getGraphicsType(tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex);
                            if (GRAPHICSTYPE_GIF.equals(graphicsType)) {
                                try {
                                    gifDrawable = new GifDrawable(tunaDownloadfile);
                                    mHandler.sendEmptyMessage(DOWN_COMPLETE);
                                    return;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                mHandler.sendEmptyMessage(DOWN_COMPLETE);
                            }
                        } else {

                            String cacheFileName = tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex;
                            String tmpFileName = cacheFileName + TUNADOWN_LOADSUFFIX;

                            is = httpURLConn.getInputStream();
                            fos = new FileOutputStream(tmpFileName);

                            int count = 0;
                            byte buf[] = new byte[BUF_SIZE];
                            do {
                                int numread = is.read(buf);
                                count += numread;
                                tunaDownloadPercent = count * 1f / contentLength;
                                mHandler.sendEmptyMessage(DOWN_UPDATE);
                                if (numread <= 0) {
                                    File tmpFile = new File(tmpFileName);
                                    if (!tmpFile.renameTo(new File(cacheFileName))) {
                                        mHandler.sendEmptyMessage(DOWN_ERROR);
                                    }

                                    graphicsType = getGraphicsType(tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex);
                                    if (GRAPHICSTYPE_GIF.equals(graphicsType)) {
                                        try {
                                            gifDrawable = new GifDrawable(tunaDownloadfile);
                                            mHandler.sendEmptyMessage(DOWN_COMPLETE);
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        mHandler.sendEmptyMessage(DOWN_COMPLETE);
                                    }
                                    break;
                                }
                                fos.write(buf, 0, numread);
                                // if need cancel function to setting this to
                                // stop downloading should change the
                                // interceptFlag
                            } while (!tunaDownInterceptFlag);
                            fos.close();
                            is.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            if (is != null) {
                                is.close();
                            }
                            if (fos != null) {
                                fos.close();
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(DOWN_ERROR);
                    }
                }
            }.start();

        } else {

            new Thread() {
                public void run() {
                    InputStream is = null;
                    FileOutputStream fos = null;
                    try {
                        if (tunaDownloadfile.exists()) {
                            graphicsType = getGraphicsType(tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex);

                            if (GRAPHICSTYPE_GIF.equals(graphicsType)) {
                                try {
                                    gifDrawable = new GifDrawable(tunaDownloadfile);
                                    mHandler.sendEmptyMessage(DOWN_COMPLETE);
                                    return;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                mHandler.sendEmptyMessage(DOWN_COMPLETE);
                            }
                        } else {
                            URL url = new URL(tunaDownloadGraphicsSrcURL);
                            HttpURLConnection httpURLConn = (HttpURLConnection) url.openConnection();

                            httpURLConn.setConnectTimeout(TIMEOUT);
                            httpURLConn.setReadTimeout(TIMEOUT);

                            httpURLConn.setRequestProperty("Accept-Encoding", "identity");

                            int contentLength = httpURLConn.getContentLength();

                            String cacheFileName = tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex;
                            String tmpFileName = cacheFileName + TUNADOWN_LOADSUFFIX;

                            is = httpURLConn.getInputStream();
                            fos = new FileOutputStream(tmpFileName);

                            int count = 0;
                            byte buf[] = new byte[BUF_SIZE];
                            do {
                                int numread = is.read(buf);
                                count += numread;
                                tunaDownloadPercent = count * 1f / contentLength;
                                mHandler.sendEmptyMessage(DOWN_UPDATE);
                                if (numread <= 0) {
                                    File tmpFile = new File(tmpFileName);
                                    if (!tmpFile.renameTo(new File(cacheFileName))) {
                                        mHandler.sendEmptyMessage(DOWN_ERROR);
                                    }
                                    //
                                    graphicsType = getGraphicsType(tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex);
                                    if (GRAPHICSTYPE_GIF.equals(graphicsType)) {
                                        try {
                                            gifDrawable = new GifDrawable(tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex);
                                            mHandler.sendEmptyMessage(DOWN_COMPLETE);
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        mHandler.sendEmptyMessage(DOWN_COMPLETE);
                                    }
                                    break;
                                }
                                fos.write(buf, 0, numread);
                                // if need cancel function to setting this to
                                // stop
                                // downloading should change the interceptFlag
                            } while (!tunaDownInterceptFlag);
                            fos.close();
                            is.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            if (is != null) {
                                is.close();
                            }
                            if (fos != null) {
                                fos.close();
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(DOWN_ERROR);
                    }
                }
            }.start();
        }
    }

    //
    public static void cache(String tunaDownloadCacheFolder, final String tunaDownloadGraphicsSrcURL) {
        cache(tunaDownloadCacheFolder, tunaDownloadGraphicsSrcURL, false);
    }


    //
    public static void cache(String tunaDownloadCacheFolder, final String tunaDownloadGraphicsSrcURL, boolean needCleanCache) {

        final String tunaDownloadCacheFolderDirectory = Environment.getExternalStorageDirectory() + "/" + tunaDownloadCacheFolder + "/";
        final String tunaDownloadGraphicsSrcURLIndex = tunaDownloadGraphicsSrcURL.replaceAll("/", "").replaceAll(":", "");
        final File tunaDownloadCachefile = new File(tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex);

        //
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File fileCacheFolderDirectory = new File(tunaDownloadCacheFolderDirectory);
            if (!fileCacheFolderDirectory.exists()) {
                fileCacheFolderDirectory.mkdir();
            }
        }

        if (needCleanCache) {
            cleanCache(tunaDownloadCacheFolderDirectory, tunaDownloadGraphicsSrcURLIndex);
        }

        new Thread() {
            public void run() {
                InputStream is = null;
                FileOutputStream fos = null;
                try {
                    if (!tunaDownloadCachefile.exists()) {

                        URL url = new URL(tunaDownloadGraphicsSrcURL);
                        HttpURLConnection httpURLConn = (HttpURLConnection) url.openConnection();

                        httpURLConn.setConnectTimeout(TIMEOUT);
                        httpURLConn.setReadTimeout(TIMEOUT);

                        httpURLConn.setRequestProperty("Accept-Encoding", "identity");

                        String cacheFileName = tunaDownloadCacheFolderDirectory + tunaDownloadGraphicsSrcURLIndex;
                        String tmpFileName = cacheFileName + TUNADOWN_LOADSUFFIX;

                        is = httpURLConn.getInputStream();
                        fos = new FileOutputStream(tmpFileName);

                        byte buf[] = new byte[BUF_SIZE];
                        do {
                            int numread = is.read(buf);
                            if (numread <= 0) {
                                File tmpFile = new File(tmpFileName);
                                if (!tmpFile.renameTo(new File(cacheFileName))) {
                                }
                                //
                                break;
                            }
                            fos.write(buf, 0, numread);
                            // if need cancel function to setting this to
                            // stop
                            // downloading should change the interceptFlag
                        } while (true);
                        fos.close();
                        is.close();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //
    public static void cleanCache(String filePath, String exceptFile) {
        File dirFile = new File(filePath);
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file=files[i];
            if (file.isFile() && (!file.getName().equals(exceptFile))) {
                deleteFile(file.getAbsolutePath());
            }
        }
    }

    //
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }


}