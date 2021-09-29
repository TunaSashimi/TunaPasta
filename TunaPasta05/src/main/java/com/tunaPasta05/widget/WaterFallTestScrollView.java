package com.tunaPasta05.widget;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
public class WaterFallTestScrollView extends ScrollView {
	public OnScrollListener onScrollListener;
	private Handler handler;
	private View view;
	public interface OnScrollListener {
		void onBottom();
		void onAutoScroll(int l, int t, int oldl, int oldt);
	}
	public WaterFallTestScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		onScrollListener.onAutoScroll(l, t, oldl, oldt);
	}
	/**
	 * 获得参考的View，主要是为了获得它的MeasuredHeight，然后和滚动条的ScrollY+getHeight作比较。
	 */
	public void getView() {
		this.view = getChildAt(0);
		if (view != null) {
			this.setOnTouchListener((v, event) -> {
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					if (view != null && onScrollListener != null) {
						handler.sendMessageDelayed(handler.obtainMessage(1), 200);
					}
					break;
				}
				return false;
			});
			handler = new Handler() {
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					switch (msg.what) {
					case 1:
						if (view.getMeasuredHeight() - 20 <= getScrollY()+ getHeight()) {
							if (onScrollListener != null) {
								onScrollListener.onBottom();
							}
						}
						break;
					}
				}
			};
		}
	}
}
