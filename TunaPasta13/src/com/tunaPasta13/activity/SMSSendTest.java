package com.tunaPasta13.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tunaPasta13.R;

public class SMSSendTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.smssendtest);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                Uri smsToUri = Uri.parse("smsto:");
                String shareContent = "shareContent";
                int requestCode = 2;

                // Android 4.4 and up
                if (Build.VERSION.SDK_INT >= 19) {
                    String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(SMSSendTest.this);
                    Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
                    intent.putExtra("sms_body", shareContent);
                    // Can be null in case that there is no default, then
                    // the user would be able to choose any app that
                    // supports this intent.
                    if (defaultSmsPackageName != null) {
                        intent.setPackage(defaultSmsPackageName);
                        startActivityForResult(intent, requestCode);
                    }
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, smsToUri);
                    intent.setType("vnd.android-dir/mms-sms");
                    intent.putExtra("sms_body", shareContent);
                    intent.putExtra("exit_on_sent", true);
                    startActivityForResult(intent, requestCode);
                }

            }
        });
    }

}
