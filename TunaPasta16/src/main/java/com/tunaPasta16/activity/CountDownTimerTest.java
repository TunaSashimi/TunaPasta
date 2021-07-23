package com.tunaPasta16.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.tunaPasta16.R;

import androidx.annotation.Nullable;

/**
 * @author TunaSashimi
 * @date 2020-07-09 16:24
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class CountDownTimerTest extends Activity {
    private Button btn_start;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.countdowntest);

        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                new CountDownTimer(60 * 1000, 1000) {
                    @Override
                    public void onFinish() {
                        if (btn_start != null) {
                            btn_start.setText("重新获取");
                            btn_start.setClickable(true);
                            btn_start.setEnabled(true);
                        }
                        cancel();
                    }

                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (btn_start != null) {
                            btn_start.setClickable(false);
                            btn_start.setEnabled(false);
                            btn_start.setText(millisUntilFinished / 1000 + "s");
                            btn_start.setTextColor(Color.parseColor("#999999"));
                        }
                    }
                }.start();
            }
        });
    }
}
