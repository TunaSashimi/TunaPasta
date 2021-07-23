package com.tunaPasta14.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tunaPasta14.R;

public class HotelActivity extends Activity{
	
	private TextView title;
	private LinearLayout layout;
	private RelativeLayout search;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotel);
		title			=(TextView) findViewById(R.id.title_text);
		title.setText("全部酒店");
		search			=(RelativeLayout) findViewById(R.id.hote_search);
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent			=new Intent();
				intent.setClass(getApplicationContext(), HotelSearch.class);
				startActivity(intent);
			}
		});
		
		layout			=(LinearLayout) findViewById(R.id.title_back);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

}
