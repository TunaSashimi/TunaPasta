package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta14.R;
import com.tunaPasta14.widget.LEDView;

public class LEDViewTest extends Activity {
    private LEDView ledView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledviewtest);
        ledView = findViewById(R.id.ledview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ledView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ledView.stop();
    }
}
