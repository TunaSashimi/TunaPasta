package com.tunaPasta05.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.tunaPasta05.R;

public class ViewFlipperTest extends Activity {
	private ViewFlipper viewfliper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflippertest);
		viewfliper =  findViewById(R.id.fliper);

		viewfliper.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
		viewfliper.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
		
		viewfliper.startFlipping();
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		menu.add(0, 0, 0, "切换");
		menu.add(0, 1, 1, "淡出");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			viewfliper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_up_in));
			viewfliper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_up_out));
			break;
		case 1:
			viewfliper.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
			viewfliper.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}