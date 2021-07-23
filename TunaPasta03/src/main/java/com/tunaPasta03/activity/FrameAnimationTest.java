package com.tunaPasta03.activity;



import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.tunaPasta03.R;

//Frame-animation 帧动画 
public class FrameAnimationTest extends Activity {
	private ImageView image;
	private Button btn01, btn02;
	private AnimationDrawable draw;
	private int[] images = { R.drawable.earth_01, R.drawable.earth_02,
			R.drawable.earth_03, R.drawable.earth_04 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frameanimationtest);
		image = (ImageView) findViewById(R.id.frameanim_img);
		btn01 = (Button) findViewById(R.id.frameanim_btn01);
		btn02 = (Button) findViewById(R.id.frameanim_btn02);
		draw = new AnimationDrawable();
		for (int i = 1; i <= 4; i++) {
			draw.addFrame(getResources().getDrawable(images[i - 1]), 200);
		}
		draw.setOneShot(false);
		image.setImageDrawable(draw);
		btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				draw.start();
			}
		});
		btn02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				draw.stop();
			}
		});
	}
}
