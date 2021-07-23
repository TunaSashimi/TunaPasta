package com.tunaPasta07.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.tunaPasta07.activity.MessageSeeTest;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       // TODO
    	
    	String action="android.intent.action.MAIN";  
        String category="android.intent.category.LAUNCHER";
  		
  		Intent mBootIntent = new Intent(context, MessageSeeTest.class); 
  		mBootIntent.setAction(action);
  		mBootIntent.addCategory(category);  
        mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = intent.getExtras();
        Object messages[] = (Object[]) bundle.get("pdus");
        SmsMessage smsMessage[] = new SmsMessage[messages.length];
        for (int n = 0; n < messages.length; n++) {
                smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
       }
      // show first message
        mBootIntent.putExtra("mess", "Received SMS: " + smsMessage[0].getMessageBody());
        context.startActivity(mBootIntent);
    }

}  
