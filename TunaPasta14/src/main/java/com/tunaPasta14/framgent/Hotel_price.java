package com.tunaPasta14.framgent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tunaPasta14.R;

import androidx.fragment.app.Fragment;

public class Hotel_price extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.hotel_filter, container,false);
	}
}
