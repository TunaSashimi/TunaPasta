package com.tunaPasta14.framgent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tunaPasta14.R;
import com.tunaPasta14.activity.AirOrder;

import androidx.fragment.app.Fragment;

public class Order_Fragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.order, container, false);
		TextView textView = (TextView) view.findViewById(R.id.title_text);
		textView.setText("我的订单");
		((RelativeLayout) view.findViewById(R.id.airorder))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(getActivity(), AirOrder.class);
						startActivity(intent);
					}
				});
		((RelativeLayout) view.findViewById(R.id.order1))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(getActivity(), AirOrder.class);
						startActivity(intent);
					}
				});
		((RelativeLayout) view.findViewById(R.id.order2))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(getActivity(), AirOrder.class);
						startActivity(intent);
					}
				});
		((RelativeLayout) view.findViewById(R.id.order3))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(getActivity(), AirOrder.class);
						startActivity(intent);
					}
				});
		((RelativeLayout) view.findViewById(R.id.order4))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(getActivity(), AirOrder.class);
						startActivity(intent);
					}
				});

		return view;
	}

}
