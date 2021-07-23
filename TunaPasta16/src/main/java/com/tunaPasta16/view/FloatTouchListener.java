package com.tunaPasta16.view;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;

import java.lang.reflect.Field;


public class FloatTouchListener implements View.OnTouchListener {
    //
    public static class Builder {
        private Activity activity;
        private int width = FrameLayout.LayoutParams.WRAP_CONTENT;
        private int height = FrameLayout.LayoutParams.WRAP_CONTENT;
        private int defaultTop = 0;
        private int defaultLeft = 0;
        private boolean needSuctEdge = false;
        private View view;

        public Builder setActivity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public Builder setSize(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder setLoacation(int left, int top) {
            this.defaultLeft = left;
            this.defaultTop = top;
            return this;
        }

        public Builder setNeedSuctEdge(boolean needSuctEdge) {
            this.needSuctEdge = needSuctEdge;
            return this;
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        public FloatTouchListener build() {
            return createDragView(this);
        }
    }

    private static FloatTouchListener createDragView(FloatTouchListener.Builder builder) {
        if (builder == null) {
            throw new NullPointerException("the param builder is null when execute method create");
        }
        if (builder.activity == null) {
            throw new NullPointerException("the activity is null");
        }
        if (builder.view == null) {
            throw new NullPointerException("the view is null");
        }
        FloatTouchListener dragTouchListener = new FloatTouchListener(builder);
        return dragTouchListener;
    }

    //
    private Builder mBuilder;
    private int mStatusBarHeight, mScreenWidth, mScreenHeight;

    //手指按下位置
    private int mStartX, mStartY, mLastX, mLastY;
    private boolean mTouchResult = false;

    private FloatTouchListener(FloatTouchListener.Builder builder) {
        mBuilder = builder;
        initDragView();
    }

    public View getDragView() {
        return mBuilder.view;
    }

    public Activity getActivity() {
        return mBuilder.activity;
    }

    public boolean getSuctEdge() {
        return mBuilder.needSuctEdge;
    }

    public void setNeedSuctEdge(boolean needNearEdge) {
        mBuilder.needSuctEdge = needNearEdge;
        if (mBuilder.needSuctEdge) {
            moveSuctEdge();
        }
    }

    private void initDragView() {
        if (getActivity() == null) {
            throw new NullPointerException("the activity is null");
        }
        if (mBuilder.view == null) {
            throw new NullPointerException("the view is null");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && mBuilder.activity.isDestroyed()) {
            return;
        }

        //屏幕宽高
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
            mScreenWidth = displayMetrics.widthPixels;
            mScreenHeight = displayMetrics.heightPixels;
        }

        //状态栏高度
        Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        mStatusBarHeight = rect.top;
        if (mStatusBarHeight <= 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                mStatusBarHeight = getActivity().getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //
        int left;
        //        int left = mBuilder.needSuctEdge ? 0 : mBuilder.defaultLeft;
        if (mBuilder.needSuctEdge) {
            if (mBuilder.defaultLeft + mBuilder.width / 2 >= mScreenWidth / 2) {
                left = mScreenWidth - mBuilder.width;
            } else {
                left = 0;
            }
        } else {
            left = mBuilder.defaultLeft;
        }

        FrameLayout.LayoutParams layoutParams = createLayoutParams(left, mStatusBarHeight + mBuilder.defaultTop, 0, 0);
        //在DecorView上面添加的DragView
        FrameLayout frameLayout = (FrameLayout) getActivity().getWindow().getDecorView();
        frameLayout.addView(getDragView(), layoutParams);
        getDragView().setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchResult = false;
                mStartX = mLastX = (int) event.getRawX();
                mStartY = mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int left, top, right, bottom;
                int dx = (int) event.getRawX() - mLastX;
                int dy = (int) event.getRawY() - mLastY;
                left = view.getLeft() + dx;
                if (left < 0) {
                    left = 0;
                }
                right = left + view.getWidth();
                if (right > mScreenWidth) {
                    right = mScreenWidth;
                    left = right - view.getWidth();
                }
                top = view.getTop() + dy;
                if (top < mStatusBarHeight + 2) {
                    top = mStatusBarHeight + 2;
                }
                bottom = top + view.getHeight();
                if (bottom > mScreenHeight) {
                    bottom = mScreenHeight;
                    top = bottom - view.getHeight();
                }
                view.layout(left, top, right, bottom);
                mLastX = (int) event.getRawX();
                mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                //这里需设置LayoutParams，不然按home后回再到页面等view会回到原来的地方
                view.setLayoutParams(createLayoutParams(view.getLeft(), view.getTop(), 0, 0));
                float endX = event.getRawX();
                float endY = event.getRawY();
                if (Math.abs(endX - mStartX) > 5 || Math.abs(endY - mStartY) > 5) {
                    //防止点击的时候稍微有点移动点击事件被拦截了
                    mTouchResult = true;
                }
                if (mTouchResult && mBuilder.needSuctEdge) {
                    //是否每次都移至屏幕边沿
                    moveSuctEdge();
                }
                break;
        }
        return mTouchResult;
    }

    /**
     * 移至最近的边沿
     */
    private void moveSuctEdge() {
        int left = getDragView().getLeft();
        int lastX;
        if (left + mBuilder.width / 2 <= mScreenWidth / 2) {
            lastX = 0;
        } else {
            lastX = mScreenWidth - mBuilder.width;
        }
        ValueAnimator valueAnimator = ValueAnimator.ofInt(left, lastX);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(0);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int left = (int) animation.getAnimatedValue();
                getDragView().setLayoutParams(createLayoutParams(left, getDragView().getTop(), 0, 0));
            }
        });
        valueAnimator.start();
    }

    private FrameLayout.LayoutParams createLayoutParams(int left, int top, int right, int bottom) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(mBuilder.width, mBuilder.height);
        layoutParams.setMargins(left, top, right, bottom);
        return layoutParams;
    }
}

