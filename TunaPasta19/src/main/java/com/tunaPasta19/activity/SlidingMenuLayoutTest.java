package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.tunaPasta19.R;
import com.tunaPasta19.widget.SlidingMenuLinearLayout;

public class SlidingMenuLayoutTest extends Activity {
    private SlidingMenuLinearLayout right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.slidingmenutest);

        right = findViewById(R.id.sliding_menu_view);

        findViewById(R.id.title_btn_back).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                right.scrollToTarget();
            }
        });
    }
}
