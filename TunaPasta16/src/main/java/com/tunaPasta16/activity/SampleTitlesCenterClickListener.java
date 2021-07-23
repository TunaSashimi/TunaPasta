package com.tunaPasta16.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.tunaPasta16.R;
import com.tunaPasta16.viewpagerindicator.TitlePageIndicator;
import com.tunaPasta16.viewpagerindicator.TitlePageIndicator.IndicatorStyle;
import com.tunaPasta16.viewpagerindicator.TitlePageIndicator.OnCenterItemClickListener;

public class SampleTitlesCenterClickListener extends BaseSampleActivity implements OnCenterItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        TitlePageIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setFooterIndicatorStyle(IndicatorStyle.Underline);
        indicator.setOnCenterItemClickListener(this);
        mIndicator = indicator;
    }

    @Override
    public void onCenterItemClick(int position) {
        Toast.makeText(this, "You clicked the center title!", Toast.LENGTH_SHORT).show();
    }
}