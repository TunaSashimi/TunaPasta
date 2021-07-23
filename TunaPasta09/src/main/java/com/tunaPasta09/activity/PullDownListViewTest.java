package com.tunaPasta09.activity;

import java.util.LinkedList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tunaPasta09.R;
import com.tunaPasta09.widget.PullDownListView;
import com.tunaPasta09.widget.PullDownListView.OnRefreshListener;

public class PullDownListViewTest extends Activity {

    private LinkedList<String> data;
    private int count = 1;

    private BaseAdapter adapter = new BaseAdapter() {
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pulldownlistviewtestitem, null);
            TextView textView = convertView.findViewById(R.id.textView_item);
            textView.setText(data.get(position));
            return convertView;
        }

        public long getItemId(int position) {
            return position;
        }

        public Object getItem(int position) {
            return data.get(position);
        }

        public int getCount() {
            return data.size();
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pulldownlistviewtest);

        data = new LinkedList();
        for (int i = 0; i < 10; i++) {
            data.add(String.valueOf(i));
        }

        final PullDownListView pullDownRefreshListView = findViewById(R.id.listView);

        pullDownRefreshListView.setAdapter(adapter);
        pullDownRefreshListView.setOnRefreshListener(new OnRefreshListener() {

            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        data.addFirst("添加" + count);
                        count++;
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        adapter.notifyDataSetChanged();
                        pullDownRefreshListView.onRefreshComplete();
                    }

                }.execute();
            }
        });
    }
}