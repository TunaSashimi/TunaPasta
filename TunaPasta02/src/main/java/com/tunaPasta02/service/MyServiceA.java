package com.tunaPasta02.service;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.tunaPasta02.R;

public class MyServiceA extends Service{
	private boolean isRunning=true;
	private MediaPlayer player;
	
	/**
	 * 自定义的 绑定器
	 * @author teacher
	 *
	 */
	public class MyBinder extends Binder{
		public MyServiceA getService(){
			return MyServiceA.this;
		}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("OUTPUT", "============= Service onBind()");
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		Log.i("OUTPUT", "============= Service onCreate()");
		super.onCreate();
		player=MediaPlayer.create(this, R.raw.hetang);
		player.setLooping(true);
		player.start();
		new Thread(){
			public void run(){
				int i=0;
				while(isRunning){
					Log.i("OUTPUT", "服务运行中..."+i);
					i++;
					try {
						Thread.sleep(5000);
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
		player.stop();
		Log.i("OUTPUT", "============= Service onDestroy()");
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("OUTPUT", "============= Service onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("OUTPUT", "============= Service onUnbind()");
		return super.onUnbind(intent);
	}
	public void pause(){
		player.pause();
	}
	public void play(){
//		player.
	}
	

}
