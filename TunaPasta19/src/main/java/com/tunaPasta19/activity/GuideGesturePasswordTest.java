package com.tunaPasta19.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.tunaPasta19.R;
import com.tunaPasta19.application.DataTrans;

public class GuideGesturePasswordTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guidegesturepasswordtest);
        findViewById(R.id.gesturepwd_guide_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DataTrans.getInstance().getLockPatternUtils().clearLock();
                Intent intent = new Intent(GuideGesturePasswordTest.this, CreateGesturePasswordTest.class);
                // 打开新的Activity
                startActivity(intent);
                finish();
            }
        });
    }
}
