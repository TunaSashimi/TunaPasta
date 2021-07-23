package com.tunaPasta11.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta11.R;

public class EntryAct extends Activity {

    private String[] s = {"UnlockGesturePasswordTest", "SwipeListViewTest", "RelativeLayoutLayoutTest",
            "TextViewDrawableLeftTest", "DLogTest", "CutoutPictureTest", "ClippingTest",
            "Transition3DTest", "LayoutAnimation1", "LayoutAnimation2", "LayoutAnimation3",
            "LayoutAnimation4", "LayoutAnimation5", "LayoutAnimation6", "LayoutAnimation7",
            "SensorsTest", "ArcsTest", "BitmapDecodeTest", "CameraPreviewTest", "CreateBitmapTest",
            "ColorMatrixTest", "GradientDrawable", "ShapeDrawableTest", "FingerPaint",
            "MeasureTextTest", "PathEffectsTest", "PathFillTypesTest",
    };

    private Class<?>[] c = {UnlockGesturePasswordTest.class, SwipeListViewTest.class, RelativeLayoutLayoutTest.class,
            TextViewDrawableLeftTest.class, DLogTest.class, CutoutPictureTest.class, ClippingTest.class,
            Transition3DTest.class, LayoutAnimation1.class, LayoutAnimation2.class, LayoutAnimation3.class,
            LayoutAnimation4.class, LayoutAnimation5.class, LayoutAnimation6.class, LayoutAnimation7.class,
            SensorsTest.class, ArcsTest.class, BitmapDecodeTest.class, CameraPreviewTest.class, CreateBitmapTest.class,
            ColorMatrixTest.class, GradientDrawableTest.class, ShapeDrawableTest.class, FingerPaintTest.class,
            MeasureTextTest.class, PathEffectsTest.class, PathFillTypesTest.class,
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
