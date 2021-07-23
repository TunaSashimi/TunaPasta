package com.tunaPasta09.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.tunaPasta09.R;

public class RelativeLayoutClickTest extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativelayoutclicktest);

        RelativeLayout wating_vip_car_driver_layout = findViewById(R.id.wating_vip_car_driver_layout);
        wating_vip_car_driver_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("onClick");
            }
        });

    }
}