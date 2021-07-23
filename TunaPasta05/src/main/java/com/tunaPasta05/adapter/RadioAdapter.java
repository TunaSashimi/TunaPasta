package com.tunaPasta05.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tunaPasta05.R;

public class RadioAdapter extends BaseAdapter {
    private String[] sData;
    private int[] iData;
    private LayoutInflater mInflater;

    public RadioAdapter(Context context, String[] sData) {
        mInflater = LayoutInflater.from(context);
        this.sData = sData;
        iData = new int[sData.length];
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
            convertView = mInflater.inflate(R.layout.ratio_item, null);
        }
        TextView textview = convertView.findViewById(R.id.text);
        textview.setText(sData[position]);


        RadioGroup radiogroup = convertView.findViewById(R.id.radiogroup);
        if (iData[position] == 1) {
            radiogroup.check(R.id.radiobutton_01);
        } else if (iData[position] == 2) {
            radiogroup.check(R.id.radiobutton_02);
        } else {
            radiogroup.clearCheck();
        }

        RadioButton radiobutton1 = (RadioButton) convertView.findViewById(R.id.radiobutton_01);
        radiobutton1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (iData[position] == 0 || iData[position] == 2) {
                    iData[position] = 1;
                }
            }
        });

        RadioButton radiobutton2 = (RadioButton) convertView.findViewById(R.id.radiobutton_02);
        radiobutton2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (iData[position] == 0 || iData[position] == 1) {
                    iData[position] = 2;
                }
            }
        });
        return convertView;
    }
}
