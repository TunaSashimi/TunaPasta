package com.tunaPasta03.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tunaPasta03.R;

//类名不要起的跟AnimationListener一致,不然会混淆!
public class AnimationListenerTest extends Activity {
	private ImageView image, imageviewadd;
	private Button btn_add, btn_remove, btn_remove2;
	private LinearLayout linearlayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animationlistenertest);
		btn_add =  findViewById(R.id.animlistener_addbutton);
		btn_remove =  findViewById(R.id.animlistener_removebutton);
		btn_remove2 =  findViewById(R.id.animlistener_removebutton2);
		image =  findViewById(R.id.animalistener_im02);
		linearlayout =  findViewById(R.id.animlistener_li);
		// 布局问题的解决,只有暂时使用setVisibility(View.GONE);
		btn_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim = new AlphaAnimation(0.0f, 1.0f);
				anim.setDuration(2000);
				anim.setStartOffset(500);
				imageviewadd = new ImageView(AnimationListenerTest.this);
				imageviewadd.setImageResource(R.drawable.icon);
				linearlayout.addView(imageviewadd, new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				imageviewadd.startAnimation(anim);
			}
		});

		// 套一个匿名类,匿名类里面再套匿名类~
		btn_remove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 创建一个淡出效果的Animation对象
				Animation anima = new AlphaAnimation(1.0f, 0.0f);
				anima.setDuration(2000);
				anima.setStartOffset(500);
				// anima.setRepeatCount(1);
				// 为Animation对象设置监听器
				anima.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {
						System.out.println("start");
					}

					@Override
					// 该方法在淡出效果执行之后被调用
					public void onAnimationEnd(Animation animation) {
						System.out.println("end");
						// 从viewgroup中删除掉imageview控件会报错~
						image.setVisibility(View.GONE);
						btn_remove.setEnabled(false);
						btn_remove2.setEnabled(false);
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						System.out.println("repeat");
					}
				});
				image.startAnimation(anima);
			}
		});
		btn_remove2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim2 = new TranslateAnimation(0, 400, 0, 0);
				anim2.setDuration(2000);
				anim2.setStartOffset(500);
				anim2.setInterpolator(new BounceInterpolator());
				anim2.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {
						System.out.println("start");
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						System.out.println("end");
						// 从viewgroup中删除掉imageview控件会报错~
						// viewgroup.removeViewInLayout(image);
						image.setVisibility(View.GONE);
						btn_remove.setEnabled(false);
						btn_remove2.setEnabled(false);
					}
					@Override
					public void onAnimationRepeat(Animation animation) {
						System.out.println("repeat");
					}
				});
				image.startAnimation(anim2);
			}
		});
	}
}
