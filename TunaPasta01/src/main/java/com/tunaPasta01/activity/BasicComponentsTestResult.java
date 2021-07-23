package com.tunaPasta01.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.tunaPasta01.R;

public class BasicComponentsTestResult extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basictestresult);
        TextView tv =  findViewById(R.id.firsttext);
        Intent it = this.getIntent();
        String values = it.getStringExtra("values");
        tv.setText(values);
    }
}
