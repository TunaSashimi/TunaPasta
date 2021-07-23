package com.tunaPasta17.activity;
import android.os.Bundle;

import com.tunaPasta17.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class DBValuesTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.db_values);
    }
}
