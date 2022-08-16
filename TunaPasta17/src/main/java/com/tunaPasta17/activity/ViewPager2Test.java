package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.viewpager2.widget.ViewPager2;

import com.tunaPasta17.R;
import com.tunaPasta17.adapter.ViewPagerAdapter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author TunaSashimi
 * @date 2020-03-05 14:05
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class ViewPager2Test extends Activity {
    Timer timer = new Timer();
    ViewPager2 viewPager2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager2test);
        //
        viewPager2 = findViewById(R.id.viewpager2);
        viewPager2.setAdapter(new ViewPagerAdapter());
        //
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //
                int currentItem = viewPager2.getCurrentItem();
                viewPager2.setCurrentItem(currentItem + 1);
            }
        }, 0, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //
        if (timer != null) {
            timer.cancel();
        }
    }
}
