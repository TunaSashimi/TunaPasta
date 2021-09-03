package com.tunaPasta14.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta14.R;

public class EntryAct extends Activity {

    private String[] s = {"HotelTest", "GifViewTest", "HideDatePickerDateTest", "WindowFocusChangedTest",
            "BreakPointsResumeTest", "SimulateClickEventTest", "StaticLayoutViewTest", "BlisterViewTest",
            "TVOffDemoTest", "FixedGridLayoutTest", "ImageFilterChoice", "SugaredlistAnimationsTest",
            "KeydemoActivity", "MultiTouchTestActivity", "XListViewTest", "DocumentActivity",
            "RotateActivity", "PathMorphActivity", "FillInHeartActivity", "MetaballViewTest",
            "RecyclerViewTest", "RecyclerViewMultiTest", "PicActivity", "InflateTest",
            "SoundPoolTest", "IDKeyBoardTest", "GlideTest", "IndentFocusListTest"
    };

    private Class<?>[] c = {HotelTest.class, GifViewTest.class, HideDatePickerDateTest.class, WindowFocusChangedTest.class,
            BreakPointsResumeTest.class, SimulateClickEventTest.class, StaticLayoutViewTest.class, BlisterViewTest.class,
            TVOffDemoTest.class, FixedGridLayoutTest.class, ImageFilterChoice.class, SugaredlistAnimationsTest.class,
            KeydemoActivity.class, MultiTouchTestActivity.class, XListViewTest.class, DocumentActivity.class,
            RotateActivity.class, PathMorphActivity.class, FillInHeartActivity.class, MetaballViewTest.class,
            RecyclerViewTest.class, RecyclerViewMultiTest.class, PicActivity.class, InflateTest.class,
            SoundPoolTest.class, IDKeyBoardTest.class, GlideTest.class, IndentFocusListTest.class
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
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(EntryAct.this, c[arg2]));
            }
        });
    }
}
