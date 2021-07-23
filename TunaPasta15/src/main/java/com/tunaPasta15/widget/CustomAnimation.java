package com.tunaPasta15.widget;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class CustomAnimation extends Animation{

	//通过重写Animation的 applyTransformation (float interpolatedTime, Transformation t)函数来实现自定义动画效果，
	//另外一般也会实现 initialize (int width, int height, int parentWidth, int parentHeight)函数，
	//这是一个回调函数告诉Animation目标View的大小参数，在这里可以初始化一些相关的参数，
	//例如设置动画持续时间、设置Interpolator、设置动画的参考点等。在绘制动画的过程中会反复的调用applyTransformation 函数，
	//每次调用参数interpolatedTime值都会变化，该参数从0渐变为1，当该参数为1时表明动画结束。通过参数Transformation 来获取变换的矩阵（matrix），通过改变矩阵就可以实现各种复杂的效果。
	private int mCenterX, mCenterY;

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t){
		// 通过Matrix.setScale函数来缩放，该函数的两个参数代表X、Y轴缩放因子，由于interpolatedTime是从0到1变化所在这里实现的效果就是控件从最小逐渐变化到最大。
		Matrix matrix = t.getMatrix();
		matrix.setScale(interpolatedTime, interpolatedTime);
		// Matrix 可以实现各种复杂的变换

		// preTranslate函数是在缩放前移动而postTranslate是在缩放完成后移动。
		matrix.preTranslate(-mCenterX, -mCenterY);
		matrix.postTranslate(mCenterX, mCenterY);
	}

	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight){
		super.initialize(width, height, parentWidth, parentHeight);
		// 初始化中间坐标
		mCenterX = width / 2;
		mCenterY = height / 2;

		// 设置变换持续的时间2500毫秒，然后设置Interpolator为LinearInterpolator并设置FillAfter为true这样可以在动画结束的时候保持动画的完整性。
		setDuration(2000);
		setFillAfter(true);
		setInterpolator(new LinearInterpolator());
	}
}
