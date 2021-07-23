package com.tunaPasta19.tuna;

import java.util.ArrayList;
import java.util.List;

import com.tunaPasta19.tuna.TunaView.TunaAnimateEndListener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.View;
import android.widget.ListView;
/**
 * @author Tunasashimi
 * @date 10/30/15 16:49
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class TunaAnimation{

	/**
	 * length 9(Incomplete)
	 * Duration,StartDelay,Alpha,ScaleX,ScaleY,RotationX,RotationY,int
	 * repeatCount,int repeatMode
	 */
	public static float tunahome_relativeBottomPara[] = { 500l, 0l, 0.0f, 0.25f, 0.25f, 0.0f, 0.0f, 0, ValueAnimator.RESTART };

	public static float tunahome_relaviteLeft[] = { 500l, 0l, 1.0f, 3.0f, 3.0f, 0.0f, 180.0f, 0, ValueAnimator.RESTART };

	public static float tunahome_imageBottomPara[] = { 500l, 0l, 1.0f, 0.25f, 0.25f, 0.0f, 0.0f, 0, ValueAnimator.RESTART };
	public static float tunahome_viewHidePara[] = { 500l, 0l, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0, ValueAnimator.RESTART };
	public static float tunahome_viewShowPara[] = { 500l, 0l, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0, ValueAnimator.RESTART };

	public static float tunaload_toCenterPara[] = { 500l, 0l, 1.0f, 3.0f, 3.0f, 0.0f, 180.0f, 0, ValueAnimator.RESTART };



	/**
	 * length 11 Duration,StartDelay,Alpha,ScaleX,ScaleY,RotationX,RotationY,
	 * TranslationX,TranslationY,int repeatCount,int repeatMode
	 */

	public static float tunahome_relavitePara[] = { 500l, 0l, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, ValueAnimator.RESTART };
	public static float tunahome_relaviteLayoutPara[] = { 500l, 0l, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, ValueAnimator.RESTART };

	public static float tunaload_framePara[] = { 500l, 0l, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -240.0f, 0.0f, 0, ValueAnimator.RESTART };

	/**
	 * length 14(Incomplete)
	 * Duration,StartDelay,startAlpha,endAlpha,startScaleX,
	 * endScaleX,startScaleY,
	 * endScaleY,startRotationX,endRotationX,startRotationY,endRotationY,int
	 * repeatCount,int repeatMode
	 */
	public static float tunaload_toCenterAlphaPara[] = { 500l, 0l, 0.0f, 1.0f, 1.0f, 4.0f, 1.0f, 4.0f, 0.0f, 0.0f, 0.0f, 180.0f, 0, ValueAnimator.RESTART };

	public static float listview_fromLeftPara[] = { 500l, 0l, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0f, 0, ValueAnimator.RESTART };
	public static float listview_fromRightPara[] = { 500l, 0l, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0f, 0, ValueAnimator.RESTART };
	public static float listview_fromTopPara[] = { 500l, 0l, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0f, 0, ValueAnimator.RESTART };
	public static float listview_fromBottomPara[] = { 500l, 0l, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0f, 0, ValueAnimator.RESTART };
	public static float listview_fromLeftTopPara[] = { 500l, 0l, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0f, 0, ValueAnimator.RESTART };
	public static float listview_fromLeftBottomPara[] = { 500l, 0l, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0f, 0, ValueAnimator.RESTART };
	public static float listview_fromRightTopPara[] = { 500l, 0l, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0f, 0, ValueAnimator.RESTART };
	public static float listview_fromRightBottomPara[] = { 500l, 0l, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0f, 0, ValueAnimator.RESTART };

	/**
	 * length 18 Duration,StartDelay,startAlpha,endAlpha,startScaleX,endScaleX,
	 * startScaleY
	 * ,endScaleY,startRotationX,endRotationX,startRotationY,endRotationY
	 * ,startTranslationX,endTranslationX,startTranslationY,endTranslationY,int
	 * repeatCount,int repeatMode
	 */
	public static float tunahome_loadLayoutPara[] = { 300l, 0l, 0.1f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 130.0f, 0.0f, 0, ValueAnimator.RESTART };

	public static float tunahome_haloPara[] = { 1500l, 0l, 0.0f, 1.0f, 4.0f, 1.0f, 4.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1, ValueAnimator.REVERSE };

	public static float tunaload_loadLayoutAlphaPara[] = { 2000l, 0l, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, ValueAnimator.RESTART };
	public static float tunaload_loadLayoutScalePara[] = { 2000l, 0l, 1.0f, 1.0f, 0.5f, 1.5f, 0.5f, 1.5f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, ValueAnimator.RESTART };

	public static float tunawait_circleSpreadParaBefore[] = { 1200l, 0l, 1.0f, 0.0f, 1.0f, 1.6f, 1.0f, 1.6f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0,
			ValueAnimator.RESTART };
	public static float tunawait_circleSpreadParaMiddle[] = { 1200l, 0l, 1.0f, 0.0f, 1.0f, 1.6f, 1.0f, 1.6f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0,
			ValueAnimator.RESTART };
	public static float tunawait_circleSpreadParaAfter[] = { 1200l, 0l, 1.0f, 0.0f, 1.0f, 1.6f, 1.0f, 1.6f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0,
			ValueAnimator.RESTART };

	public static float tunawait_haloAppearPara[] = { 850, 0l, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1, ValueAnimator.REVERSE };

	public static float tunawelcome_imagePara[] = { 1000, 0l, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, ValueAnimator.RESTART };
	public static float tunawelcome_textPara[] = { 1000, 6000l, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, ValueAnimator.RESTART };

	//
	public static void playOrderLoadAnimation(final View[] viewArray, final float[] paraArray){
		playOrderLoadAnimation(viewArray, paraArray, null);
	}

	public static void playOrderLoadAnimation(final View[] viewArray, final float[] paraArray, final TunaAnimateEndListener tunaAnimateEndListener){
		viewArray[0].setVisibility(View.VISIBLE);
		playObjectAnimation(viewArray[0], paraArray, new TunaAnimateEndListener(){
			@Override
			public void tunaAnimateEnd(View v){
				if (viewArray.length > 1) {
					View[] viewArrayCopy = new View[viewArray.length - 1];
					System.arraycopy(viewArray, 1, viewArrayCopy, 0, viewArray.length - 1);
					playOrderLoadAnimation(viewArrayCopy, paraArray, tunaAnimateEndListener);
				} else {
					if (tunaAnimateEndListener != null) {
						tunaAnimateEndListener.tunaAnimateEnd(viewArray[0]);
					}
				}
			}
		});
	}

	//
	public static void playOrderLoadAnimation(final List<View> viewList, final float[] paraArray){
		playOrderLoadAnimation(viewList, paraArray, null);
	}

	public static void playOrderLoadAnimation(final List<View> viewList, final float[] paraArray, final TunaAnimateEndListener tunaAnimateEndListener){
		viewList.get(0).setVisibility(View.VISIBLE);
		playObjectAnimation(viewList.get(0), paraArray, new TunaAnimateEndListener(){
			@Override
			public void tunaAnimateEnd(View v){
				if (viewList.size() > 1) {
					// Incoming List likely through Arrays.asList, the return is
					// about array and the content of
					// java.util.Arrays$ArrayList,
					// is an inner class, inside the Arrays method is rarely, so
					// can not directly use the remove method!
					List<View> viewListCopy = new ArrayList<View>();
					viewListCopy.addAll(viewList);
					viewListCopy.remove(0);
					playOrderLoadAnimation(viewListCopy, paraArray, tunaAnimateEndListener);
				} else {
					if (tunaAnimateEndListener != null) {
						tunaAnimateEndListener.tunaAnimateEnd(viewList.get(0));
					}
				}
			}
		});
	}

	//
	public static void playListViewChildToTopAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playToTopAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildToTopAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playToTopAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playToTopAnimation(View view, float[] paraArray){
		playToTopAnimation(view, paraArray, true, null, null);
	}

	public static void playToTopAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playToTopAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playToTopAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playToTopAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	public static void playToTopAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playToTopAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	public static void playToTopAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int height = view.getHeight();
		float translationX = 0;
		float translationY = -height;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");

		} else if (paraArray.length == 9) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], translationX, translationY,
					fillBefore, (int) paraArray[7], (int) paraArray[8], interpolator, tunaAnimateEndListener);
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], 0, translationX, 0, translationY, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 9 or 14");
		}
	}

	//
	public static void playListViewChildFromTopAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromTopAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildFromTopAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromTopAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromTopAnimation(View view, float[] paraArray){
		playFromTopAnimation(view, paraArray, true, null, null);
	}

	public static void playFromTopAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playFromTopAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromTopAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playFromTopAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	public static void playFromTopAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playFromTopAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	public static void playFromTopAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int height = view.getHeight();
		float translationFromX = 0;
		float translationFromY = -height;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], translationFromX, 0, translationFromY, 0, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 14");
		}
	}

	//
	public static void playListViewChildToBottomAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playToBottomAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildToBottomAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playToBottomAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playToBottomAnimation(View view, float[] paraArray){
		playToBottomAnimation(view, paraArray, true, null, null);
	}

	public static void playToBottomAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playToBottomAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playToBottomAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playToBottomAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	public static void playToBottomAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playToBottomAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	public static void playToBottomAnimation(View view, float[] paraArray, boolean fillBefore, TunaAnimateEndListener tunaAnimateEndListener){
		playToBottomAnimation(view, paraArray, fillBefore, null, tunaAnimateEndListener);
	}

	public static void playToBottomAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int height = view.getHeight();
		float translationX = 0;
		float translationY = height;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 9) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], translationX, translationY,
					fillBefore, (int) paraArray[7], (int) paraArray[8], interpolator, tunaAnimateEndListener);
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], 0, translationX, 0, translationY, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 9 or 14");
		}
	}

	//
	public static void playListViewChildFromBottomAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromBottomAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildFromBottomAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromBottomAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromBottomAnimation(View view, float[] paraArray){
		playFromBottomAnimation(view, paraArray, true, null, null);
	}

	public static void playFromBottomAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playFromBottomAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromBottomAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playFromBottomAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	public static void playFromBottomAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playFromBottomAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	public static void playFromBottomAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int height = view.getHeight();
		float translationFromX = 0;
		float translationFromY = height;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], translationFromX, 0, translationFromY, 0, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 14");
		}
	}

	//
	public static void playListViewChildToLeftAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playToLeftAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildToLeftAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playToLeftAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playToLeftAnimation(View view, float[] paraArray){
		playToLeftAnimation(view, paraArray, true, null, null);
	}

	public static void playToLeftAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playToLeftAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playToLeftAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playToLeftAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	public static void playToLeftAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playToLeftAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	public static void playToLeftAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int width = view.getWidth();
		float translationX = -width;
		float translationY = 0;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 9) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], translationX, translationY,
					fillBefore, (int) paraArray[7], (int) paraArray[8], interpolator, tunaAnimateEndListener);
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], 0, translationX, 0, translationY, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 9 or 14");
		}
	}

	//
	public static void playListViewChildFromLeftAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromLeftAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildFromLeftAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromLeftAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromLeftAnimation(View view, float[] paraArray){
		playFromLeftAnimation(view, paraArray, true, null, null);
	}

	public static void playFromLeftAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playFromLeftAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromLeftAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playFromLeftAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	public static void playFromLeftAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playFromLeftAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	public static void playFromLeftAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int width = view.getWidth();
		float translationFromX = -width;
		float translationFromY = 0;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], translationFromX, 0, translationFromY, 0, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 14");
		}
	}

	//
	public static void playListViewChildToRightAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playToRightAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildToRightAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playToRightAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playToRightAnimation(View view, float[] paraArray){
		playToRightAnimation(view, paraArray, true, null, null);
	}

	public static void playToRightAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playToRightAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playToRightAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playToRightAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	public static void playToRightAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playToRightAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	public static void playToRightAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int width = view.getWidth();
		float translationX = width;
		float translationY = 0;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 9) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], translationX, translationY,
					fillBefore, (int) paraArray[7], (int) paraArray[8], interpolator, tunaAnimateEndListener);
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], 0, translationX, 0, translationY, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 9 or 14");
		}
	}

	//
	public static void playListViewChildFromRightAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromRightAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildFromRightAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromRightAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromRightAnimation(View view, float[] paraArray){
		playFromRightAnimation(view, paraArray, true, null, null);
	}

	public static void playFromRightAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playFromRightAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromRightAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playFromRightAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	public static void playFromRightAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playFromRightAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	public static void playFromRightAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int width = view.getWidth();
		float translationFromX = width;
		float translationFromY = 0;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], translationFromX, 0, translationFromY, 0, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 14");
		}
	}

	//
	public static void playListViewChildFromLeftTopAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromLeftTopAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildFromLeftTopAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromLeftTopAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromLeftTopAnimation(View view, float[] paraArray){
		playFromLeftTopAnimation(view, paraArray, true, null, null);
	}

	public static void playFromLeftTopAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playFromLeftTopAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromLeftTopAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playFromLeftTopAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	public static void playFromLeftTopAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playFromLeftTopAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	public static void playFromLeftTopAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int width = view.getWidth();
		int height = view.getHeight();
		float translationFromX = -width;
		float translationFromY = -height;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], translationFromX, 0, translationFromY, 0, fillBefore, (int) paraArray[12], (int) paraArray[13],
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 14");
		}
	}

	//
	public static void playListViewChildFromLeftBottomAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromLeftBottomAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildFromLeftBottomAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromLeftBottomAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromLeftBottomAnimation(View view, float[] paraArray){
		playFromLeftBottomAnimation(view, paraArray, true, null, null);
	}

	public static void playFromLeftBottomAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playFromLeftBottomAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromLeftBottomAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playFromLeftBottomAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	public static void playFromLeftBottomAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playFromLeftBottomAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	public static void playFromLeftBottomAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int width = view.getWidth();
		int height = view.getHeight();
		float translationFromX = -width;
		float translationFromY = height;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], translationFromX, 0, translationFromY, 0, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 14");
		}
	}

	//
	public static void playListViewChildFromRightTopAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromRightTopAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildFromRightTopAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromRightTopAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromRightTopAnimation(View view, float[] paraArray){
		playFromRightTopAnimation(view, paraArray, true, null, null);
	}

	public static void playFromRightTopAnimation(View view, TimeInterpolator interpolator, float[] paraArray){
		playFromRightTopAnimation(view, paraArray, true, interpolator, null);
	}

	public static void playFromRightTopAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playFromRightTopAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	public static void playFromRightTopAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playFromRightTopAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	public static void playFromRightTopAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int width = view.getWidth();
		int height = view.getHeight();
		float translationFromX = width;
		float translationFromY = -height;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], translationFromX, 0, translationFromY, 0, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 14");
		}
	}

	public static void playListViewChildFromRightBottomAnimation(ListView listView, float[] paraArray){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromRightBottomAnimation(view, paraArray, true, null, null);
	}

	public static void playListViewChildFromRightBottomAnimation(ListView listView, float[] paraArray, TimeInterpolator interpolator){
		View view = listView.getChildAt(listView.getHeaderViewsCount());
		playFromRightBottomAnimation(view, paraArray, true, interpolator, null);
	}

	// 2 Parameters
	public static void playFromRightBottomAnimation(View view, float[] paraArray){
		playFromRightBottomAnimation(view, paraArray, true, null, null);
	}

	// 3 Parameters
	public static void playFromRightBottomAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playFromRightBottomAnimation(view, paraArray, true, interpolator, null);
	}

	// 3 Parameters
	public static void playFromRightBottomAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playFromRightBottomAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	// 4 Parameters
	public static void playFromRightBottomAnimation(View view, float[] paraArray, final TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playFromRightBottomAnimation(view, paraArray, true, null, tunaAnimateEndListener);
	}

	// 5 Parameters
	public static void playFromRightBottomAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		int width = view.getWidth();
		int height = view.getHeight();
		float translationFromX = width;
		float translationFromY = height;
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], translationFromX, 0, translationFromY, 0, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 14");
		}
	}

	// 4 Parameters
	public static void playToCenterAnimation(Activity activity, View view, boolean containsStatusBar, float[] paraArray){
		playToCenterAnimation(activity, view, containsStatusBar, paraArray, true, null);
	}

	// 5 Parameters
	public static void playToCenterAnimation(Activity activity, View view, boolean containsStatusBar, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playToCenterAnimation(activity, view, containsStatusBar, paraArray, true, null, tunaAnimateEndListener);
	}

	// 6 Parameters
	public static void playToCenterAnimation(Activity activity, View view, boolean containsStatusBar, float[] paraArray, boolean fillBefore,
			TunaAnimateEndListener tunaAnimateEndListener){
		playToCenterAnimation(activity, view, containsStatusBar, paraArray, fillBefore, null, tunaAnimateEndListener);

	}

	// 7 Parameters
	public static void playToCenterAnimation(Activity activity, View view, boolean containsStatusBar, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator,
			TunaAnimateEndListener tunaAnimateEndListener){
		int screenWidth = TunaView.getScreenWidth(activity);
		int screenHeight = TunaView.getScreenHeight(activity);
		int statusBarHeight = TunaView.getStatusBarHeight(activity);

		float x = view.getX();
		float y = view.getY();

		int width = view.getWidth();
		int height = view.getHeight();

		float translationX = (screenWidth >> 1) - (x + width * 0.5f);
		float translationY = (screenHeight >> 1) - (y + height * 0.5f);

		translationY -= containsStatusBar ? statusBarHeight : statusBarHeight >> 1;

		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 9) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], translationX, translationY,
					fillBefore, (int) paraArray[7], (int) paraArray[8], interpolator, tunaAnimateEndListener);
		} else if (paraArray.length == 14) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], 0, translationX, 0, translationY, fillBefore, (int) paraArray[12], (int) paraArray[13], interpolator,
					tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 9 or 14");
		}
	}

	// 2 Parameters
	public static void playObjectAnimation(View view, float[] paraArray){
		playObjectAnimation(view, paraArray, true, null);
	}

	// 3 Parameters
	public static void playObjectAnimation(View view, float[] paraArray, TimeInterpolator interpolator){
		playObjectAnimation(view, paraArray, true, null, null);
	}

	// 3 Parameters
	public static void playObjectAnimation(View view, float[] paraArray, TunaAnimateEndListener tunaAnimateEndListener){
		playObjectAnimation(view, paraArray, true, tunaAnimateEndListener);
	}

	// 4 Parameters
	public static void playObjectAnimation(View view, float[] paraArray, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playObjectAnimation(view, paraArray, true, interpolator, tunaAnimateEndListener);
	}

	// 4 Parameters
	public static void playObjectAnimation(View view, float[] paraArray, boolean fillBefore, TunaAnimateEndListener tunaAnimateEndListener){
		playObjectAnimation(view, paraArray, fillBefore, null, tunaAnimateEndListener);
	}

	// 5 Parameters
	public static void playObjectAnimation(View view, float[] paraArray, boolean fillBefore, TimeInterpolator interpolator, final TunaAnimateEndListener tunaAnimateEndListener){
		if (paraArray == null) {
			throw new IllegalArgumentException("The parameters Float[] paraArray cannot be null");
		} else if (paraArray.length == 11) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					fillBefore, (int) paraArray[9], (int) paraArray[10], interpolator, tunaAnimateEndListener);
		} else if (paraArray.length == 18) {
			playObjectAnimation(view, (long) paraArray[0], (long) paraArray[1], paraArray[2], paraArray[3], paraArray[4], paraArray[5], paraArray[6], paraArray[7], paraArray[8],
					paraArray[9], paraArray[10], paraArray[11], paraArray[12], paraArray[13], paraArray[14], paraArray[15], fillBefore, (int) paraArray[16], (int) paraArray[17],
					interpolator, tunaAnimateEndListener);
		} else {
			throw new IndexOutOfBoundsException("The parameters Float[] paraArray length only can be 11 or 18");
		}
	}

	// 13 Parameters
	public static void playObjectAnimation(View view, long duration, long startDelay, float alpha, float scaleX, float scaleY, float rotationX, float rotationY,
			float translationX, float translationY, int repeatCount, int repeatMode, TunaAnimateEndListener tunaAnimateEndListener){
		playObjectAnimation(view, duration, startDelay, 1.0f, alpha, 1.0f, scaleX, 1.0f, scaleY, 0.0f, rotationX, 0.0f, rotationY, 0.0f, translationX, 0.0f, translationY, true,
				repeatCount, repeatMode, tunaAnimateEndListener);
	}

	// 14 Parameters
	public static void playObjectAnimation(View view, long duration, long startDelay, float alpha, float scaleX, float scaleY, float rotationX, float rotationY,
			float translationX, float translationY, int repeatCount, int repeatMode, TimeInterpolator interpolator, TunaAnimateEndListener tunaAnimateEndListener){
		playObjectAnimation(view, duration, startDelay, 1.0f, alpha, 1.0f, scaleX, 1.0f, scaleY, 0.0f, rotationX, 0.0f, rotationY, 0.0f, translationX, 0.0f, translationY, true,
				repeatCount, repeatMode, interpolator, tunaAnimateEndListener);
	}

	// 14 Parameters
	public static void playObjectAnimation(View view, long duration, long startDelay, float alpha, float scaleX, float scaleY, float rotationX, float rotationY,
			float translationX, float translationY, boolean fillBefore, int repeatCount, int repeatMode, TunaAnimateEndListener tunaAnimateEndListener){
		playObjectAnimation(view, duration, startDelay, 1.0f, alpha, 1.0f, scaleX, 1.0f, scaleY, 0.0f, rotationX, 0.0f, rotationY, 0.0f, translationX, 0.0f, translationY,
				fillBefore, repeatCount, repeatMode, tunaAnimateEndListener);
	}

	// 15 Parameters
	public static void playObjectAnimation(View view, long duration, long startDelay, float alpha, float scaleX, float scaleY, float rotationX, float rotationY,
			float translationX, float translationY, boolean fillBefore, int repeatCount, int repeatMode, TimeInterpolator interpolator,
			TunaAnimateEndListener tunaAnimateEndListener){
		playObjectAnimation(view, duration, startDelay, 1.0f, alpha, 1.0f, scaleX, 1.0f, scaleY, 0.0f, rotationX, 0.0f, rotationY, 0.0f, translationX, 0.0f, translationY,
				fillBefore, repeatCount, repeatMode, interpolator, tunaAnimateEndListener);
	}

	// 21 Parameters
	public static void playObjectAnimation(View view, long duration, long startDelay, float startAlpha, float endAlpha, float startScaleX, float endScaleX, float startScaleY,
			float endScaleY, float startRotationX, float endRotationX, float startRotationY, float endRotationY, float startTranslationX, float endTranslationX,
			float startTranslationY, float endTranslationY, boolean fillBefore, int repeatCount, int repeatMode, TunaAnimateEndListener tunaAnimateEndListener){

		playObjectAnimation(view, duration, startDelay, startAlpha, endAlpha, startScaleX, endScaleX, startScaleY, endScaleY, startRotationX, endRotationX, startRotationY,
				endRotationY, startTranslationX, endTranslationX, startTranslationY, endTranslationY, fillBefore, repeatCount, repeatMode, null, tunaAnimateEndListener);

	}

	// 22 Parameters
	public static void playObjectAnimation(final View view, final long duration, final long startDelay, final float startAlpha, final float endAlpha, final float startScaleX,
			final float endScaleX, final float startScaleY, final float endScaleY, final float startRotationX, final float endRotationX, final float startRotationY,
			final float endRotationY, final float startTranslationX, final float endTranslationX, final float startTranslationY, final float endTranslationY,
			final boolean fillBefore, final int repeatCount, final int repeatMode, final TimeInterpolator interpolator, final TunaAnimateEndListener tunaAnimateEndListener){

		final AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setStartDelay(startDelay);
		if (interpolator != null) {
			animatorSet.setInterpolator(interpolator);
		}

		// is sure to set the visToInvisAlpha argument
		ObjectAnimator visToInvisAlpha = ObjectAnimator.ofFloat(view, "alpha", startAlpha, endAlpha).setDuration(duration);
		// visToInvisAlpha.setStartDelay(startDelay);
		visToInvisAlpha.setRepeatCount(repeatCount);
		visToInvisAlpha.setRepeatMode(repeatMode);

		if (startScaleX != 1.0f || endScaleX != 1.0f) {
			ObjectAnimator visToInvisScaleX = ObjectAnimator.ofFloat(view, "scaleX", startScaleX, endScaleX).setDuration(duration);
			// visToInvisScaleX.setStartDelay(startDelay);
			visToInvisScaleX.setRepeatCount(repeatCount);
			visToInvisScaleX.setRepeatMode(repeatMode);
			animatorSet.play(visToInvisAlpha).with(visToInvisScaleX);
		}
		if (startScaleY != 1.0f || endScaleY != 1.0f) {
			ObjectAnimator visToInvisScaleY = ObjectAnimator.ofFloat(view, "scaleY", startScaleY, endScaleY).setDuration(duration);
			// visToInvisScaleY.setStartDelay(startDelay);
			visToInvisScaleY.setRepeatCount(repeatCount);
			visToInvisScaleY.setRepeatMode(repeatMode);
			animatorSet.play(visToInvisAlpha).with(visToInvisScaleY);
		}
		if (startRotationX != 1.0f || endRotationX != 1.0f) {
			ObjectAnimator visToInvisRotationX = ObjectAnimator.ofFloat(view, "rotationX", startRotationX, endRotationX).setDuration(duration);
			// visToInvisRotationX.setStartDelay(startDelay);
			visToInvisRotationX.setRepeatCount(repeatCount);
			visToInvisRotationX.setRepeatMode(repeatMode);
			animatorSet.play(visToInvisAlpha).with(visToInvisRotationX);
		}
		if (startRotationY != 1.0f || endRotationY != 1.0f) {
			ObjectAnimator visToInvisRotationY = ObjectAnimator.ofFloat(view, "rotationY", startRotationY, endRotationY).setDuration(duration);
			// visToInvisRotationY.setStartDelay(startDelay);
			visToInvisRotationY.setRepeatCount(repeatCount);
			visToInvisRotationY.setRepeatMode(repeatMode);
			animatorSet.play(visToInvisAlpha).with(visToInvisRotationY);
		}
		if (startTranslationX != 0.0f || endTranslationX != 0.0f) {
			ObjectAnimator visToInvisTranslationX = ObjectAnimator.ofFloat(view, "translationX", startTranslationX, endTranslationX).setDuration(duration);
			// visToInvisTranslationX.setStartDelay(startDelay);
			visToInvisTranslationX.setRepeatCount(repeatCount);
			visToInvisTranslationX.setRepeatMode(repeatMode);
			animatorSet.play(visToInvisAlpha).with(visToInvisTranslationX);
		}
		if (startTranslationY != 0.0f || endTranslationY != 0.0f) {
			ObjectAnimator visToInvisTranslationY = ObjectAnimator.ofFloat(view, "translationY", startTranslationY, endTranslationY).setDuration(duration);
			// visToInvisTranslationY.setStartDelay(startDelay);
			visToInvisTranslationY.setRepeatCount(repeatCount);
			visToInvisTranslationY.setRepeatMode(repeatMode);
			animatorSet.play(visToInvisAlpha).with(visToInvisTranslationY);
		}
		if (startScaleX == 1.0f && endScaleX == 1.0f && startScaleY == 1.0f && endScaleY == 1.0f && startRotationX == 1.0f && endRotationX == 1.0f && startRotationY == 1.0f
				&& endRotationY == 1.0f && startTranslationX == 0.0f && endTranslationX == 0.0f && startTranslationY == 0.0f && endTranslationY == 0.0f) {
			animatorSet.play(visToInvisAlpha);
		}

		animatorSet.addListener(new AnimatorListenerAdapter(){
			@Override
			public void onAnimationEnd(Animator animator){

				// First Recovery Again Monitor
				if (tunaAnimateEndListener != null) {
					tunaAnimateEndListener.tunaAnimateEnd(view);
				}

				// recoveryAnimation
				if (fillBefore) {
					if (endAlpha != 1.0f) {
						view.setAlpha(1.0f);
					}
					if (endScaleX != 1.0f) {
						view.setScaleX(1.0f);
					}
					if (endScaleY != 1.0f) {
						view.setScaleY(1.0f);
					}
					if (endRotationX != 1.0f) {
						view.setRotationX(0.0f);
					}
					if (endRotationY != 1.0f) {
						view.setRotationY(0.0f);
					}
					if (endTranslationX != 0.0f) {
						view.setTranslationX(0.0f);
					}
					if (endTranslationY != 0.0f) {
						view.setTranslationY(0.0f);
					}
				}
			}
		});
		animatorSet.start();
	}
}
