package com.tunaPasta09.widget;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;
public class Rotate3D extends Animation {
	//开始角度
	private final float mFromDegrees;
	//结束角度
	private final float mToDegrees;
	private final float mCenterX;
	private final float mCenterY;
	//中心点
	private final float mDepthZ;
	//是否需要扭曲
	private final boolean mReverse;
	//摄像头
	private Camera mCamera;

	public Rotate3D(float fromDegrees, float toDegrees, float centerX, float centerY, float depthZ, boolean reverse) {
		mFromDegrees = fromDegrees;
		mToDegrees = toDegrees;
		mCenterX = centerX;
		mCenterY = centerY;
		mDepthZ = depthZ;
		mReverse = reverse;
	}

	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mCamera = new Camera();
	}

	// 生成Transformation
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation transformation) {
		float fromDegrees = mFromDegrees;
		// 生成中间角度
		float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
		float centerX = mCenterX;
		float centerY = mCenterY;
		Camera camera = mCamera;
		Matrix matrix = transformation.getMatrix();
		camera.save();
		if (mReverse) {
			camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
		} else {
			camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
		}
		camera.rotateY(degrees);
		 // 取得变换后的矩阵
		camera.getMatrix(matrix);
		camera.restore();

		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
		
		//其中包括了旋转的开始和结束角度，中心点、是否扭曲、和一个Camera，这里我们主要分析applyTransformation函数，
		//其中第一个参数就是通过getTransformation函数传递的差指点，然后我们根据这个差值通过线性差值算法计算出一个中间角度degrees，
		//Camera类是用来实现绕Y轴旋转后透视投影的，因此我们首先通过t.getMatrix()取得当前的矩阵，然后通过camera.translate来对矩阵进行平移变换操作，
		//camera.rotateY进行旋转。这样我们就可以很轻松的实现3D旋转效果了。
	}
}