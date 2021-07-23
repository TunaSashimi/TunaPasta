package com.tunaPasta14.framgent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tunaPasta14.R;

import androidx.fragment.app.Fragment;


public class AirOrder_all extends Fragment {

	private EditText editText1;
	private EditText editText2;
	
	private Button but1;
	private Button but2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view		= inflater.inflate(R.layout.air_order_all, container, false);
		editText1		=(EditText) view.findViewById(R.id.editText1);
		editText2		=(EditText) view.findViewById(R.id.editText2);
		but1			=(Button) view.findViewById(R.id.button1);
		but2			=(Button) view.findViewById(R.id.button2);
		editText1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(count==0){
					but1.setEnabled(false);
				}else{
					but1.setEnabled(true);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				if(count>10)
					return;
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		editText2.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(count==0){
					but2.setEnabled(false);
				}else{
					but2.setEnabled(true);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		
		return view;
	}
	
	
}
