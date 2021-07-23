package com.tunaPasta05.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tunaPasta05.tool.ConnectionHelp;

public class NetWorkStateTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ConnectionHelp.openGPSSettings(this)) {
            Toast.makeText(this, "装置已经开启GPS", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "装置未开启GPS", Toast.LENGTH_SHORT).show();
        }
    }
}
