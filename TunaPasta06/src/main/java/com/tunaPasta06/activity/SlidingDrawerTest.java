package com.tunaPasta06.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.tunaPasta06.R;
import com.tunaPasta06.widget.C_SlidingDrawer;

public class SlidingDrawerTest extends Activity {
	private C_SlidingDrawer m_SlidingDrawer;
	private ImageView m_ImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.slidingdrawertest);

		m_SlidingDrawer =  findViewById(R.id.lyrics_sliding_drawer);

		m_ImageView =  findViewById(R.id.lyrics_handle_image);

		// 监听打开抽屉事件
		m_SlidingDrawer
				.setOnDrawerOpenListener(new C_SlidingDrawer.OnDrawerOpenListener() {
					public void onDrawerOpened() {
						m_ImageView.setImageResource(R.drawable.lyrics_handle_expand);
					}
				});
		// 监听关闭抽屉事件
		m_SlidingDrawer
				.setOnDrawerCloseListener(new C_SlidingDrawer.OnDrawerCloseListener() {
					public void onDrawerClosed() {
						m_ImageView.setImageResource(R.drawable.lyrics_handle_shrink);
					}
				});
	}
}
