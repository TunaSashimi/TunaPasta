package com.tunaPasta06.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ListView;

import com.tunaPasta06.R;
import com.tunaPasta06.widget.Offect3DAnimation;

public class Offect3DAnimationTest extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offect3danimationtest);
		// 获取ListView组件
		ListView list =  findViewById(R.id.list);
		WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		// 获取屏幕的宽和高
		int screenWidth = display.getWidth();
		int screenHeight = display.getHeight();
		// 设置对ListView组件应用动画
		list.setAnimation(new Offect3DAnimation(screenWidth / 2, screenHeight / 2,3500));
	}
}