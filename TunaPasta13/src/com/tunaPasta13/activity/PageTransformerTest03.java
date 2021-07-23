package com.tunaPasta13.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.tunaPasta13.R;
import com.tunaPasta13.widget.JazzyViewPager;
import com.tunaPasta13.widget.JazzyViewPager.TransitionEffect;
import com.tunaPasta13.widget.OutlineContainer;

import androidx.viewpager.widget.PagerAdapter;

public class PageTransformerTest03 extends Activity {
    private JazzyViewPager mJazzy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagetransformertest03);
        setupJazziness(TransitionEffect.Tablet);
    }

    // Manu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Toggle Fade");
        String[] effects = this.getResources().getStringArray(R.array.jazzy_effects);
        for (String effect : effects) {
            menu.add(effect);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().toString().equals("Toggle Fade")) {
            mJazzy.setFadeEnabled(!mJazzy.getFadeEnabled());
        } else {
            TransitionEffect effect = TransitionEffect.valueOf(item.getTitle().toString());
            setupJazziness(effect);
        }
        return true;
    }

    private void setupJazziness(TransitionEffect effect) {
        mJazzy = findViewById(R.id.jazzy_pager);
        mJazzy.setTransitionEffect(effect);
        mJazzy.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView View = new TextView(getApplicationContext());
                View.setGravity(Gravity.CENTER);
                View.setTextSize(30);
                View.setTextColor(Color.WHITE);
                View.setText("Page " + position);
                View.setPadding(30, 30, 30, 30);
                int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64, (int) Math.floor(Math.random() * 128) + 64, (int) Math.floor(Math.random() * 128) + 64);
                View.setBackgroundColor(bg);
                container.addView(View, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                mJazzy.setObjectForPosition(View, position);
                return View;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mJazzy.findViewFromObject(position));
            }

            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public boolean isViewFromObject(View view, Object obj) {
                if (view instanceof OutlineContainer) {
                    return ((OutlineContainer) view).getChildAt(0) == obj;
                } else {
                    return view == obj;
                }
            }
        });
        mJazzy.setPageMargin(30);
        // mJazzy.setOutlineEnabled(true);
    }
}
