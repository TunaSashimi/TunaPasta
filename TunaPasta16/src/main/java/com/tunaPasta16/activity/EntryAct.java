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

    private String[] s = {"ListSamples", "CardViewTest", "SwipeRefreshLayoutTest", "FloatTouchListenerTest"
            , "TSectorTest", "SoftKeyBoardListenerTest", "TextInputLayoutTest", "VerificationCodeViewTest"
            , "CountDownTimerTest", "RelativeLayoutCodeTest", "LaunchModeTest", "LaunchJumpTest"
            , "LiveDataTest", "CoordinatorLayoutTest", "ConstraintLayoutTest", "DialogFragmentTest"
            , "DividerTest", "CircularTest", "TransparentStatusBarTest", "ImmersiveStatusBarTest"
            , "MineTest","PathInterpolatorTest"
            , "MotionLayoutOnClickTest", "MotionLayoutOnSwipeTest"
            , "MotionLayoutConstraintSetTest", "MotionSceneTest"};

    private Class<?>[] c = {ListSamples.class, CardViewTest.class, SwipeRefreshLayoutTest.class, FloatTouchListenerTest.class
            , TSectorTest.class, SoftKeyBoardListenerTest.class, TextInputLayoutTest.class, VerificationCodeViewTest.class
            , CountDownTimerTest.class, RelativeLayoutCodeTest.class, LaunchModeTest.class, LaunchJumpTest.class
            , LiveDataTest.class, CoordinatorLayoutTest.class, ConstraintLayoutTest.class, DialogFragmentTest.class
            , DividerTest.class, CircularTest.class, TransparentStatusBarTest.class, ImmersiveStatusBarTest.class
            , MineTest.class, PathInterpolatorTest.class
            , MotionLayoutOnClickTest.class, MotionLayoutOnSwipeTest.class
            , MotionLayoutConstraintSetTest.class, MotionSceneTest.class
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
