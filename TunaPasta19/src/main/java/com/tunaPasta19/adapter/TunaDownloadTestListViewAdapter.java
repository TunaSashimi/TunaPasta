package com.tunaPasta19.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tunaPasta19.R;
import com.tunaPasta19.application.DataTrans;
import com.tunaPasta19.tuna.TunaDownload;

public class TunaDownloadTestListViewAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	private String[] tunaDownloadBitmapSrcURLArray;
	private SparseArray<View> tunaDownloadMap = new SparseArray<>();

	public TunaDownloadTestListViewAdapter(Context context,String[] tunaDownloadBitmapSrcURLArray) {
		layoutInflater = LayoutInflater.from(context);
		this.tunaDownloadBitmapSrcURLArray = tunaDownloadBitmapSrcURLArray;
	}

	@Override
	public int getCount() {
		return tunaDownloadBitmapSrcURLArray.length;
	}

	@Override
	public Object getItem(int position) {
		return tunaDownloadBitmapSrcURLArray[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (tunaDownloadMap.get(position) == null) {
			final TunaDownload tunaDownload = (TunaDownload) layoutInflater.inflate(R.layout.tunadownloadtestitem, null);
            tunaDownload.init(DataTrans.tunaGraphicsMap, tunaDownloadBitmapSrcURLArray[position], true, "日期" , "NEW", "2014-11-04");
			tunaDownload.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					tunaDownload.setTunaDownloadMark(false);
				}
			});
			tunaDownloadMap.put(position, tunaDownload);
		}
		return tunaDownloadMap.get(position);
	}
}
