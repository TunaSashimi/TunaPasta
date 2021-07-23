package com.tunaPasta07.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tunaPasta07.adapter.FlipViewAdapter;
import com.tunaPasta07.widget.NumberButton;

public class FlipButtonTest extends Activity {
	private FlipViewAdapter flipViewadapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		flipViewadapter = new FlipViewAdapter(this);
		flipViewadapter.setAdapter(new BaseAdapter() {
			public int getCount() {
				return Integer.MAX_VALUE;
			}
			public Object getItem(int position) {
				return position;
			}
			public long getItemId(int position) {
				return position;
			}
			public View getView(int position, View convertView, ViewGroup parent) {
				NumberButton button;
				if (convertView == null) {
					button = new NumberButton(parent.getContext(), position);
					button.setTextSize(360);
				}
				else {
					button = (NumberButton) convertView;
					button.setNumber(position);
				}
				return button;
			}
		});
		
		//放下面不然报空指针
		setContentView(flipViewadapter);
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		flipViewadapter.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
	}
}
