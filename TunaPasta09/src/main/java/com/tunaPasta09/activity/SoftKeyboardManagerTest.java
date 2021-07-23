package com.tunaPasta09.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tunaPasta09.R;
import com.tunaPasta09.widget.SoftKeyboardManager;

public class SoftKeyboardManagerTest extends Activity{
@Override
protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.softkeyboardmanagertest);
	
	RelativeLayout relativeLayout= findViewById(R.id.relativeLayout);
	
	SoftKeyboardManager softKeyboardManager=new SoftKeyboardManager(this, relativeLayout) ;
	
	softKeyboardManager.addAfterKeyboardShownRunnable(new Runnable(){
		@Override
		public void run(){
			Toast.makeText(SoftKeyboardManagerTest.this, "Show", Toast.LENGTH_SHORT).show();;
		}
	});
	
	softKeyboardManager.addAfterKeyboardHiddenRunnable(new Runnable(){
		@Override
		public void run(){
			Toast.makeText(SoftKeyboardManagerTest.this, "Hide", Toast.LENGTH_SHORT).show();;
		}
	});
	
	
}
}
