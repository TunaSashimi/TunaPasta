package com.tunaPasta18.application;

import android.app.Application;
import androidx.multidex.BuildConfig;
import com.facebook.stetho.Stetho;

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
    }
}
