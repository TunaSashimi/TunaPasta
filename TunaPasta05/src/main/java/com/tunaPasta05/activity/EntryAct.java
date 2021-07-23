package com.tunaPasta05.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.SanAngeles.NDK3Test;
import com.tunaPasta05.R;

public class EntryAct extends Activity {

    private String[] s = {"TabhostTest01", "TabhostTest02", "ViewFlipperTest", "NetWorkStateTest",
            "WaterFallTest", "ViewPagerTest", "MeasureSDcardTest", "AutoDeleteTest",
            "ThumbTest", "ListViewListenerTest", "ChangeScreenOrationTest", "CallOtherActTest(TunaPasta03)",
            "GetWifiInformationTest", "NotificationTest", "UpdateUITest", "XmlnsTest",
            "PopupWindowTest", "SurfaceChangeClothTest", "ScrollViewTest", "RotaryDialTest",
            "ScreenshotTest", "RadioButtonTest", "HandDrawTest", "CustomPlayerTest",
            "ChangePhotoTest", "GalleryChoiceTest", "TouchFlipperTest", "NDK3Test"};

    private Class<?>[] c = {TabhostTest01.class, TabhostTest02.class, ViewFlipperTest.class, NetWorkStateTest.class,
            WaterFallTest.class, ViewPagerTest.class, MeasureSDcardTest.class, AutoDeleteTest.class,
            ThumbTest.class, ListViewListenerTest.class,ChangeScreenOrationTest.class, CallOtherActTest.class,
            GetWifiInformationTest.class, NotificationTest.class, UpdateUITest.class,XmlnsTest.class,
            PopupWindowTest.class, SurfaceChangeClothTest.class, ScrollViewTest.class, RotaryDialTest.class,
            ScreenshotTest.class, RadioButtonTest.class, HandDrawTest.class, CustomPlayerTest.class,
            ChangePhotoTest.class, GalleryChoiceTest.class, TouchFlipperTest.class, NDK3Test.class};

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