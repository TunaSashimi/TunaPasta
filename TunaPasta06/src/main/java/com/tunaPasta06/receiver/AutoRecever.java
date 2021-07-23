package com.tunaPasta06.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoRecever extends BroadcastReceiver {
	//android:installLocation="preferExternal"不能有,就是程序装在sd卡的不能接受广播
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
//			System.out.println("可以启动开机运行的服务和代码--->2");
//			Intent intents=new Intent(context,BootTest.class);
//			intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(intents);
		}
	}
}
