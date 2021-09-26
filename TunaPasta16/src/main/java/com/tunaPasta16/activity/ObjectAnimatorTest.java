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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.tunaPasta16.R;
import com.tunaPasta16.view.FlingImageView;

public class ObjectAnimatorTest extends AppCompatActivity {
    private static int SWEEP_ANGLE = 60;
    private static long DURATION = 500;
    private long lastTime;
    //
    int dialCount;
    //
    int[] resourceArray =
            {
                    R.drawable.head_man_common,
                    R.drawable.head_woman_common,
//                    R.drawable.head_boy_common,
//                    R.drawable.head_boy_common,
            };
    //
    ImageView image_add;
    //
    FlingImageView img_angle_345, img_angle_285, img_angle_225, img_angle_165, img_angle_105, img_angle_045;
    //
    FlingImageView.ClockListener clockListener;

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
        img_angle_345 = findViewById(R.id.img_angle_345);
        img_angle_285 = findViewById(R.id.img_angle_285);
        img_angle_225 = findViewById(R.id.img_angle_225);
        img_angle_165 = findViewById(R.id.img_angle_165);
        img_angle_105 = findViewById(R.id.img_angle_105);
        img_angle_045 = findViewById(R.id.img_angle_045);

        Spinner spinner = findViewById(R.id.spinner);
        String[] stringArray = getResources().getStringArray(R.array.flexWrapArray);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stringArray);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
//                        flexboxLayout.setFlexWrap(FlexWrap.NOWRAP);
                        break;
                    case 1:
//                        flexboxLayout.setFlexWrap(FlexWrap.WRAP);
                        break;
                    case 2:
//                        flexboxLayout.setFlexWrap(FlexWrap.WRAP_REVERSE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        setValue(resourceArray);
    }

    private int getIndex(int count, int modulo) {
        int index = count % modulo;
        if (index < 0) {
            index += modulo;
        }
        return index;
    }

    private void setValue(int[] intArray) {
        //2个,底下两个
        //3个,两边各补一个
        //4个以上,逆时针从上到下摆放,img_angle_045不需要设置

        if (intArray.length == 2) {
            img_angle_225.setImageResource(intArray[0]);
            img_angle_165.setImageResource(intArray[1]);
        } else if (intArray.length == 3) {
            img_angle_345.setImageResource(intArray[getIndex(-1, intArray.length)]);
            img_angle_285.setImageResource(intArray[getIndex(0, intArray.length)]);
            img_angle_225.setImageResource(intArray[getIndex(1, intArray.length)]);
            img_angle_165.setImageResource(intArray[getIndex(2, intArray.length)]);
            img_angle_105.setImageResource(intArray[getIndex(3, intArray.length)]);
        } else if (intArray.length >= 4) {
            img_angle_345.setImageResource(intArray[getIndex(0, intArray.length)]);
            img_angle_285.setImageResource(intArray[getIndex(1, intArray.length)]);
            img_angle_225.setImageResource(intArray[getIndex(2, intArray.length)]);
            img_angle_165.setImageResource(intArray[getIndex(3, intArray.length)]);
            img_angle_105.setImageResource(intArray[getIndex(4, intArray.length)]);
        }

        //
        clockListener = new FlingImageView.ClockListener() {
            @Override
            public void clockWise() {
                clockTurn(intArray, true);
            }

            @Override
            public void clockWiseAnti() {
                clockTurn(intArray, false);
            }
        };

        //
        if (intArray.length == 2) {
            img_angle_225.setClockListener(clockListener);
            img_angle_165.setClockListener(clockListener);
        } else {
            img_angle_345.setClockListener(clockListener);
            img_angle_285.setClockListener(clockListener);
            img_angle_225.setClockListener(clockListener);
            img_angle_165.setClockListener(clockListener);
            img_angle_105.setClockListener(clockListener);
            img_angle_045.setClockListener(clockListener);
        }
    }

    private void clockTurn(int[] intArray, boolean clockwise) {
        if (System.currentTimeMillis() - lastTime < DURATION + 100) {
            return;
        }
        lastTime = System.currentTimeMillis();

        if (intArray.length == 2) {
            if (dialCount % 2 == 0) {
                readyAnimation(image_add, img_angle_225, intArray, 135, clockwise);
                readyAnimation(image_add, img_angle_165, intArray, 75, clockwise);
            } else {
                readyAnimation(image_add, img_angle_225, intArray, 75, clockwise);
                readyAnimation(image_add, img_angle_165, intArray, 135, clockwise);
            }
        } else {
            readyAnimation(image_add, img_angle_345, intArray, 255, clockwise);
            readyAnimation(image_add, img_angle_285, intArray, 195, clockwise);
            readyAnimation(image_add, img_angle_225, intArray, 135, clockwise);
            readyAnimation(image_add, img_angle_165, intArray, 75, clockwise);
            readyAnimation(image_add, img_angle_105, intArray, 15, clockwise);
            readyAnimation(image_add, img_angle_045, intArray, -45, clockwise);
        }

        if (clockwise) {
            dialCount++;
        } else {
            dialCount--;
        }
    }

    private void readyAnimation(View view, ImageView imageView, int[] intArray, int firstAngle, boolean clockwise) {
        float centerX = view.getX() + view.getWidth() * 0.5f;
        float centerY = view.getY() + view.getHeight() * 0.5f;
        float radiusPX = getResources().getDimension(R.dimen.clock_radius);
        int startAngle = getIndex(firstAngle + SWEEP_ANGLE * dialCount, 360);

        if (intArray.length == 2) {
            if (firstAngle == 135) {
                playAnimation(centerX, centerY, radiusPX, imageView, intArray, firstAngle, -SWEEP_ANGLE, 1f, 0.7f, 1f, 0.4f, DURATION, clockwise);
            } else {
                playAnimation(centerX, centerY, radiusPX, imageView, intArray, firstAngle, SWEEP_ANGLE, 0.7f, 1f, 0.4f, 1f, DURATION, clockwise);
            }
        } else {
            if (startAngle == 135) {
                playAnimation(centerX, centerY, radiusPX, imageView, intArray, startAngle, clockwise ? SWEEP_ANGLE : -SWEEP_ANGLE, 1f, 0.7f, 1f, 0.4f, DURATION, clockwise);
            } else if ((clockwise && startAngle == 75) || (!clockwise && startAngle == 195)) {
                playAnimation(centerX, centerY, radiusPX, imageView, intArray, startAngle, clockwise ? SWEEP_ANGLE : -SWEEP_ANGLE, 0.7f, 1f, 0.4f, 1f, DURATION, clockwise);
            } else {
                playAnimation(centerX, centerY, radiusPX, imageView, intArray, startAngle, clockwise ? SWEEP_ANGLE : -SWEEP_ANGLE, 0.7f, 0.7f, 0.4f, 0.4f, DURATION, clockwise);
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

                    if (intArray.length == 2) {

                    } else {
                        if (startAngle == 315) {
                            if (intArray.length == 3) {
                                imageView.setImageResource(intArray[getIndex(clockwise ? 4 + dialCount : -2 + dialCount, resourceArray.length)]);
                            } else if (intArray.length >= 4) {
                                imageView.setImageResource(intArray[getIndex(clockwise ? 5 + dialCount : -1 + dialCount, resourceArray.length)]);
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
}