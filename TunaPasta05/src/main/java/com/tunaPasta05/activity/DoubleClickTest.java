package com.tunaPasta05.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.tunaPasta05.R;

public class DoubleClickTest extends Activity {
	long count, firstClick, secondClick;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doubleclicktest);
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			count++;
			if (count == 1) {
				firstClick = System.currentTimeMillis();
			} else if (count == 2) {
				secondClick = System.currentTimeMillis();
				if (secondClick - firstClick < 500) {
					Toast.makeText(this, "双击事件!", Toast.LENGTH_LONG).show();
				}
				count = 0;
				firstClick = 0;
				secondClick = 0;
			}
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

}