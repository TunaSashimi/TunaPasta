package com.tunaPasta03.activity;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tunaPasta03.R;

public class DataBaseAdapter extends BaseAdapter {
	private ArrayList<ArrayList<String>> lists;
	private Context context;
	public DataBaseAdapter(Context context, ArrayList<ArrayList<String>> lists) {
		this.context = context;
		this.lists = lists;
	}
	@Override
	public int getCount() {
		return lists.size();
	}
	@Override
	public Object getItem(int arg0) {
		return arg0;
	}
	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ArrayList<String> list = lists.get(position);
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.databasetestitem, null);
		}
		TextView textView1 =  convertView.findViewById(R.id.text1);
		textView1.setTextColor(Color.BLACK);
		textView1.setText(list.get(0)+" "+list.get(1)+" "+list.get(2)+" "+list.get(3)+" "+list.get(4)+" "+list.get(5));
		return convertView;
	}
}
