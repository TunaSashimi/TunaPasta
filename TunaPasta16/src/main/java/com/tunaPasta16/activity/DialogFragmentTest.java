package com.tunaPasta16.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tunaPasta16.R;
import com.tunaPasta16.view.GlobalFragment;

public class DialogFragmentTest extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialogfragmenttest);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(v -> new GlobalFragment().show(getSupportFragmentManager(), "GlobalFragment"));
    }
}