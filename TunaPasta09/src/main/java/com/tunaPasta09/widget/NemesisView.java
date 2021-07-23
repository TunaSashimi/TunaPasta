package com.tunaPasta09.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.tunaPasta09.R;


public class NemesisView extends View {

    private Paint nemesisPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path nemesisPath = new Path();//reset();

    private int nemesisWidth, nemesisHeight;

    //view nemesisBackgroundNormal default white
    private int nemesisBackgroundNormal;
    //view nemesisBackgroundPress default white
    private int nemesisBackgroundPress;
    //view nemesisBackgroundSelect default white
    private int nemesisBackgroundSelect;
    //view nemesisBackgroundMark default white
    private int nemesisBackgroundMark;

    //view draw string value default null
    private String nemesisTextValue;
    //view draw string  size default 18
    private float nemesisTextSize;

    //view draw normal string  color default white
    private int nemesisTextColorNormal;
    //view draw press string  color default white
    private int nemesisTextColorPress;
    //view draw select string  color default white
    private int nemesisTextColorSelect;

    //view stroke width default 0
    private float nemesisStrokeWidth;
    //view stroke color default white
    private int nemesisStrokeColor;

    //when draw a triangle need hide Maximum Border Line default false
    private boolean nemesisHideMaxBorderLine;

    //radius of draw roundRect default 0
    private float nemesisRoundRadius;

    private float nemesisMarkRadius;

    //radius of draw custom roundRect leftTop,leftButtom,rightTop,rightBuoom default 0
    private float nemesisLeftTopRadius, nemesisLeftBottomRadius, nemesisRightTopRadius, nemesisRightBottomRadius;

    //is nemesisPressed default false
    private boolean nemesisPressed;

    //is nemesisSelected default false
    private boolean nemesisSelected;

    //is nemesisMarked default false
    private boolean nemesisMarked;

    //draw pitcure resources
    private Bitmap nemesisBitmapResources;
    //draw pitcure width and height
    private int bitmapWidth, bitmapHeight;
    //draw pitcure tensile matrix
    private Matrix nemesisMatrix;

    //original pitcure area,four parameters is top left corner x width, height occupied the upper left corner y,
    //x width occupied the upper right corner,  lower right corner of the height
    private float[] nemesisRectangleArea;
    //rect after pitcure stretching
    private Rect nemesisTouchRectagle;

    private float nemesisCurrentX;

    //NemesisType.CUSTOM_DRAGBAR == nemesisType
    private String[] nemesisCustomDragbarArray;
    private int nemesisCustomDragbarMargin;
    private int nemesisCustomDragbarAngle;
    private int nemesisCustomDragbarFillingColor;
    private float nemesisCustomDragbarBallStrokeWidth;
    private int nemesisCustomDragbarBallStrokeColorNormal;
    private int nemesisCustomDragbarBallStrokeColorPress;
    private int nemesisCustomDragbarBallBackgroundNormal;
    private int nemesisCustomDragbarBallBackgroundPress;
    private float nemesisCustomDragbarBallSizeNormal;
    private float nemesisCustomDragbarBallSizePress;
    private int nemesisCustomDragbarBallTextColor;
    private int nemesisCustomDragbarCurrentIndex;
    private int[] nemesisCustomDragbarCircleCentreXArray;
    private int nemesisCustomDragbarNum;
    private Bitmap nemesisCustomDragbarBitmapResourcesPress;

    //NemesisType.CUSTOM_DUMBBELL == nemesisType
    private int nemesisCustomDumbbellRectColor;
    private float nemesisCustomDumbbellRectWidth;
    private float nemesisCustomDumbbellRectHeight;
    private float nemesisCustomDumbbellRectMargin;
    private float nemesisCustomDumbbellRectStrokeWidth;

    private float nemesisCustomDumbbellCircleRadius;
    private float nemesisCustomDumbbellCircleMargin;
    private float nemesisCustomDumbbellCircleStrokeWidth;
    private int nemesisCustomDumbbellCircleColorBefore;
    private int nemesisCustomDumbbellCircleColorAfter;

    private float nemesisCustomDumbbellTextSize;
    private float nemesisCustomDumbbellTextStrokeWidth;
    private String nemesisCustomDumbbellTextValueBefore;
    private String nemesisCustomDumbbellTextValueAfter;


    //draw shape mode: "rect_round","rect_positive","rect_custom","trangle_reverse","trangle_positive","custom_dragbar","custom_dumbbell"
    private NemesisType nemesisType;

    public enum NemesisType {
        RECT_ROUND(0),
        RECT_POSITIVE(1),
        RECT_CUSTOM(2),
        TRANGLE_REVERSE(3),
        TRANGLE_POSITIVE(4),
        CUSTOM_DRAGBAR(5),
        CUSTOM_DUMBBELL(6),
        ;
        final int nativeInt;

        NemesisType(int ni) {
            nativeInt = ni;
        }
    }

    private static final NemesisType[] NemesisTypeArray = {
            NemesisType.RECT_ROUND,
            NemesisType.RECT_POSITIVE,
            NemesisType.RECT_CUSTOM,
            NemesisType.TRANGLE_REVERSE,
            NemesisType.TRANGLE_POSITIVE,
            NemesisType.CUSTOM_DRAGBAR,
            NemesisType.CUSTOM_DUMBBELL,
    };

    //pictures stretching mode:"width_top","width_center","width_bottom"
    private NemesisScale nemesisScale;

    public enum NemesisScale {
        WIDTH_TOP(0),
        WIDTH_CENTER(1),
        WIDTH_BOTTOM(2),
        ;
        final int nativeInt;

        NemesisScale(int ni) {
            nativeInt = ni;
        }
    }

    private static final NemesisScale[] NemesisScaleArray = {
            NemesisScale.WIDTH_TOP,
            NemesisScale.WIDTH_CENTER,
            NemesisScale.WIDTH_BOTTOM,
    };

    //nemesisRectangleArea can be null
    public NemesisView(Context context, Bitmap nemesisBitmapResources, NemesisScale nemesisScale, float[] nemesisRectangleArea) {
        super(context);
        this.nemesisBitmapResources = nemesisBitmapResources;
        this.nemesisScale = nemesisScale;
        this.nemesisRectangleArea = nemesisRectangleArea;
        bitmapWidth = nemesisBitmapResources.getWidth();
        bitmapHeight = nemesisBitmapResources.getHeight();
    }

    public NemesisView(Context context, int nemesisWidth, int nemesisHeight, Bitmap bitmapResources, NemesisScale nemesisScale, float[] nemesisRectangleArea) {
        this(context, bitmapResources, nemesisScale, nemesisRectangleArea);
        this.nemesisWidth = nemesisWidth;
        this.nemesisHeight = nemesisHeight;
    }

    public NemesisView(Context context, int nemesisWidth, int nemesisHeight, NemesisType nemesisType, int nemesisBackgroundNormal) {
        super(context);
        this.nemesisWidth = nemesisWidth;
        this.nemesisHeight = nemesisHeight;
        this.nemesisType = nemesisType;
        this.nemesisBackgroundNormal = nemesisBackgroundNormal;
    }

    public NemesisView(Context context, int nemesisWidth, int nemesisHeight, NemesisType nemesisType, int nemesisBackgroundNormal, int nemesisStrokeWidth, int nemesisStrokeColor, boolean nemesisHideMaxBorderLine) {
        this(context, nemesisWidth, nemesisHeight, nemesisType, nemesisBackgroundNormal);
        this.nemesisStrokeWidth = nemesisStrokeWidth;
        this.nemesisStrokeColor = nemesisStrokeColor;
        this.nemesisHideMaxBorderLine = nemesisHideMaxBorderLine;
    }

    public NemesisView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NemesisView);

        int nemesisTypeIndex = typedArray.getInt(R.styleable.NemesisView_nemesisType, -1);
        if (nemesisTypeIndex >= 0) {
            nemesisType = NemesisTypeArray[nemesisTypeIndex];
        }

        int nemesisScaleIndex = typedArray.getInt(R.styleable.NemesisView_nemesisScale, -1);
        if (nemesisScaleIndex >= 0) {
            nemesisScale = NemesisScaleArray[nemesisScaleIndex];
        }

        nemesisBackgroundNormal = typedArray.getColor(R.styleable.NemesisView_nemesisBackgroundNormal, Color.WHITE);
        nemesisBackgroundPress = typedArray.getColor(R.styleable.NemesisView_nemesisBackgroundPress, Color.WHITE);
        nemesisBackgroundSelect = typedArray.getColor(R.styleable.NemesisView_nemesisBackgroundSelect, Color.WHITE);
        nemesisBackgroundMark = typedArray.getColor(R.styleable.NemesisView_nemesisBackgroundMark, Color.WHITE);

        nemesisStrokeWidth = typedArray.getDimension(R.styleable.NemesisView_nemesisStrokeWidth, 0);
        nemesisStrokeColor = typedArray.getColor(R.styleable.NemesisView_nemesisStrokeColor, Color.WHITE);

        nemesisMarkRadius = typedArray.getDimension(R.styleable.NemesisView_nemesisMarkRadius, 18);

        nemesisRoundRadius = typedArray.getDimension(R.styleable.NemesisView_nemesisRoundRadius, 0);
        nemesisLeftTopRadius = typedArray.getDimension(R.styleable.NemesisView_nemesisLeftTopRadius, 0);
        nemesisLeftBottomRadius = typedArray.getDimension(R.styleable.NemesisView_nemesisLeftBottomRadius, 0);
        nemesisRightTopRadius = typedArray.getDimension(R.styleable.NemesisView_nemesisRightTopRadius, 0);
        nemesisRightBottomRadius = typedArray.getDimension(R.styleable.NemesisView_nemesisRightBottomRadius, 0);

        nemesisTextValue = typedArray.getString(R.styleable.NemesisView_nemesisTextValue);
        nemesisTextSize = typedArray.getDimension(R.styleable.NemesisView_nemesisTextSize, 18);

        nemesisTextColorNormal = typedArray.getColor(R.styleable.NemesisView_nemesisTextColorNormal, Color.WHITE);
        nemesisTextColorPress = typedArray.getColor(R.styleable.NemesisView_nemesisTextColorPress, Color.WHITE);
        nemesisTextColorSelect = typedArray.getColor(R.styleable.NemesisView_nemesisTextColorSelect, Color.WHITE);

        nemesisHideMaxBorderLine = typedArray.getBoolean(R.styleable.NemesisView_nemesisHideMaxBorderLine, false);

        nemesisPressed = typedArray.getBoolean(R.styleable.NemesisView_nemesisPressed, false);
        nemesisSelected = typedArray.getBoolean(R.styleable.NemesisView_nemesisSelected, false);
        nemesisMarked = typedArray.getBoolean(R.styleable.NemesisView_nemesisMarked, false);

        if (NemesisType.CUSTOM_DRAGBAR == nemesisType) {
            nemesisCustomDragbarMargin = typedArray.getDimensionPixelOffset(R.styleable.NemesisView_nemesisCustomDragbarMargin, 5);
            nemesisCustomDragbarAngle = typedArray.getInteger(R.styleable.NemesisView_nemesisCustomDragbarAngle, 30);
            nemesisCustomDragbarFillingColor = typedArray.getColor(R.styleable.NemesisView_nemesisCustomDragbarFillingColor, Color.WHITE);
            nemesisCustomDragbarBallBackgroundNormal = typedArray.getColor(R.styleable.NemesisView_nemesisCustomDragbarBallBackgroundNormal, Color.WHITE);
            nemesisCustomDragbarBallBackgroundPress = typedArray.getColor(R.styleable.NemesisView_nemesisCustomDragbarBallBackgroundPress, Color.WHITE);
            nemesisCustomDragbarBallStrokeWidth = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDragbarBallStrokeWidth, 2);
            nemesisCustomDragbarBallStrokeColorNormal = typedArray.getColor(R.styleable.NemesisView_nemesisCustomDragbarBallStrokeColorNormal, Color.WHITE);
            nemesisCustomDragbarBallStrokeColorPress = typedArray.getColor(R.styleable.NemesisView_nemesisCustomDragbarBallStrokeColorPress, Color.WHITE);
            nemesisCustomDragbarBallSizeNormal = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDragbarBallSizeNormal, 18);
            nemesisCustomDragbarBallSizePress = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDragbarBallSizePress, 18);
            nemesisCustomDragbarBallTextColor = typedArray.getColor(R.styleable.NemesisView_nemesisCustomDragbarBallTextColor, Color.WHITE);
            nemesisCustomDragbarCurrentIndex = typedArray.getInteger(R.styleable.NemesisView_nemesisCustomDragbarCurrentIndex, 0);
            int typedArrayId = typedArray.getResourceId(R.styleable.NemesisView_nemesisCustomDragbarArray, 0);
            if (typedArrayId == 0) {
                throw new IllegalArgumentException("The content attribute require a property named nemesisCustomDragbarArray");
            } else {
                nemesisCustomDragbarArray = typedArray.getResources().getStringArray(typedArrayId);
                nemesisCustomDragbarNum = nemesisCustomDragbarArray.length;
                if (nemesisCustomDragbarCurrentIndex >= nemesisCustomDragbarNum) {
                    throw new IllegalArgumentException("The content attribute nemesisCustomDragbarCurrentIndex length must be smaller than the nemesisCustomDragbarArray length");
                }
                if (nemesisCustomDragbarNum < 2) {
                    throw new IllegalArgumentException("The content attribute nemesisCustomDragbarArray length must be at least 2");
                }

                int nemesisCustomDragbarBitmapResourcesPressId = typedArray.getResourceId(R.styleable.NemesisView_nemesisCustomDragbarBitmapResourcesPress, 0);
                if (nemesisCustomDragbarBitmapResourcesPressId != 0) {
                    nemesisCustomDragbarBitmapResourcesPress = BitmapFactory.decodeResource(getResources(), nemesisCustomDragbarBitmapResourcesPressId);
                }
            }
        }

        if (NemesisType.CUSTOM_DUMBBELL == nemesisType) {
            nemesisCustomDumbbellRectColor = typedArray.getColor(R.styleable.NemesisView_nemesisCustomDumbbellRectColor, Color.WHITE);
            nemesisCustomDumbbellRectWidth = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDumbbellRectWidth, 2);
            nemesisCustomDumbbellRectHeight = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDumbbellRectHeight, 2);
            nemesisCustomDumbbellRectMargin = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDumbbellRectMargin, 2);
            nemesisCustomDumbbellRectStrokeWidth = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDumbbellRectStrokeWidth, 2);

            nemesisCustomDumbbellCircleRadius = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDumbbellCircleRadius, 2);
            nemesisCustomDumbbellCircleMargin = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDumbbellCircleMargin, 2);
            nemesisCustomDumbbellCircleStrokeWidth = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDumbbellCircleStrokeWidth, 2);
            nemesisCustomDumbbellCircleColorBefore = typedArray.getColor(R.styleable.NemesisView_nemesisCustomDumbbellCircleColorBefore, 2);
            nemesisCustomDumbbellCircleColorAfter = typedArray.getColor(R.styleable.NemesisView_nemesisCustomDumbbellCircleColorAfter, 2);

            nemesisCustomDumbbellTextSize = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDumbbellTextSize, 2);
            nemesisCustomDumbbellTextStrokeWidth = typedArray.getDimension(R.styleable.NemesisView_nemesisCustomDumbbellTextStrokeWidth, 2);
            nemesisCustomDumbbellTextValueBefore = typedArray.getString(R.styleable.NemesisView_nemesisCustomDumbbellTextValueBefore);
            nemesisCustomDumbbellTextValueAfter = typedArray.getString(R.styleable.NemesisView_nemesisCustomDumbbellTextValueAfter);
        }

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (nemesisWidth == 0 || nemesisHeight == 0) {
            nemesisWidth = getWidth();
            nemesisHeight = getHeight();
        }

        canvas.clipRect(0, 0, nemesisWidth, nemesisHeight);

        //draw nemesisBitmapResources
        if (nemesisBitmapResources != null) {
            drawBitmap(canvas);
        }

        //draw nemesisShape
        if (NemesisType.RECT_ROUND == nemesisType) {
            drawRoundRect(canvas);
        } else if (NemesisType.RECT_POSITIVE == nemesisType) {
            drawPositiveRect(canvas);
        } else if (NemesisType.RECT_CUSTOM == nemesisType) {
            drawCustomRect(canvas);
        } else if (NemesisType.TRANGLE_REVERSE == nemesisType) {
            drawReverseTrangle(canvas);
        } else if (NemesisType.TRANGLE_POSITIVE == nemesisType) {
            drawPositiveTrangle(canvas);
        } else if (NemesisType.CUSTOM_DRAGBAR == nemesisType) {
            drawCustomDragbar(canvas);
        } else if (NemesisType.CUSTOM_DUMBBELL == nemesisType) {
            drawCustomDumbbell(canvas);
        }

        //draw nemesisText
        if (nemesisTextValue != null) {
            drawText(canvas);
        }

        //draw nemesisMark
        if (nemesisMarked) {
            drawMark(canvas);
        }
    }

    private void drawBitmap(Canvas canvas) {
        nemesisMatrix = new Matrix();

        float scale = (float) nemesisWidth / bitmapWidth;

        float bitmapMorphWidth = bitmapWidth * scale;
        float bitmapMorphHeight = bitmapHeight * scale;
        float dy = bitmapMorphHeight - nemesisHeight;

        nemesisMatrix.setScale(scale, scale);

        if (NemesisScale.WIDTH_TOP == nemesisScale) {

        } else if (NemesisScale.WIDTH_CENTER == nemesisScale) {
            nemesisMatrix.postTranslate(0, -(int) (dy * 0.5f + 0.5f));
        } else if (NemesisScale.WIDTH_BOTTOM == nemesisScale) {
            nemesisMatrix.postTranslate(0, -(int) (dy + 0.5f));
        }

        canvas.drawBitmap(nemesisBitmapResources, nemesisMatrix, null);

        if (nemesisRectangleArea != null) {
            if (NemesisScale.WIDTH_TOP == nemesisScale) {
                nemesisTouchRectagle = new Rect((int) (bitmapMorphWidth * nemesisRectangleArea[0]), (int) (bitmapMorphHeight * nemesisRectangleArea[1]),
                        (int) (bitmapMorphWidth * nemesisRectangleArea[2]), (int) (bitmapMorphHeight * nemesisRectangleArea[3]));
            } else if (NemesisScale.WIDTH_CENTER == nemesisScale) {
                nemesisTouchRectagle = new Rect((int) (bitmapMorphWidth * nemesisRectangleArea[0]), (int) (bitmapMorphHeight * nemesisRectangleArea[1] - dy / 2),
                        (int) (bitmapMorphWidth * nemesisRectangleArea[2]), (int) (bitmapMorphHeight * nemesisRectangleArea[3] - dy / 2));
            } else if (NemesisScale.WIDTH_BOTTOM == nemesisScale) {
                nemesisTouchRectagle = new Rect((int) (bitmapMorphWidth * nemesisRectangleArea[0]), (int) (bitmapMorphHeight * nemesisRectangleArea[1] - dy),
                        (int) (bitmapMorphWidth * nemesisRectangleArea[2]), (int) (bitmapMorphHeight * nemesisRectangleArea[3] - dy));
            }
        }
    }

    private void drawCustomRect(Canvas canvas) {
        nemesisPaint.setStyle(Paint.Style.FILL);
        nemesisPaint.setColor(nemesisStrokeWidth != 0 ? nemesisStrokeColor : nemesisSelected ? nemesisBackgroundSelect : nemesisPressed ? nemesisBackgroundPress : nemesisBackgroundNormal);

        float[] radii01 = {nemesisLeftTopRadius, nemesisLeftTopRadius, nemesisRightTopRadius, nemesisRightTopRadius, nemesisRightBottomRadius, nemesisRightBottomRadius, nemesisLeftBottomRadius, nemesisLeftBottomRadius};
        nemesisPath.reset();
        nemesisPath.addRoundRect(new RectF(0, 0, nemesisWidth, nemesisHeight), radii01, Path.Direction.CW);
        canvas.drawPath(nemesisPath, nemesisPaint);

        if (0 != nemesisStrokeWidth) {
            nemesisPath.reset();
            nemesisPaint.setStyle(Paint.Style.FILL);
            nemesisPaint.setColor(nemesisSelected ? nemesisBackgroundSelect : nemesisPressed ? nemesisBackgroundPress : nemesisBackgroundNormal);

            float[] radii2 = {nemesisLeftTopRadius - nemesisStrokeWidth, nemesisLeftTopRadius - nemesisStrokeWidth, nemesisRightTopRadius - nemesisStrokeWidth, nemesisRightTopRadius - nemesisStrokeWidth,
                    nemesisRightBottomRadius - nemesisStrokeWidth, nemesisRightBottomRadius - nemesisStrokeWidth, nemesisLeftBottomRadius - nemesisStrokeWidth, nemesisLeftBottomRadius - nemesisStrokeWidth};

            //Note that this value is likely to be negative radii2! Want to change it back!
            for (int i = 0; i < radii2.length; i++) {
                if (radii2[i] < 0) {
                    radii2[i] = 0;
                }
            }

            nemesisPath.addRoundRect(new RectF(nemesisStrokeWidth, nemesisStrokeWidth, nemesisWidth - nemesisStrokeWidth, nemesisHeight - nemesisStrokeWidth), radii2, Path.Direction.CW);
            canvas.drawPath(nemesisPath, nemesisPaint);
        }
    }

    private void drawRoundRect(Canvas canvas) {
        nemesisPaint.setStyle(Paint.Style.FILL);

        nemesisPaint.setColor(nemesisStrokeWidth != 0 ? nemesisStrokeColor : nemesisSelected ? nemesisBackgroundSelect : nemesisPressed ? nemesisBackgroundPress : nemesisBackgroundNormal);

        RectF re1 = new RectF(0, 0, nemesisWidth, nemesisHeight);
        canvas.drawRoundRect(re1, nemesisRoundRadius, nemesisRoundRadius, nemesisPaint);

        if (nemesisStrokeWidth != 0) {
            nemesisPaint.setStyle(Paint.Style.FILL);
            nemesisPaint.setColor(nemesisSelected ? nemesisBackgroundSelect : nemesisPressed ? nemesisBackgroundPress : nemesisBackgroundNormal);

            RectF re2 = new RectF(nemesisStrokeWidth, nemesisStrokeWidth, nemesisWidth - nemesisStrokeWidth, nemesisHeight - nemesisStrokeWidth);

            canvas.drawRoundRect(re2, nemesisRoundRadius - nemesisStrokeWidth, nemesisRoundRadius - nemesisStrokeWidth, nemesisPaint);
        }
    }

    private void drawPositiveRect(Canvas canvas) {
        nemesisPaint.setStyle(Paint.Style.FILL);

        nemesisPaint.setColor(nemesisStrokeWidth != 0 ? nemesisStrokeColor : nemesisPressed ? nemesisBackgroundPress : nemesisBackgroundNormal);

        canvas.drawRect(0, 0, nemesisWidth, nemesisHeight, nemesisPaint);

        if (nemesisStrokeWidth != 0) {
            nemesisPaint.setStyle(Paint.Style.FILL);
            nemesisPaint.setColor(nemesisBackgroundNormal);

            canvas.drawRect(nemesisStrokeWidth, nemesisStrokeWidth, nemesisWidth - nemesisStrokeWidth, nemesisHeight - nemesisStrokeWidth, nemesisPaint);
        }
    }

    private void drawReverseTrangle(Canvas canvas) {
        //Radians Corner A
        double dadianA = Math.atan(nemesisHeight / (nemesisWidth / 2));
        //Radians Vertex T
        double dadianButton = Math.PI - dadianA * 2;

        //Hypotenuse=stroke/sina
        float hypotenuseBottom = (float) (nemesisStrokeWidth / Math.sin(dadianButton / 2));
        //RepairHy potenuse=stroke/cosA
        float repairHypotenuseBottom = (float) (nemesisStrokeWidth / Math.cos(dadianButton / 2));

        //Internal Triangle Height
        float trangleHeight = nemesisHeight - hypotenuseBottom - nemesisStrokeWidth;
        //Internal Triangle  Width
        float trangleWidth = nemesisWidth / nemesisHeight * trangleHeight;

        nemesisPaint.setStyle(Paint.Style.FILL);
        nemesisPaint.setColor(0 != nemesisStrokeWidth ? nemesisStrokeColor : nemesisBackgroundNormal);
        nemesisPath.reset();
        if (0 != nemesisStrokeWidth && nemesisHideMaxBorderLine) {
            nemesisPath.moveTo(nemesisWidth / 2 - trangleWidth / 2 - repairHypotenuseBottom, nemesisStrokeWidth);
            nemesisPath.lineTo(nemesisWidth / 2, nemesisHeight);
            nemesisPath.lineTo(nemesisWidth / 2 + trangleWidth / 2 + repairHypotenuseBottom, nemesisStrokeWidth);
        } else {
            nemesisPath.moveTo(0, 0);
            nemesisPath.lineTo(nemesisWidth / 2, nemesisHeight);
            nemesisPath.lineTo(nemesisWidth, 0);
        }
        nemesisPath.close();
        canvas.drawPath(nemesisPath, nemesisPaint);

        if (0 != nemesisStrokeWidth) {
            nemesisPaint.setStyle(Paint.Style.FILL);
            nemesisPaint.setColor(nemesisBackgroundNormal);
            nemesisPath.reset();
            nemesisPath.moveTo(nemesisWidth / 2 - trangleWidth / 2, nemesisStrokeWidth);
            nemesisPath.lineTo(nemesisWidth / 2, nemesisHeight - hypotenuseBottom);
            nemesisPath.lineTo(nemesisWidth / 2 + trangleWidth / 2, nemesisStrokeWidth);
            if (nemesisHideMaxBorderLine) {
                nemesisPath.lineTo(nemesisWidth / 2 + trangleWidth / 2 + repairHypotenuseBottom, nemesisStrokeWidth);
                nemesisPath.lineTo(nemesisWidth, 0);
                nemesisPath.lineTo(0, 0);
                nemesisPath.lineTo(nemesisWidth / 2 - trangleWidth / 2 - repairHypotenuseBottom, nemesisStrokeWidth);
            }
            nemesisPath.close();
            canvas.drawPath(nemesisPath, nemesisPaint);
        }
    }

    private void drawPositiveTrangle(Canvas canvas) {
        //Radians Corner A
        double dadianA = Math.atan(nemesisHeight / (nemesisWidth / 2));
        //Radians Vertex T
        double dadianTop = Math.PI - dadianA * 2;

        //Hypotenuse=stroke/sina
        float hypotenuseTop = (float) (nemesisStrokeWidth / Math.sin(dadianTop / 2));
        //RepairHy potenuse=stroke/cosA
        float repairHypotenuseBottom = (float) (nemesisStrokeWidth / Math.cos(dadianTop / 2));

        //Internal Triangle Height
        float trangleHeight = nemesisHeight - hypotenuseTop - nemesisStrokeWidth;
        //Internal Triangle  Width
        float trangleWidth = nemesisWidth / nemesisHeight * trangleHeight;

        nemesisPaint.setStyle(Paint.Style.FILL);
        nemesisPaint.setColor(0 != nemesisStrokeWidth ? nemesisStrokeColor : nemesisBackgroundNormal);
        nemesisPath.reset();
        nemesisPath.moveTo(nemesisWidth / 2, 0);
        if (0 != nemesisStrokeWidth && nemesisHideMaxBorderLine) {
            nemesisPath.lineTo(nemesisWidth / 2 - trangleWidth / 2 - repairHypotenuseBottom, nemesisHeight - nemesisStrokeWidth);
            nemesisPath.lineTo(nemesisWidth / 2 + trangleWidth / 2 + repairHypotenuseBottom, nemesisHeight - nemesisStrokeWidth);
        } else {
            nemesisPath.lineTo(0, nemesisHeight);
            nemesisPath.lineTo(nemesisWidth, nemesisHeight);
        }
        nemesisPath.close();
        canvas.drawPath(nemesisPath, nemesisPaint);

        if (0 != nemesisStrokeWidth) {
            nemesisPaint.setStyle(Paint.Style.FILL);
            nemesisPaint.setColor(nemesisBackgroundNormal);
            nemesisPath.reset();
            nemesisPath.moveTo(nemesisWidth / 2, hypotenuseTop);
            nemesisPath.lineTo(nemesisWidth / 2 - trangleWidth / 2, nemesisHeight - nemesisStrokeWidth);
            if (nemesisHideMaxBorderLine) {
                nemesisPath.lineTo(nemesisWidth / 2 - trangleWidth / 2 - repairHypotenuseBottom, nemesisHeight - nemesisStrokeWidth);
                nemesisPath.lineTo(0, nemesisHeight);
                nemesisPath.lineTo(nemesisWidth, nemesisHeight);
                nemesisPath.lineTo(nemesisWidth / 2 + trangleWidth / 2 + repairHypotenuseBottom, nemesisHeight - nemesisStrokeWidth);
            }
            nemesisPath.lineTo(nemesisWidth / 2 + trangleWidth / 2, nemesisHeight - nemesisStrokeWidth);
            nemesisPath.close();
            canvas.drawPath(nemesisPath, nemesisPaint);
        }
    }

    private void drawCustomDragbar(Canvas canvas) {
        int circleDiameter = nemesisHeight - nemesisCustomDragbarMargin * 2;
        int surplusSpace = nemesisWidth - nemesisCustomDragbarMargin * 2 - circleDiameter * nemesisCustomDragbarNum;
        int surplusSplit = surplusSpace / (nemesisCustomDragbarNum - 1);

        //The view must be greater than the height multiplied by the nemesisCustomDragbarArray length
        if (surplusSplit <= 0) {
            throw new IllegalArgumentException("The view must be greater than the height multiplied by the nemesisCustomDragbarArray length");
        }

        //draw background
        canvas.drawColor(nemesisBackgroundNormal);

        //first start draw bottom of the picture:
        nemesisPaint.setStyle(Paint.Style.STROKE);
        //set the nemesisStrokeColor color
        nemesisPaint.setColor(nemesisStrokeColor);
        nemesisPaint.setStrokeWidth(nemesisStrokeWidth);

        //offsetY=hypotenuse*sin(nemesisCustomDragbarAngle/2)
        float offsetY = (float) (circleDiameter / 2 * Math.sin(Math.toRadians(nemesisCustomDragbarAngle / 2)));
        //offsetX=hypotenuse-hypotenuse*cos(nemesisCustomDragbarAngle/2)
        float offsetX = (float) (circleDiameter / 2 - circleDiameter / 2 * Math.cos(Math.toRadians(nemesisCustomDragbarAngle / 2)));

        //rectArray
        RectF[] rectArray = new RectF[nemesisCustomDragbarNum];
        for (int i = 0; i < nemesisCustomDragbarNum; i++) {
            rectArray[i] = new RectF(i * (circleDiameter + surplusSplit) + nemesisCustomDragbarMargin, nemesisCustomDragbarMargin, i * (circleDiameter + surplusSplit) + circleDiameter + nemesisCustomDragbarMargin, circleDiameter + nemesisCustomDragbarMargin);
        }

        //nemesisCustomDragbarCircleCentreXArray
        nemesisCustomDragbarCircleCentreXArray = new int[nemesisCustomDragbarNum];
        for (int i = 0; i < nemesisCustomDragbarNum; i++) {
            nemesisCustomDragbarCircleCentreXArray[i] = i * (circleDiameter + surplusSplit) + nemesisCustomDragbarMargin + circleDiameter / 2;
        }

        nemesisPath.reset();
        for (int i = 0; i < nemesisCustomDragbarNum; i++) {
            if (i == 0) {
                nemesisPath.addArc(rectArray[i], nemesisCustomDragbarAngle / 2, 360 - nemesisCustomDragbarAngle);
            } else {
                nemesisPath.lineTo(nemesisCustomDragbarCircleCentreXArray[i] - circleDiameter / 2 + offsetX, circleDiameter / 2 - offsetY + nemesisCustomDragbarMargin);
                nemesisPath.addArc(rectArray[i], 180 + nemesisCustomDragbarAngle / 2, i != rectArray.length - 1 ? 180 - nemesisCustomDragbarAngle : 360 - nemesisCustomDragbarAngle);
            }
        }

        for (int i = nemesisCustomDragbarNum - 2; i >= 0; i--) {
            nemesisPath.lineTo(nemesisCustomDragbarCircleCentreXArray[i] + circleDiameter / 2 - offsetX, circleDiameter / 2 + offsetY + nemesisCustomDragbarMargin);
            if (i != 0) {
                nemesisPath.addArc(rectArray[i], nemesisCustomDragbarAngle / 2, 180 - nemesisCustomDragbarAngle);
            }
        }

        //draw bottom pitcure

//		nemesisPaint.setShader(shader)

        canvas.drawPath(nemesisPath, nemesisPaint);

        //draw bottom filling
        nemesisPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < nemesisCustomDragbarNum; i++) {
            nemesisPaint.setColor(nemesisCustomDragbarFillingColor);
            canvas.drawCircle(nemesisCustomDragbarCircleCentreXArray[i], nemesisHeight / 2, circleDiameter / 2 - nemesisCustomDragbarBallStrokeWidth, nemesisPaint);

            if (i != nemesisCustomDragbarNum - 1) {
                //	Normally rectangle from the top left corner should be nemesisCustomDragbarCircleCentreXArray [i] + circleDiameter / 2 ,
                //	Considering the circles and rectangles are not well matched and another reason from float to int when deleting the decimal, so the need to subtract about fivefold offsetX adjustment (),
                //	To the left :nemesisCustomDragbarCircleCentreXArray [i] + circleDiameter/2-offsetX * 5 ,
                //	To the right:nemesisCustomDragbarCircleCentreXArray [i] + circleDiameter / 2 + surplusSplit + offsetX * 5
                Rect r = new Rect((int) (nemesisCustomDragbarCircleCentreXArray[i] + circleDiameter / 2 - offsetX * 5), (int) (nemesisHeight / 2 - offsetY + nemesisCustomDragbarBallStrokeWidth),
                        (int) (nemesisCustomDragbarCircleCentreXArray[i] + circleDiameter / 2 + surplusSplit + offsetX * 5), (int) (nemesisHeight / 2 + offsetY - nemesisCustomDragbarBallStrokeWidth));
                canvas.drawRect(r, nemesisPaint);
            }
        }

        //draw bottom text
        nemesisPaint.setColor(nemesisTextColorNormal);
        nemesisPaint.setTextSize(nemesisTextSize);
        nemesisPaint.setTextAlign(Paint.Align.CENTER);

        FontMetricsInt fontMetrics = nemesisPaint.getFontMetricsInt();
        int baseline = (nemesisHeight - fontMetrics.bottom - fontMetrics.top) / 2;
        for (int i = 0; i < nemesisCustomDragbarNum; i++) {
            canvas.drawText(nemesisCustomDragbarArray[i], nemesisCustomDragbarCircleCentreXArray[i], baseline, nemesisPaint);
        }

        //draw response
        if (nemesisPressed) {
            //If the incoming the background painted directly
            if (nemesisCustomDragbarBitmapResourcesPress != null) {
                Rect mRect_Bg = new Rect((int) (nemesisCurrentX - nemesisCustomDragbarBallSizePress), (int) (nemesisHeight / 2 - nemesisCustomDragbarBallSizePress),
                        (int) (nemesisCurrentX + nemesisCustomDragbarBallSizePress), (int) (nemesisHeight / 2 + nemesisCustomDragbarBallSizePress));
                canvas.drawBitmap(nemesisCustomDragbarBitmapResourcesPress, null, mRect_Bg, nemesisPaint);
                //No not incoming the background draw the default texture
            } else {
                nemesisPaint.setColor(nemesisCustomDragbarBallStrokeColorPress);
                canvas.drawCircle(nemesisCurrentX, nemesisHeight / 2, nemesisCustomDragbarBallSizePress, nemesisPaint);
                nemesisPaint.setColor(nemesisCustomDragbarBallBackgroundPress);
                canvas.drawCircle(nemesisCurrentX, nemesisHeight / 2, nemesisCustomDragbarBallSizePress - nemesisCustomDragbarBallStrokeWidth, nemesisPaint);

//				//draw veins overlapping round
//				float ccl=nemesisCustomDragbarBallSizePress-nemesisCustomDragbarBallStrokeWidth;
//				nemesisPaint.setStyle(Paint.Style.STROKE);
//				nemesisPaint.setColor(Color.parseColor("#e1e1e1"));
//				for (int i = 0; i < ccl; i+=10) {
//					canvas.drawCircle(nemesisCurrentX, nemesisHeight/2, i, nemesisPaint);
//				}

                nemesisPaint.setStyle(Paint.Style.STROKE);
                nemesisPaint.setColor(nemesisStrokeColor);

                ////////
                // 左下的图形圆心
                float bezierOvalX = nemesisCurrentX;
                float bezierOvalY = nemesisHeight / 2;

                //左下的图形宽度的一半,高度的一半
                float deviationOvalX = 4;
                float deviationOvalY = 10;
                //宽度的一半,高度的一般的变动量
                float deviationOvalXOffset = 4;
                float deviationOvalYOffset = 4;

                //左下的图形拉伸距离的宽度,高度
                float controlOvalX = 4;
                float controlOvalY = 12;
                //拉伸距离的宽度,高度的变动量
                float controlOvalXOffset = 4;
                float controlOvalYOffset = 4;

                int cyclesNum = 6;
                nemesisPath.reset();

                for (int i = 0; i < cyclesNum; i++) {
                    if (i == 0) {
                        //中间段曲线
                        nemesisPath.moveTo(bezierOvalX, bezierOvalY + 15);
                        nemesisPath.lineTo(bezierOvalX + 2, bezierOvalY + 2);
                    }
                    nemesisPath.quadTo(bezierOvalX, bezierOvalY - deviationOvalY - controlOvalY,
                            bezierOvalX + deviationOvalX, bezierOvalY - deviationOvalY);// 右上

                    nemesisPath.quadTo(bezierOvalX + deviationOvalX + controlOvalX, bezierOvalY,
                            bezierOvalX + deviationOvalX, bezierOvalY + deviationOvalY);// 右下

                    nemesisPath.quadTo(bezierOvalX, bezierOvalY + deviationOvalY + controlOvalY,
                            bezierOvalX - deviationOvalX, bezierOvalY + deviationOvalY);// 左下

                    nemesisPath.quadTo(bezierOvalX - deviationOvalX - controlOvalX, bezierOvalY,
                            bezierOvalX - deviationOvalX, bezierOvalY - deviationOvalY);// 左上

                    deviationOvalX += deviationOvalXOffset;
                    deviationOvalY += deviationOvalYOffset;

                    controlOvalX += controlOvalXOffset;
                    controlOvalY += controlOvalYOffset;
                }

                canvas.drawPath(nemesisPath, nemesisPaint);
                ////////

            }
            nemesisPaint.setStyle(Paint.Style.FILL);
            nemesisPaint.setColor(nemesisCustomDragbarBallTextColor);
            canvas.drawText(nemesisCustomDragbarArray[nemesisCustomDragbarCurrentIndex], nemesisCurrentX, baseline, nemesisPaint);
        } else {
            int adjuestX = nemesisCustomDragbarCircleCentreXArray[nemesisCustomDragbarCurrentIndex];

            //draw response circle
            nemesisPaint.setColor(nemesisCustomDragbarBallStrokeColorNormal);
            canvas.drawCircle(adjuestX, nemesisHeight / 2, nemesisCustomDragbarBallSizeNormal, nemesisPaint);
            nemesisPaint.setColor(nemesisCustomDragbarBallBackgroundNormal);
            canvas.drawCircle(adjuestX, nemesisHeight / 2, nemesisCustomDragbarBallSizeNormal - nemesisCustomDragbarBallStrokeWidth, nemesisPaint);

            //draw response text
            nemesisPaint.setColor(nemesisCustomDragbarBallTextColor);
            canvas.drawText(nemesisCustomDragbarArray[nemesisCustomDragbarCurrentIndex], adjuestX, baseline, nemesisPaint);
        }
    }


    private void drawCustomDumbbell(Canvas canvas) {
        if (nemesisCustomDumbbellCircleStrokeWidth != 0) {
            nemesisPaint.setStyle(Paint.Style.STROKE);
            nemesisPaint.setStrokeWidth(nemesisCustomDumbbellCircleStrokeWidth);
        } else {
            nemesisPaint.setStyle(Paint.Style.FILL);
        }
        nemesisPaint.setColor(nemesisCustomDumbbellCircleColorBefore);

        float circleCenterYBefore = nemesisCustomDumbbellCircleRadius + nemesisCustomDumbbellCircleMargin;
        canvas.drawCircle(nemesisWidth / 2, circleCenterYBefore, nemesisCustomDumbbellCircleRadius, nemesisPaint);

        if (nemesisCustomDumbbellTextStrokeWidth != 0) {
            nemesisPaint.setStyle(Paint.Style.STROKE);
            nemesisPaint.setStrokeWidth(nemesisCustomDumbbellTextStrokeWidth);
        } else {
            nemesisPaint.setStyle(Paint.Style.FILL);
        }

        nemesisPaint.setTextSize(nemesisCustomDumbbellTextSize);
        nemesisPaint.setTextAlign(Paint.Align.CENTER);
        FontMetricsInt fontMetrics = nemesisPaint.getFontMetricsInt();
        float baseline = (circleCenterYBefore * 2 - fontMetrics.bottom - fontMetrics.top) / 2;
        canvas.drawText(nemesisCustomDumbbellTextValueBefore, nemesisWidth / 2, baseline, nemesisPaint);

        if (nemesisCustomDumbbellRectStrokeWidth != 0) {
            nemesisPaint.setStyle(Paint.Style.STROKE);
            nemesisPaint.setStrokeWidth(nemesisCustomDumbbellRectStrokeWidth);
        } else {
            nemesisPaint.setStyle(Paint.Style.FILL);
        }
        nemesisPaint.setColor(nemesisCustomDumbbellRectColor);

        float rectCenterX = nemesisWidth / 2;
        float rectCenterY = nemesisCustomDumbbellCircleMargin + nemesisCustomDumbbellCircleRadius * 2 + nemesisCustomDumbbellCircleStrokeWidth * 2 + nemesisCustomDumbbellRectMargin + nemesisCustomDumbbellRectHeight / 2 + nemesisCustomDumbbellRectWidth;

        for (; ; ) {
            canvas.drawRect(rectCenterX - nemesisCustomDumbbellRectWidth / 2, rectCenterY - nemesisCustomDumbbellRectHeight / 2,
                    rectCenterX + nemesisCustomDumbbellRectWidth / 2, rectCenterY + nemesisCustomDumbbellRectHeight / 2, nemesisPaint);
            if (nemesisHeight - (rectCenterY + nemesisCustomDumbbellRectHeight / 2 + nemesisCustomDumbbellRectMargin + nemesisCustomDumbbellRectStrokeWidth + nemesisCustomDumbbellCircleMargin + nemesisCustomDumbbellCircleRadius * 2 + nemesisCustomDumbbellCircleStrokeWidth * 2) <= nemesisCustomDumbbellRectHeight + nemesisCustomDumbbellRectStrokeWidth * 2 + nemesisCustomDumbbellRectMargin) {
                break;
            }
            rectCenterY += nemesisCustomDumbbellRectHeight + nemesisCustomDumbbellRectMargin + nemesisCustomDumbbellRectMargin * 2;
        }

        if (nemesisCustomDumbbellCircleStrokeWidth != 0) {
            nemesisPaint.setStyle(Paint.Style.STROKE);
            nemesisPaint.setStrokeWidth(nemesisCustomDumbbellCircleStrokeWidth);
        } else {
            nemesisPaint.setStyle(Paint.Style.FILL);
        }
        nemesisPaint.setColor(nemesisCustomDumbbellCircleColorAfter);

        float circleCenterAfter = rectCenterY + nemesisCustomDumbbellRectHeight / 2 + nemesisCustomDumbbellRectMargin + nemesisCustomDumbbellRectStrokeWidth + nemesisCustomDumbbellCircleRadius + nemesisCustomDumbbellCircleStrokeWidth;
        canvas.drawCircle(nemesisWidth / 2, circleCenterAfter, nemesisCustomDumbbellCircleRadius, nemesisPaint);

        if (nemesisCustomDumbbellTextStrokeWidth != 0) {
            nemesisPaint.setStyle(Paint.Style.STROKE);
            nemesisPaint.setStrokeWidth(nemesisCustomDumbbellTextStrokeWidth);
        } else {
            nemesisPaint.setStyle(Paint.Style.FILL);
        }
        baseline = (circleCenterAfter * 2 - fontMetrics.bottom - fontMetrics.top) / 2;
        canvas.drawText(nemesisCustomDumbbellTextValueAfter, nemesisWidth / 2, baseline, nemesisPaint);

    }

    //考虑传入参数
    private void drawText(Canvas canvas) {
        nemesisPaint.setTextSize(nemesisTextSize);
        nemesisPaint.setColor(nemesisSelected ? nemesisTextColorSelect : nemesisPressed ? nemesisTextColorPress : nemesisTextColorNormal);
        nemesisPaint.setTextAlign(Paint.Align.CENTER);

        FontMetricsInt fontMetrics = nemesisPaint.getFontMetricsInt();

        // int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        int baseline = (nemesisHeight - fontMetrics.bottom - fontMetrics.top) / 2;

        // canvas.drawText(textValue, targetRect.centerX(), baseline, nemesisPaint);
        canvas.drawText(nemesisTextValue, nemesisWidth / 2, baseline, nemesisPaint);
    }

    private void drawMark(Canvas canvas) {
        nemesisPaint.setColor(nemesisBackgroundMark);
        canvas.drawCircle(nemesisWidth * 0.5f + nemesisPaint.measureText(nemesisTextValue) * 0.5f + nemesisMarkRadius, nemesisHeight * 0.5f, nemesisMarkRadius, nemesisPaint);
    }

    public boolean isNemesisPressed() {
        return nemesisPressed;
    }

    public void setNemesisPressed(boolean nemesisIsPressed) {
        this.nemesisPressed = nemesisIsPressed;
        this.nemesisSelected = false;
        invalidate();
    }

    public boolean isNemesisSelected() {
        return nemesisSelected;
    }

    public void setNemesisSelected(boolean nemesisIsSelected) {
        this.nemesisSelected = nemesisIsSelected;
        this.nemesisPressed = false;
        invalidate();
    }

    public boolean isNemesisMarked() {
        return nemesisMarked;
    }

    public void setNemesisMarked(boolean nemesisMarked) {
        this.nemesisMarked = nemesisMarked;
        invalidate();
    }

    public Rect getNemesisTouchRectagle() {
        return nemesisTouchRectagle;
    }

    public void setNemesisTouchRectagle(Rect touchRectagle) {
        this.nemesisTouchRectagle = touchRectagle;
    }

    public float getNemesisCurrentX() {
        return nemesisCurrentX;
    }

    public void setNemesisCurrentX(float nemesisCurrentX) {
        this.nemesisCurrentX = nemesisCurrentX;
        //calculate index
        float minDistence = nemesisWidth;
        //From 0 to judge one by one, if the distance farther on the end of the cycle
        for (int i = 0; i < nemesisCustomDragbarNum; i++) {
            float circleCentreDistance = Math.abs(nemesisCurrentX - nemesisCustomDragbarCircleCentreXArray[i]);
            if (circleCentreDistance < minDistence) {
                nemesisCustomDragbarCurrentIndex = i;
                minDistence = circleCentreDistance;
            } else {
                break;
            }
        }
        invalidate();
    }

    public int getNemesisCurrentIndex() {
        return nemesisCustomDragbarCurrentIndex;
    }

    public void setNemesisCurrentIndex(int nemesisCustomDragbarCurrentIndex) {
        if (nemesisCustomDragbarCurrentIndex < 0 || nemesisCustomDragbarCurrentIndex >= nemesisCustomDragbarNum) {
            throw new IllegalArgumentException("The content attribute nemesisCustomDragbarCurrentIndex length must be not less than zero and smaller than the nemesisCustomDragbarArray length");
        }
        this.nemesisCustomDragbarCurrentIndex = nemesisCustomDragbarCurrentIndex;
        invalidate();
    }


}