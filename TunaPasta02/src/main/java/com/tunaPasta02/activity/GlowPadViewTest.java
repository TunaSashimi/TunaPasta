package com.tunaPasta02.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tunaPasta02.R;
import com.tunaPasta02.widget.GlowPadView;
import com.tunaPasta02.widget.GlowPadView.OnTriggerListener;

public class GlowPadViewTest extends Activity implements OnTriggerListener {

	private GlowPadView mGlowPadView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.glowpadviewtest);

		mGlowPadView =  findViewById(R.id.glow_pad_view);

		mGlowPadView.setOnTriggerListener(this);
		
		// uncomment this to make sure the glowpad doesn't vibrate on touch
		// mGlowPadView.setVibrateEnabled(false);
		
		// uncomment this to hide targets
		mGlowPadView.setShowTargetsOnIdle(true);
	}

	@Override
	public void onGrabbed(View v, int handle) {

	}

	@Override
	public void onReleased(View v, int handle) {
		mGlowPadView.ping();

	}

	@Override
	public void onTrigger(View v, int target) {
		final int resId = mGlowPadView.getResourceIdForTarget(target);
		switch (resId) {
		case R.drawable.ic_item_camera:
			Toast.makeText(this, "Camera selected", Toast.LENGTH_SHORT).show();
			break;

		case R.drawable.ic_item_google:
			Toast.makeText(this, "Google selected", Toast.LENGTH_SHORT).show();

			break;
		default:
			// Code should never reach here.
		}

	}

	@Override
	public void onGrabbedStateChange(View v, int handle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinishFinalAnimation() {
		// TODO Auto-generated method stub

	}

}
