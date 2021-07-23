package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tunaPasta14.R;
import com.tunaPasta14.widget.TVOffAnimation;

public class TVOffDemoTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tvoffdemotest);
        Button b = findViewById(R.id.Button01);
        b.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                View img = findViewById(R.id.ImageView01);
                img.startAnimation(new TVOffAnimation());
            }
        });
    }
}