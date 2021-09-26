package com.tunaPasta19.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta19.R;

public class LinkEntryTest extends Activity {

    private Class<?>[] c = {ViewPagerTest.class, SplashEffectTest.class, HollowEffectTest.class,
            UnlockGesturePasswordTest.class,};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.linkentrytest);

        ListView lv = findViewById(R.id.listView);
        List<String> list = new ArrayList();

        String[] WelcomeEntryTestTitleArray = getResources().getStringArray(R.array.welcomeentrytest_titleArray);
        for (int i = 0; i < WelcomeEntryTestTitleArray.length; i++) {
            list.add(WelcomeEntryTestTitleArray[i]);
        }

        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
        lv.setOnItemClickListener((arg0, arg1, arg2, arg3) -> startActivity(new Intent(LinkEntryTest.this, c[arg2])));
    }
}
