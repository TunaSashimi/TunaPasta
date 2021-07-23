package com.tunaPasta07.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta07.R;

/**
 * 展示的最终Activity
 */
public class SwitchActivityResultTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switchactivityresulttest);

        findViewById(R.id.button01).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}