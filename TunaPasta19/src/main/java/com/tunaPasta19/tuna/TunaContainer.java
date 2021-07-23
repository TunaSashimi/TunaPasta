package com.tunaPasta19.tuna;

import android.content.Context;

import android.graphics.Canvas;
import android.util.AttributeSet;
/**
 * @author Tunasashimi
 * @date 10/30/15 16:51
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaContainer extends TunaView {
	public TunaContainer(Context context) {
		this(context, null);
	}

	public TunaContainer(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TunaContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		tunaTag = TunaContainer.class.getSimpleName();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
}
