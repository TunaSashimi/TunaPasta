package com.tunaPasta14.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class DontPressWithParentButton extends Button{
	public DontPressWithParentButton(Context context, AttributeSet attrs){
		super(context, attrs);
	}

	@Override
	public void setPressed(boolean pressed){
		if (pressed && ((View) getParent()).isPressed()) {
			return;
		}
		super.setPressed(pressed);
	}
}