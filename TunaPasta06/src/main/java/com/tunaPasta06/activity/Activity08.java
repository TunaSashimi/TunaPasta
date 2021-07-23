package com.tunaPasta06.activity;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.tunaPasta06.R;

public class Activity08 extends Activity {

	private Button m_Button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity08);

		m_Button =  findViewById(R.id.backBut);

		m_Button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewFlipper container =  ( getParent()).getWindow().findViewById(R.id.fliper);

				container.removeView(container.getCurrentView());

				Intent intent = new Intent(Activity08.this,Activity07.class);

				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// 这句代码应该是移除所有的Activity
				((ActivityGroup) Activity08.this.getParent()).getLocalActivityManager().removeAllActivities();
				// 注意：这个唯一的字符串ID一定要和跳转前那个字符串ID一样 不然在ActivityManager里无法找到Activity
				Window subActivity = ((ActivityGroup) Activity08.this.getParent()).getLocalActivityManager().startActivity("locallist", intent);

				container.addView(subActivity.getDecorView(), 1);

				container.setDisplayedChild(1);
			}
		});
	}
}
