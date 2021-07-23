package com.tunaPasta09.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta09.R;

public class DrawTextcenterTest extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mytextcentertest);
		
		
		//注:需要android.permission.INTERNET权限
		//<uses-permission android:name="android.permission.INTERNET" />
		
		 //ViewServer.get(this).addWindow(this);
		
	}

	@Override
	public void onResume() {
		super.onResume();

		 //ViewServer.get(this).setFocusedWindow(this);

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		 //ViewServer.get(this).removeWindow(this);
		 
	}
	
}
