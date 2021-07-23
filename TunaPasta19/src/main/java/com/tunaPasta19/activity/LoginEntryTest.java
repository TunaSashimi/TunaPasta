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
import com.tunaPasta19.tuna.TunaView;
import com.tunaPasta19.widget.Offect3DAnimation;

public class LoginEntryTest extends Activity {

    private Class<?>[] c = {UserLoginTest01.class, UserLoginTest02.class,};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loginentrytest);

        ListView lv = findViewById(R.id.listview);
        List<String> list = new ArrayList<String>();

        String[] LoginEntryTestTitleArray = getResources().getStringArray(R.array.loginentrytest_titleArray);
        for (int i = 0; i < LoginEntryTestTitleArray.length; i++) {
            list.add(LoginEntryTestTitleArray[i]);
        }

        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(LoginEntryTest.this, c[arg2]));
            }
        });

        int screenWidth = TunaView.getScreenWidth(this);
        int screenHeight = TunaView.getScreenHeight(this);

        lv.setAnimation(new Offect3DAnimation(screenWidth / 2, screenHeight / 2, 1000));
    }
}
