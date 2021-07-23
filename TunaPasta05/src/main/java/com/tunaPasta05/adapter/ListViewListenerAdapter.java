package com.tunaPasta05.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta05.R;


public class ListViewListenerAdapter extends BaseAdapter {
	private Context context;
	private int[] i;

	public ListViewListenerAdapter(Context context, int[] i, ListView listView) {
		this.context = context;
		this.i = i;
		listView.setOnScrollListener(onScrollListener);
	}

	@Override
	public int getCount() {
		return i.length;
	}

	@Override
	public Object getItem(int arg0) {
		return i[arg0];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.listlisteneractitem, null);
		}
		TextView text = (TextView) convertView.findViewById(R.id.textview);
		text.setText(i[position] + "");
		return convertView;
	}

	// 在onScrollStateChanged(AbsListView view, int scrollState)
	// 中，scrollState有三种状态，分别是开始滚动（SCROLL_STATE_FLING），正在滚动(SCROLL_STATE_TOUCH_SCROLL),
	// 已经停止（SCROLL_STATE_IDLE），对于滚动事件的处理，很有必要知道。

	AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			switch (scrollState) {
			case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
				Toast.makeText(context, "开始滚动", Toast.LENGTH_SHORT).show();
				System.out.println("开始滚动");
				break;
			case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
				Toast.makeText(context, "已经停止", Toast.LENGTH_SHORT).show();
				System.out.println("已经停止");
				break;
			case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				Toast.makeText(context, "正在滚动", Toast.LENGTH_SHORT).show();
				System.out.println("正在滚动");
				break;
			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
		}
	};
}
