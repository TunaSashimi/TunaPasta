package com.tunaPasta14.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.tunaPasta14.R;
import com.tunaPasta14.widget.XListView;
import com.tunaPasta14.widget.XListView.IXListViewListener;

public class XListViewTest extends Activity {

    private XListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList();
    private Handler mHandler;
    private int start = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xlistviewtest);

        generateItems();

        mListView = findViewById(R.id.xListView);
        mListView.setPullLoadEnable(true);

        mAdapter = new ArrayAdapter(this, R.layout.xlistviewtestitem, items);
        mListView.setAdapter(mAdapter);

        //是否允许下拉刷新
        mListView.setPullRefreshEnable(true);
        //是否允许上拉加载更多
        mListView.setPullLoadEnable(true);

        mListView.setXListViewListener(new IXListViewListener() {
            // 下拉刷新
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        items.clear();
                        generateItems();
                        mAdapter.notifyDataSetChanged();
                        // 更新界面显示
                        onLoad();
                    }
                }, 2000);
            }

            // 上拉加载更多
            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        generateItems();
                        mAdapter.notifyDataSetChanged();
                        onLoad();
                    }
                }, 2000);
            }
        });
        mHandler = new Handler();
    }

    private void generateItems() {
        for (int i = 0; i != 10; ++i) {
            items.add("refresh cnt " + start++);
        }
    }

    private void onLoad() {

        // 停止刷新，重置header view
        mListView.stopRefresh();

        // 停止加载更多，重置footer view
        mListView.stopLoadMore();

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");

        // 设置上次刷新的时间
        mListView.setRefreshTime(format.format(new Date()));
    }

}