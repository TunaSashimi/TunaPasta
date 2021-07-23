package com.tunaPasta04.tool;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;

public class NativeTool {

    //获取屏幕高度
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        return display.getHeight();
    }

    //异步操作保存图片
    public static void savePicture(final Bitmap bitmap, final String path) {
        new Thread() {
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
