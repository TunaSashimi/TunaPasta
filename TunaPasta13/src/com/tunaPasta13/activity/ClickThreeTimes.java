package com.tunaPasta13.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.tunaPasta13.R;

public class ClickThreeTimes extends Activity {

    int count = 0;
    long start;
    long end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.clickthreetimes);

        Button button01 = findViewById(R.id.button01);
        button01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                if (count == 1) {
                    start = System.currentTimeMillis();
                    System.out.println("====start====" + start);
                }
                if (count == 3) {
                    end = System.currentTimeMillis();
                    System.out.println("====end====" + end);
                }
                if (count >= 3) {
                    if ((end - start) < 700) {

                        Toast.makeText(ClickThreeTimes.this, "连点三次",
                                Toast.LENGTH_SHORT).show();

                    }
                    System.out.println("====end-start====" + (end - start));
                    count = 0;
                }
                if ((System.currentTimeMillis() - start) > 1000) {
                    count = 0;
                    start = System.currentTimeMillis();
                    System.out.println("====超过1000ms=====");
                }

            }
        });

    }

}
