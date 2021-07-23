package com.tunaPasta04.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.tunaPasta04.R;
import com.tunaPasta04.widget.FaceDetectView;

public class PhotoTest extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		BitmapFactory.Options BitmapFactoryOptionsbfo = new BitmapFactory.Options();
		BitmapFactoryOptionsbfo.inPreferredConfig = Bitmap.Config.RGB_565;// 输入图片必须为bitmapRBG565格式
		Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.double1, BitmapFactoryOptionsbfo);

		FaceDetectView facedecte = new FaceDetectView(this);
		facedecte.set(myBitmap);
		facedecte.invalidate();
		
		setContentView(facedecte);
	}
}