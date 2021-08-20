package com.tunaPasta15.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta15.widget.BezierDrawView;

public class BezierDrawViewTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new BezierDrawView(this));
    }
}
