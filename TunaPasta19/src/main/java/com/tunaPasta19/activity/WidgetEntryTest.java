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

public class WidgetEntryTest extends Activity {
    private Class<?>[] c = {

            TouchPaintTest.class,

            TunaViewTest.class,

            TunaAnimateTest.class,
            TunaAnalysisTest.class,

            TunaBetaTest.class,
            TunaBubbleTest.class,
            TunaButtonTest.class,

            TunaDialogTest.class,
            TunaDownloadTest.class,
            TunaDownloadGifTest.class,
            TunaDownloadMultipleTest.class,
            TunaDragTest.class,
            TunaDumbbellTest.class,

            TunaGifTest.class,

            TunaHeartTest.class,
            TunaHollowTest.class,
            TunaHomeTest.class,

            TunaImageTest.class,
            TunaIrregularTest.class,

            TunaLayoutTest.class,
            TunaLineTest.class,
            TunaLoad.class,

            TunaMaterialTest.class,

            TunaDrawTest.class,
            TunaProgressTest.class,

            TunaRepeatTest.class,
            TunaRippleTest.class,
            TunaRowTest.class,

            TunaScaleTest.class,
            TunaScratchTest.class,
            TunaSVGTest.class,

            TunaTrackballTest.class,
            TunaTrapezoidTest.class,
            TunaTrangleTest.class,

            TunaWait.class,
            TunaWaveTest.class,
            TunaWelcome.class,
            TunaMoveTest.class,
            TunaWrapTest.class,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.widgetentrytest);

        ListView lv = findViewById(R.id.listView);
        List<String> list = new ArrayList();

        for (int i = 0; i < c.length; i++) {
            list.add(c[i].getSimpleName());
        }

        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(WidgetEntryTest.this, c[arg2]));
            }
        });
    }
}
