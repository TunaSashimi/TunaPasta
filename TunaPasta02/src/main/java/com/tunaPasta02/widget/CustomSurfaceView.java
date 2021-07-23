package com.tunaPasta02.widget;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tunaPasta02.entity.Box;
public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	private boolean isReady = false;
	private Box box;
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	public CustomSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 添加一个回调函数；执行完成后，马上就会回调 surfaceCreated方法了 
		//	SurfaceHolder.Callback在底层的Surface状态发生变化的时候通知View
		getHolder().addCallback(this);
	}
	public void draw() { 
		if (isReady) {
			SurfaceHolder holder = getHolder();
			Canvas canvas = holder.lockCanvas();
//			画布设置背景~
			canvas.drawColor(Color.BLACK);
//			做出一个画笔
			Paint paint = new Paint();
//			给画笔设置颜色
			paint.setColor(Color.rgb(0xFF, 0xCC, 0));
//			用画笔在画布上画一个矩形
			canvas.drawRect(new Rect(box.x, box.y, box.x + box.width, box.y+ box.height), paint);
//			画布解锁
			holder.unlockCanvasAndPost(canvas);
		}
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		isReady = true;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}
}
