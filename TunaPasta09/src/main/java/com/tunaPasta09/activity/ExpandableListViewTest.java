package com.tunaPasta09.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.tunaPasta09.R;
import com.tunaPasta09.adapter.ExpandableListAdapter;

public class ExpandableListViewTest extends Activity {
    private String[] groupArray = {"射手", "辅助", "坦克", "法师"};
    private String[][] childArray = {
            {"孙尚香", "后羿", "马可波罗", "狄仁杰"},
            {"孙膑", "蔡文姬", "鬼谷子", "杨玉环"},
            {"张飞", "廉颇", "牛魔", "项羽"},
            {"诸葛亮", "王昭君", "安琪拉", "干将"}
    };
    private ExpandableListView expandableListView;
    private ExpandableListAdapter jobExpandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandablelistviewtest);

        jobExpandableListAdapter = new ExpandableListAdapter(groupArray, childArray);
        expandableListView = findViewById(R.id.expandableListView);
        expandableListView.setAdapter(jobExpandableListAdapter);

        //设置分组的监听
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getApplicationContext(), groupArray[groupPosition], Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //设置子项布局监听
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), childArray[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //全部展开
        for (int i = 0; i < jobExpandableListAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }

        //控制他只能打开一个组
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                int count = jobExpandableListAdapter.getGroupCount();
//                for (int i = 0; i < count; i++) {
//                    if (i != groupPosition) {
//                        expandableListView.collapseGroup(i);
//                    }
//                }
//            }
//        });
    }
}