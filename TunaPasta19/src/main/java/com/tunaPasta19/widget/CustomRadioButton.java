package com.tunaPasta19.widget;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioButton;
public class CustomRadioButton extends RadioButton{
	public CustomRadioButton(Context context,int resid,String string) {
		super(context);
		this.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		this.setButtonDrawable(resid);
		this.setTextColor(Color.BLACK);
		this.setText(string);
	}
}
