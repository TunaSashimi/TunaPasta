package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Bundle;

public class StaticLayoutViewTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new StaticLayoutView(this));
    }
}
