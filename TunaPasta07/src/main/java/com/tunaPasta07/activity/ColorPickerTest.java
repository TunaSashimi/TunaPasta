package com.tunaPasta07.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tunaPasta07.R;
import com.tunaPasta07.widget.ColorPickerDialog;

public class ColorPickerTest extends Activity {
	Context context;
	private Button btnColorPicker;
	private TextView tvText;

	private ColorPickerDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		context = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.colorpickertest);
		initViews();
	}

	/**
	 */
	private void initViews() {
		btnColorPicker =  findViewById(R.id.btn_color_picker);
		btnColorPicker.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog = new ColorPickerDialog(context, tvText.getTextColors()
						.getDefaultColor(), "颜色选择器",
						new ColorPickerDialog.OnColorChangedListener() {
							@Override
							public void colorChanged(int color) {
								tvText.setTextColor(color);
							}
						});
				dialog.show();
			}
		});
		tvText = (TextView) findViewById(R.id.tv_text);
	}
}