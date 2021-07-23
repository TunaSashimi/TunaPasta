package com.tunaPasta06.widget;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.tunaPasta06.adapter.RollNavigationBarAdapter;

public class RollNavigationBar extends CanGetSizeLinearLayout {
	private String tag = "RollNavigationBar";
	private NavigationBarListener listener;
	private RollNavigationBarAdapter adapter;
	private int selectedChildPosition = 0;

	private float selecterMoveContinueTime = 0.1F;

	private boolean isMove = false;
	private Rect rect;
	private Paint paint;
	private BitmapDrawable selecter;
	private int selecterDrawableSource;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			RollNavigationBar.this.refreshView(RollNavigationBar.this.adapter);
		}
	};

	public int getSelectedChildPosition() {
		return this.selectedChildPosition;
	}

	public void setSelectedChildPosition(int selectedChildPosition) {
		this.selectedChildPosition = selectedChildPosition;
	}

	public float getSelecterMoveContinueTime() {
		return this.selecterMoveContinueTime;
	}

	public void setSelecterMoveContinueTime(float selecterMoveContinueTime) {
		if ((selecterMoveContinueTime >= 0.1D)
				&& (selecterMoveContinueTime <= 1.0F))
			this.selecterMoveContinueTime = selecterMoveContinueTime;
	}

	public int getSelecterDrawableSource() {
		return this.selecterDrawableSource;
	}

	public void setSelecterDrawableSource(int selecterDrawableSource) {
		this.selecterDrawableSource = selecterDrawableSource;
	}

	public RollNavigationBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case 0:
					RollNavigationBar.this.selectedChildPosition = RollNavigationBar.this
							.getChooseFieldPosition(v, event);
					RollNavigationBar.this.moveSelecter();
					break;
				case 2:
					break;
				case 1:
				}

				if (RollNavigationBar.this.listener != null)
					RollNavigationBar.this.listener.onNavigationBarClick(
							RollNavigationBar.this.selectedChildPosition,
							RollNavigationBar.this, event);
				return true;
			}
		});
	}

	private int getChooseFieldPosition(View v, MotionEvent event) {
		ViewSizeAndPosition vsp = getViewSizeAndPosition();
		float singleChildViewWidth = vsp.getRight() / this.adapter.getCount();
		return T.getInt(event.getX() / singleChildViewWidth, T.ABANDON);
	}

	public void setAdapter(RollNavigationBarAdapter adapter) {
		this.adapter = adapter;
		LinearLayout linearLayout = null;
		for (int i = 0; i < this.adapter.getCount(); i++) {
			linearLayout = new LinearLayout(getContext());
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					-1, -1);
			params.weight = 1.0F;
			params.gravity = 17;
			Log.v(this.tag, "paramsSize=" + params.width + "," + params.height);
			linearLayout.setLayoutParams(params);
			addView(linearLayout);
			this.adapter.getView(i, linearLayout, this);
		}
	}

	public void setNavigationBarListener(NavigationBarListener listener) {
		this.listener = listener;
	}

	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	protected void onDraw(Canvas canvas) {
		if (!this.isMove) {
			setSelecter();
		}

		super.onDraw(canvas);
		this.selecter.setBounds(this.rect);
		this.selecter.draw(canvas);
	}

	private void setSelecter() {
		if ((this.paint == null) || (this.rect == null)
				|| (this.selecter == null)) {
			this.paint = new Paint();
			this.rect = new Rect();
			this.selecter = new BitmapDrawable(getContext().getResources(),
					BitmapFactory.decodeResource(getContext().getResources(),
							getSelecterDrawableSource()));
		}

		ViewSizeAndPosition vsp = getViewSizeAndPosition();
		Log.v(this.tag, "roll,ViewSizeAndPosition=" + vsp.getWidth() + ","
				+ vsp.getHeight());
		int left = this.selectedChildPosition
				* (vsp.getWidth() / this.adapter.getCount());
		int right = left + vsp.getWidth() / this.adapter.getCount();
		int top = 0;
		int bottom = vsp.getHeight();
		this.rect.set(left, top, right, bottom);
	}

	public void moveSelecter() {
		new Thread() {
			public void run() {
				float s = 0.01F;
				RollNavigationBar.WillMoveInfo willMoveInfo = RollNavigationBar.this
						.getWillMoveInfo();
				int left = willMoveInfo.getStartViewSizeAndPosition().getLeft();
				int top = willMoveInfo.getStartViewSizeAndPosition().getTop();
				int right = willMoveInfo.getStartViewSizeAndPosition()
						.getRight();
				int bottom = willMoveInfo.getStartViewSizeAndPosition()
						.getBottom();
				float time = 0.0F;
				RollNavigationBar.this.isMove = true;
				while (RollNavigationBar.this.isMove) {
					time += s;
					left = (int) (left + willMoveInfo.getDv() * s);
					right = (int) (right + willMoveInfo.getDv() * s);
					RollNavigationBar.this.rect.set(left, top, right, bottom);
					if (time >= RollNavigationBar.this.selecterMoveContinueTime) {
						RollNavigationBar.this.isMove = false;
						RollNavigationBar.this.rect.set(willMoveInfo
								.getEndViewSizeAndPosition().getLeft(),
								willMoveInfo.getEndViewSizeAndPosition()
										.getTop(),
								willMoveInfo.getEndViewSizeAndPosition()
										.getRight(), willMoveInfo
										.getEndViewSizeAndPosition()
										.getBottom());
						RollNavigationBar.this.handler
								.sendMessage(new Message());
					}
					RollNavigationBar.this.postInvalidate();
					try {
						Thread.sleep((long) (s * 1000.0F));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	private WillMoveInfo getWillMoveInfo() {
		WillMoveInfo willMoveInfo = new WillMoveInfo();
		ViewSizeAndPosition startViewSizeAndPosition = new ViewSizeAndPosition();
		startViewSizeAndPosition.setLeft(this.rect.left);
		startViewSizeAndPosition.setTop(this.rect.top);
		startViewSizeAndPosition.setRight(this.rect.right);
		startViewSizeAndPosition.setBottom(this.rect.bottom);
		startViewSizeAndPosition.setWidth(this.rect.right - this.rect.left);
		startViewSizeAndPosition.setHeight(this.rect.bottom - this.rect.top);
		willMoveInfo.setStartViewSizeAndPosition(startViewSizeAndPosition);

		int endViewDistance = (this.rect.right - this.rect.left)
				* this.selectedChildPosition;

		ViewSizeAndPosition endViewSizeAndPosition = new ViewSizeAndPosition();
		endViewSizeAndPosition.setLeft(endViewDistance);
		endViewSizeAndPosition.setTop(this.rect.top);
		endViewSizeAndPosition.setRight(this.rect.right - this.rect.left
				+ endViewDistance);
		endViewSizeAndPosition.setBottom(this.rect.bottom);
		endViewSizeAndPosition.setWidth(this.rect.right - this.rect.left);
		endViewSizeAndPosition.setHeight(this.rect.bottom - this.rect.top);
		willMoveInfo.setEndViewSizeAndPosition(endViewSizeAndPosition);

		int willMoveDistance = endViewDistance - this.rect.left;
		int dv = (int) (willMoveDistance / getSelecterMoveContinueTime());
		willMoveInfo.setDv(dv);

		return willMoveInfo;
	}

	public void refreshView(RollNavigationBarAdapter adapter) {
		removeAllViews();
		setAdapter(adapter);
	}

	/**
	 * private ViewSizeAndPosition getChildViewSize() { ViewSizeAndPosition vsp
	 * = getViewSizeAndPosition(); ViewSizeAndPosition childViewSize = new
	 * ViewSizeAndPosition(); childViewSize.setWidth(vsp.getWidth() /
	 * this.adapter.getCount()); childViewSize.setHeight(vsp.getHeight());
	 * return childViewSize; }
	 */

	public static abstract interface NavigationBarListener {
		public abstract void onNavigationBarClick(int paramInt, View paramView,
				MotionEvent paramMotionEvent);
	}

	static class T {
		public static int ABANDON = 0;

		public static int CARRY = 1;

		public static int getInt(double d, int require) {
			int i = 0;
			if (ABANDON == require) {
				i = (int) d;
			} else if (CARRY == require) {
				int ii = (int) d;
				if (ii != 0) {
					i = ii + (d % ii > 0.0D ? 1 : 0);
				} else if (d == 0.0D)
					i = 0;
				else {
					i = 1;
				}
			}
			return i;
		}
	}

	class WillMoveInfo {
		private int dv;
		private ViewSizeAndPosition startViewSizeAndPosition;
		private ViewSizeAndPosition endViewSizeAndPosition;

		WillMoveInfo() {
		}

		public int getDv() {
			return this.dv;
		}

		public void setDv(int dv) {
			this.dv = dv;
		}

		public ViewSizeAndPosition getStartViewSizeAndPosition() {
			return this.startViewSizeAndPosition;
		}

		public void setStartViewSizeAndPosition(
				ViewSizeAndPosition startViewSizeAndPosition) {
			this.startViewSizeAndPosition = startViewSizeAndPosition;
		}

		public ViewSizeAndPosition getEndViewSizeAndPosition() {
			return this.endViewSizeAndPosition;
		}

		public void setEndViewSizeAndPosition(
				ViewSizeAndPosition endViewSizeAndPosition) {
			this.endViewSizeAndPosition = endViewSizeAndPosition;
		}
	}
}