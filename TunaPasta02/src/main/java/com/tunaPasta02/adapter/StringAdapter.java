package com.tunaPasta02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tunaPasta02.R;

public class StringAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private String[] stringArrary;

    public StringAdapter(String[] stringArrary, Context context) {
        this.stringArrary = stringArrary;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return stringArrary.length;
    }

    @Override
    public Object getItem(int position) {
        return stringArrary[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.mysimple, null);
        }
        TextView textview = convertView.findViewById(R.id.text01);
        textview.setText(stringArrary[position]);
        return convertView;
    }
}
