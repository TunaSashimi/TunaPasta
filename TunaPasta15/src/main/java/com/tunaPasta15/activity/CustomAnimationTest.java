package com.tunaPasta15.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.tunaPasta15.R;
import com.tunaPasta15.widget.CustomAnimation;

public class CustomAnimationTest extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.customanimationtest);

		Button button =  findViewById(R.id.button);
		final ImageView image =  findViewById(R.id.image);
		
		final CustomAnimation customAnimation=new CustomAnimation();
		
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				image.startAnimation(customAnimation);
			}
		});
	}
}
