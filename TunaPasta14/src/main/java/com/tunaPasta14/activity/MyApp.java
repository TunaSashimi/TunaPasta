package com.tunaPasta14.activity;

import java.util.Locale;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

public class MyApp extends Application {

private BroadcastReceiver myReceiver = new BroadcastReceiver() {

    @Override
    public void onReceive(Context context, Intent intent) {
        String locale = Locale.getDefault().getCountry();
        Toast.makeText(context, "LOCALE CHANGED to " + locale,
                Toast.LENGTH_LONG).show();
    }
};

@Override
public void onCreate() {
    super.onCreate();
    IntentFilter filter = new IntentFilter(Intent.ACTION_LOCALE_CHANGED);
    registerReceiver(myReceiver, filter);
}

}
