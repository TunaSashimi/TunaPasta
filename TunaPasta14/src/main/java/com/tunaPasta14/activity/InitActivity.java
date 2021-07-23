package com.tunaPasta14.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tunaPasta14.R;

public class InitActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.init);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent intent		=new Intent();
				intent.setClass(getApplicationContext(), HotelTest.class);
				startActivity(intent);
			}
		}).start();
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		this.finish();
	}
	
	
}
