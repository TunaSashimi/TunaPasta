package com.tunaPasta16.activity;

import android.os.Bundle;

import com.tunaPasta16.R;
import com.tunaPasta16.viewpagerindicator.LinePageIndicator;

public class SampleLinesStyledTheme extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //The look of this sample is set via a style in the manifest
        setContentView(R.layout.simple_lines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (LinePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}