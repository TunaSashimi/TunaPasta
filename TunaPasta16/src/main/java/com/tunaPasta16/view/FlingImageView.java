package com.tunaPasta16.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class FlingImageView extends ImageView {
    private ClockListener clockListener;
    private float actionDownX, actionDownY;
    private static int THRESHOLD_DISTANCE = 10;

    public FlingImageView(Context context) {
        super(context);
    }

    public FlingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (clockListener == null) {
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionDownX = event.getX();
                actionDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (actionDownX == 0 || actionDownY == 0) {
                    return true;
                }
                float diffenceX = event.getX() - actionDownX;
                float diffenceY = event.getY() - actionDownY;

                if (Math.abs(diffenceX) > Math.abs(diffenceY) && Math.abs(diffenceX) > THRESHOLD_DISTANCE) {
                    if (diffenceX >= 0) {
                        clockListener.clockWiseAnti();
                    } else {
                        clockListener.clockWise();
                    }
                } else if (Math.abs(diffenceY) > Math.abs(diffenceX) && Math.abs(diffenceY) > THRESHOLD_DISTANCE) {
                    if (diffenceY >= 0) {
                        clockListener.clockWiseAnti();
                    } else {
                        clockListener.clockWise();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                actionDownX = 0f;
                actionDownY = 0f;
                break;
        }
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