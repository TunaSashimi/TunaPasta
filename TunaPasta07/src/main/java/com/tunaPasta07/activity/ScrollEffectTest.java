package com.tunaPasta07.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.tunaPasta07.R;
import com.tunaPasta07.widget.ScrollEffectLinearLayout;

public class ScrollEffectTest extends Activity {
    private ScrollEffectLinearLayout right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scrolleffecttest);

        right = findViewById(R.id.sliding_menu_view);

        findViewById(R.id.title_btn_back).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                right.scrollToTarget();
            }
        });

    }
}
