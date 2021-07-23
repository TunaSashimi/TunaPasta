package com.tunaPasta18.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.tunaPasta18.R;

import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;

/**
 * @author Tunasashimi
 * @date 2018/9/4 13:45
 * @Copyright 2018 TunaSashimi. All rights reserved.
 * @Description
 */
public class HandlerActivityTest extends Activity {

    int[] data = new int[1024 * 1024 * 2];

    //
    public static class WeakHandler extends Handler {
        private final WeakReference<HandlerActivityTest> weakActivity;

        public WeakHandler(HandlerActivityTest activity) {
            weakActivity = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerActivityTest activity = weakActivity.get();
            super.handleMessage(msg);
            if (activity != null) {
                //
            }
        }
    }

    private static final Runnable staticRunnable = new Runnable() {
        @Override
        public void run() {
            //
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.handleractivitytest);

        WeakHandler weakHandler = new WeakHandler(this);

        //解决了内存泄漏,延迟5分钟后发送
        weakHandler.postDelayed(staticRunnable, 1000 * 60 * 5);


    }
}
