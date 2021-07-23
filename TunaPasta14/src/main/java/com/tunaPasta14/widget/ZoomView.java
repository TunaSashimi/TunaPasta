package com.tunaPasta14.widget;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public abstract class ZoomView<V extends View> {

	protected V view;

	// -----------------------------------------------
	private static final int NONE = 0;
	private static final int DRAG = 1;
	private static final int ZOOM = 2;

	private int mode = NONE;

	float oldDist;

	public ZoomView(V view){
		this.view = view;
		setTouchListener();
	}

	private void setTouchListener(){
		view.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event){
				switch (event.getAction() & MotionEvent.ACTION_MASK) {
					case MotionEvent.ACTION_DOWN:
						mode = DRAG;
						break;
					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_POINTER_UP:
						mode = NONE;
						break;
					case MotionEvent.ACTION_POINTER_DOWN:
						oldDist = spacing(event);
						if (oldDist > 10f) {
							mode = ZOOM;
						}
						break;
					case MotionEvent.ACTION_MOVE:
						if (mode == ZOOM) {
							float newDist = spacing(event);

							if (newDist > oldDist) {
								zoomOut();
							}
							if (newDist < oldDist) {
								zoomIn();
							}

						}
						break;
				}
				return true;
			}

			/**
			 * 
			 * @param event
			 * @return
			 */
			private float spacing(MotionEvent event){
				float x = event.getX(0) - event.getX(1);
				float y = event.getY(0) - event.getY(1);
				return (float)Math.sqrt(x * x + y * y);
			}
		});
	}

	protected abstract void zoomIn();

	protected abstract void zoomOut();
}
