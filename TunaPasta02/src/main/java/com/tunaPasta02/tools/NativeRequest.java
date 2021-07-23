package com.tunaPasta02.tools;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class NativeRequest {

	//获取屏幕密度
	public static float getScreenDensity(Context context) {
		DisplayMetrics dm = new DisplayMetrics();   
		dm = context.getResources().getDisplayMetrics();   
		return dm.density;
	}
	
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
