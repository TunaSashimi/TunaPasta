package com.tunaPasta06.tool;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
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
	
	//将assets中的文件拷贝到sd卡中,注意如果有文件夹,要先创建文件夹不然报错: java.io.FileNotFoundException: /mnt/sdcard/word.docx (No such file or directory)
	public static void copyAssets2SDCard(Context context){
		AssetManager assetmanager=context.getAssets();
		 InputStream in = null;
	     OutputStream out = null;
        try {
        	in=assetmanager.open("AppWidgetProviderIntroduce.txt");
			out = new FileOutputStream(Environment.getExternalStorageDirectory()+ "/AppWidgetProviderIntroduce.txt");
        	//copyFile
        	  byte[] buffer = new byte[1024];
              int read;
              while((read = in.read(buffer)) != -1){
                out.write(buffer, 0, read);
              }
              in.close();
              in = null;
              out.flush();
              out.close();
              out = null;
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
}
