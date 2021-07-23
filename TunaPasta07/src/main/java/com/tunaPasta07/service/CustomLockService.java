package com.tunaPasta07.service;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.tunaPasta07.activity.CustomLockTest;

public class CustomLockService extends Service {

    private static String TAG = "ZdLockService";
    private Intent zdLockIntent = null;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    public void onCreate() {
        super.onCreate();


        zdLockIntent = new Intent(CustomLockService.this, CustomLockTest.class);
        zdLockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		/*注册广播*/
        IntentFilter mScreenOnFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        CustomLockService.this.registerReceiver(mScreenOnReceiver, mScreenOnFilter);
		
		/*注册广播*/
        IntentFilter mScreenOffFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        CustomLockService.this.registerReceiver(mScreenOffReceiver, mScreenOffFilter);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        return Service.START_STICKY;

    }

    public void onDestroy() {
        super.onDestroy();
        CustomLockService.this.unregisterReceiver(mScreenOnReceiver);
        CustomLockService.this.unregisterReceiver(mScreenOffReceiver);
        //再此重新启动
//        startService(new Intent(CustomLockService.this, CustomLockService.class));
    }

    private KeyguardManager mKeyguardManager = null;
    private KeyguardManager.KeyguardLock mKeyguardLock = null;
    //屏幕变亮的广播,我们要隐藏默认的锁屏界面
    private BroadcastReceiver mScreenOnReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i(TAG, intent.getAction());

            if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                Log.i(TAG, "----------------- android.intent.action.SCREEN_ON------");
//				mKeyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
//				mKeyguardLock = mKeyguardManager.newKeyguardLock("zdLock 1"); 
//				mKeyguardLock.disableKeyguard();
            }
        }

    };

    //屏幕变暗/变亮的广播 ， 我们要调用KeyguardManager类相应方法去解除屏幕锁定
    private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            Log.i(TAG, intent.toString());

            if (action.equals("android.intent.action.SCREEN_OFF")
                    || action.equals("android.intent.action.SCREEN_ON")) {
                mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
                mKeyguardLock = mKeyguardManager.newKeyguardLock("zdLock 1");
                mKeyguardLock.disableKeyguard();
                startActivity(zdLockIntent);
            }
        }

    };

}
