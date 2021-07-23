package com.tunaPasta15.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tunaPasta15.R;

public class LinkMoveListViewTest extends Activity implements OnItemClickListener, OnScrollListener {
    ListView list1, list2;
    ListView touchPart;
    List<String> list = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linkmovelistviewtest);
        list1 = findViewById(R.id.list1);
        list2 = findViewById(R.id.list2);
        for (int i = 0; i < 20; i++) {
            list.add("list==>" + i);
        }
        MyAdapter adapter1 = new MyAdapter();
        MyAdapter adapter2 = new MyAdapter();
        list1.setAdapter(adapter1);
        list2.setAdapter(adapter2);

        list1.setOnScrollListener(this);
        list2.setOnScrollListener(this);

        list1.setOnItemClickListener(this);
        list2.setOnItemClickListener(this);

    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.linkmovelistviewtestitem, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.item);
            tv.setText(list.get(position));
            return convertView;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == list1) {
            list1.requestFocus();
        }
        if (parent == list2) {
            list2.requestFocus();
        }

    }

    public void startTouch(LinkMoveListView paramCustomListView) {
        this.touchPart = paramCustomListView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view != touchPart) {
            return;
        }
        int j = 0;
        int i = touchPart.getFirstVisiblePosition();
        if (touchPart.getChildAt(0) != null) {
            j = touchPart.getChildAt(0).getTop();
        }
        if (view == list1) {
            list2.setSelectionFromTop(i, j);
        }
        if (view == list2) {
            list1.setSelectionFromTop(i, j);
        }
    }

}