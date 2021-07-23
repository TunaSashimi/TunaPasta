package com.tunaPasta02.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.tunaPasta02.R;
import com.tunaPasta02.entity.TalkEntity;

public class TalkAdapter implements ListAdapter{
	private ArrayList<TalkEntity> talkEntityArrary;
	private Context context;
	
	public TalkAdapter(Context context , ArrayList<TalkEntity> detailEntityArrary) {
		this.context = context;
		this.talkEntityArrary = detailEntityArrary;
	}
	
	public boolean areAllItemsEnabled() {
		return false;
	}
	public boolean isEnabled(int arg0) {
		return false;
	}
	public int getCount() {
		return talkEntityArrary.size();
	}
	public Object getItem(int position) {
		return talkEntityArrary.get(position);
	}
	public long getItemId(int position) {
		return position;
	}
	public int getItemViewType(int position) {
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		TalkEntity detailEntitu = talkEntityArrary.get(position);
		
		LayoutInflater inflate = ((Activity) context).getLayoutInflater();
		View view = (View) inflate.inflate(detailEntitu.getLayoutID(),null);
		
		TextView tvName = (TextView) view.findViewById(R.id.messagedetail_row_name);
		tvName.setText(detailEntitu.getName());
		
		TextView tvDate = (TextView) view.findViewById(R.id.messagedetail_row_date);
		tvDate.setText(detailEntitu.getDate());
		
		TextView tvText = (TextView) view.findViewById(R.id.messagedetail_row_text);
		tvText.setText(detailEntitu.getText());
		return view;
	}
	public int getViewTypeCount() {
		return talkEntityArrary.size();
	}
	public boolean hasStableIds() {
		return false;
	}
	public boolean isEmpty() {
		return false;
	}
	public void registerDataSetObserver(DataSetObserver observer) {
	}
	public void unregisterDataSetObserver(DataSetObserver observer) {
	}
    
}
