package com.tunaPasta09.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta09.R;

public class EntryAct extends Activity {

    private String[] s = {"ViewStubTest", "StringFormatTest", "PullDownListViewTest", "CompassTest", "GesturePasswordTest",
            "ViewTreeObserverTest", "ClipDrawableTest", "ExpandableListViewTest", "OrderConfirmPriceTest", "WidgetLibraryText",
            "AnimatorFunctionTest", "RelativeLayoutClickTest", "LevelDetailsTest", "LinearLayoutTest", "DatePickerTest",
            "ImageRotateTest", "ThreePaintTypesCompare", "DrawTextCenterTest", "SlidingBarViewTest", "OpenGLThreePyramidAndCubeRotationTest",
            "OpenGLGestureCubeRotationTest", "OpenGLThreeDimensionalMazeTest", "OpenGLReelWavesTest", "OpenGLMatrixRotationTest", "OpenGLFireworksCombustionTest",
            "OpenGLTwoDirectionsDisplacementTest", "OpenGLCircularArrayTest", "SoftKeyboardManagerTest"};

    private Class<?>[] c = {ViewStubTest.class, StringFormatTest.class, PullDownListViewTest.class, CompassTest.class, GesturePasswordTest.class,
            ViewTreeObserverTest.class, ClipDrawableTest.class, ExpandableListViewTest.class, OrderConfirmPriceTest.class, WidgetLibraryText.class,
            AnimatorFunctionTest.class, RelativeLayoutClickTest.class, LevelDetailsTest.class, LinearLayoutTest.class, DatePickerTest.class,
            ImageRotateTest.class, ThreePaintTypesCompare.class, DrawTextcenterTest.class, SlidingBarViewTest.class, OpenGLThreePyramidAndCubeRotationTest.class,
            OpenGLGestureCubeRotationTest.class, OpenGLThreeDimensionalMazeTest.class, OpenGLReelWavesTest.class, OpenGLMatrixRotationTest.class, OpenGLFireworksCombustionTest.class,
            OpenGLTwoDirectionsDisplacementTest.class, OpenGLCircularArrayTest.class, SoftKeyboardManagerTest.class};

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

        // 注:需要android.permission.INTERNET权限
        // <uses-permission android:name="android.permission.INTERNET" />
        // ViewServer.get(this).addWindow(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        // ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ViewServer.get(this).removeWindow(this);
    }
}