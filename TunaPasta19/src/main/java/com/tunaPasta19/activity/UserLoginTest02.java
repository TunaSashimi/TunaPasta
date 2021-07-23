package com.tunaPasta19.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tunaPasta19.R;

public class UserLoginTest02 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogintest02);

        WindowManager windowManager = this.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int wight = display.getWidth();

        RelativeLayout relative01 = findViewById(R.id.relative01);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (wight * 0.8), LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        relative01.setLayoutParams(params);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Toast.makeText(UserLoginTest02.this, getResources().getString(R.string.userlogin_login), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.imange_switch:
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                break;
            case R.id.image_set:
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                break;
        }
    }
}
