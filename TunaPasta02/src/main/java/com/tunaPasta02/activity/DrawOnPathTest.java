package com.tunaPasta02.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.tunaPasta02.R;
import com.tunaPasta02.widget.DrawOnPathView;

public class DrawOnPathTest extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 两种方法均可行

		// final DrawOnPathView drawonpath=new DrawOnPathView(this);
		// setContentView(drawonpath);
		// drawonpath.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// drawonpath.setVisibility(View.INVISIBLE);
		// }
		// });

		setContentView(R.layout.drawonpathtest);

		final FrameLayout frameLayout =  findViewById(R.id.frameLayout);

		// 注:xml布局中的DrawOnPathView只能设置invisible不能设置为gone,否则frameLayout没有点击事件
		final DrawOnPathView drawonpath =  findViewById(R.id.drawonpathview);

		frameLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 注:xml布局中的DrawOnPathView只能设置invisible不能设置为gone,否则frameLayout没有点击事件
				if (View.VISIBLE == drawonpath.getVisibility()) {
					drawonpath.setVisibility(View.INVISIBLE);
				} else {
					drawonpath.setVisibility(View.VISIBLE);
				}
			}
		});
	}
}
