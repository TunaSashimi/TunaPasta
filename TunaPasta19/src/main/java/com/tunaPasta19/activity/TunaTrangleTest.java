package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaView;
import com.tunaPasta19.tuna.TunaView.TunaTouchUpListener;

public class TunaTrangleTest extends Activity {

    private TunaView tunaViewButton, tunaViewDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunatrangletest);

        tunaViewButton = findViewById(R.id.tunaViewButton);
        tunaViewDialog = findViewById(R.id.tunaViewDialog);

        tunaViewButton.setTunaTouchUpListener(new TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                if ("TunaSashimi".equals(tunaViewDialog.getTunaTextValue())) {
                    tunaViewDialog.setTunaTextValue("金枪鱼刺身");
                } else {
                    tunaViewDialog.setTunaTextValue("TunaSashimi");
                }
            }
        });
    }
}
