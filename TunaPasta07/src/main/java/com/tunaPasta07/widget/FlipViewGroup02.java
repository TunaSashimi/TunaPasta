package com.tunaPasta07.widget;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class FlipViewGroup02 extends ViewGroup {
	
	private static final int MSG_SURFACE_CREATED = 1;

	private LinkedList<View> flipViews = new LinkedList<View>();
	
	private Handler handler = new Handler(new Handler.Callback() {		
		@Override
		public boolean handleMessage(Message msg) {
			if (msg.what == MSG_SURFACE_CREATED) {
				width = 0;
				height = 0;
				requestLayout();
				return true;
			}
			return false;
		}
	});

	private GLSurfaceView surfaceView;
	private FlipRenderer03 renderer;

	private int width;
	private int height;

	private boolean flipping = false;

	public FlipViewGroup02(Context context) {
		super(context);
		setupSurfaceView();
	}

	private void setupSurfaceView() {
		surfaceView = new GLSurfaceView(getContext());
		
		renderer = new FlipRenderer03(this);
		
		surfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		surfaceView.setZOrderOnTop(true);
		surfaceView.setRenderer(renderer);
		surfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
		
		addView(surfaceView);
	}

	public GLSurfaceView getSurfaceView() {
		return surfaceView;
	}

	public FlipRenderer03 getRenderer() {
		return renderer;
	}

	public void addFlipView(View v) {
		flipViews.add(v);
		addView(v);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
		for (View child : flipViews)
			child.layout(0, 0, r - l, b - t);

		if (changed || width == 0) {
			int w = r - l;
			int h = b - t;
			surfaceView.layout(0, 0, w, h);
			
			if (width != w || height != h) {
				width = w;
				height = h;

				if (flipping && flipViews.size() >= 2) {
					View frontView = flipViews.get(flipViews.size() - 1);
					View backView = flipViews.get(flipViews.size() - 2);
					renderer.updateTexture(frontView, backView);
					frontView.setVisibility(View.INVISIBLE);
					backView.setVisibility(View.INVISIBLE);
				}
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		for (View child : flipViews)
			child.measure(widthMeasureSpec, heightMeasureSpec);
	}

	public void startFlipping() {
		flipping = true;
	}
	
	public void onResume() {
		surfaceView.onResume();
	}

	public void onPause() {
		surfaceView.onPause();
	}
	
	public void reloadTexture() {
		handler.sendMessage(Message.obtain(handler, MSG_SURFACE_CREATED));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return renderer.getCards().handleTouchEvent(event);
	}
}
