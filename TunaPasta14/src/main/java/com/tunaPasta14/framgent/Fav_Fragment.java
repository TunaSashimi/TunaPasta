package com.tunaPasta14.framgent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tunaPasta14.R;

import androidx.fragment.app.Fragment;


public class Fav_Fragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fav, container, false);
		TextView textView =  view.findViewById(R.id.title_text);
		textView.setText("我的收藏");
		return view;
	}

}
