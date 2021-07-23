package com.tunaPasta02.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tunaPasta02.R;

public class TelephoneTest extends Activity {
    private EditText et01, et02;
    private Button btn01, btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telephoneservice);

        et01 =  findViewById(R.id.tel_ser_number);
        et02 =  findViewById(R.id.tel_ser_sms);
        btn01 =  findViewById(R.id.tel_ser_btn01);
        btn02 =  findViewById(R.id.tel_ser_btn02);

        btn01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:" + et01.getText().toString().trim());
                it.setData(uri);
                startActivity(it);
            }
        });

        btn02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = et01.getText().toString().trim();
                String smsContent = et02.getText().toString();
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(
                        phoneNumber,
                        null, smsContent,
                        null, null);
                Toast.makeText(TelephoneTest.this,
                        "成功发送短消息到" + phoneNumber + ":" + smsContent,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}
