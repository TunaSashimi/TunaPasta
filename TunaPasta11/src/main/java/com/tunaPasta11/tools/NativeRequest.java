package com.tunaPasta11.tools;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

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

	//获取屏幕密度
	public static float getScreenDensity(Context context) {
		DisplayMetrics dm = new DisplayMetrics();   
		dm = context.getResources().getDisplayMetrics();   
		return dm.density;
	}
	
	//	设置Test的值	Button,Edittext为 TextView 的子类
	public static void setText(TextView textview,String string,OnClickListener onClickListener){
		if (string!=null) {
			textview.setText(string);
		}else {
			textview.setText(" ");//安卓3.0和2.2的区别,不能直接设置为""
		}
		textview.setOnClickListener(onClickListener);
	}
	
	// 把信息保存至本机(用户信息,表信息)~
	public static void saveInfomation(Context context, Object object,String checkbat) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(context.getFilesDir() + "/" + checkbat));
			oos.writeObject(object);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 从本机获取信息~
	
	//异步操作保存图片
	public static void savePicture(final Bitmap bitmap, final String path) {
			new Thread(){
			public void run() {
				try {
					File picfile = new File(Environment.getExternalStorageDirectory() + path);
					picfile.createNewFile();
					FileOutputStream fileoutput = new FileOutputStream(picfile);
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileoutput);
					fileoutput.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();		
			
	}
	
}
