package com.tunaPasta19.activity;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Gallery;

import com.tunaPasta19.R;
import com.tunaPasta19.adapter.RollLayoutGalleryImageAdapter;
public class RollLayoutTest extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rolllayouttest);
		
		Gallery mygallery = (Gallery) findViewById(R.id.mygallery);
		mygallery.setAdapter(new RollLayoutGalleryImageAdapter(this));
		mygallery.setSelection(Integer.MAX_VALUE / 2);
	}
	public void onViewClick(View target) {
		switch (target.getId()) {
		}
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			DialogHelper.ExitPrompt(this);
		}
		return super.onKeyDown(keyCode, event);
	}
}
