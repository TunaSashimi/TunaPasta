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

public class PageTransformerTest02 extends Activity {
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagetransformertest02);

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

            //ViewGroup container, int position 和 View arg0, int arg1 的区别?
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

        viewpager.setPageTransformer(true, new ZoomOutPageTransformer());

    }

    class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1) This page is way off-screen to the left.  
                view.setAlpha(0);
            } else if (position <= 1) { // [-1,1] Modify the default slide transition to shrink the page as well  
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }
                // Scale the page down (between MIN_SCALE and 1)  
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                // Fade the page relative to its size.  
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                        / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else { // (1,+Infinity] This page is way off-screen to the right.  
                view.setAlpha(0);
            }
        }
    }

}
