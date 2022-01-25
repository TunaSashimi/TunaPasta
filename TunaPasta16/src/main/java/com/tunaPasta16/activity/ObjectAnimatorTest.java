package com.tunaPasta16.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.tunaPasta16.R;
import com.tunaPasta16.application.DataTrans;
import com.tunaPasta16.view.FlingImageView;

import java.util.ArrayList;

public class ObjectAnimatorTest extends AppCompatActivity {
    private static int SWEEP_ANGLE = 60;
    private static long DURATION = 500;
    private long lastTime;

    //
    private int dialStart = DataTrans.dialStart;//默认转动次数
    private int dialCount = 0;//已经转动次数

    //
    ImageView image_add;
//    FlingImageView img_angle_345, img_angle_285, img_angle_225, img_angle_165, img_angle_105, img_angle_045;
    FlingImageView.ClockListener clockListener;

    //
    private FlingImageView[] imageArray = new FlingImageView[6];

    private int[] resourceArray =
            {
                    R.drawable.head_man_common,
                    R.drawable.head_woman_common,
                    R.drawable.head_boy_common,
                    R.drawable.head_girl_common,
            };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.objectanimatortest);
        //
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option =
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //导航栏
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //状态栏
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //
        image_add = findViewById(R.id.image_add);
        imageArray[0] = findViewById(R.id.img_angle_345);
        imageArray[1] = findViewById(R.id.img_angle_285);
        imageArray[2] = findViewById(R.id.img_angle_225);
        imageArray[3] = findViewById(R.id.img_angle_165);
        imageArray[4] = findViewById(R.id.img_angle_105);
        imageArray[5] = findViewById(R.id.img_angle_045);

        //
        setValue(resourceArray);
    }

    private int getIndex(int count, int size) {
        int index = count % size;
        if (index < 0) {
            index += size;
        }
        return index;
    }

    //setValue 增加转动过后的保存恢复功能
    private void setValue(int[] intArray) {
        //2个,底下两个
        //3个,两边各补一个
        //4个以上,逆时针从上到下摆放,img_angle_045不需要设置

        if (intArray.length == 2) {
            //
            imageArray[2].setImageResource(intArray[getIndex(dialStart + 0, intArray.length)]);
            imageArray[3].setImageResource(intArray[getIndex(dialStart + 1, intArray.length)]);
        } else if (intArray.length == 3) {
            //
            imageArray[0].setImageResource(intArray[getIndex(dialStart - 1, intArray.length)]);
            imageArray[1].setImageResource(intArray[getIndex(dialStart + 0, intArray.length)]);
            imageArray[2].setImageResource(intArray[getIndex(dialStart + 1, intArray.length)]);
            imageArray[3].setImageResource(intArray[getIndex(dialStart + 2, intArray.length)]);
            imageArray[4].setImageResource(intArray[getIndex(dialStart + 3, intArray.length)]);
        } else if (intArray.length >= 4) {
            //
            imageArray[0].setImageResource(intArray[getIndex(dialStart + 0, intArray.length)]);
            imageArray[1].setImageResource(intArray[getIndex(dialStart + 1, intArray.length)]);
            imageArray[2].setImageResource(intArray[getIndex(dialStart + 2, intArray.length)]);
            imageArray[3].setImageResource(intArray[getIndex(dialStart + 3, intArray.length)]);
            imageArray[4].setImageResource(intArray[getIndex(dialStart + 4, intArray.length)]);
        }

        //
        clockListener = new FlingImageView.ClockListener() {
            @Override
            public void clockWise() {
                clockTurn(intArray, true, DURATION);
            }

            @Override
            public void clockWiseAnti() {
                clockTurn(intArray, false, DURATION);
            }
        };

        //
        if (intArray.length == 2) {
            imageArray[2].setClockListener(clockListener);
            imageArray[3].setClockListener(clockListener);
        } else {
            imageArray[0].setClockListener(clockListener);
            imageArray[1].setClockListener(clockListener);
            imageArray[2].setClockListener(clockListener);
            imageArray[3].setClockListener(clockListener);
            imageArray[4].setClockListener(clockListener);
            imageArray[5].setClockListener(clockListener);
        }
    }

    private void clockTurn(int[] intArray, boolean clockwise, long duration) {
        if (System.currentTimeMillis() - lastTime < duration + 100) {
            return;
        }
        lastTime = System.currentTimeMillis();
        if (intArray.length == 2) {
            if (dialCount % 2 == 0) {
                readyAnimation(image_add, imageArray[2], intArray, 135, clockwise, DURATION);
                readyAnimation(image_add, imageArray[3], intArray, 75, clockwise, DURATION);
            } else {
                readyAnimation(image_add, imageArray[2], intArray, 75, clockwise, DURATION);
                readyAnimation(image_add, imageArray[3], intArray, 135, clockwise, DURATION);
            }
        } else {
            readyAnimation(image_add, imageArray[0], intArray, 255, clockwise, duration);
            readyAnimation(image_add, imageArray[1], intArray, 195, clockwise, duration);
            readyAnimation(image_add, imageArray[2], intArray, 135, clockwise, duration);
            readyAnimation(image_add, imageArray[3], intArray, 75, clockwise, duration);
            readyAnimation(image_add, imageArray[4], intArray, 15, clockwise, duration);
            readyAnimation(image_add, imageArray[5], intArray, 315, clockwise, duration);
        }
        //顺时针为正
        if (clockwise) {
            dialCount++;
        } else {
            dialCount--;
        }
    }

    private void readyAnimation(View view, ImageView imageView, int[] intArray, int firstAngle, boolean clockwise, long duration) {
        float centerX = view.getX() + view.getWidth() * 0.5f;
        float centerY = view.getY() + view.getHeight() * 0.5f;
        float radiusPX = getResources().getDimension(R.dimen.clock_radius);
        int startAngle = getIndex(firstAngle + SWEEP_ANGLE * dialCount, 360);

        if (intArray.length == 2) {
            if (firstAngle == 135) {
                playAnimation(centerX, centerY, radiusPX, imageView, intArray, firstAngle, -SWEEP_ANGLE, 1f, 0.7f, 1f, 0.4f, duration, clockwise);
            } else {
                playAnimation(centerX, centerY, radiusPX, imageView, intArray, firstAngle, SWEEP_ANGLE, 0.7f, 1f, 0.4f, 1f, duration, clockwise);
            }
        } else {
            if (startAngle == 135) {
                playAnimation(centerX, centerY, radiusPX, imageView, intArray, startAngle, clockwise ? SWEEP_ANGLE : -SWEEP_ANGLE, 1f, 0.7f, 1f, 0.4f, duration, clockwise);
            } else if ((clockwise && startAngle == 75) || (!clockwise && startAngle == 195)) {
                playAnimation(centerX, centerY, radiusPX, imageView, intArray, startAngle, clockwise ? SWEEP_ANGLE : -SWEEP_ANGLE, 0.7f, 1f, 0.4f, 1f, duration, clockwise);
            } else {
                playAnimation(centerX, centerY, radiusPX, imageView, intArray, startAngle, clockwise ? SWEEP_ANGLE : -SWEEP_ANGLE, 0.7f, 0.7f, 0.4f, 0.4f, duration, clockwise);
            }
        }
    }

    private void playAnimation(float centerX, float centerY, float radiusPX, ImageView imageView, int[] intArray,
                               int startAngle, int sweepAngle, float startScale, float endScale,
                               float startAlpha, float endAlpha, long duration, boolean clockwise) {
        //
        float left = centerX - radiusPX - imageView.getWidth() * 0.5f;
        float top = centerY - radiusPX - imageView.getHeight() * 0.5f;
        float right = centerX + radiusPX - imageView.getWidth() * 0.5f;
        float bottom = centerY + radiusPX - imageView.getHeight() * 0.5f;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //
            Path path = new Path();
            path.arcTo(left, top, right, bottom, startAngle, sweepAngle, true);
            ObjectAnimator animTranslation = ObjectAnimator.ofFloat(imageView, View.X, View.Y, path);
            ObjectAnimator animationAlpha = ObjectAnimator.ofFloat(imageView, View.ALPHA, startAlpha, endAlpha);
            ObjectAnimator animationScaleX = ObjectAnimator.ofFloat(imageView, View.SCALE_X, startScale, endScale);
            ObjectAnimator animationScaleY = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, startScale, endScale);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(duration);
            animatorSet.playTogether(animTranslation, animationAlpha, animationScaleX, animationScaleY);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    //为2的时候不需要设置背后的值
                    if (intArray.length != 2) {
                        if (startAngle == 315) {
                            if (intArray.length == 3) {
                                imageView.setImageResource(intArray[getIndex(clockwise ? dialStart + dialCount + 4 : dialStart + dialCount - 2, resourceArray.length)]);
                            } else if (intArray.length >= 4) {
                                imageView.setImageResource(intArray[getIndex(clockwise ? dialStart + dialCount + 5 : dialStart + dialCount - 1, resourceArray.length)]);
                            }
                        }
                    }
                }
            });
            animatorSet.start();
        } else {
            // Create animator without using curved path
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataTrans.dialStart = dialCount;
    }
}