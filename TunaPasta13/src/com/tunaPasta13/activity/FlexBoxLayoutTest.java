package com.tunaPasta13.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.tunaPasta13.R;

public class FlexBoxLayoutTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.flexboxlayouttest);

        Spinner spinner = findViewById(R.id.spinner);
        FlexboxLayout flexboxLayout = findViewById(R.id.flexboxLayout);

        String[] stringArray = getResources().getStringArray(R.array.flexWrapArray);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stringArray);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        flexboxLayout.setFlexWrap(FlexWrap.NOWRAP);
                        break;
                    case 1:
                        flexboxLayout.setFlexWrap(FlexWrap.WRAP);
                        break;
                    case 2:
                        flexboxLayout.setFlexWrap(FlexWrap.WRAP_REVERSE);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
