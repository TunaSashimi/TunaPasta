package com.tunaPasta16.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class FlingImageView extends ImageView {
    private ClockListener clockListener;
    private GestureDetector gestureDetector;
    int MAJOR_MOVE = 20;

    public FlingImageView(Context context) {
        super(context);
        setGestureDetector(context);
    }

    public FlingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setGestureDetector(context);
    }

    public FlingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGestureDetector(context);
    }

    private void setGestureDetector(Context context) {
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                int dx = (int) (e2.getX() - e1.getX());
                int dy = (int) (e2.getY() - e1.getY());
                //降噪处理，必须有较大的动作才识别
                if (Math.abs(dx) > MAJOR_MOVE && Math.abs(velocityX) > Math.abs(velocityY)) {
                    if (velocityX > 0) {
                        if (clockListener != null) {
                            clockListener.clockWiseAnti();
                        }
                    } else {
                        if (clockListener != null) {
                            clockListener.clockWise();
                        }
                    }
                    return true;
                } else if (Math.abs(dy) > MAJOR_MOVE && Math.abs(velocityY) > Math.abs(velocityX)) {
                    if (velocityY > 0) {
                        if (clockListener != null) {
                            clockListener.clockWiseAnti();
                        }
                    } else {
                        if (clockListener != null) {
                            clockListener.clockWise();
                        }
                    }
                    return true;
                } else {
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

    public void setClockListener(ClockListener clockListener) {
        this.clockListener = clockListener;
    }

    public interface ClockListener {
        void clockWise();

        void clockWiseAnti();
    }
}