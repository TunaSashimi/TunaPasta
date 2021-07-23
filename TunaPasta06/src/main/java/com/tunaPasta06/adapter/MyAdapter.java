package com.tunaPasta06.adapter;

import android.view.View;
import android.view.ViewGroup;

public interface MyAdapter {
	public int getCount();

	public View getView(int paramInt, View paramView,
			ViewGroup paramViewGroup);
}