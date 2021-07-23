package com.tunaPasta14.framgent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tunaPasta14.R;

import androidx.fragment.app.Fragment;


public class Uc_Fragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.uc, container, false);
		TextView textView =  view.findViewById(R.id.title_text);
		textView.setText("个人中心");
		return view;
	}

}
