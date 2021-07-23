package com.tunaPasta05.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta05.tool.ConnectionHelp;

public class DownLoadTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ConnectionHelp.netStatus(this)) {
            new UpdateHelper(this).checkUpdateInfo();
        }
        ConnectionHelp.getNetworkType(this);
    }
}