package com.tunaPasta05.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;

import com.tunaPasta05.R;
import com.tunaPasta05.widget.SurfaceChangeCloth;

public class SurfaceChangeClothTest extends Activity {
    private boolean flag;
    private SurfaceChangeCloth view;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        flag = false;
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surfacechangeclothtest);
        view =  findViewById(R.id.view);
        painting();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            view.setFlagdress(!view.isFlagdress());
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void finish() {
        flag = !flag;
        super.finish();
    }

    private void painting() {
        new Thread() {
            @Override
            public void run() {
                for (; !flag; ) {
                    try {
//                        view.draw();
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}