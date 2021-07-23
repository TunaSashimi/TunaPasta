package com.tunaPasta19.tuna;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout.LayoutParams;
/**
 * @author Tunasashimi
 * @date 10/30/15 16:52
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaCustomAnimation extends Animation {

	private View mAnimationView = null;
	private LayoutParams mViewLayoutParams = null;
	private int mStart = 0;
	private int mEnd = 0;

	public TunaCustomAnimation(View view) {
		animationSettings(view, 500);
	}

	public TunaCustomAnimation(View view, int duration) {
		animationSettings(view, duration);
	}

	private void animationSettings(View view, int duration) {
		setDuration(duration);
		mAnimationView = view;
		mViewLayoutParams = (LayoutParams) view.getLayoutParams();
		mStart = mViewLayoutParams.bottomMargin;
		mEnd = (mStart == 0 ? (0 - view.getHeight()) : 0);
		view.setVisibility(View.VISIBLE);
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		super.applyTransformation(interpolatedTime, t);

		// 变化从0到1
		if (interpolatedTime < 1.0f) {

			System.out.println("bottomMargin==>" + (mStart + (int) (mEnd - mStart) * interpolatedTime));

			mViewLayoutParams.bottomMargin = mStart + (int) ((mEnd - mStart) * interpolatedTime);
			mAnimationView.requestLayout();
		} else {

			System.out.println("mEnd.bottomMargin====>" + mEnd);

			mViewLayoutParams.bottomMargin = mEnd;
			mAnimationView.requestLayout();
			if (mEnd != 0) {
				mAnimationView.setVisibility(View.GONE);
			}
		}
	}

}
