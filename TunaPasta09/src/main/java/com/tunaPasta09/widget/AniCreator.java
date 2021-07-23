package com.tunaPasta09.widget;

import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
//import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

public class AniCreator {

	public static final int DURATION = 300;
	public static final int DURATION_RATION = 6000;

	public static final float FACTOR_ACE = 0.5f;
	public static final float FACTOR_DEC = 2f;

	public static final int ANIMATION_MODE_DROPDOWN = 100;
	public static final int ANIMATION_MODE_DROPDOWN_RESEVERD = 104;
	public static final int ANIMATION_MODE_POPUP = 101;
	public static final int ANIMATION_MODE_POPUP_RESERVED = 105;
	public static final int ANIMATION_MODE_SILDEOUT_LEFT = 102;
	public static final int ANIMATION_MODE_SLIDIN_LEFT = 106;
	public static final int ANIMATION_MODE_SLIDEOUT_RIGHT = 103;
	public static final int ANIMATION_MODE_SLIDEIN_RIGHT = 107;

	public static final int ANIMATION_MODE_ROTATION_CLOCKWISED = 108;
	public static final int ANIMATION_MODE_ROTAION_ANTI_CLOCKWISED = 109;

	public static final int ANIMATION_MODE_ALPHA_VISABLE = 110;
	public static final int ANIMATION_MODE_ALPHA_INVISABLE = 111;

	public static Handler handler = new Handler();

	private Animation mTranslateAnimationDropDown = null;
	private Animation mTranslateAnimationDropDownReserved = null;

	private Animation mTranslateAnimationPopUp = null;
	private Animation mTranslateAnimationPopUpReserved = null;

	private Animation mTranslateAnimationSlideOut_Left = null;
	private Animation mTranslateAnimationSlideIn_Left = null;

	private Animation mTranslateAnimationSlideOut_Right = null;
	private Animation mTranslateAnimationSlideIn_Right = null;

	private Animation mAlphaAnimationVisable = null;
	private Animation mAlphaAnimationInvisable = null;

	private Animation mRotationAnimationClockWise = null;
	private Animation mRotationAnimationAntiClockWise = null;

	private DecelerateInterpolator mInterpolator_Ace = null;
	private DecelerateInterpolator mInterpolator_Dec = null;

	private LinearInterpolator mLinearInter = new LinearInterpolator();

	private static AniCreator mInstance = null;

	private AniCreator() {

		mInterpolator_Ace = new MyDecelerateInterpolatorAcc();
		mInterpolator_Dec = new MyDecelerateInterpolatorDec();

	}

	public static AniCreator getInstance() {

		if (mInstance == null)
			mInstance = new AniCreator();

		return mInstance;

	}

	public void apply_animation_Ratation(View view, int ani_mode,
			int view_display) {

		if (ani_mode == ANIMATION_MODE_ROTATION_CLOCKWISED) {

			if (mRotationAnimationClockWise == null)
				mRotationAnimationClockWise = new RotateAnimation(0f, 360f,
						Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);

			mRotationAnimationClockWise.setDuration(DURATION_RATION);
			mRotationAnimationClockWise
					.setRepeatCount(RotateAnimation.INFINITE);
			mRotationAnimationClockWise.setInterpolator(mLinearInter);
			view.startAnimation(mRotationAnimationClockWise);
			view.setVisibility(view_display);

		}

		if (ani_mode == ANIMATION_MODE_ROTAION_ANTI_CLOCKWISED) {

			if (mRotationAnimationAntiClockWise == null)
				mRotationAnimationAntiClockWise = new RotateAnimation(360f, 0f,
						Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);

			mRotationAnimationAntiClockWise.setDuration(DURATION_RATION);
			mRotationAnimationAntiClockWise
					.setRepeatCount(RotateAnimation.INFINITE);
			mRotationAnimationClockWise.setInterpolator(mLinearInter);
			view.startAnimation(mRotationAnimationAntiClockWise);
			view.setVisibility(view_display);

		}
	}

	private void apply_animation_alpha(View view, int ani_mode,
			int view_display, boolean isFillAfter) {

		if (ani_mode == ANIMATION_MODE_ALPHA_VISABLE) {

			if (mAlphaAnimationVisable == null)
				mAlphaAnimationVisable = new AlphaAnimation(0, 1);

			mAlphaAnimationVisable.setDuration(DURATION);
			mAlphaAnimationVisable.setFillAfter(isFillAfter);
			// mAlphaAnimationVisable.setInterpolator(mInterpolator_Ace);
			view.startAnimation(mAlphaAnimationVisable);
			view.setVisibility(view_display);

		}

		if (ani_mode == ANIMATION_MODE_ALPHA_INVISABLE) {

			if (mAlphaAnimationInvisable == null)
				mAlphaAnimationInvisable = new AlphaAnimation(1, 0);

			mAlphaAnimationInvisable.setDuration(DURATION);
			mAlphaAnimationInvisable.setFillAfter(isFillAfter);
			// mAlphaAnimationInvisable.setInterpolator(mInterpolator_Dec);
			view.startAnimation(mAlphaAnimationInvisable);
			view.setVisibility(view_display);

		}

	}

	public void apply_animation_translate(View view, int ani_mode,int view_display, boolean isFillAfter,AnimationListener animationListener) {

		switch (ani_mode) {
		case ANIMATION_MODE_DROPDOWN: {

			if (mTranslateAnimationDropDown == null)
				mTranslateAnimationDropDown = new TranslateAnimation(
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, -1.0f,
						Animation.RELATIVE_TO_SELF, 0f);

			mTranslateAnimationDropDown.setDuration(DURATION);
			mTranslateAnimationDropDown.setFillAfter(isFillAfter);
			mTranslateAnimationDropDown.setInterpolator(mInterpolator_Ace);
			
			if (animationListener!=null) {
				mTranslateAnimationDropDown.setAnimationListener(animationListener);
			}
			
			view.startAnimation(mTranslateAnimationDropDown);
			view.setVisibility(view_display);

			break;
		}

		case ANIMATION_MODE_DROPDOWN_RESEVERD: {

			if (mTranslateAnimationDropDownReserved == null)
				mTranslateAnimationDropDownReserved = new TranslateAnimation(
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, -1.0f);

			mTranslateAnimationDropDownReserved.setDuration(DURATION);
			mTranslateAnimationDropDownReserved.setFillAfter(isFillAfter);
			mTranslateAnimationDropDownReserved.setInterpolator(mInterpolator_Dec);
			
			if (animationListener!=null) {
				mTranslateAnimationDropDownReserved.setAnimationListener(animationListener);
			}
			
			view.startAnimation(mTranslateAnimationDropDownReserved);
			view.setVisibility(view_display);
			break;

		}
		case ANIMATION_MODE_POPUP: {

			if (mTranslateAnimationPopUp == null)
				mTranslateAnimationDropDownReserved = new TranslateAnimation(
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 1.0f,
						Animation.RELATIVE_TO_SELF, 0f);

			mTranslateAnimationDropDownReserved.setDuration(DURATION);
			mTranslateAnimationDropDownReserved.setFillAfter(isFillAfter);
			mTranslateAnimationDropDownReserved.setInterpolator(mInterpolator_Dec);
			
			if (animationListener!=null) {
				mTranslateAnimationDropDownReserved.setAnimationListener(animationListener);
			}
			
			view.startAnimation(mTranslateAnimationDropDownReserved);
			view.setVisibility(view_display);

			break;

		}

		case ANIMATION_MODE_POPUP_RESERVED: {

			if (mTranslateAnimationPopUpReserved == null)
				mTranslateAnimationPopUpReserved = new TranslateAnimation(
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 1.0f);

			mTranslateAnimationPopUpReserved.setDuration(DURATION);
			mTranslateAnimationPopUpReserved.setFillAfter(isFillAfter);
			mTranslateAnimationPopUpReserved.setInterpolator(mInterpolator_Dec);
			
			if (animationListener!=null) {
				mTranslateAnimationPopUpReserved.setAnimationListener(animationListener);
			}
			
			view.startAnimation(mTranslateAnimationPopUpReserved);
			view.setVisibility(view_display);

			break;
		}

		case ANIMATION_MODE_SILDEOUT_LEFT: {

			if (mTranslateAnimationSlideOut_Left == null)
				mTranslateAnimationSlideOut_Left = new TranslateAnimation(
						Animation.RELATIVE_TO_SELF, -1.0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f);

			mTranslateAnimationSlideOut_Left.setDuration(DURATION);
			mTranslateAnimationSlideOut_Left.setFillAfter(isFillAfter);
			mTranslateAnimationSlideOut_Left.setInterpolator(mInterpolator_Ace);
			
			if (animationListener!=null) {
				mTranslateAnimationSlideOut_Left.setAnimationListener(animationListener);
			}
			
			view.startAnimation(mTranslateAnimationSlideOut_Left);
			view.setVisibility(view_display);

			break;

		}

		case ANIMATION_MODE_SLIDIN_LEFT: {

			if (mTranslateAnimationSlideIn_Left == null)
				mTranslateAnimationSlideIn_Left = new TranslateAnimation(
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, -1.0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f);

			mTranslateAnimationSlideIn_Left.setDuration(DURATION);
			mTranslateAnimationSlideIn_Left.setFillAfter(isFillAfter);
			mTranslateAnimationSlideIn_Left.setInterpolator(mInterpolator_Dec);
			
			if (animationListener!=null) {
				mTranslateAnimationSlideIn_Left.setAnimationListener(animationListener);
			}
			
			view.startAnimation(mTranslateAnimationSlideIn_Left);
			view.setVisibility(view_display);

			break;

		}

		case ANIMATION_MODE_SLIDEOUT_RIGHT: {

			if (mTranslateAnimationSlideOut_Right == null)
				mTranslateAnimationSlideOut_Right = new TranslateAnimation(
						Animation.RELATIVE_TO_SELF, 1.0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f);

			mTranslateAnimationSlideOut_Right.setDuration(DURATION);
			mTranslateAnimationSlideOut_Right.setFillAfter(isFillAfter);
			mTranslateAnimationSlideOut_Right.setInterpolator(mInterpolator_Ace);
			
			if (animationListener!=null) {
				mTranslateAnimationSlideOut_Right.setAnimationListener(animationListener);
			}
			
			view.startAnimation(mTranslateAnimationSlideOut_Right);
			view.setVisibility(view_display);

			break;

		}

		case ANIMATION_MODE_SLIDEIN_RIGHT: {

			if (mTranslateAnimationSlideIn_Right == null)
				mTranslateAnimationSlideIn_Right = new TranslateAnimation(
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 1.0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f);

			mTranslateAnimationSlideIn_Right.setDuration(DURATION);
			mTranslateAnimationSlideIn_Right.setFillAfter(isFillAfter);
			mTranslateAnimationSlideIn_Right.setInterpolator(mInterpolator_Dec);
			
			if (animationListener!=null) {
				mTranslateAnimationSlideIn_Right.setAnimationListener(animationListener);
			}
			
			view.startAnimation(mTranslateAnimationSlideIn_Right);
			view.setVisibility(view_display);

			break;

		}

		}

	}

	public void applyAnimation(View view, int ani_mode, int view_display,AnimationListener animationListener) {

		applyAnimation(view, ani_mode, view_display, true,animationListener);

	}

	public void applyAnimation(View view, int ani_mode, int view_display,boolean isFillAfter,AnimationListener animationListener) {

		if (ani_mode == ANIMATION_MODE_ALPHA_VISABLE
				|| ani_mode == ANIMATION_MODE_ALPHA_INVISABLE) {

			apply_animation_alpha(view, ani_mode, view_display, true);

		} else if (ani_mode == ANIMATION_MODE_ROTATION_CLOCKWISED
				|| ani_mode == ANIMATION_MODE_ROTAION_ANTI_CLOCKWISED) {

			apply_animation_Ratation(view, ani_mode, view_display);

		} else {
			apply_animation_translate(view, ani_mode, view_display, true,animationListener);
		}

	}

	private class MyDecelerateInterpolatorAcc extends DecelerateInterpolator {

		@Override
		public float getInterpolation(float input) {

			if (FACTOR_ACE == 1.0f) {
				return input * input;
			} else {
				return (float) Math.pow(input, FACTOR_ACE * FACTOR_ACE);
			}
		}

	}

	private class MyDecelerateInterpolatorDec extends DecelerateInterpolator {

		@Override
		public float getInterpolation(float input) {

			if (FACTOR_DEC == 1.0f) {
				return input * input;
			} else {
				return (float) Math.pow(input, FACTOR_DEC * FACTOR_DEC);
			}
		}

	}

}
