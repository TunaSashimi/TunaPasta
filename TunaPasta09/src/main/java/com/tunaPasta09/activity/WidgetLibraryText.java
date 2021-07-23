package com.tunaPasta09.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.tunaPasta09.R;

public class WidgetLibraryText extends Activity {
    private ProgressBar progressBar01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.widgetlibrarytext);

        progressBar01 = findViewById(R.id.progressBar01);
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i == 100) {
                        i = 0;
                    }
                    try {
                        progressBar01.setProgress(100 - i);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

}
