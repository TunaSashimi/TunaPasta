package com.tunaPasta16.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.tunaPasta16.R;

public class ObjectAnimatorTest extends AppCompatActivity {
    //要跟XML里面的一致
    private static int RADIUS_PX = 75;
    private static int SWEEP_ANGLE = -60;
    private static long DURATION = 1500;
    private long lastTime;
    //
    int dialCount;

    //
    ImageView image_add,
            img_angle_345, img_angle_285, img_angle_225, img_angle_165, img_angle_105, img_angle_045;
    //
    int[] resourceArray =
            {R.drawable.head_man_common, R.drawable.head_woman_common,
                    R.drawable.head_boy_common, R.drawable.head_boy_common};

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

        //img_angle_045不需要设置
        img_angle_345.setImageResource(resourceArray[getIndex(-1, resourceArray.length)]);
        img_angle_285.setImageResource(resourceArray[getIndex(0, resourceArray.length)]);
        img_angle_225.setImageResource(resourceArray[getIndex(1, resourceArray.length)]);
        img_angle_165.setImageResource(resourceArray[getIndex(2, resourceArray.length)]);
        img_angle_105.setImageResource(resourceArray[getIndex(3, resourceArray.length)]);

        //
        image_add.setOnClickListener(v -> {
            if (System.currentTimeMillis() - lastTime < DURATION + 100) {
                return;
            }
            lastTime = System.currentTimeMillis();

            //
//            readyAnimation(image_add, img_angle_345, getIndex(255 - 60 * dialCount, 360));
            readyAnimation(image_add, img_angle_285, getIndex(195 - 60 * dialCount, 360));
            readyAnimation(image_add, img_angle_225, getIndex(135 - 60 * dialCount, 360));
            readyAnimation(image_add, img_angle_165, getIndex(75 - 60 * dialCount, 360));
//            readyAnimation(image_add, img_angle_105, getIndex(15 - 60 * dialCount, 360));
//            readyAnimation(image_add, img_angle_045, getIndex(-45 - 60 * dialCount, 360));
            dialCount++;
        });
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()
        );
    }

    private int getIndex(int count, int modulo) {
        int index = count % modulo;
        if (index < 0) {
            index = index + modulo;
        }
        return index;
    }

    private void readyAnimation(View view, ImageView imageView, int startAngle) {
        float radiusPX = dpToPx(RADIUS_PX);
        float centerX = view.getX() + view.getWidth() * 0.5f;
        float centerY = view.getY() + view.getHeight() * 0.5f;

        if (startAngle == 195) {
            playAnimation(centerX, centerY, radiusPX, imageView, startAngle, SWEEP_ANGLE, 0.7f, 1f, 0.4f, 1f, DURATION);
        } else if (startAngle == 135) {
            playAnimation(centerX, centerY, radiusPX, imageView, startAngle, SWEEP_ANGLE, 1f, 0.7f, 1f, 0.4f, DURATION);
        } else {
            playAnimation(centerX, centerY, radiusPX, imageView, startAngle, SWEEP_ANGLE, 0.7f, 0.7f, 0.4f, 0.4f, DURATION);
        }
    }

    private void playAnimation(float centerX, float centerY, float radiusPX, ImageView imageView
            , int startAngle, int sweepAngle, float startScale, float endScale, float startAlpha, float endAlpha, long duration) {
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
            animatorSet.start();
        } else {
            // Create animator without using curved path
        }
    }
}