package com.tunaPasta04.activity;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.tunaPasta04.R;
import com.tunaPasta04.application.PhotoTrans;
public class GetPicture extends Activity {
	public static final int IMAGE_FROM_CAMERA = 0xff1;
	public static final int IMAGE_FROM_ALBUM = 0xff2;
	File CompletePathFile;
	float density;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getpicture);
		 getParameters();
	}
	/**
	 * 获取系统的屏幕像素密度~
	 */
	private void getParameters(){
		WindowManager windowManager = getWindowManager();
		DisplayMetrics metric = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metric);
		density=metric.density;
	}
	/**
	 * 点击事件~
	 */
	public void onTypeClick(View target) {
		switch (target.getId()) {
		case R.id.button_camera:doTakePhotoAction();break;
		case R.id.button_album:doFromAlbum();break;
		default:break;
		}
	}
	/**
	 * 拍照获取~
	 */
	private void doTakePhotoAction() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File photoPath = new File(Environment.getExternalStorageDirectory() +"/DCIM/JS08");	// 图片的存储目录基本为 /mnt/sdcard/JS08
			photoPath.mkdirs();																											// 需要创建~
			 CompletePathFile = new File(photoPath, getFileName()); 													// 用当前时间给取得的图片命名
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(CompletePathFile));
			startActivityForResult(intent, IMAGE_FROM_CAMERA );
		} else {
			Toast.makeText(this, "没有sd卡", Toast.LENGTH_LONG).show();
		}
	}
	/**
	 * 用日期和时间命名~
	 */
		private String getFileName() {
			Date date = new Date(System.currentTimeMillis());
			return new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss").format(date) + ".jpg";
		}
	/**
	 * 相册选择~
	 */
	private void doFromAlbum() {																	
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intentDeal(intent),IMAGE_FROM_ALBUM);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK)
			return;
		switch (requestCode) {
		case IMAGE_FROM_CAMERA: //由照相程序返回,则调用裁剪程序,设置requestCode为下者
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(Uri.fromFile(CompletePathFile), "image/*");
			startActivityForResult(intentDeal(intent),IMAGE_FROM_ALBUM );
			break;
		case IMAGE_FROM_ALBUM: 	//由相册选取返回,则从data中获取数据转成bitmap放入PhotoTrans
			Bundle extras = data.getExtras();
			((PhotoTrans)getApplication()).setBitmap((Bitmap)extras.get("data"));
		    startActivity(new Intent(this, GetResult.class));
			break;
		}
	}
	protected Intent intentDeal(Intent intent) {
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 3);
		intent.putExtra("aspectY", 4);
		intent.putExtra("outputX",(int)(180*density));	
		intent.putExtra("outputY",(int)(240*density));
		intent.putExtra("return-data", true);				
		return intent;
	}
}
