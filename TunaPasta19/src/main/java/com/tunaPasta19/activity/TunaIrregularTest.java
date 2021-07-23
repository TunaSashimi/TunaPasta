package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaIrregular;
import com.tunaPasta19.tuna.TunaView;

public class TunaIrregularTest extends Activity {

    private TunaIrregular tunaIrregular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunairregulartest);

        tunaIrregular = findViewById(R.id.tunaIrregular);

        tunaIrregular.setTunaTouchListener(new TunaView.TunaTouchListener() {
            @Override
            public void tunaTouch(View v) {
                tunaIrregular.setTunaIrregularCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaIrregular.getTunaTouchEventX());
            }
        });

        tunaIrregular.setTunaIrregularChangeListener(new TunaIrregular.TunaIrregularChangeListener() {
            @Override
            public void tunaIrregularChange(boolean b) {
                Toast.makeText(getApplication(), "Change==>" + b, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
