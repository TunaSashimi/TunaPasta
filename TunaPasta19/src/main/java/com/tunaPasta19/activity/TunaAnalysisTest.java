package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaAnalysis;

public class TunaAnalysisTest extends Activity {
    private TunaAnalysis tunaanalysis;
    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunaanalysistest);

        tunaanalysis = findViewById(R.id.tunaanalysis);
        seekbar = findViewById(R.id.seekbar);

        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tunaanalysis.SetControlXandY(progress, progress);
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
