package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.tunaPasta14.R;

public class WindowFocusChangedTest extends Activity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.windowfocuschangedtest);

        button = findViewById(R.id.button);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            Toast.makeText(getApplicationContext(), "ButtonWidth==>" + button.getWidth(), Toast.LENGTH_SHORT).show();
        }
    }

}
