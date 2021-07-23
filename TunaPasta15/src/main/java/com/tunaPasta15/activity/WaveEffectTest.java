package com.tunaPasta15.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta15.R;
import com.tunaPasta15.widget.WaveEffect;
import com.tunaPasta15.widget.WaveEffectTextView;


public class WaveEffectTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waveeffecttest);

        WaveEffectTextView tv = (WaveEffectTextView) findViewById(R.id.my_text_view);

        // start animation
        new WaveEffect().start(tv);
    }

}
