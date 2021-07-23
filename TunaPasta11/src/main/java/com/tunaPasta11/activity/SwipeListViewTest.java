package com.tunaPasta11.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.tunaPasta11.R;
import com.tunaPasta11.adapter.SwipeListViewAdapter;
import com.tunaPasta11.widget.DumpClass;
import com.tunaPasta11.widget.SwipeListViewTouchListener;

public class SwipeListViewTest extends Activity {

    private ListView listView;
    private SwipeListViewAdapter swipeListViewAdapter;
    private ArrayList<DumpClass> listData;

    private SwipeListViewTouchListener swipeListViewTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.swipelistviewtest);

        listView = findViewById(R.id.listView);
        listData = new ArrayList();

        for (int i = 0; i < 10; i++) {
            listData.add(new DumpClass("item" + i));
        }

        swipeListViewAdapter = new SwipeListViewAdapter(this, listData);
        listView.setAdapter(swipeListViewAdapter);

        swipeListViewTouchListener = new SwipeListViewTouchListener(listView, new SwipeListViewTouchListener.TouchCallbacks() {
            @Override
            public void FullSwipeListView(int position) {
                Toast.makeText(getApplicationContext(), "Action_2", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HalfSwipeListView(int position) {
                Toast.makeText(getApplicationContext(), "Action_1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void LoadDataForScroll(int count) {
            }

            @Override
            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                Toast.makeText(getApplicationContext(), "Action_D", Toast.LENGTH_SHORT).show();
                for (int i : reverseSortedPositions) {
                    listData.remove(i);
                }
                swipeListViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnClickListView(int position) {
                Toast.makeText(getApplicationContext(), "Action_C", Toast.LENGTH_SHORT).show();
            }
        }, this);

//		swipeListViewTouchListener.SwipeType = SwipeListViewTouchListener.Double;
        swipeListViewTouchListener.SwipeType = SwipeListViewTouchListener.Dismiss;

        listView.setOnTouchListener(swipeListViewTouchListener);

    }

}
