package com.tunaPasta01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tunaPasta01.R;

public class HandlerTest extends Activity {
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handlertest);
        Button startButton = findViewById(R.id.startButton);
        Button endButton = findViewById(R.id.endButton);
        handler = new Handler();
        //将要执行的操作写在线程对象的run方法当中
        runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("==>UpdateThread");
                //在run方法内部,执行postDelayed或者post方法
                handler.postDelayed(runnable, 4000);
            }
        };
        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用handler的post方法,将要执行的线程对象添加到队列当中
                handler.post(runnable);
            }
        });
        endButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
            }
        });
    }
}