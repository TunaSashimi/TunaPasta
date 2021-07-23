package com.tunaPasta06.service;
 


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
  
public class BootService extends Service{   
 
    @Override  
    public IBinder onBind(Intent intent) {   
        return null;   
    }   
    @Override  
    public void onCreate() {   
        Log.i("TAG","BootService onCreate");   
         
           
        super.onCreate();   
    }   
    @Override  
    public void onStart(Intent intent, int startId) {   
        Log.i("TAG","BootService onStart");   
       
        super.onStart(intent, startId);   
    }   
}  