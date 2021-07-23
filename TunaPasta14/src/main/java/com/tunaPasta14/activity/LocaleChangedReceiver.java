package com.tunaPasta14.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LocaleChangedReceiver extends BroadcastReceiver{
	
	
	@Override
	public void onReceive(Context context, Intent intent){
		if (intent.getAction().compareTo(Intent.ACTION_LOCALE_CHANGED) == 0) {
			System.out.println("LocaleChangedRecevier");
		}
	}
}
