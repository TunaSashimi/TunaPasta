package com.tunaPasta18.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.squareup.leakcanary.RefWatcher;
import com.tunaPasta18.R;
import com.tunaPasta18.util.CommentUtils;

import java.util.ArrayList;
import java.util.List;

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