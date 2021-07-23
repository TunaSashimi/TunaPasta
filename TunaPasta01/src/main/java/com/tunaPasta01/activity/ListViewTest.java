package com.tunaPasta01.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.tunaPasta01.R;

public class ListViewTest extends Activity {
    private int[] images = {R.drawable.mv05, R.drawable.mv06, R.drawable.mv07, R.drawable.mv08, R.drawable.mv09};
    private String[] names = {"琪琪 ", "宁宁", "彤彤", "依依", "苗苗"};
    private String[] phones = {"13812345678", "13764803379", "13189012301", "15809567812", "15121098901"};

    private List<Map<String, Object>> data = new ArrayList();
    private SimpleAdapter simpleAdapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewtest);
        listView = findViewById(R.id.listView);
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", images[i]);
            map.put("name", names[i]);
            map.put("phone", phones[i]);
            data.add(map);
        }
        simpleAdapter = new SimpleAdapter(this, data, R.layout.listviewtestitem, new String[]{"name", "phone", "image"},
                new int[]{R.id.text01, R.id.text02, R.id.image01});

        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View view, int index, long arg3) {
                Toast.makeText(ListViewTest.this, names[index] + ",被选择！", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(ListViewTest.this, names[arg2] + ",被长按！", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // 滚动到最后一个,listview.getCount()-1
        Button button01 = findViewById(R.id.button01);
        button01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.smoothScrollToPosition(listView.getCount() - 1);
            }
        });

        // 滚动到头部,0
        Button button02 = findViewById(R.id.button02);
        button02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // smoothScrollToPosition有动效
                listView.smoothScrollToPosition(0);
                // setSelection没有动效
                // listView.setSelection(0);
            }
        });

        //
        Button button03 = findViewById(R.id.button03);
        button03.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap();
                map.put("name", names[0]);
                map.put("phone", phones[0]);
                map.put("image", images[0]);
                data.add(0, map);

                simpleAdapter.notifyDataSetChanged();

                //
                View childView = listView.getChildAt(0);

                int childHeight = childView.getHeight();
                int childWidth = childView.getWidth();

                Animation translateAnimationX = new TranslateAnimation(childWidth, 0, 0, 0);
                translateAnimationX.setDuration(500);
                childView.startAnimation(translateAnimationX);

                //
                Animation translateAnimationY = new TranslateAnimation(0, 0, -childHeight, 0);
                translateAnimationY.setDuration(500);
                listView.startAnimation(translateAnimationY);
            }
        });
    }
}