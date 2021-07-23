package com.tunaPasta13.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.tunaPasta13.R;

public class IMETest extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.imetest);

		EditText editText =  findViewById(R.id.editText);
		editText.setOnEditorActionListener(new OnEditorActionListener(){
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
				Toast.makeText(IMETest.this, String.valueOf(actionId), Toast.LENGTH_SHORT).show();
				return true;
			}
		});

	}
}
