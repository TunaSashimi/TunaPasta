package com.tunaPasta16.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tunaPasta16.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author TunaSashimi
 * @date 2021/7/29 14:12
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
public class LaunchModeTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launchmodetest);
        TextView textView = findViewById(R.id.tv);
        textView.setText("LaunchModeTest==>"+this.toString());
        Button button = findViewById(R.id.btn_skip);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchModeTest.this, LaunchJumpTest.class);
                startActivity(intent);
            }
        });
    }
}