package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaView;
import com.tunaPasta19.tuna.TunaWrap;

import java.util.Arrays;

public class TunaWrapTest extends Activity {
    private TunaWrap tunaWrap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunawraptest);

        tunaWrap = findViewById(R.id.tunaWrap);
        tunaWrap.setTunaWrapItemTextValueArray(
            new String[]{
                " 用户要求换车 ", "车辆不整洁", "车辆设施不完备 ", "车辆损坏 "
            });

        tunaWrap.setTunaTouchUpListener(new TunaView.TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                tunaWrap.setTunaWrapCurrentXY(tunaWrap.getTunaTouchEventX(), tunaWrap.getTunaTouchEventY());
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), Arrays.toString(tunaWrap.getTunaWrapCurrentSelect()), Toast.LENGTH_LONG).show();
            }
        });
    }
}