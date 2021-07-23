package com.tunaPasta09.widget;

import android.view.View;
import android.view.animation.TranslateAnimation;

public class MoveBg {
	public static void moveFrontBg(View view, int startX, int toX, int startY, int toY) {
		TranslateAnimation anim = new TranslateAnimation(startX, toX, startY, toY);
		anim.setDuration(200);
		anim.setFillAfter(true);
		view.startAnimation(anim);
	}
}
