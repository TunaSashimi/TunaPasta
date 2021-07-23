package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.SeekBar;

import com.tunaPasta14.R;

public class SimulateClickEventTest extends Activity {
    boolean bool = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simulateclickeventtest);

        final SeekBar seekBar = findViewById(R.id.seekBar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (bool) {
                    Activity activity = SimulateClickEventTest.this;
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MotionEvent evenDownt = MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis() + 100, MotionEvent.ACTION_DOWN, 355.66f, 51.49f, 0);
                                seekBar.dispatchTouchEvent(evenDownt);
                                MotionEvent evenMove = MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis() + 100, MotionEvent.ACTION_MOVE, 355.66f, 51.49f, 0);
                                seekBar.dispatchTouchEvent(evenMove);
                                MotionEvent evenUp = MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis() + 100, MotionEvent.ACTION_UP, 355.66f, 51.49f, 0);
                                seekBar.dispatchTouchEvent(evenUp);
                            }
                        });
                    }
                    bool = false;
                }
            }
        }).start();
    }
}
