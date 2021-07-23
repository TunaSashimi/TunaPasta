package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaButton;
import com.tunaPasta19.tuna.TunaView.TunaTouchUpListener;

public class TunaButtonTest extends Activity {

    private TunaButton tunaButton01, tunaButton02, tunaButton03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunabuttontest);

        tunaButton01 = findViewById(R.id.tunaButton01);
        tunaButton02 = findViewById(R.id.tunaButton02);
        tunaButton03 = findViewById(R.id.tunaButton03);

        tunaButton01.setTunaTouchUpListener(new TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                Toast.makeText(TunaButtonTest.this, tunaButton01.getTunaButtonTextValue(), Toast.LENGTH_SHORT).show();
            }
        });
        tunaButton02.setTunaTouchUpListener(new TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                Toast.makeText(TunaButtonTest.this, tunaButton02.getTunaButtonTextValue(), Toast.LENGTH_SHORT).show();
            }
        });
        tunaButton03.setTunaTouchUpListener(new TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                Toast.makeText(TunaButtonTest.this, tunaButton03.getTunaButtonTextValue(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
