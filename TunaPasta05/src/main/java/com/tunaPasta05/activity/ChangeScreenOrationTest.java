package com.tunaPasta05.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tunaPasta05.R;

public class ChangeScreenOrationTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.changescreenorationtest);
        // 获取资源
        Button bt1 = findViewById(R.id.bt1);
        bt1.setOnClickListener(new Button.OnClickListener() {

//		需要在manifest中设置android:screenOrientation="portrait"

            @Override
            public void onClick(View v) {
                // 如果是竖排,则改为横排
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                // 如果是横排,则改为竖排
                else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });
    }
}
