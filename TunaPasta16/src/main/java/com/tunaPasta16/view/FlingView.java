package com.tunaPasta16.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class FlingView extends View {
    private GestureDetector gestureDetector;
    int MAJOR_MOVE = 20;

    public FlingView(Context context) {
        super(context);

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                int dx = (int) (e2.getX() - e1.getX()); //计算滑动的距离
                //降噪处理，必须有较大的动作才识别
                if (Math.abs(dx) > MAJOR_MOVE && Math.abs(velocityX) > Math.abs(velocityY)) {
                    if (velocityX > 0) {//向右边
                        System.out.println("==>向右边");
                    } else {//向左边
                        System.out.println("==>向左边");
                    }
                    return true;
                } else {
                    //当然可以处理velocityY处理向上和向下的动作
                    return false;
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }
}