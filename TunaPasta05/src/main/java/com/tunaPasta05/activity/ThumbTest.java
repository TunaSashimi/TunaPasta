package com.tunaPasta05.activity;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore.Video.Thumbnails;
import android.widget.ImageView;

import com.tunaPasta05.R;

public class ThumbTest extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thumbtest);
		
		copyAssetsToData();
		
		Bitmap bitmap=ThumbnailUtils.createVideoThumbnail(getExternalCacheDir()+"/advance01.3gp", Thumbnails.MINI_KIND ); 
		ImageView image= findViewById(R.id.image01);
		
//		System.out.println(getExternalCacheDir());		/mnt/sdcard/Android/data/com.nemesisJS05.activity/cache
//		System.out.println(Environment.getExternalStorageDirectory());	 /mnt/sdcard
		
		image.setImageBitmap(bitmap);
		
	}
	
	
	//将assets中的文件拷贝到sd卡中,注意如果有文件夹,要先创建文件夹不然报错: java.io.FileNotFoundException: /mnt/sdcard/word.docx (No such file or directory)
	private void copyAssetsToData(){
		AssetManager assetmanager=this.getAssets();
		 InputStream in = null;
	     OutputStream out = null;
        try {
        	in=assetmanager.open("advance01.3gp");
			out = new FileOutputStream(getExternalCacheDir()+ "/advance01.3gp");
			
			System.out.println(in);
			System.out.println(out);
			
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
