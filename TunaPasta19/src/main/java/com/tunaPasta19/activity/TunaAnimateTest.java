package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaAnimateCircle;
import com.tunaPasta19.tuna.TunaAnimateTransfer;
import com.tunaPasta19.tuna.TunaView.TunaSubAnimateEndListener;
import com.tunaPasta19.tuna.TunaView.TunaTouchUpListener;

public class TunaAnimateTest extends Activity {
    private TunaAnimateCircle tunaAnimateCircle;
    private TunaAnimateTransfer tunaAnimateTransfer01, tunaAnimateTransfer02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunaanimatetest);

        tunaAnimateCircle = findViewById(R.id.tunaAnimateCircle);
        tunaAnimateTransfer01 = findViewById(R.id.tunaAnimateTransfer01);
        tunaAnimateTransfer02 = findViewById(R.id.tunaAnimateTransfer02);

        tunaAnimateCircle.setTunaTouchUpListener(new TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                if (tunaAnimateCircle.isTunaAnimateCircleSpreadable()) {
                    tunaAnimateCircle.setTunaAnimateCircleSpreadable(false);
                    tunaAnimateTransfer01.setTunaAnimationable(true);
                    tunaAnimateTransfer02.setTunaAnimationable(true);
                } else {
                    tunaAnimateCircle.setTunaAnimateCircleSpreadable(true);
                    tunaAnimateTransfer01.setTunaAnimationable(false);
                    tunaAnimateTransfer02.setTunaAnimationable(false);
                }
            }
        });

        tunaAnimateCircle.setTunaSubAnimateEndListener(new TunaSubAnimateEndListener() {
            @Override
            public void tunaSubAnimateEnd(View v) {
                tunaAnimateCircle.setTunaAnimateCircleSpreadable(false);
                tunaAnimateTransfer01.setTunaAnimationable(true);
                tunaAnimateTransfer02.setTunaAnimationable(true);
            }
        });
    }
}
