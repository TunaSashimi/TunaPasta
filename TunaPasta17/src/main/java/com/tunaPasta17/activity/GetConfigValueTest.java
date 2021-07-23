package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.tunaPasta17.R;

public class GetConfigValueTest extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.getconfigvaluetest);

        TextView tv_configvalue = findViewById(R.id.tv_configvalue);

        NetworkOption mNetworkOption = new NetworkOption();
        tv_configvalue.setText("appId==>" + mNetworkOption.getAppId() + "\ngroupId==>" + mNetworkOption.getGroupId());
    }
}