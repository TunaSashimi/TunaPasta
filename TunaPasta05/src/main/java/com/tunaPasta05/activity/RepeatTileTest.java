package com.tunaPasta05.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.tunaPasta05.R;
import com.tunaPasta05.widget.ShakeButton;

public class RepeatTileTest extends Activity {

    private ShakeButton shakebtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeattitletest);

        shakebtn = findViewById(R.id.shakebtn);
        shakebtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RepeatTileTest.this, "自定义shakebutton,抖动事件不会被覆盖", Toast.LENGTH_SHORT).show();
            }
        });

    }
}