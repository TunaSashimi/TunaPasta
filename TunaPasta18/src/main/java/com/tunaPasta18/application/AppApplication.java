package com.tunaPasta18.application;


import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.tunaPasta18.BuildConfig;

/**
 * @author Tunasashimi
 * @date 2018/8/20 17:20
 * @Copyright 2018 TunaSashimi. All rights reserved.
 * @Description
 */
public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        //
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

    }
}
