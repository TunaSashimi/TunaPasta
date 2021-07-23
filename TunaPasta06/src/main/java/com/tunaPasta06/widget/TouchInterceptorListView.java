package com.tunaPasta06.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.tunaPasta06.R;


public class TouchInterceptorListView extends ListView {

	public static interface TouchInterceptorAdapter extends ListAdapter{
		void setItem(int pos,Object value);
		void setSelectedPos(int pos);
	}
	
	private Context mContext;
	private WindowManager.LayoutParams mWindowParams;
	private WindowManager mWindowManager;
	private ImageView mDragView;
	private Bitmap mDragBitmap;
	private TouchInterceptorAdapter mAdapter;
	private boolean mMoveMode = false;
	private int mDragPoint;
	private int mCoordOffset;
	private int mSelectedPos = -1;
	public TouchInterceptorListView(Context context) {
		super(context);
		mContext = context;
	}

	public TouchInterceptorListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public TouchInterceptorListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			int x = (int) ev.getX();
			int y = (int) ev.getY();
			int pos = pointToPosition(x, y);
			int fst_pos = getFirstVisiblePosition();
			mSelectedPos = pos;
			if(mSelectedPos<0)
				break;
			View localView = getChildAt(mSelectedPos-fst_pos);
			View moveView = findViewById(R.id.icon);
			if (x < moveView.getLeft() || x > moveView.getRight())
				break;
			mMoveMode = true;
			int top = localView.getTop();
			mDragPoint = y - top;
			mCoordOffset = (int) ev.getRawY() - y;
			localView.setDrawingCacheEnabled(true);
			Bitmap localBitmap = Bitmap.createBitmap(localView.getDrawingCache());
			startDragging(localBitmap, y);
			mAdapter.setSelectedPos(mSelectedPos);
			if(mAdapter instanceof BaseAdapter)
				((BaseAdapter) mAdapter).notifyDataSetChanged();
			return true;
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (!mMoveMode)
			return super.onTouchEvent(ev);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_MOVE:
			int x = (int) ev.getX();
			int y = (int) ev.getY();
			dragView(x, y);
			break;
		case MotionEvent.ACTION_UP:
			stopDragging();
			mMoveMode = false;
			mSelectedPos = -1;
			mAdapter.setSelectedPos(mSelectedPos);
			if(mAdapter instanceof BaseAdapter)
				((BaseAdapter) mAdapter).notifyDataSetChanged();
			break;
		}
		return true;
	}

	private void startDragging(Bitmap paramBitmap, int paramInt) {
		stopDragging();
		WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
		this.mWindowParams = localLayoutParams;
		this.mWindowParams.gravity = 48;
		mWindowParams.x = 0;
		int i = this.mDragPoint;
		int j = paramInt - i;
		int k = this.mCoordOffset;
		int l = j + k;
		mWindowParams.y = l;
		mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mWindowParams.flags = 408;
		mWindowParams.format = -1;
		mWindowParams.alpha = 100;
		mDragView = new ImageView(mContext);
		int bgColor = this.mContext.getResources().getColor(R.color.selected);
		mDragView.setBackgroundColor(bgColor);
		mDragView.setImageBitmap(paramBitmap);
		mDragView.setAlpha(100);
		this.mDragBitmap = paramBitmap;
		mWindowManager = (WindowManager)mContext.getSystemService("window");
		mWindowManager.addView(mDragView, mWindowParams);
	}

	private void stopDragging() {
		if (this.mDragView != null) {
			WindowManager localWindowManager = (WindowManager) this.mContext.getSystemService("window");
			ImageView localImageView = this.mDragView;
			localWindowManager.removeView(localImageView);
			mDragView.setImageDrawable(null);
			mDragView = null;
		}
		if (mDragBitmap != null) {
			mDragBitmap.recycle();
			mDragBitmap = null;
		}
	}

	private void dragView(int x, int y) {
//		System.out.println("dragView->x:"+x+"y:"+y);
		int i1 = y - mDragPoint;
		int i3 = i1 + mCoordOffset;
		mWindowParams.y = i3;
		mWindowManager.updateViewLayout(mDragView, mWindowParams);
		int pos = pointToPosition(x, y);
		int fst_pos = getFirstVisiblePosition();
		int fst_top = getChildAt(0).getTop();
		int distance = getChildAt(1).getHeight();
		int upBound = getChildAt(0).getBottom();
		int downBound = getHeight()-2*distance;
		changeItem(pos, mSelectedPos);
//		if(y<upBound&&!(fst_pos==0&&fst_top<=0)){
		if(y<upBound&&(fst_top!=0||fst_pos!=0)){
			smoothScrollBy(-distance, distance*4);
		}else if(y>downBound&&!(fst_pos+getChildCount()>=getCount())){
			smoothScrollBy(distance, distance*4);
		}
	}
	
	private void changeItem(int des,int src){
		if(des==src||des<0||src<0)
			return;
		Object objDes = getItemAtPosition(des);
		Object objSrc = getItemAtPosition(src);
		mAdapter.setItem(des, objSrc);
		mAdapter.setItem(src, objDes);
		if(mAdapter instanceof BaseAdapter){
			((BaseAdapter) mAdapter).notifyDataSetChanged();
		}
		mSelectedPos = des;
		mAdapter.setSelectedPos(mSelectedPos);
	}
	
	public void setAdapter(TouchInterceptorAdapter adapter) {
		mAdapter = adapter;
		super.setAdapter(adapter);
	}
}
