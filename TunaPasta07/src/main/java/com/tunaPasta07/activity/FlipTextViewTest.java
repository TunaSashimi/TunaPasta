package com.tunaPasta07.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.tunaPasta07.R;
import com.tunaPasta07.adapter.FlipViewAdapter;
import com.tunaPasta07.widget.NumberTextView;

public class FlipTextViewTest extends Activity {
    private FlipViewAdapter flipViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fliptestview);

        LinearLayout linear02 = findViewById(R.id.linear02);

        flipViewAdapter = new FlipViewAdapter(this);
        flipViewAdapter.setAdapter(new BaseAdapter() {
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            public Object getItem(int position) {
                return position;
            }

            public long getItemId(int position) {
                return position;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                NumberTextView view;
                if (convertView == null) {
                    view = new NumberTextView(parent.getContext(), position);
                    view.setTextSize(360);
                } else {
                    view = (NumberTextView) convertView;
                    view.setNumber(position);
                }
                return view;
            }
        });

        //放下面不然报空指针
        linear02.addView(flipViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        flipViewAdapter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flipViewAdapter.onPause();
    }
}
