package com.tunaPasta11.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.tunaPasta11.R;
import com.tunaPasta11.widget.DumpClass;

import java.util.ArrayList;

public class SwipeListViewAdapter extends BaseAdapter{
	private Activity activity;
	private ArrayList<DumpClass> dataList;

	public SwipeListViewAdapter(Activity activity, ArrayList<DumpClass> dataList){
		this.activity = activity;
		this.dataList = dataList;
	}

	@Override
	public int getCount(){
		return dataList.size();
	}

	@Override
	public Object getItem(int position){
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position){
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(activity).inflate(R.layout.swipelistviewtestitem, null);

			viewHolder.image =  convertView.findViewById(R.id.image_photo);
			viewHolder.text =  convertView.findViewById(R.id.text_info);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.text.setText(dataList.get(position).title);

		return convertView;
	}

	public static class ViewHolder{
		TextView text;
		ImageView image;
	}
}
