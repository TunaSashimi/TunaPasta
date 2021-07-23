package com.tunaPasta19.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tunaPasta19.R;


public class TunaBetaTestArrayAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;

    private String[] tunaBetaTestArray;
    private SparseArray<View> viewMap = new SparseArray<View>();

    public TunaBetaTestArrayAdapter(Context context, String[] tunaBetaTestArray) {
        layoutInflater = LayoutInflater.from(context);
        this.tunaBetaTestArray = tunaBetaTestArray;
    }

    @Override
    public int getCount() {
        return tunaBetaTestArray.length;
    }

    @Override
    public Object getItem(int position) {
        return tunaBetaTestArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (viewMap.get(position) == null) {
            final View view = layoutInflater.inflate(R.layout.tunabetatestitem, null);
            viewMap.put(position, view);
        }
        return viewMap.get(position);
    }
}

