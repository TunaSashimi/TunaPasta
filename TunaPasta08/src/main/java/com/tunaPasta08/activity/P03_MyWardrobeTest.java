package com.tunaPasta08.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.ant.liao.GifView;
import com.ant.liao.GifView.GifImageType;
import com.tunaPasta08.R;

public class P03_MyWardrobeTest extends Activity{
	private GifView gf1, gf2;
	private boolean f = true;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.p03_mywardrobetest);
		
		gf1 = findViewById(R.id.gif1);// 设置Gif图片源

		gf1.setGifImage(R.drawable.gif_test1);
		
		gf2 = findViewById(R.id.gif2);
		// 设置加载方式：先加载后显示、边加载边显示、只显示第一帧再显示   
		gf2.setGifImageType(GifImageType.COVER);
		// 设置显示的大小，拉伸或者压缩   
		gf2.setShowDimension(300, 300);
		gf2.setGifImage(R.drawable.gif_test2);
	}
	//说明在xml中配置的android:onClick事件,虽然eclipse点不出快捷,但是还是有效的~
	public void onViewClick(View v) {
		if(f){
			gf1.showCover();
			f = false;
		}else{
			gf1.showAnimation();
			f = true;
		}
	}
}
