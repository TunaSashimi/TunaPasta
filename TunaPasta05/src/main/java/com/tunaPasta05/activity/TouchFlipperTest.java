package com.tunaPasta05.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.tunaPasta05.R;

public class TouchFlipperTest extends Activity implements OnGestureListener {
	private ViewFlipper flipper;
	private GestureDetector detector;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touchflippertest);
        
        detector = new GestureDetector(this);
		flipper = findViewById(R.id.ViewFlipper1);
		
		flipper.addView(addMyView(R.drawable.inn1));
		flipper.addView(addMyView(R.drawable.inn2));
		flipper.addView(addMyView(R.drawable.inn3));
		flipper.addView(addMyView(R.drawable.inn4));
		flipper.addView(addMyView(R.drawable.inn5));
		
    }
    private View addMyView(int id) {
		ImageView iv = new ImageView(this);
		iv.setImageResource(id);
		return iv;
	}
    public boolean onTouchEvent(MotionEvent event) {
    	return detector.onTouchEvent(event);
    }
	public boolean onDown(MotionEvent e) {
		return false;
	}
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > 120) {
			flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left00_in));
			flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left00_out));
			flipper.showNext();
		} else if (e1.getX() - e2.getX() < -120) {
			flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right00_in));
			flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right00_out));
			flipper.showPrevious();
		}
		return true;
	}
    public void onLongPress(MotionEvent e) {
    }
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
    		float distanceY) {
    	return false;
    }
    public void onShowPress(MotionEvent e) {
    }
    public boolean onSingleTapUp(MotionEvent e) {
    	return false;
    }
}