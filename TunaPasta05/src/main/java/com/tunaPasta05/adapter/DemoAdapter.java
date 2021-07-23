package com.tunaPasta05.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tunaPasta05.R;


public class DemoAdapter extends BaseAdapter {
    private String[] sData;
    private boolean[] bData;
    private LayoutInflater mInflater;

    public DemoAdapter(Context context, String[] sData) {
        mInflater = LayoutInflater.from(context);
        this.sData = sData;

        bData = new boolean[sData.length];
        for (int i = 0; i < bData.length; i++) {
            bData[i] = false;
        }

    }

    @Override
    public int getCount() {
        return sData.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.checkboxlisttestitem, null);
        }

        TextView textview = (TextView) convertView.findViewById(R.id.text);
        textview.setText(sData[position]);

        CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.check_box);
        checkbox.setChecked(bData[position]);

//		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				bData[position]=isChecked;
//			}
//		});
        checkbox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bData[position] = !bData[position];
            }
        });


        return convertView;
    }
}
