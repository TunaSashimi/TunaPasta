package com.tunaPasta17.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.tunaPasta17.R;

import java.util.ArrayList;
import java.util.List;

public class EntryAct extends Activity {
    private String[] s = {"GetConfigValueTest", "PermissionUtilTest", "ArouterTest", "AndroidQTest",
            "WebPTest", "VerifyTest", "SVGTest", "TCalendarTest",
            "CalendarSelectTest", "ExpandViewTest", "MVPTest", "DataBindingDemo",
            "TLoadingViewTest"};

    private Class<?>[] c = {GetConfigValueTest.class, PermissionUtilTest.class, ArouterTest.class, AndroidQTest.class,
            WebPTest.class, VerifyTest.class, SVGTest.class, TCalendarTest.class,
            CalendarSelectTest.class, ExpandViewTest.class, MVPTest.class, DataBindingDemo.class,
            TLoadingViewTest.class};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entryact);

        ListView lv = findViewById(R.id.listView);
        List<String> list = new ArrayList();
        for (int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }

        lv.setAdapter(new ArrayAdapter(this, R.layout.entryactitem, list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(EntryAct.this, c[arg2]));
            }
        });
    }
}