package com.tunaPasta05.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.tunaPasta05.R;
import com.tunaPasta05.adapter.DemoAdapter;

public class CheckboxListTest extends Activity {
    private ListView listview;
    private String[] sData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkboxlisttest);

        sData = getResources().getStringArray(R.array.lv_data);

        listview = findViewById(R.id.listview);
        listview.setAdapter(new DemoAdapter(this, sData));

    }
}