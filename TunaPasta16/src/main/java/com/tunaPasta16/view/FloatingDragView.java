package com.tunaPasta16.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewConfigurationCompat;

/**
 * @author TunaSashimi
 * @date 2021/8/25 22:59
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
public class FloatingDragView extends androidx.appcompat.widget.AppCompatImageView {

    private int parentHeight;
    private int parentWidth;
    private int slop;

    public FloatingDragView(@NonNull Context context) {
        this(context, null);
    }

    public FloatingDragView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingDragView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        slop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }

    private int lastX;
    private int lastY;

    private boolean isDrag;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                setPressed(true);
                isDrag = false;
                getParent().requestDisallowInterceptTouchEvent(true);
                lastX = rawX;
                lastY = rawY;
                ViewGroup parent;
                if (getParent() != null) {
                    parent = (ViewGroup) getParent();
                    parentHeight = parent.getHeight();
                    parentWidth = parent.getWidth();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (parentHeight <= 0 || parentWidth <= 0) {
                    //??????????????????????????????????????????????????????????????????false
                    isDrag = false;
                    break;
                }
                int dx = rawX - lastX;
                int dy = rawY - lastY;
                //??????????????????????????????????????????????????????
                int distance = (int) Math.sqrt(dx * dx + dy * dy);
                //?????????????????????????????????????????????????????????????????????????????????????????????????????????
                if (distance == 0 || distance <= slop) {
                    isDrag = false;
                    break;
                }
                //??????????????????????????????????????????
                isDrag = true;
                float x = getX() + dx;
                float y = getY() + dy;
                //???????????????????????? ????????????
                x = x < 0 ? 0 : x > parentWidth - getWidth() ? parentWidth - getWidth() : x;
                y = getY() < 0 ? 0 : getY() + getHeight() > parentHeight ? parentHeight - getHeight() : y;
                setX(x);
                setY(y);
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                if (isDrag()) {
                    //??????????????????
                    setPressed(false);
                }
                welt(rawX);
                break;
        }
        //????????????????????????????????????????????????????????????
        return isDrag() || super.onTouchEvent(event);
    }

    private boolean isDrag() {
        return isDrag;
    }

    private boolean isLeftSide() {
        return getX() == 0;
    }

    private boolean isRightSide() {
        return getX() == parentWidth - getWidth();
    }

    private void welt(int currentX) {
        if (!isLeftSide() || !isRightSide()) {
            if (currentX >= parentWidth / 2) {
                //????????????
                animate().setInterpolator(new DecelerateInterpolator())
                        .setDuration(500)
                        .xBy(parentWidth - getWidth() - getX())
                        .start();
            } else {
                //????????????
                ObjectAnimator oa = ObjectAnimator.ofFloat(this, "x", getX(), 0);
                oa.setInterpolator(new DecelerateInterpolator());
                oa.setDuration(500);
                oa.start();
            }
        }
    }
}
