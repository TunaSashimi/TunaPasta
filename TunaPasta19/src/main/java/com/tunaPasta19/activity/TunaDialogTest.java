package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaDialog;
import com.tunaPasta19.tuna.TunaView.TunaTouchListener;
import com.tunaPasta19.tuna.TunaView.TunaTouchUpListener;

public class TunaDialogTest extends Activity {

    private TunaDialog tunaDialogChoice, tunaDialogConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunadialogtest);

        tunaDialogChoice = findViewById(R.id.tunaDialogChoice);
        tunaDialogConfirm = findViewById(R.id.tunaDialogConfirm);

        tunaDialogChoice.setTunaTouchListener(new TunaTouchListener() {
            @Override
            public void tunaTouch(View v) {
                tunaDialogChoice.setTunaDialogCurrentXY(tunaDialogChoice.getTunaTouchEventX(), tunaDialogChoice.getTunaTouchEventY());
            }
        });

        tunaDialogChoice.setTunaTouchUpListener(new TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                String choiceTextValueIndex = tunaDialogChoice.getTunaDialogCurrentChoiceTextValue();
                if (choiceTextValueIndex != null) {
                    Toast.makeText(TunaDialogTest.this, choiceTextValueIndex, Toast.LENGTH_SHORT).show();
                }
                tunaDialogChoice.setTunaDialogChoiceCurrentIndex(-1);
            }
        });

        tunaDialogConfirm.setTunaTouchListener(new TunaTouchListener() {
            @Override
            public void tunaTouch(View v) {
                tunaDialogConfirm.setTunaDialogCurrentXY(tunaDialogConfirm.getTunaTouchEventX(), tunaDialogConfirm.getTunaTouchEventY());
            }
        });

        tunaDialogConfirm.setTunaTouchUpListener(new TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                String choiceTextValueIndex = tunaDialogConfirm.getTunaDialogCurrentChoiceTextValue();
                if (choiceTextValueIndex != null) {
                    Toast.makeText(TunaDialogTest.this, choiceTextValueIndex, Toast.LENGTH_SHORT).show();
                }
                tunaDialogConfirm.setTunaDialogChoiceCurrentIndex(-1);
            }
        });
    }
}
