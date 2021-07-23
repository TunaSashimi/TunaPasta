package com.tunaPasta19.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaScale;
import com.tunaPasta19.tuna.TunaView.TunaTouchUpListener;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerTest extends Activity {
    private ViewPager viewPager;
    private TunaScale tunaScaleArrary[] = new TunaScale[4];
    private LayoutInflater layoutInflater;
    private Rect rect;
    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(View arg0, final int arg1) {
            if (tunaScaleArrary[arg1] == null) {
                switch (arg1) {
                    case 0:
                        TunaScale tunaScale01 = (TunaScale) layoutInflater.inflate(R.layout.viewpagertestitem01, null);
                        tunaScaleArrary[0] = tunaScale01;
                        break;
                    case 1:
                        TunaScale tunaScale02 = (TunaScale) layoutInflater.inflate(R.layout.viewpagertestitem02, null);
                        tunaScaleArrary[1] = tunaScale02;
                        break;
                    case 2:
                        TunaScale tunaScale03 = (TunaScale) layoutInflater.inflate(R.layout.viewpagertestitem03, null);
                        tunaScaleArrary[2] = tunaScale03;
                        break;
                    case 3:
                        TunaScale tunaScale04 = (TunaScale) layoutInflater.inflate(R.layout.viewpagertestitem04, null);
                        tunaScaleArrary[3] = tunaScale04;
                        break;
                }
            }
            //onTouchUP
            if (arg1 == (tunaScaleArrary.length - 1)) {
                tunaScaleArrary[arg1].setTunaTouchUpListener(new TunaTouchUpListener() {
                    @Override
                    public void tunaTouchUp(View v) {
                        if (rect == null) {
                            rect = tunaScaleArrary[arg1].getTunaTouchRectagle();
                        }
                        float x = tunaScaleArrary[arg1].getTunaTouchEventX();
                        float y = tunaScaleArrary[arg1].getTunaTouchEventY();
                        if (rect.contains((int) x, (int) y)) {
                            finish();
                        }
                    }
                });
            }
            ((ViewPager) arg0).addView(tunaScaleArrary[arg1]);
            return tunaScaleArrary[arg1];
        }

        @Override
        public int getCount() {
            return tunaScaleArrary.length;
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
            arg2 = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewpagertest);

        layoutInflater = LayoutInflater.from(ViewPagerTest.this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
}
