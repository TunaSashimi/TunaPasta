package com.tunaPasta16.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tunaPasta16.R;
import com.tunaPasta16.view.TSector;

public class TSectorTest extends Activity {
    TSector tSector;
    TextView tvProgress;
    Button btnAdd;
    Button btnSubstract;
    private int progress = 80;//进度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tsectortest);

        //
        tSector = findViewById(R.id.tSector);
        tvProgress = findViewById(R.id.tv_progress);

        tSector.setProgress(progress);//初始化进度
        tvProgress.setText(progress + "%");

        btnAdd = findViewById(R.id.bt_add);
        btnSubstract = findViewById(R.id.bt_subtract);

        //
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress += 1;
                if (progress > 99) {
                    progress = 100;
                }
                tSector.setProgress(progress);
                tvProgress.setText(progress + "%");

            }
        });

        //
        btnSubstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress -= 1;
                if (progress < 0) {
                    progress = 0;
                }
                tSector.setProgress(progress);
                tvProgress.setText(progress + "%");
            }
        });
    }
}
