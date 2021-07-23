package com.tunaPasta07.widget;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;

public class DialLayoutAnimation {
	/**
	 * 收动画 
	 * @param viewGroup
	 * View的子类，这里代替有很多ImageView的相对布局
	 * @param duration
	 * 持续时间
	 */
	public static void startAnimationIN(ViewGroup viewGroup, int duration){
		
		for(int i = 0; i < viewGroup.getChildCount(); i++ ){
			viewGroup.getChildAt(i).setVisibility(View.VISIBLE);//设置显示
			viewGroup.getChildAt(i).setFocusable(true);//获得焦点
			viewGroup.getChildAt(i).setClickable(true);//可以点击
		}
		
		Animation animation;
		/**
		 * 旋转动画
		 * RotateAnimation(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue)
		 * fromDegrees 开始旋转角度
		 * toDegrees 旋转到的角度
		 * pivotXType X轴 参照物
		 * pivotXValue x轴 旋转的参考点
		 * pivotYType Y轴 参照物
		 * pivotYValue Y轴 旋转的参考点
		 */
		animation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
		animation.setFillAfter(true);//停留在动画结束位置
		animation.setDuration(duration);
		
		viewGroup.startAnimation(animation);
		
	}
	
	/**
	 * 展示动画
	 * @param viewGroup
	 * 这里是相对布局，
	 * @param duration
	 * 持续时间
	 * @param startOffSet
	 * 开始时间
	 */
	public static void startAnimationOUT(final ViewGroup viewGroup, int duration , int startOffSet){
		Animation animation;
		animation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
		animation.setFillAfter(true);//停留在动画结束位置
		animation.setDuration(duration);
		animation.setStartOffset(startOffSet);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				for(int i = 0; i < viewGroup.getChildCount(); i++ ){
					viewGroup.getChildAt(i).setVisibility(View.GONE);//设置显示
					viewGroup.getChildAt(i).setFocusable(false);//获得焦点
					viewGroup.getChildAt(i).setClickable(false);//可以点击
				}
			}
		});
		viewGroup.startAnimation(animation);
	}
}
