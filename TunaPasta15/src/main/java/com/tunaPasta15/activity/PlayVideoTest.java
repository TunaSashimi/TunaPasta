package com.tunaPasta15.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class PlayVideoTest extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/Test_Movie.m4v");   
		//调用系统自带的播放器  
		    Intent intent = new Intent(Intent.ACTION_VIEW);  
		    Log.v("URI:::::::::", uri.toString());  
		    intent.setDataAndType(uri, "video/mp4");  
		    startActivity(intent);  
	}

}
