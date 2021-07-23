package com.tunaPasta03.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tunaPasta03.R;


public class DressUpGameResult extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dressupgameresult);
		Button bt1=findViewById(R.id.messzbu1);
		Button bt2=findViewById(R.id.messzbu2);
		bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(DressUpGameResult.this,DressUpGameTest.class));
				DressUpGameResult.this.finish();
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DressUpGameResult.this.finish();
			}
		});
	}
}
