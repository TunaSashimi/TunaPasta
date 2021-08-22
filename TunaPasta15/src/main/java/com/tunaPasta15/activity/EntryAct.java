package com.tunaPasta15.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta15.R;

public class EntryAct extends Activity {

    private String[] s = {"WheelTest", "RadialWidgetTest", "PageddragTest", "AnimationCloning",
            "AnimationLoading", "AnimationSeeking", "AnimatorEvents", "BouncingBalls",
            "CustomEvaluator", "MultiPropertyAnimation", "ReversingAnimation", "Droidflakes",
            "PathAnimationTest", "TogglesTest", "ViewPropertyAnimatorTest", "ImageSrcTest", "TestViewUnitTest",
            "FragmentSwitchTest", "FragmentShowHideTest", "LinkMoveListViewTest",
            "VideoViewTest", "VideoViewControllerTest", "WeightTest", "VerticalScrollTestViewTest",
            "CustomAnimationTest", "MoveLineQuadCubicArcTest", "BezierDrawViewTest",
            "WaveEffectTest",
    };

    private Class<?>[] c = {WheelTest.class, RadialWidgetTest.class, PageddragTest.class, AnimationCloning.class,
            AnimationLoading.class, AnimationSeeking.class, AnimatorEvents.class, BouncingBalls.class,
            CustomEvaluator.class, MultiPropertyAnimation.class, ReversingAnimation.class, Droidflakes.class,
            PathAnimationTest.class, TogglesTest.class, ViewPropertyAnimatorTest.class, ImageSrcTest.class, TestViewUnitTest.class,
            FragmentSwitchTest.class, FragmentShowHideTest.class, LinkMoveListViewTest.class,
            VideoViewTest.class, VideoViewControllerTest.class, WeightTest.class, VerticalScrollTestViewTest.class,
            CustomAnimationTest.class, MoveLineQuadCubicArcTest.class, BezierDrawViewTest.class,
            WaveEffectTest.class,
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
