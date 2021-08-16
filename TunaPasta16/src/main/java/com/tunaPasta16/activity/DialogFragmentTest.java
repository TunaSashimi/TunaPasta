package com.tunaPasta16.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tunaPasta16.R;
import com.tunaPasta16.view.GlobalInteractiveFragment;

public class DialogFragmentTest extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialogfragmenttest);

        GlobalInteractiveFragment globalInteractiveFragment = new GlobalInteractiveFragment();
        globalInteractiveFragment.show(getSupportFragmentManager(), "GlobalInteractiveFragment");
    }
}