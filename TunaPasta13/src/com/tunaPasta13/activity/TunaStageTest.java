package com.tunaPasta13.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import com.tunaPasta13.R;
import com.tunaPasta13.adapter.TunaResourceImageAdapter;
import com.tunaPasta13.widget.TunaStage;

public class TunaStageTest extends Activity {
    private TunaStage tunaStage;
    private TextView textView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunastagereflectiontest);

        textView =  findViewById(R.id.statusText);

        tunaStage = findViewById(R.id.tunaStage);

        tunaStage.setAdapter(new TunaResourceImageAdapter(this));
//    tunaStage.setSelection(2, true);

        tunaStage.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText("Item clicked! : " + id);
            }
        });
        tunaStage.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText("Item selected! : " + id);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                textView.setText("Nothing clicked!");
            }
        });
    }


}
