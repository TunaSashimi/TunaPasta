package com.tunaPasta19.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta19.R;

import java.util.ArrayList;
import java.util.List;

public class BusinessEntryTest extends Activity {
    private Class<?>[] c = {CustomFoodTest.class, CustomLotteryTest.class, CustomThreadTest.class, CustomEffectTest.class
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.businessentrytest);

        ListView lv =  findViewById(R.id.listView);
        List<String> list = new ArrayList();

        String[] BusinessEntryTestTitleArray = getResources().getStringArray(R.array.businessentrytest_titleArray);
        for (int i = 0; i < BusinessEntryTestTitleArray.length; i++) {
            list.add(BusinessEntryTestTitleArray[i]);
        }

        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(BusinessEntryTest.this, c[arg2]));
            }
        });
    }
}
