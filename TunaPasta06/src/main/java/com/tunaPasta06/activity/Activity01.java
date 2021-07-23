package com.tunaPasta06.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.tunaPasta06.R;

public class Activity01 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity01);
		
		//跳转到第二个Activity
		Button btn =  findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				//要跳转的Activity
				Intent intent = new Intent(Activity01.this, Activity02.class);
				// 把Activity转换成一个Window，然后转换成View
				Window w = ActivityGroup01.activitygroup.getLocalActivityManager().startActivity("Activity02",intent);
			    View view = w.getDecorView();
			    //设置要跳转的Activity显示为本ActivityGroup的内容
			    ActivityGroup01.activitygroup.setContentView(view);			 
			}
		});
	}
}