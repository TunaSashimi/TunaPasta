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
 * @date 10/30/15 16:49
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaAnimateTransfer extends TunaView{
	private int tunaAnimateTransferTotal;

	private Bitmap tunaAnimateTransferBitmapSrc;
	private float tunaAnimateTransferItemWidth, tunaAnimateTransferItemHeight;

	private float tunaAnimateTransferBefore, tunaAnimateTransferAfter;
	private float tunaAnimateTransferDeviation;

	private float[] tunaAnimateTransferCurrent;

	private int tunaAnimateTransferDelay;

	public TunaAnimateTransfer(Context context) {
		this(context, null);
	}

	public TunaAnimateTransfer(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TunaAnimateTransfer(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		tunaTag = TunaAnimateTransfer.class.getSimpleName();

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaAnimateTransfer);

		tunaAnimateTransferTotal = typedArray.getInt(R.styleable.TunaAnimateTransfer_tunaAnimateTransferTotal, 0);
		if (tunaAnimateTransferTotal < 1) {
			throw new IndexOutOfBoundsException("The content attribute tunaDragArray length must be at least 1");
		} else {
			tunaAnimateTransferCurrent = new float[tunaAnimateTransferTotal];
		}

		int tunaAnimateTransferBitmapSrcId = typedArray.getResourceId(R.styleable.TunaAnimateTransfer_tunaAnimateTransferBitmapSrc, 0);
		if (tunaAnimateTransferBitmapSrcId != 0) {
			tunaAnimateTransferBitmapSrc = BitmapFactory.decodeResource(getResources(), tunaAnimateTransferBitmapSrcId);
		} else {
			throw new IllegalArgumentException("The content attribute require a property named tunaAnimateTransferBitmapSrc");
		}

		tunaAnimateTransferItemWidth = typedArray.getDimension(R.styleable.TunaAnimateTransfer_tunaAnimateTransferItemWidth, 0);
		tunaAnimateTransferItemHeight = typedArray.getDimension(R.styleable.TunaAnimateTransfer_tunaAnimateTransferItemHeight, 0);

		if (tunaAnimateTransferItemWidth <= 0 || tunaAnimateTransferItemHeight <= 0) {
			throw new IndexOutOfBoundsException("The content attribute tunaAnimateTransferItemWidth and tunaAnimateTransferItemHeight must be at least 1");
		}

		tunaAnimateTransferBefore = typedArray.getDimension(R.styleable.TunaAnimateTransfer_tunaAnimateTransferBefore, 0);
		tunaAnimateTransferAfter = typedArray.getDimension(R.styleable.TunaAnimateTransfer_tunaAnimateTransferAfter, 0);

		tunaAnimateTransferDeviation = typedArray.getDimension(R.styleable.TunaAnimateTransfer_tunaAnimateTransferDeviation, 0);

		tunaAnimateTransferDelay = typedArray.getInt(R.styleable.TunaAnimateTransfer_tunaAnimateTransferDelay, 0);

		typedArray.recycle();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom){
		super.onLayout(changed, left, top, right, bottom);

		initTunaMatrix(tunaAnimateTransferItemWidth * 1f / tunaAnimateTransferBitmapSrc.getWidth(), tunaAnimateTransferItemHeight * 1f / tunaAnimateTransferBitmapSrc.getHeight());

		tunaSurplus = tunaAnimateTransferBefore - tunaAnimateTransferAfter;
		tunaShare = tunaSurplus / tunaAnimateTransferTotal;

		tunaDx = (tunaWidth - tunaAnimateTransferItemWidth) * 0.5f;

		// From bottom to top
		for (int i = 0; i < tunaAnimateTransferTotal; i++) {
			tunaAnimateTransferCurrent[i] = tunaAnimateTransferBefore - tunaShare * (i + 1);
		}

	}

	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);

		canvas.save();
		canvas.clipRect(initTunaRect(0, (int) (tunaAnimateTransferAfter), tunaWidth, (int) tunaAnimateTransferBefore));

		for (int i = 0; i < tunaAnimateTransferTotal; i++) {

			canvas.translate(tunaDx, tunaAnimateTransferCurrent[i]);

			int alpha = (int) (255 - (tunaAnimateTransferCurrent[i] + tunaAnimateTransferItemHeight - tunaAnimateTransferAfter)
					/ (tunaAnimateTransferBefore - tunaAnimateTransferAfter + tunaAnimateTransferItemHeight) * 255);

			canvas.drawBitmap(tunaAnimateTransferBitmapSrc, tunaMatrix, initTunaPaint(alpha));

			canvas.translate(-tunaDx, -tunaAnimateTransferCurrent[i]);

			tunaAnimateTransferCurrent[i] -= tunaAnimateTransferDeviation;
			if (tunaAnimateTransferCurrent[i] <= tunaAnimateTransferAfter - tunaAnimateTransferItemHeight) {
				tunaAnimateTransferCurrent[i] = tunaAnimateTransferBefore - tunaAnimateTransferItemHeight;
			}
		}

		canvas.restore();

		if (tunaAnimationable) {
			if (tunaAnimateTransferDelay == 0) {
				invalidate();
			} else {
				postInvalidateDelayed(0);
			}
		}
	}

}
