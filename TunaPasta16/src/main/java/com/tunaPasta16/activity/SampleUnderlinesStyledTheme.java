package com.tunaPasta16.activity;

import android.os.Bundle;

import com.tunaPasta16.R;
import com.tunaPasta16.viewpagerindicator.UnderlinePageIndicator;

import androidx.viewpager.widget.ViewPager;

public class SampleUnderlinesStyledTheme extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //The look of this sample is set via a style in the manifest
        setContentView(R.layout.simple_underlines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (UnderlinePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}