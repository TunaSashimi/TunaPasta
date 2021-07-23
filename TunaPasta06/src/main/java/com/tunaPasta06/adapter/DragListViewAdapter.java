package com.tunaPasta06.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tunaPasta06.R;
import com.tunaPasta06.widget.TouchInterceptorListView.TouchInterceptorAdapter;

public class DragListViewAdapter extends BaseAdapter implements TouchInterceptorAdapter{

	private String[] mData;
	private LayoutInflater mInflater;
	private int mSelectedPos = -1;
	
	public DragListViewAdapter(Context context,String[] strs){
		mInflater = LayoutInflater.from(context);
		mData = strs;
	}
	
	@Override
	public int getCount() {
		return mData.length;
	}

	@Override
	public Object getItem(int position) {
		return mData[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = mInflater.inflate(R.layout.draglistviewtestitem, null);
		}
		TextView tv = (TextView)convertView.findViewById(R.id.text);
		tv.setText(mData[position]);
		if(position==mSelectedPos)
			convertView.setVisibility(View.INVISIBLE);
		else
			convertView.setVisibility(View.VISIBLE);
		return convertView;
	}

	@Override
	public void setItem(int pos, Object value) {
		mData[pos]=value.toString();
	}

	@Override
	public void setSelectedPos(int pos) {
		mSelectedPos = pos;
	}
	
}
