package com.tunaPasta05.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.tunaPasta05.R;


public class ShakeButton extends Button {
	public ShakeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	//The writing of the parent class already contains the effect of jitter, but is not overwritten!
	public void setOnClickListener(final OnClickListener finalOnClicklistener) {
		super.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
				finalOnClicklistener.onClick(view);
			}
		});
	}

}
