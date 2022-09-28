package com.tunaPasta02.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tunaPasta02.R;
import com.tunaPasta02.service.BroadcastServiceDemo;

public class SendbroadcastTest extends Activity {
    private Button startBtn, stopBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendbroadcasttest);

        startBtn =  findViewById(R.id.start_btn);
        stopBtn =  findViewById(R.id.stop_btn);

        startBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                doStartService();
            }
        });

        stopBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                doStopService();
            }
        });
    }

    private void doStartService() {
        Intent it = new Intent(this, BroadcastServiceDemo.class);
        this.startService(it);
    }

    private void doStopService() {
        Intent it = new Intent(this, BroadcastServiceDemo.class);
        this.stopService(it);
    }
}