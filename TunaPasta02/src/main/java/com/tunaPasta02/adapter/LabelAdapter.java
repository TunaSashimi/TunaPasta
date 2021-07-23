package com.tunaPasta02.adapter;

import android.widget.BaseAdapter;

public abstract class LabelAdapter extends BaseAdapter {
	private double[] mValues;

	public void setValues(double[] points) {
		mValues = points;
	}

	@Override
	public int getCount() {
		return mValues.length;
	}

	public Double getItem(int position) {
		return mValues[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}