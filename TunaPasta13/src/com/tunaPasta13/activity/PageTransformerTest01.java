package com.tunaPasta13.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.tunaPasta13.R;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PageTransformerTest01 extends Activity {
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagetransformertest01);

        viewpager = findViewById(R.id.viewpager);
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return 10;
            }

            // ViewGroup container, int position 和 View arg0, int arg1 的区别?
            @Override
            public Object instantiateItem(View arg0, int arg1) {
                TextView textView = new TextView(getApplicationContext());
                textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                textView.setTextSize(30);
                textView.setTextColor(Color.WHITE);
                textView.setText("Page " + arg1);
                textView.setPadding(30, 30, 30, 30);
                int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64, (int) Math.floor(Math.random() * 128) + 64, (int) Math.floor(Math.random() * 128) + 64);
                textView.setBackgroundColor(bg);
                ((ViewPager) arg0).addView(textView);
                return textView;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView((View) arg2);
                arg2 = null;
            }
        });

        viewpager.setPageTransformer(true, new DepthPageTransformer());

    }
}

class DepthPageTransformer implements ViewPager.PageTransformer {
    private static float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        if (position < -1) { // [-Infinity,-1) This page is way off-screen to the left.
            view.setAlpha(0);
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);
        } else if (position <= 1) { // (0,1] Fade the page out.
            view.setAlpha(1 - position);
            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);
            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else { // (1,+Infinity] This page is way off-screen to the right.
            view.setAlpha(0);

        }
    }

}