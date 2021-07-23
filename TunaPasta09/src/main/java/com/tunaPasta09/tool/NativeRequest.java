package com.tunaPasta09.tool;
import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class NativeRequest {

	//获取屏幕宽度
	public static int getScreenWidth(Context context) {
		WindowManager windowManager = ((Activity) context).getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		return display.getWidth();
	}
	
	//获取屏幕高度
	public static int getScreenHeight(Context context) {
		WindowManager windowManager = ((Activity) context).getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		return display.getHeight();
	}
	
	
}
