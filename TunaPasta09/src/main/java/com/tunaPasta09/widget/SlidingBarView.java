package com.tunaPasta09.widget;
import java.util.Hashtable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
/**
 * @author junzhang
 */
public class SlidingBarView extends View implements OnTouchListener {

	private final static int MESSAGE_REFREASH_SCREEM = 100;
	private final static double fCheckRectGapFactor = 0.2;

	private Bitmap mCurTagImg = null;

	private double mSlider_Bg_Scale_Factor = 0;

	private double mBg_Width_Factor = 0;
	private double mBg_Height_Factor = 0;
	private double mBg_Width_Accy = 0;
	private double mBg_Height_Accy = 0;
	private int mSlider_Right_Limit = 0;
	private int mSlider_Left_Limit = 0;

	private double mSlider_Width_Factor = 0;
	private double mSilder_Wdith_Accy = 0;
	private double mSlider_Height_Accy = 0;

	private Bitmap mBackgournd;

	private Bitmap mSlider_Normal = null;
	private Bitmap mSlider_Pressed = null;

	private Rect mRect_Bg = new Rect();

	private Rect mRect_Slider = new Rect();

	private int mTag_Divider = -1;

	private Hashtable<String, Bitmap> mTagImg = null;
	private String[] mTag = null;

	private RectChain mRectChain = null;
	private SlidingAnimation mAni = null;
	private Handler handler = new MyHandler();

	private boolean isPressed = false;

	private float mTmpXDiff = 0;

	private boolean mHasMeasured = false;

	private OnChangedListener mOnchangeListener;

	private int width;
	private int height;
	
	public SlidingBarView(Context context) {
		super(context);
	}

	public SlidingBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void init(Bitmap bitmap, Bitmap bitmap_slider_normal,
			Bitmap bitmap_slider_pressed, String[] tagString,
			Hashtable<String, Bitmap> tagImg) {

		mBackgournd = bitmap;
		
		int tmp_width = mBackgournd.getWidth();
		int tmp_height = mBackgournd.getHeight();
		
		//宽度因素,图片宽度/高度
		mBg_Width_Factor = (double) tmp_width / (double) tmp_height;
		//高度因素,图片高度/宽度
		mBg_Height_Factor = (double) tmp_height / (double) tmp_width;

		//
		mSlider_Normal = bitmap_slider_normal;
		//
		mSlider_Pressed = bitmap_slider_pressed;

		//
		mTag = tagString;
		//
		mTagImg = tagImg;
		//
		mCurTagImg = tagImg.get(tagString[0]);

		
		tmp_width = mSlider_Normal.getWidth();
		tmp_height = mSlider_Normal.getHeight();

		mSlider_Width_Factor = (double) tmp_width / (double) tmp_height;
		
		//背景高度/normalHeight
		mSlider_Bg_Scale_Factor = (double) mBackgournd.getHeight() / (double) tmp_height;
		//分隔
		mTag_Divider = mTag.length;

		ViewTreeObserver vto = this.getViewTreeObserver();
		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			public boolean onPreDraw() {
				if (mHasMeasured == false) {
					 width = SlidingBarView.this.getMeasuredWidth();
					 height = SlidingBarView.this.getMeasuredHeight();
					initializeComponent(width, height);
					mHasMeasured = true;
				}
				return true;
			}
		});

		initializeComponent(width,height);
		
		setOnTouchListener(this);
		
	}

	// initialize Rect
	private void initializeComponent(int width, int height) {

		if (width >= height) {

			mSlider_Height_Accy = height;
			mSilder_Wdith_Accy = mSlider_Height_Accy * mSlider_Width_Factor;

			mBg_Height_Accy = height * mSlider_Bg_Scale_Factor;
			mBg_Width_Accy = mBg_Height_Accy * mBg_Width_Factor;

			int left = (int) ((width - mBg_Width_Accy) / 2);
			int top = (int) ((height - mBg_Height_Accy) / 2);
			int right = (int) ((width - mBg_Width_Accy) / 2)+ (int) mBg_Width_Accy;
			int bottom = (int) ((height - mBg_Height_Accy) / 2)+ (int) mBg_Height_Accy;

			mRect_Bg.set(left, top, right, bottom);

		} else {

			mBg_Width_Accy = width;
			mBg_Height_Accy = mBg_Width_Accy * mBg_Height_Factor;

			int left_bg = (int) ((width - mBg_Width_Accy) / 2);
			int top_bg = (int) ((height - mBg_Height_Accy) / 2);
			int right_bg = (int) ((width - mBg_Width_Accy) / 2) + (int) mBg_Width_Accy;
			int bottom_bg = (int) ((height - mBg_Height_Accy) / 2) + (int) mBg_Height_Accy;

			mRect_Bg.set(left_bg, top_bg, right_bg, bottom_bg);

		}

		mRectChain = new RectChain(mRect_Bg, mTag_Divider, width, height);

		mAni = new SlidingAnimation();
		mAni.start();

	}

	protected void onDraw(Canvas canvas) {

		if (width==0||height==0) {
			width=getWidth();
			height=getHeight();
			initializeComponent(width, height);
		}
		
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG| Paint.FILTER_BITMAP_FLAG));
		
		super.onDraw(canvas);
		
		Paint paint = new Paint();

		canvas.drawBitmap(mBackgournd, null, mRect_Bg, paint);
		
		
		// if (onSlip) {// 是否是在滑动状态,

		if (mRect_Slider.right >= mSlider_Right_Limit) {

			mRect_Slider.left = mSlider_Right_Limit - (int) mSilder_Wdith_Accy;
			mRect_Slider.right = mSlider_Right_Limit;
		}

		if (mRect_Slider.left <= mSlider_Left_Limit) {

			mRect_Slider.left = mSlider_Left_Limit;
			mRect_Slider.right = mRect_Slider.left + (int) mSilder_Wdith_Accy;

		}

		// }

		if (isPressed == true) {
			canvas.drawBitmap(mSlider_Pressed, null, mRect_Slider, paint);
		} else {
			canvas.drawBitmap(mSlider_Normal, null, mRect_Slider, paint);
		}

		canvas.drawBitmap(mCurTagImg, null, mRect_Slider, paint);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (mAni.isTranslating() == true) {

			return true;

		}

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN: {

			if (mTmpXDiff == 0)
				mTmpXDiff = event.getX() - mRect_Slider.left;
			
			isPressed = true;

			break;
		}

		case MotionEvent.ACTION_MOVE: {


			mRect_Slider.left = (int) (event.getX() - mTmpXDiff);
			mRect_Slider.right = mRect_Slider.left + (int) mSilder_Wdith_Accy;

			int[] centrePoint = mRectChain.getOverLapRect((int) (event.getX()),mRect_Slider.centerY());
			this.mCurTagImg = mRectChain.getCurTagImg();


			break;
		}

		case MotionEvent.ACTION_UP: {

			int[] centrePoint = mRectChain.getOverLapRect((int) (event.getX()),mRect_Slider.centerY());
			this.mCurTagImg = mRectChain.getCurTagImg();

			mAni.setAnimationPath(mRect_Slider.left,(centrePoint[0] - (int) (mRect_Slider.width() / 2)));
			mAni.wakeUp();

			isPressed = false;

			break;
		}

		}
		// 刷新界面
		invalidate();
		return true;
	}


	/**
	 * 为WiperSwitch设置一个监听，供外部调用的方法
	 * 
	 * @param listener
	 */
	public void setOnChangedListener(OnChangedListener listener) {
		this.mOnchangeListener = listener;
	}

	@Override
	public void setOnClickListener(OnClickListener listener) {
	}

	public interface OnChangedListener {
		public void OnChanged(SlidingBarView component_Sliding_Bar, String tag);
	}

	private class MyHandler extends Handler {

		public void handleMessage(Message msg) {

			invalidate();

		}

	}

	private class SlidingAnimation extends Thread {

		private final static int STEP = 5;

		private final static int STEP_TIME_GAP = 20;

		private int mBaseStepDistance = 0;

		private Object mObserverSetLock = new Object();

		private boolean isStop = false;

		private int mSrc = 0;

		private int mDes = 0;

		private boolean isTranslating = false;

		private int mTanslateDirection = 1;

		public SlidingAnimation() {

		}

		public void run() {

			synchronized (mObserverSetLock) {

				try {
					while (isStop == false) {

						waiting();
						int step = 0;

						for (int cruStep = 1; cruStep < STEP + 1; cruStep++) {

							mBaseStepDistance = (int) (mBaseStepDistance * 0.5);

							step = (int) (mTanslateDirection * mBaseStepDistance);

							mRect_Slider.left = mRect_Slider.left + step;
							mRect_Slider.right = mRect_Slider.left
									+ (int) mSilder_Wdith_Accy;
							//
							handler.sendEmptyMessage(MESSAGE_REFREASH_SCREEM);
							Thread.sleep(STEP_TIME_GAP);
						}

						mRect_Slider.left = mDes;
						mRect_Slider.right = mRect_Slider.left
								+ (int) mSilder_Wdith_Accy;
						handler.sendEmptyMessage(MESSAGE_REFREASH_SCREEM);

						isTranslating = false;
					}
				} catch (Exception e) {
				} finally {
					isTranslating = false;
				}

			}

		}

		private void waiting() {
			synchronized (mObserverSetLock) {
				try {
					mObserverSetLock.wait();
				} catch (InterruptedException e) {

				}
			}
		}

		public boolean isTranslating() {

			return isTranslating;
		}

		public void setAnimationPath(int src, int des) {

			mSrc = src;
			mDes = des;

			if (mSrc <= mDes)
				mTanslateDirection = 1;
			else
				mTanslateDirection = -1;

			mBaseStepDistance = (int) (Math.abs(mSrc - mDes));

		}

		public void wakeUp() {

			synchronized (mObserverSetLock) {
				mObserverSetLock.notify();
				isTranslating = true;
			}

		}

	}

	private class RectChain {

		private Rect[] mRectArray = null;
		private int rect_width = 0;
		private int mOverLapIndex = 0;
		private int mTmpCursor = 0;

		private int[] centerPoint = new int[2];

		public RectChain(Rect BaseRect, int divider, int width, int height) {

			mRectArray = new Rect[divider];

			for (int i = 0; i < divider; i++) {

				mRectArray[i] = new Rect();

			}

			iniRectArray(BaseRect, divider, width, height);

		}

		public Rect[] getRectSet() {

			return this.mRectArray;

		}

		private void iniRectArray(Rect baseRect, int divider, int width, int height) {

			rect_width = (baseRect.width() - 3) / divider;

			baseRect.height();

			for (int i = 0; i < divider; i++) {

				mRectArray[i].left = rect_width * i + baseRect.left;
				mRectArray[i].right = mRectArray[i].left + rect_width;
				mRectArray[i].top = (int) (baseRect.top * (1 + fCheckRectGapFactor));
				mRectArray[i].bottom = (int) (baseRect.bottom * (1 + fCheckRectGapFactor));

			}

			mRect_Slider.left = mRectArray[0].centerX() - (int) (mSilder_Wdith_Accy / 2);
			mRect_Slider.right = mRect_Slider.left + (int) mSilder_Wdith_Accy;
			mRect_Slider.top = (int) ((height - mSlider_Height_Accy) / 2);
			mRect_Slider.bottom = (int) ((height - mSlider_Height_Accy) / 2) + (int) mSlider_Height_Accy;

			mSlider_Left_Limit = mRect_Slider.left;
			mSlider_Right_Limit = mRectArray[divider - 1].right;

			mTmpCursor = mOverLapIndex;
		}

		public int[] getOverLapRect(int centerX, int centreY) {

			for (int i = 0; i < mRectArray.length; i++) {

				if (mRectArray[i].contains(centerX, centreY)) {

					mOverLapIndex = i;

					centerPoint[0] = mRectArray[i].centerX();
					centerPoint[1] = mRectArray[i].centerY();

				}

			}

			return centerPoint;
		}

		public Bitmap getCurTagImg() {

			if (mTmpCursor != mOverLapIndex) {
				mOnchangeListener.OnChanged(SlidingBarView.this,getCurTag());
				mTmpCursor = mOverLapIndex;
			}

			return mTagImg.get(mTag[mOverLapIndex]);

		}

		private String getCurTag() {

			return mTag[mOverLapIndex];

		}

	}

	public void reset() {
		Rect[] tmp = mRectChain.getRectSet();
		int[] centrePoint = mRectChain.getOverLapRect((int) (tmp[0].centerX()),mRect_Slider.centerY());
		this.mCurTagImg = mRectChain.getCurTagImg();
		mAni.setAnimationPath(mRect_Slider.left,(centrePoint[0] - (int) (mRect_Slider.width() / 2)));
		mAni.wakeUp();
		isPressed = false;
		this.mOnchangeListener = null;
		tmp = null;
	}
}
