package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaLine;
import com.tunaPasta19.tuna.TunaView.TunaTouchListener;

public class TunaLineTest extends Activity {
    private TunaLine tunaLineAC, tunaLineMove;
    private Button buttonCenterAC, buttonHiddenAC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunalinetest);

        float tuna_button_width = getResources().getDimension(
                R.dimen.tuna_button_width);

        tunaLineAC = findViewById(R.id.tunaLineAC);
        tunaLineMove = findViewById(R.id.tunaLineMove);

        buttonCenterAC = findViewById(R.id.buttonCenterAC);
        buttonHiddenAC = findViewById(R.id.buttonHiddenAC);

        //
        tunaLineAC.setTunaLineCurrentX(TypedValue.COMPLEX_UNIT_PX, tuna_button_width);

        //
        buttonCenterAC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tunaLineAC.centerArrow();
            }
        });

        buttonHiddenAC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tunaLineAC.hideArrow();
            }
        });

        tunaLineMove.setTunaLineCurrentX(TypedValue.COMPLEX_UNIT_PX, tuna_button_width);

        tunaLineMove.setTunaTouchListener(new TunaTouchListener() {
            @Override
            public void tunaTouch(View v) {
                tunaLineMove.setTunaLineCurrentX(TypedValue.COMPLEX_UNIT_PX, tunaLineMove.getTunaTouchEventX());
            }
        });
    }
}
