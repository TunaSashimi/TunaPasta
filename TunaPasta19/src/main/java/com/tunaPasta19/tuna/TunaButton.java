package com.tunaPasta19.tuna;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.tunaPasta19.R;

/**
 * @author Tunasashimi
 * @date 10/30/15 16:51
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaButton extends TunaView{

	private float tunaButtonBitmapFractionTop, tunaButtonBitmapFractionBottom;
	private float tunaButtonTextFractionTop, tunaButtonTextFractionBottom;

	private int tunaButtonBackgroundNormal, tunaButtonBackgroundPress;
	private int tunaButtonForegroundNormal, tunaButtonForegroundPress;

	private String tunaButtonTextValue;

	public String getTunaButtonTextValue(){
		return tunaButtonTextValue;
	}

	public void setTunaButtonTextValue(String tunaButtonTextValue){
		this.tunaButtonTextValue = tunaButtonTextValue;
	}

	private float tunaButtonTextSize;
	private int tunaButtonTextColorNormal;

	private Bitmap tunaButtonBitmapSrcNormal, tunaButtonBitmapSrcPress;

    public TunaButton(Context context) {
        this(context, null);
    }

    public TunaButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TunaButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

		tunaTag = TunaButton.class.getSimpleName();

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaButton);

		tunaButtonBackgroundNormal = typedArray.getColor(R.styleable.TunaButton_tunaButtonBackgroundNormal, Color.TRANSPARENT);
		tunaButtonBackgroundPress = typedArray.getColor(R.styleable.TunaButton_tunaButtonBackgroundPress, tunaButtonBackgroundNormal);

		tunaButtonForegroundNormal = typedArray.getColor(R.styleable.TunaButton_tunaButtonForegroundNormal, Color.TRANSPARENT);
		tunaButtonForegroundPress = typedArray.getColor(R.styleable.TunaButton_tunaButtonForegroundPress, tunaButtonForegroundNormal);

		int tunaButtonBitmapSrcNormalId = typedArray.getResourceId(R.styleable.TunaButton_tunaButtonBitmapSrcNormal, -1);
		if (tunaButtonBitmapSrcNormalId != -1) {
			tunaButtonBitmapSrcNormal = BitmapFactory.decodeResource(getResources(), tunaButtonBitmapSrcNormalId);
		}

		int tunaButton_tunaButtonBitmapSrcPressId = typedArray.getResourceId(R.styleable.TunaButton_tunaButtonBitmapSrcPress, tunaButtonBitmapSrcNormalId);
		if (tunaButton_tunaButtonBitmapSrcPressId != -1) {
			tunaButtonBitmapSrcPress = BitmapFactory.decodeResource(getResources(), tunaButton_tunaButtonBitmapSrcPressId);
		}

		// depending on the type of fraction, the getFraction method multiples
		// the values accordingly.
		// If you specify a parent fraction (%p), it uses the second argument
		// (pbase), ignoring the first.
		// On the other hand, specifying a normal fraction, only the base
		// argument is used, multiplying the fraction by this.
		tunaButtonBitmapFractionTop = typedArray.getFraction(R.styleable.TunaButton_tunaButtonBitmapFractionTop, 1, 1, 0);
		tunaButtonBitmapFractionBottom = typedArray.getFraction(R.styleable.TunaButton_tunaButtonBitmapFractionBottom, 1, 1, 1);

		tunaButtonTextValue = typedArray.getString(R.styleable.TunaButton_tunaButtonTextValue);
		tunaButtonTextSize = typedArray.getDimension(R.styleable.TunaButton_tunaButtonTextSize, 0);
		tunaButtonTextColorNormal = typedArray.getColor(R.styleable.TunaButton_tunaButtonTextColorNormal, Color.TRANSPARENT);

		tunaButtonTextFractionTop = typedArray.getFraction(R.styleable.TunaButton_tunaButtonTextFractionTop, 1, 1, 0);
		tunaButtonTextFractionBottom = typedArray.getFraction(R.styleable.TunaButton_tunaButtonTextFractionBottom, 1, 1, 1);

		typedArray.recycle();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom){
		super.onLayout(changed, left, top, right, bottom);

		int tunaButtonBitmapSrcNormalWidth = tunaButtonBitmapSrcNormal.getWidth();
		int tunaButtonBitmapSrcNormalHeight = tunaButtonBitmapSrcNormal.getHeight();
		int tunaButtonBitmapSrcPressWidth = tunaButtonBitmapSrcPress.getWidth();
		int tunaButtonBitmapSrcPressHeight = tunaButtonBitmapSrcPress.getHeight();

		if (tunaButtonBitmapSrcNormalWidth != tunaButtonBitmapSrcPressWidth || tunaButtonBitmapSrcNormalHeight != tunaButtonBitmapSrcPressHeight) {
			throw new IllegalArgumentException("Both the width and height of the attribute tunaButtonBitmapSrcNormal and tunaButtonBitmapSrcPress needed equal");
		}

		tunaScale = tunaHeight * (tunaButtonBitmapFractionBottom - tunaButtonBitmapFractionTop) / tunaButtonBitmapSrcNormalWidth;
		tunaDy = tunaHeight * tunaButtonBitmapFractionTop;
		tunaDx = (tunaWidth - tunaButtonBitmapSrcNormalWidth * tunaScale) * 0.5f;

		initTunaMatrix(tunaScale, tunaScale);
	}

	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawColor(tunaPress ? tunaButtonBackgroundPress : tunaButtonBackgroundNormal);

		canvas.save();
		canvas.translate(tunaDx, tunaDy);
		canvas.drawBitmap(tunaPress ? tunaButtonBitmapSrcPress : tunaButtonBitmapSrcNormal, tunaMatrix, null);
		canvas.translate(-tunaDx, -tunaDy);

		drawTunaText(canvas, tunaButtonTextValue, tunaWidth, tunaWidth >> 1, (tunaHeight * tunaButtonTextFractionTop + tunaHeight * tunaButtonTextFractionBottom) * 0.5f, 0, 0,
				initTunaTextPaint(Paint.Style.FILL, tunaButtonTextColorNormal, tunaButtonTextSize, Paint.Align.CENTER));

		canvas.drawColor(tunaPress ? tunaButtonForegroundPress : tunaButtonForegroundNormal);

	}
}
