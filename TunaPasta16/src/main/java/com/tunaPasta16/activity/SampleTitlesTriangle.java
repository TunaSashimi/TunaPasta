package com.tunaPasta16.activity;

import android.os.Bundle;
import com.tunaPasta16.R;
import com.tunaPasta16.viewpagerindicator.TitlePageIndicator;
import com.tunaPasta16.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

public class SampleTitlesTriangle extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setFooterIndicatorStyle(IndicatorStyle.Triangle);
        mIndicator = indicator;
    }
}