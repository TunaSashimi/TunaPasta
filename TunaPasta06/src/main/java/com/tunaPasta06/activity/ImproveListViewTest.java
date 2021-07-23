package com.tunaPasta06.activity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.tunaPasta06.R;
import com.tunaPasta06.adapter.ImproveListViewTestAdapter;
public class ImproveListViewTest extends Activity {
	private ListView listview;
	private ImproveListViewTestAdapter improvelistviewadapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.improvelistviewtest);
		
		listview =  findViewById(R.id.main_lv_list);
		improvelistviewadapter = new ImproveListViewTestAdapter(500, this);
		listview.setAdapter(improvelistviewadapter);
		listview.setOnScrollListener(new OnScrollListener() {
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case OnScrollListener.SCROLL_STATE_FLING:
					improvelistviewadapter.setFlagBusy(true);
					break;
				case OnScrollListener.SCROLL_STATE_IDLE:
					improvelistviewadapter.setFlagBusy(false);
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
					improvelistviewadapter.setFlagBusy(false);
					break;
				}
				improvelistviewadapter.notifyDataSetChanged();
			}
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
			}
		});
		
	}
}