package com.tunaPasta02.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;

import com.tunaPasta02.R;

public class EditTextTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edittexttest);

        final FrameLayout sub_waiting_taxi_radio = findViewById(R.id.sub_waiting_taxi_radio);
        final View view01 = findViewById(R.id.view01);
        Button button01 = findViewById(R.id.button01);

        button01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutParams layoutParams = view01.getLayoutParams();
                layoutParams.width = 500;
                layoutParams.height = 500;
                view01.setLayoutParams(layoutParams);
            }
        });

        sub_waiting_taxi_radio.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sub_waiting_taxi_radio.setVisibility(View.GONE);
            }
        });
    }
}
