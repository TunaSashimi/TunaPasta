package com.tunaPasta13.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

import com.tunaPasta13.R;

/**
 **
 */
public class FloatView extends ImageView{
	private float mTouchX;
	private float mTouchY;
	private float x;
	private float y;
	private OnClickListener mClickListener;

	private WindowManager windowManager = (WindowManager) getContext()
			.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
	// 此windowManagerParams变量为获取的全局变量，用以保存悬浮窗口的属性
	private WindowManager.LayoutParams windowManagerParams = ((FloatApplication) getContext().getApplicationContext()).getWindowParams();

	public FloatView(Context context) {
		super(context);
		isMove = false;
		isRight = false;
		setImageResource(leftResource);
		windowManagerParams.type = LayoutParams.TYPE_PHONE; // 设置window type
		windowManagerParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明
		// 设置Window flag
		windowManagerParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
				| LayoutParams.FLAG_NOT_FOCUSABLE;
		/*
		 * 注意，flag的值可以为：
		 * LayoutParams.FLAG_NOT_TOUCH_MODAL 不影响后面的事件
		 * LayoutParams.FLAG_NOT_FOCUSABLE  不可聚焦
		 * LayoutParams.FLAG_NOT_TOUCHABLE 不可触摸
		 */
		// 调整悬浮窗口至左上角，便于调整坐标
		windowManagerParams.gravity = Gravity.LEFT | Gravity.TOP; 
		// 以屏幕左上角为原点，设置x、y初始值
		windowManagerParams.x = 0;
		windowManagerParams.y = 0;
		// 设置悬浮窗口长宽数据
		windowManagerParams.width = LayoutParams.WRAP_CONTENT;
		windowManagerParams.height = LayoutParams.WRAP_CONTENT;
	}

	private boolean  isMove = false;
	private boolean  isRight = false;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//获取到状态栏的高度
		Rect frame =  new  Rect();  
		getWindowVisibleDisplayFrame(frame);
		int  statusBarHeight = frame.top;
		
		System.out.println("statusBarHeight:"+statusBarHeight);
		// 获取相对屏幕的坐标，即以屏幕左上角为原点
		x = event.getRawX();
		y = event.getRawY() - statusBarHeight; // statusBarHeight是系统状态栏的高度
		Log.i("tag", "currX" + x + "====currY" + y);

		int screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: // 捕获手指触摸按下动作
			// 获取相对View的坐标，即以此View左上角为原点
			mTouchX = event.getX();
			mTouchY = event.getY();
			isMove = false;
			Log.i("tag", "startX" + mTouchX + "====startY"
					+ mTouchY);
			if(isRight) {
				setImageResource(focusRightResource);
			} else {
				setImageResource(focusLeftResource);
			}
			break;

		case MotionEvent.ACTION_MOVE: // 捕获手指触摸移动动作
			if(x > 35 && (screenWidth - x) >35) {
				isMove = true;
				setImageResource(defaultResource);
				updateViewPosition();
			}
			break;
		case MotionEvent.ACTION_UP: // 捕获手指触摸离开动作
			if(isMove) {
				isMove = false;
				float halfScreen = screenWidth/2;
				if(x <= halfScreen) {
					setImageResource(leftResource);
					x = 0 ;
					isRight = false;
				} else {
					setImageResource(rightResource);
					x = screenWidth;
					isRight = true;
				}
				updateViewPosition();
			} else {
				setImageResource(leftResource);
				if(mClickListener!=null) {
					mClickListener.onClick(this);
				}
			}
			mTouchX = mTouchY = 0;
			break;
		}
		return true;
	}
	@Override
	public void setOnClickListener(OnClickListener l) {
		this.mClickListener = l;
	}
	private void updateViewPosition() {
		// 更新浮动窗口位置参数
		windowManagerParams.x = (int) (x - mTouchX);
		windowManagerParams.y = (int) (y - mTouchY);
		windowManager.updateViewLayout(this, windowManagerParams); // 刷新显示
	}

	private int defaultResource = R.drawable.icon_default;
	private int focusLeftResource = R.drawable.icon_focus_left;
	private int focusRightResource = R.drawable.icon_focus_right;
	private int leftResource = R.drawable.icon_default_left;
	private int rightResource = R.drawable.icon_default_right;
}
