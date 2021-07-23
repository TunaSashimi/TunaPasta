package com.tunaPasta11.activity;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.tunaPasta11.R;
import com.tunaPasta11.tools.DLog;

public class DLogTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlogtest);

        TextView textview1 = findViewById(R.id.textview1);
        TextView textview2 = findViewById(R.id.textview2);

        boolean isdebug = (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
        DLog.setIsDebug(isdebug);

        textview1.setText("是否为debug模式==>" + isdebug);
        textview2.setText("debuggable不要在manifest中设置,用BuildConfig.DEBUG即可。BuildConfig.DEBUG的值默认是true，当你导入签名过后的apk时，BuildConfig.DEBUG会变成false。");
    }
}
