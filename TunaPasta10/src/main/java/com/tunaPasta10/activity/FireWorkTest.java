package com.tunaPasta10.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.tunaPasta10.fireview.FireworkView;

public class FireWorkTest extends Activity {
	static final String LOG_TAG = FireWorkTest.class.getSimpleName();
	
//	static int SCREEN_W ;//// 当前窗口的大小  
//	static int SCREEN_H;

	FireworkView fireworkView;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
//		SCREEN_W=NativeRequest.getScreenWidth(this);
//		SCREEN_H=NativeRequest.getScreenHeight(this);
		
		fireworkView = new FireworkView(this);
		setContentView(fireworkView);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (fireworkView.isRunning()) {
			fireworkView.setRunning(false);
		}
	}
}