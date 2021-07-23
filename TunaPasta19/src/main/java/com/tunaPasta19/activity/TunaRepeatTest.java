package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaLine;
import com.tunaPasta19.tuna.TunaRepeat;
import com.tunaPasta19.tuna.TunaView;
import com.tunaPasta19.tuna.TunaView.TunaTouchDownListener;
import com.tunaPasta19.tuna.TunaView.TunaTouchListener;
import com.tunaPasta19.tuna.TunaView.TunaTouchType;
import com.tunaPasta19.tuna.TunaView.TunaTouchUpListener;

public class TunaRepeatTest extends Activity {
    private TunaLine tunaLine;
    private TunaRepeat tunaRepeatStar, tunaRepeatCar, tunaRepeatTips;

    private Button buttonEvaluation01;
    private Spinner spinnerEvaluation01;

    private String[]
        indexes = {"-1", "0", "1", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunarepeatbartest);

        tunaLine = findViewById(R.id.tunaLine);

        buttonEvaluation01 = findViewById(R.id.buttonEvaluation01);

        tunaRepeatStar = findViewById(R.id.tunaRepeatStar);
        tunaRepeatCar = findViewById(R.id.tunaRepeatCar);
        tunaRepeatTips = findViewById(R.id.tunaRepeatTips);

        tunaRepeatStar.setTunaRepeatListener(
            new TunaTouchListener() {
                @Override
                public void tunaTouch(View v) {
                    tunaRepeatStar.setTunaRepeatCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaRepeatStar.getTunaTouchEventX());
                    tunaRepeatCar.setTunaRepeatCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaRepeatStar.getTunaTouchEventX(), true);
                    tunaLine.setTunaLineCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaRepeatStar.getTunaTouchEventX());
                }
            },
            new TunaTouchDownListener() {
                @Override
                public void tunaTouchDown(View v) {
                    tunaRepeatCar.setTunaPress(true);
                }
            },
            new TunaTouchUpListener() {
                @Override
                public void tunaTouchUp(View v) {
                    afterChoice();
                    tunaRepeatCar.setTunaPress(false);
                }
            }
        );

        //
        tunaRepeatCar.setTunaRepeatTotal(indexes.length);
        tunaRepeatCar.setTunaRepeatItemTextValueArray(indexes);

        tunaRepeatCar.setTunaRepeatListener(
            new TunaTouchListener() {
                @Override
                public void tunaTouch(View v) {
                    tunaRepeatCar.setTunaRepeatCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaRepeatCar.getTunaTouchEventX());
                    tunaRepeatStar.setTunaRepeatCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaRepeatCar.getTunaTouchEventX(), true);
                    tunaLine.setTunaLineCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaRepeatCar.getTunaTouchEventX());
                }
            },
            new TunaTouchDownListener() {
                @Override
                public void tunaTouchDown(View v) {
                    tunaRepeatStar.setTunaPress(true);
                }
            },
            new TunaTouchUpListener() {
                @Override
                public void tunaTouchUp(View v) {
                    afterChoice();
                    tunaRepeatStar.setTunaPress(false);
                }
            }
        );

        buttonEvaluation01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("Unable".equals(buttonEvaluation01.getText().toString().trim())) {
                    tunaRepeatStar.setTunaTouchType(TunaTouchType.NONE);
                    tunaRepeatCar.setTunaTouchType(TunaTouchType.NONE);
                    buttonEvaluation01.setText("Enable");
                } else {
                    tunaRepeatStar.setTunaTouchType(TunaTouchType.EDGE);
                    tunaRepeatCar.setTunaTouchType(TunaTouchType.EDGE);
                    buttonEvaluation01.setText("Unable");
                }
            }
        });

        spinnerEvaluation01 = findViewById(R.id.spinnerEvaluation01);
        spinnerEvaluation01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, indexes));
        spinnerEvaluation01.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tunaRepeatStar.setTunaRepeatCurrentIndex(position - 1);
                tunaRepeatCar.setTunaRepeatCurrentIndex(position - 1);
                float tunaTouchEventX = tunaRepeatStar.getTunaTouchEventX();
                if (tunaTouchEventX != 0) {
                    tunaLine.setTunaLineCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaRepeatStar.getTunaTouchEventX());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        TunaView.setLayoutByWidth(tunaRepeatTips, 5 * 40);

        //
        tunaRepeatTips.setTunaRepeatListener(
            null,
            null,
            new TunaTouchUpListener() {
                @Override
                public void tunaTouchUp(View v) {
                    tunaRepeatTips.setTunaRepeatCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaRepeatTips.getTunaTouchEventX());
//						Toast.makeText(TunaRepeatTest.this, "TuochUp", Toast.LENGTH_SHORT).show();
                }
            });
    }

    //
    private void afterChoice() {
        int tunaRepeatStarCurrentIndex = tunaRepeatStar.getTunaRepeatCurrentIndex();
        tunaLine.setTunaLineCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaRepeatStar.getTunaRepeatCurrentX());
        if (tunaRepeatStarCurrentIndex == 0) {
        } else if (tunaRepeatStarCurrentIndex == 1) {
        } else if (tunaRepeatStarCurrentIndex == 2) {
        } else if (tunaRepeatStarCurrentIndex == 3) {
        } else if (tunaRepeatStarCurrentIndex == 4) {
        }
    }
}
