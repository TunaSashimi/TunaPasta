package com.tunaPasta17.activity;

import android.os.Bundle;
import com.tunaPasta17.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarSelectTest extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isMult = true;

        if (isMult) {
            setContentView(R.layout.select_date_mult);
        } else {
            setContentView(R.layout.select_date_single);
        }
    }
}
