package com.tunaPasta15.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import com.tunaPasta15.R;

public class TestViewUnitTest extends Activity {

    private TextView textviewSetPT, textviewSetPX, textviewSetDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.textviewunittest);

        textviewSetPT = findViewById(R.id.textviewSetPT);
        textviewSetPX = findViewById(R.id.textviewSetPX);
        textviewSetDP = findViewById(R.id.textviewSetDP);

        textviewSetPT.setTextSize(TypedValue.COMPLEX_UNIT_PT, 32);
        textviewSetPX.setTextSize(TypedValue.COMPLEX_UNIT_PX, 32);
        textviewSetDP.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 32);
    }
}
