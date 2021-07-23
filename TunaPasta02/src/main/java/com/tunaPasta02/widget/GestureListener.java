package com.tunaPasta02.widget;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public  class GestureListener extends SimpleOnGestureListener implements OnTouchListener {  
    /** 左右滑动的最短距离 */  
    private int distance = 100;  
    /** 左右滑动的最大速度 */  
    private int velocity = 200;  
      
    private GestureDetector gestureDetector;  
      
    public GestureListener(Context context) {  
        super();  
        gestureDetector = new GestureDetector(context, this);  
    }  
    /** 
     * 向左滑的时候调用的方法，子类应该重写 
     * @return 
     */  
    public boolean left() {  
        return false;  
    }  
    /** 
     * 向右滑的时候调用的方法，子类应该重写 
     * @return 
     */  
    public boolean right() {  
        return false;  
    }  
      
    @Override  
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
            float velocityY) {  
        // e1：第1个ACTION_DOWN MotionEvent  
        // e2：最后一个ACTION_MOVE MotionEvent  
        // velocityX：X轴上的移动速度（像素/秒）  
        // velocityY：Y轴上的移动速度（像素/秒）  
        // 向左滑  
        if (e1.getX() - e2.getX() > distance  
                && Math.abs(velocityX) > velocity) {  
            left();  
        }  
        // 向右滑  
        if (e2.getX() - e1.getX() > distance && Math.abs(velocityX) > velocity) {  
            right();  
        }  
        return false;  
    }  
  
    @Override  
    public boolean onTouch(View v, MotionEvent event) {  
        gestureDetector.onTouchEvent(event);  
        return false;  
    }  
  
    public int getDistance() {  
        return distance;  
    }  
  
    public void setDistance(int distance) {  
        this.distance = distance;  
    }  
  
    public int getVelocity() {  
        return velocity;  
    }  
  
    public void setVelocity(int velocity) {  
        this.velocity = velocity;  
    }  
  
    public GestureDetector getGestureDetector() {  
        return gestureDetector;  
    }  
  
    public void setGestureDetector(GestureDetector gestureDetector) {  
        this.gestureDetector = gestureDetector;  
    }  
}  