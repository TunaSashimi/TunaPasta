package com.tunaPasta03.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tunaPasta03.R;

public class BroadcastReceiverTest extends Activity {
    private Button startBtn, stopBtn;
    private TextView tv;
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcastreceivertest);
        startBtn =  findViewById(R.id.start_btn);
        stopBtn =  findViewById(R.id.stop_btn);
        tv =  findViewById(R.id.message_tv);
        receiver = new MyReceiver();
        startBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startReceiver();
            }
        });
        stopBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopReceiver();
            }
        });
    }

    private void startReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.tunaPasta02.BROADCAST_01");
        this.registerReceiver(receiver, filter);

        //注册短消息广播接收器代码
//		IntentFilter filter=new IntentFilter();
//		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
//		this.registerReceiver(receiver, filter);
    }

    private void stopReceiver() {
        this.unregisterReceiver(receiver);
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String str = "另一个接收器接受到的程序" + intent.getStringExtra("values");
            tv.append(str + "\n");
        }
    }
}
