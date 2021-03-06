package com.tunaPasta19.tuna;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.util.AttributeSet;

import com.tunaPasta19.R;

/**
 * @author Tunasashimi
 * @date 10/30/15 16:53
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaDumbbell extends TunaView{

	private int tunaDumbbellRectColor;
	private float tunaDumbbellRectWidth, tunaDumbbellRectHeight;
	private float tunaDumbbellRectMargin;
	private float tunaDumbbellRectStrokeWidth;

	private float tunaDumbbellCircleRadius;
	private float tunaDumbbellCircleMargin;
	private float tunaDumbbellCircleStrokeWidth;
	private int tunaDumbbellCircleColorBefore, tunaDumbbellCircleColorAfter;

	private float tunaDumbbellTextSize;
	private float tunaDumbbellTextStrokeWidth;
	private String tunaDumbbellTextValueBefore, tunaDumbbellTextValueAfter;

	private TunaDumbbellDirection tunaDumbbellDirection;

	public enum TunaDumbbellDirection {
		HORIZONTAL(0), VERTICAL(1), ;
		final int nativeInt;

		TunaDumbbellDirection(int ni){
			nativeInt = ni;
		}
	}

	private static final TunaDumbbellDirection[] tunaDumbbellDirectionArray = { TunaDumbbellDirection.HORIZONTAL, TunaDumbbellDirection.VERTICAL, };

	public TunaDumbbell(Context context) {
		this(context, null);
	}

	public TunaDumbbell(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TunaDumbbell(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		tunaTag = TunaDumbbell.class.getSimpleName();

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TunaDumbbell);

		tunaDumbbellRectColor = typedArray.getColor(R.styleable.TunaDumbbell_tunaDumbbellRectColor, Color.TRANSPARENT);
		tunaDumbbellRectWidth = typedArray.getDimension(R.styleable.TunaDumbbell_tunaDumbbellRectWidth, 2);
		tunaDumbbellRectHeight = typedArray.getDimension(R.styleable.TunaDumbbell_tunaDumbbellRectHeight, 2);
		tunaDumbbellRectMargin = typedArray.getDimension(R.styleable.TunaDumbbell_tunaDumbbellRectMargin, 2);
		tunaDumbbellRectStrokeWidth = typedArray.getDimension(R.styleable.TunaDumbbell_tunaDumbbellRectStrokeWidth, 2);

		tunaDumbbellCircleRadius = typedArray.getDimension(R.styleable.TunaDumbbell_tunaDumbbellCircleRadius, 2);
		tunaDumbbellCircleMargin = typedArray.getDimension(R.styleable.TunaDumbbell_tunaDumbbellCircleMargin, 2);
		tunaDumbbellCircleStrokeWidth = typedArray.getDimension(R.styleable.TunaDumbbell_tunaDumbbellCircleStrokeWidth, 2);
		tunaDumbbellCircleColorBefore = typedArray.getColor(R.styleable.TunaDumbbell_tunaDumbbellCircleColorBefore, Color.TRANSPARENT);
		tunaDumbbellCircleColorAfter = typedArray.getColor(R.styleable.TunaDumbbell_tunaDumbbellCircleColorAfter, Color.TRANSPARENT);

		tunaDumbbellTextSize = typedArray.getDimension(R.styleable.TunaDumbbell_tunaDumbbellTextSize, 2);
		tunaDumbbellTextStrokeWidth = typedArray.getDimension(R.styleable.TunaDumbbell_tunaDumbbellTextStrokeWidth, 2);
		tunaDumbbellTextValueBefore = typedArray.getString(R.styleable.TunaDumbbell_tunaDumbbellTextValueBefore);
		tunaDumbbellTextValueAfter = typedArray.getString(R.styleable.TunaDumbbell_tunaDumbbellTextValueAfter);

		int tunaDumbbellDirectionIndex = typedArray.getInt(R.styleable.TunaDumbbell_tunaDumbbellDirection, -1);
		if (tunaDumbbellDirectionIndex >= 0) {
			tunaDumbbellDirection = tunaDumbbellDirectionArray[tunaDumbbellDirectionIndex];
		} else {
			throw new IllegalArgumentException("The content attribute tunaDumbbellDirectionIndex type must be given");
		}

		typedArray.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);

		if (tunaDumbbellCircleStrokeWidth != 0) {
			initTunaPaint(Paint.Style.STROKE, tunaDumbbellCircleColorBefore, tunaDumbbellCircleStrokeWidth);
		} else {
			initTunaPaint(Paint.Style.FILL, tunaDumbbellCircleColorBefore);
		}

		float circleCenterYBefore = tunaDumbbellCircleRadius + tunaDumbbellCircleMargin;
		canvas.drawCircle(tunaWidth >> 1, circleCenterYBefore, tunaDumbbellCircleRadius, tunaPaint);

		// ????????????
		if (tunaDumbbellTextStrokeWidth != 0) {
			tunaPaint.setStyle(Paint.Style.STROKE);
			tunaPaint.setStrokeWidth(tunaDumbbellTextStrokeWidth);
		} else {
			tunaPaint.setStyle(Paint.Style.FILL);
		}

		tunaPaint.setTextSize(tunaDumbbellTextSize);
		tunaPaint.setTextAlign(Paint.Align.CENTER);
		FontMetricsInt fontMetrics = tunaPaint.getFontMetricsInt();
		float baseline = (circleCenterYBefore * 2 - fontMetrics.bottom - fontMetrics.top) * 0.5f;

		if (tunaDumbbellTextValueBefore != null) {
			canvas.drawText(tunaDumbbellTextValueBefore, tunaWidth >> 1, baseline, tunaPaint);
		}

		if (tunaDumbbellRectStrokeWidth != 0) {
			tunaPaint.setStyle(Paint.Style.STROKE);
			tunaPaint.setStrokeWidth(tunaDumbbellRectStrokeWidth);
		} else {
			tunaPaint.setStyle(Paint.Style.FILL);
		}
		tunaPaint.setColor(tunaDumbbellRectColor);

		if (tunaDumbbellDirection == TunaDumbbellDirection.VERTICAL) {

			float rectCenterX = tunaWidth >> 1;
			float rectCenterY = tunaDumbbellCircleMargin + tunaDumbbellCircleRadius * 2 + tunaDumbbellCircleStrokeWidth * 2 + tunaDumbbellRectMargin + tunaDumbbellRectHeight / 2
					+ tunaDumbbellRectWidth;

			for (;;) {
				canvas.drawRect(rectCenterX - tunaDumbbellRectWidth / 2, rectCenterY - tunaDumbbellRectHeight / 2, rectCenterX + tunaDumbbellRectWidth / 2, rectCenterY
						+ tunaDumbbellRectHeight / 2, tunaPaint);
				if (tunaHeight
						- (rectCenterY + tunaDumbbellRectHeight / 2 + tunaDumbbellRectMargin + tunaDumbbellRectStrokeWidth + tunaDumbbellCircleMargin + tunaDumbbellCircleRadius
								* 2 + tunaDumbbellCircleStrokeWidth * 2) <= tunaDumbbellRectHeight + tunaDumbbellRectStrokeWidth * 2 + tunaDumbbellRectMargin) {
					break;
				}
				rectCenterY += tunaDumbbellRectHeight + tunaDumbbellRectMargin + tunaDumbbellRectMargin * 2;
			}

			if (tunaDumbbellCircleStrokeWidth != 0) {
				tunaPaint.setStyle(Paint.Style.STROKE);
				tunaPaint.setStrokeWidth(tunaDumbbellCircleStrokeWidth);
			} else {
				tunaPaint.setStyle(Paint.Style.FILL);
			}
			tunaPaint.setColor(tunaDumbbellCircleColorAfter);

			float circleCenterAfter = rectCenterY + tunaDumbbellRectHeight / 2 + tunaDumbbellRectMargin + tunaDumbbellRectStrokeWidth + tunaDumbbellCircleRadius
					+ tunaDumbbellCircleStrokeWidth;
			canvas.drawCircle(tunaWidth >> 1, circleCenterAfter, tunaDumbbellCircleRadius, tunaPaint);

			if (tunaDumbbellTextStrokeWidth != 0) {
				tunaPaint.setStyle(Paint.Style.STROKE);
				tunaPaint.setStrokeWidth(tunaDumbbellTextStrokeWidth);
			} else {
				tunaPaint.setStyle(Paint.Style.FILL);
			}
			baseline = (circleCenterAfter * 2 - fontMetrics.bottom - fontMetrics.top) * 0.5f;

			if (tunaDumbbellTextValueAfter != null) {
				canvas.drawText(tunaDumbbellTextValueAfter, tunaWidth >> 1, baseline, tunaPaint);
			}

		} else {

			float rectCenterX = tunaDumbbellCircleMargin + tunaDumbbellCircleRadius * 2 + tunaDumbbellCircleStrokeWidth * 2 + tunaDumbbellRectMargin + tunaDumbbellRectHeight / 2
					+ tunaDumbbellRectWidth;
			float rectCenterY = tunaHeight >> 1;

			for (;;) {
				canvas.drawRect(rectCenterX - tunaDumbbellRectWidth / 2, rectCenterY - tunaDumbbellRectHeight / 2, rectCenterX + tunaDumbbellRectWidth / 2, rectCenterY
						+ tunaDumbbellRectHeight / 2, tunaPaint);

				if (tunaWidth
						- (rectCenterX + tunaDumbbellRectWidth / 2 + tunaDumbbellRectMargin + tunaDumbbellRectStrokeWidth + tunaDumbbellCircleMargin + tunaDumbbellCircleRadius * 2 + tunaDumbbellCircleStrokeWidth * 2) <= tunaDumbbellRectWidth
						+ tunaDumbbellRectStrokeWidth * 2 + tunaDumbbellRectMargin) {
					break;
				}
				rectCenterX += tunaDumbbellRectWidth + tunaDumbbellRectMargin + tunaDumbbellRectMargin * 2;
			}

		}

	}

}
