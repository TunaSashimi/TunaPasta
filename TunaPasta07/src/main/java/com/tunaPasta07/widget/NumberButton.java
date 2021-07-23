package com.tunaPasta07.widget;

import android.R;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NumberButton extends Button {
	private int number;

	public NumberButton(Context context, final int n) {
		super(context, null, R.attr.buttonStyleInset);
		setNumber(n);
		setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "Clicked button " + number,Toast.LENGTH_SHORT).show();
			}
		});
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
		setText(Integer.toString(number));
	}

	@Override
	public String toString() {
		return "NumberButton: " + number;
	}
}
