package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaProgress;

public class TunaProgressTest extends Activity {

    private TunaProgress tunaProgressCircleClockWise, tunaProgressCircleUpWard, tunaProgressCircleUpDown,
        tunaProgressCustomClockWise, tunaProgressCustomUpWard, tunaProgressCustomUpDown;

    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunaprogresstest);

        tunaProgressCircleClockWise = findViewById(R.id.tunaProgressCircleClockWise);
        tunaProgressCircleUpWard = findViewById(R.id.tunaProgressCircleUpWard);
        tunaProgressCircleUpDown = findViewById(R.id.tunaProgressCircleUpDown);

        tunaProgressCustomClockWise = findViewById(R.id.tunaProgressCustomClockWise);
        tunaProgressCustomUpWard = findViewById(R.id.tunaProgressCustomUpWard);
        tunaProgressCustomUpDown = findViewById(R.id.tunaProgressCustomUpDown);

        seekbar = findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tunaProgressCircleClockWise.setTunaProgressPercent(progress * 0.01f);
                tunaProgressCircleUpWard.setTunaProgressPercent(progress * 0.01f);
                tunaProgressCircleUpDown.setTunaProgressPercent(progress * 0.01f);

                tunaProgressCustomClockWise.setTunaProgressPercent(progress * 0.01f);
                tunaProgressCustomUpWard.setTunaProgressPercent(progress * 0.01f);
                tunaProgressCustomUpDown.setTunaProgressPercent(progress * 0.01f);
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
