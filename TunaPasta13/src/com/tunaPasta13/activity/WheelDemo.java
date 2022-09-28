package com.tunaPasta13.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta13.R;

public class WheelDemo extends Activity {

	private String[] s = { "TimeActivity", "PasswordActivity", "CitiesActivity",
			"EmptyActivity", };

	private Class<?>[] c = { TimeActivity.class, PasswordActivity.class,
			CitiesActivity.class, EmptyLayout.class, };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.entryact);

		ListView lv =  findViewById(R.id.listView);
		List<String> list = new ArrayList();

		for (int i = 0; i < s.length; i++) {
			list.add(s[i]);
		}

		lv.setAdapter(new ArrayAdapter(this, R.layout.entryactitem,
				list));
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				startActivity(new Intent(WheelDemo.this, c[arg2]));
			}
		});

	}
}