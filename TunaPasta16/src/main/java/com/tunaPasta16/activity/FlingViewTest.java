package com.tunaPasta16.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.tunaPasta16.R;
import com.tunaPasta16.view.FlingView;

public class FlingViewTest extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new FlingView(this));
    }
}