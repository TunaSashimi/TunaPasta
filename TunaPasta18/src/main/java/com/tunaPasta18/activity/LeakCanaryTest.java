package com.tunaPasta18.activity;

import android.app.Activity;
import android.os.Bundle;
import com.tunaPasta18.R;
import com.tunaPasta18.util.CommentUtils;

public class LeakCanaryTest extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.leakcanarytest);
        CommentUtils utils = CommentUtils.getUtils(this);

        LeakThread leakThread = new LeakThread();
        leakThread.start();
    }

    class LeakThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(6 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}