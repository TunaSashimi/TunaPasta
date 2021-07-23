package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.tunaPasta14.R;
import com.tunaPasta14.widget.MetaballDebugView;

public class MetaballViewTest extends Activity implements SeekBar.OnSeekBarChangeListener {

    private MetaballDebugView debugMetaballView;
    private SeekBar seekBar, seekBar2, seekBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metaballviewtest);
        debugMetaballView = findViewById(R.id.debug_metaball);
        seekBar = findViewById(R.id.seekBar);
        seekBar2 = findViewById(R.id.seekBar2);
        seekBar3 = findViewById(R.id.seekBar3);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekBar:
                debugMetaballView.setMaxDistance(progress);
                break;
            case R.id.seekBar2:
                debugMetaballView.setMv(progress / 100f);
                break;
            case R.id.seekBar3:
                debugMetaballView.setHandleLenRate(progress / 100f);
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
