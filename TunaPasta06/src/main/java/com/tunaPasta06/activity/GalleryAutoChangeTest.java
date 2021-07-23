package com.tunaPasta06.activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tunaPasta06.R;
import com.tunaPasta06.adapter.GalleryAutoChangeTestAdapter;
import com.tunaPasta06.widget.GuideGallery;
public class GalleryAutoChangeTest extends Activity {
	public HashMap<String,Bitmap> imagesCache=new HashMap(); //图片缓存
	public List<String> urls ;
	public GuideGallery images_ga;
	private int positon = 0;
	private Thread timeThread = null;
	public boolean timeFlag = true;
	private boolean isExit = false;
	public ImageTimerTask timeTaks = null;
	 Timer autoGallery = new Timer();
	
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.galleryautochangetest);  
        timeTaks = new ImageTimerTask();
    	autoGallery.scheduleAtFixedRate(timeTaks, 5000, 5000);
    	timeThread = new Thread() {
            public void run() {
                while(!isExit) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (timeTaks) {
                    	if(!timeFlag){
                    		timeTaks.timeCondition = true;
                    		timeTaks.notifyAll();
                        }
                    }
                    timeFlag = true;
                }
            };
        };
        timeThread.start();
    }  
    
    @Override
    protected void onStart() {
    	super.onStart();
    	init();
    }
    
    private void init(){  
        Bitmap image= BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        imagesCache.put("background_non_load",image);       //设置缓存中默认的图片  
  
        images_ga =  findViewById(R.id.image_wall_gallery);
        images_ga.setImageActivity(this);
        urls = new ArrayList(); //图片地址List
        urls.add("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/E717B2523E5248308939B4536CB3A46A");  
        urls.add("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/CB4863FA11AD4F2890D7948FFFA8C1DC");  
        urls.add("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/92B3BBBE9EBD4BC6BEEC21651E49EF63");  
        urls.add("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/CE7FCBA104B3488685CC0CAA5FC5FEFE");  
        urls.add("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/EEFC5E0172E646D49C893D6309F41F3D");  
        urls.add("http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/25424AEF8F0D4A478617691D48F8FD82");  
        
        GalleryAutoChangeTestAdapter galleryimageAdapter = new GalleryAutoChangeTestAdapter(urls,this);  
        images_ga.setAdapter(galleryimageAdapter);  
        
        LinearLayout pointLinear =  findViewById(R.id.gallery_point_linear);
        pointLinear.setBackgroundColor(Color.argb(200, 135, 135, 152));
        for (int i = 0; i < urls.size(); i++) {
        	ImageView pointView = new ImageView(this);
        	if(i==0){
        		pointView.setBackgroundResource(R.drawable.feature_point_cur);
        	}else
        		pointView.setBackgroundResource(R.drawable.feature_point);
        	pointLinear.addView(pointView);
		}
    }  
    
    public void changePointView(int cur){
    	LinearLayout pointLinear =  findViewById(R.id.gallery_point_linear);
    	View view = pointLinear.getChildAt(positon);
    	View curView = pointLinear.getChildAt(cur);
    	if(view!=null&& curView!=null){
    		ImageView pointView = (ImageView)view;
    		ImageView curPointView = (ImageView)curView;
    		pointView.setBackgroundResource(R.drawable.feature_point);
    		curPointView.setBackgroundResource(R.drawable.feature_point_cur);
    		positon = cur;
    	}
    }
    
    final Handler autoGalleryHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                	images_ga.setSelection(message.getData().getInt("pos"));
                    break;
            }
            }
    }; 
    
    @Override
    protected void onResume() {
    	super.onResume();  
    	timeFlag = false;
    }
	
	@Override
	protected void onPause() {
		super.onPause();
		timeTaks.timeCondition = false;
	}
    
    public class ImageTimerTask extends TimerTask{
    	public volatile boolean timeCondition = true;
        int gallerypisition = 0;
        public void run() {
            synchronized (this) {
                while(!timeCondition) {
                    try {
                    	Thread.sleep(100);
                        wait();
                    } catch (InterruptedException e) {
                        Thread.interrupted();
                    }
                }
            }
            try {
            	gallerypisition = images_ga.getSelectedItemPosition() + 1;
                Message msg = new Message();
                Bundle date = new Bundle();// 存放数据
                date.putInt("pos", gallerypisition);
                msg.setData(date);
                msg.what = 1;//消息标识
                autoGalleryHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}