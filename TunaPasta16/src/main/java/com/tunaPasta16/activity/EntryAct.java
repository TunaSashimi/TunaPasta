package com.tunaPasta16.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta16.R;

import java.util.ArrayList;
import java.util.List;

public class EntryAct extends Activity {

    private String[] s = {"ListSamples", "SwipeRefreshLayoutTest", "FloatingDragViewTest", "FloatingDecorViewTest",
            "TSectorTest", "TextInputLayoutTest", "VerificationCodeViewTest", "CountDownTimerTest",
            "RelativeLayoutCodeTest", "LaunchModeTest", "LaunchJumpTest", "LiveDataTest",
            "CoordinatorLayoutTest", "ConstraintLayoutTest", "DialogFragmentTest", "LayoutDividerTest",
            "CircularTest", "TransparentStatusBarTest", "ImmersiveStatusBarTest", "FlingImageViewTest",
            "ObjectAnimatorTest", "MotionLayoutOnClickTest", "MotionLayoutOnSwipeTest", "MotionLayoutConstraintSetTest",
            "MotionSceneTest"
    };

    private Class<?>[] c = {ListSamples.class, SwipeRefreshLayoutTest.class, FloatingDragViewTest.class, FloatingDecorViewTest.class,
            TSectorTest.class, TextInputLayoutTest.class, VerificationCodeViewTest.class, CountDownTimerTest.class,
            RelativeLayoutCodeTest.class, LaunchModeTest.class, LaunchJumpTest.class, LiveDataTest.class,
            CoordinatorLayoutTest.class, ConstraintLayoutTest.class, DialogFragmentTest.class, LayoutDividerTest.class,
            CircularTest.class, TransparentStatusBarTest.class, ImmersiveStatusBarTest.class, FlingImageViewTest.class,
            ObjectAnimatorTest.class, MotionLayoutOnClickTest.class, MotionLayoutOnSwipeTest.class, MotionLayoutConstraintSetTest.class,
            MotionSceneTest.class
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
