package com.tunaPasta02.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tunaPasta02.R;
import com.tunaPasta02.service.MyServiceA;

public class ServiceTest extends Activity {
    private Button startBtn, stopBtn;
    //	private Button bindBtn,unBindBtn;
//	private MyServiceA service;
    private boolean isBound = false;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.i("OUTPUT", "============= ServiceConnection onServiceConnected()");
//			service=((MyServiceA.MyBinder)binder).getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicetest);
        startBtn =  findViewById(R.id.start_service_btn);
        stopBtn =  findViewById(R.id.stop_service_btn);

        startBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                doStartService();
            }
        });
        stopBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                doStopService();
            }
        });
        doBindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnBindService();
    }

    private void doStartService() {
        Intent it = new Intent(this, MyServiceA.class);
        this.startService(it);
    }

    private void doStopService() {
        Intent it = new Intent(this, MyServiceA.class);
        this.stopService(it);
    }

    private void doBindService() {
        Intent it = new Intent(this, MyServiceA.class);
        this.bindService(it, conn, Context.BIND_AUTO_CREATE);
        //1, Class c=it.getServiceClass();
        //2, if( c 已经有实例 ){
        //      Service ser=c.的实例；
        //   } else if( flag ==Context.BIND_AUTO_CREATE){
        //      Service ser=c.newInstance();
        //   }
        //      ser.onCreate();
        //     IBinder binder= ser.onBind();
        //     conn.onServiceConnected(xx,binder);
    }

    private void doUnBindService() {
        if (isBound) {
            this.unbindService(conn);
            isBound = false;
        }
    }
}
