package com.tunaPasta07.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta07.R;

public class EntryAct extends Activity {

    private String[] s = {"ContactSearchTest", "FlipUsageTest", "QRCodeTest", "ScrollEffectTest", "MoveBackgroundTabTest",
            "CustomLockTest", "RectDemoTest", "ArcDemoTest", "LinesDemoTest", "MessageSeeTest",
            "ClipboardTest ", "ColorPickerTest", "DialLayoutTest", "DragGridViewTest", "CustomGragGridViewTest",
            "GPSFlightModeChangeTest", "UninstallTest", "HomeWatcherTest", "OnlyChineseInputTest", "StageGalleryTest",
            "SwitchActivityEffectTest", "MatrixChangeTest", "SplashEffectTest", "ShaderTest", "GetPingyinTest",
            "MoonChangeTest", "CustomtabTest", "RadialMenuTest"};

    private Class<?>[] c = {ContactSearchTest.class, FlipUsageTest.class, QRCodeTest.class, ScrollEffectTest.class, MoveBackgroundTabTest.class,
            CustomLockTest.class, RectDemoTest.class, ArcDemoTest.class, LinesDemoTest.class, MessageSeeTest.class,
            ClipboardTest.class, ColorPickerTest.class, DialLayoutTest.class, DragGridViewTest.class, CustomGragGridViewTest.class,
            GPSFlightModeChangeTest.class, UninstallTest.class, HomeWatcherTest.class, OnlyChineseInputTest.class, StageGalleryTest.class,
            SwitchActivityEffectTest.class, MatrixChangeTest.class, SplashEffectTest.class, ShaderTest.class, GetPingyinTest.class,
            MoonChangeTest.class, CustomtabTest.class, RadialMenuTest.class};

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