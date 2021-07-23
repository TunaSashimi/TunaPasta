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
import android.widget.Toast;

import com.tunaPasta19.R;

public class FunctionEntryTest extends Activity {
    private String[] s = {"指南针", "二维码", "人脸探测"};

    private Class<?>[] c = {CompassTest.class, QRCodeTest.class, FaceDetectTest.class};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.functionentrytest);

        ListView lv = findViewById(R.id.listView);
        List<String> list = new ArrayList();

        for (int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }

        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == 2) {
                    Toast.makeText(FunctionEntryTest.this, "程序正在启动,请稍后....", Toast.LENGTH_LONG).show();
                }
                startActivity(new Intent(FunctionEntryTest.this, c[arg2]));
            }
        });
    }
}
