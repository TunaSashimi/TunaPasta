package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaImage;

public class TunaImageTest extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunaimagetest);

        final TunaImage tunaImage01 = findViewById(R.id.tunaImage01);

        SeekBar seekBar01 = findViewById(R.id.seekBar01);
        SeekBar seekBar02 = findViewById(R.id.seekBar02);
        SeekBar seekBar03 = findViewById(R.id.seekBar03);

        seekBar01.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tunaImage01.setTunaImageBright(progress / 50f);//0-2
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBar02.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tunaImage01.setTunaImageHue((progress - 50) / 50f * 180);//-180-180
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBar03.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tunaImage01.setTunaImageSaturation(progress / 50f);//0-2
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}