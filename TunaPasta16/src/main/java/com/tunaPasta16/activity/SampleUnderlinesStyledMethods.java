package com.tunaPasta16.activity;

import android.os.Bundle;

import com.tunaPasta16.R;
import com.tunaPasta16.viewpagerindicator.UnderlinePageIndicator;


public class SampleUnderlinesStyledMethods extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_underlines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        UnderlinePageIndicator indicator = findViewById(R.id.indicator);
        mIndicator = indicator;
        indicator.setViewPager(mPager);
        indicator.setSelectedColor(0xFFCC0000);
        indicator.setBackgroundColor(0xFFCCCCCC);
        indicator.setFadeDelay(1000);
        indicator.setFadeLength(1000);
    }
}