package com.tunaPasta13.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta13.R;

public class EntryAct extends Activity {

    private String[] s = {"PentagramTest", "RoundCornerTest", "ClickThreeTimes", "RoundImageViewTest",
            "SoundRecordingTest", "OptimalPathTest", "TakeBlowTest", "WheelDemo",
            "SlideCutListViewTest", "CurlTest", "TunaStageTest", "DifferentGetMeasuredWidth",
            "UseJSResolveTest", "CustomExceptionTest", "ScrollerRelativeLayoutTest", "ScrollerLinearLayoutTest",
            "PagerContainerTest", "IMETest", "RequestFocusTest", "SMSSendTest",
            "MovieDecodeGifViewTest", "PageTransformerTest01", "PageTransformerTest02", "PageTransformerTest03",
            "FlexBoxLayoutTest",};

    private Class<?>[] c = {PentagramTest.class, RoundCornerTest.class, ClickThreeTimes.class, RoundImageViewTest.class,
            SoundRecordingTest.class, OptimalPathTest.class, TakeBlowTest.class, WheelDemo.class,
            SlideCutListViewTest.class, CurlTest.class, TunaStageTest.class, DifferentGetMeasuredWidth.class,
            UseJSResolveTest.class, CustomExceptionTest.class, ScrollerRelativeLayoutTest.class, ScrollerLinearLayoutTest.class,
            PagerContainerTest.class, IMETest.class, RequestFocusTest.class, SMSSendTest.class,
            MovieDecodeGifViewTest.class, PageTransformerTest01.class, PageTransformerTest02.class, PageTransformerTest03.class,
            FlexBoxLayoutTest.class,};

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
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(EntryAct.this, c[arg2]));
            }
        });
    }
}
