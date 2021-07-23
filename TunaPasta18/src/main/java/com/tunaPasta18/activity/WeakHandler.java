package com.tunaPasta18.activity;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @author Tunasashimi
 * @date 2018/9/4 14:53
 * @Copyright 2018 TunaSashimi. All rights reserved.
 * @Description
 */
public  class WeakHandler extends Handler {
    private final WeakReference<HandlerActivityTest> activty;

    public WeakHandler(HandlerActivityTest activity) {
        activty = new WeakReference(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        HandlerActivityTest activity = activty.get();
        super.handleMessage(msg);
        if (activity != null) {
            //
        }
    }
}
