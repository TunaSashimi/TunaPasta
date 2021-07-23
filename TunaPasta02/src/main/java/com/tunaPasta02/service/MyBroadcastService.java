package com.tunaPasta02.service;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
public class MyBroadcastService extends Service{
	private boolean isRunning=true;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		new Thread(){
			public void run(){
				System.out.println(isRunning);
				int i=0;
				while(isRunning){
					Intent it=new Intent();
					it.setAction("com.tunaPasta02.BROADCAST_01");
					it.putExtra("values", "hello:"+i);
					sendBroadcast(it);
					System.out.println("============成功广播消息:"+i);
					i++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		isRunning=false;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
}
