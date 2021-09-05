package com.tunaPasta16.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.tunaPasta16.R;
import com.tunaPasta16.view.FlingImageView;

public class FlingImageViewTest extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.flingimageviewtest);

        FlingImageView flingView = findViewById(R.id.flingView);
        flingView.setClockListener(new FlingImageView.ClockListener() {
            @Override
            public void clockWise() {
                System.out.println("顺时针==>");
            }

            @Override
            public void clockWiseAnti() {
                System.out.println("逆时针==>");
            }
        });
    }
}