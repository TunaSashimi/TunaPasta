package com.tunaPasta16.activity;

import android.os.Bundle;

import com.tunaPasta16.R;
import com.tunaPasta16.viewpagerindicator.CirclePageIndicator;

public class SampleCirclesStyledMethods extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_circles);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        CirclePageIndicator indicator = findViewById(R.id.indicator);
        mIndicator = indicator;
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;
        indicator.setBackgroundColor(0xFFCCCCCC);
        indicator.setRadius(10 * density);
        indicator.setPageColor(0x880000FF);
        indicator.setFillColor(0xFF888888);
        indicator.setStrokeColor(0xFF000000);
        indicator.setStrokeWidth(2 * density);
    }
}