package com.tunaPasta18.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta18.R;

import java.util.ArrayList;
import java.util.List;

public class EntryAct extends Activity {
    private String[] s = {"ImageFilterTest", "TimerTaskTest", "WebViewTest", "InvoiceTest"
            , "IndentFocusListTest", "OKHttpTest", "StethoTest", "GlideTest"
            , "SoundPoolTest", "IDKeyBoardTest", "RXJava2Test", "NotesListTest"
            , "SoundRecorder", "CropperSampleTest", "InflateTest"
            , "LeakCanaryTest", "LottieTest01", "LottieTest02"
    };

    private Class<?>[] c = {ImageFilterTest.class, TimerTaskTest.class, WebViewTest.class, InvoiceTest.class,
            IndentFocusListTest.class, OKHttpTest.class, StethoTest.class, GlideTest.class
            , SoundPoolTest.class, IDKeyBoardTest.class, RXJava2Test.class, NotesListTest.class
            , SoundRecorder.class, CropperSampleTest.class, InflateTest.class
            , LeakCanaryTest.class, LottieTest01.class, LottieTest02.class
    };

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

        //Memory leak code
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}