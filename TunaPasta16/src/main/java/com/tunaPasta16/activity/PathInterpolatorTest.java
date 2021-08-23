package com.tunaPasta16.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.tunaPasta16.R;
import com.tunaPasta16.view.MeasureView;

public class PathInterpolatorTest extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pathinterpolatortest);

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
        float radiusPX = dpToPx(75);
        ImageView image_add1 = findViewById(R.id.image_add);
        image_add1.setOnClickListener(v -> {

            ImageView image_add = findViewById(R.id.image_add);
            float centerX = image_add.getX() + image_add.getWidth() / 2;
            float centerY = image_add.getY() + image_add.getHeight() / 2;

            float left = centerX - radiusPX;
            float top = centerY - radiusPX;
            float right = centerX + radiusPX;
            float bottom = centerY + radiusPX;

            System.out.println("left==>" + left);
            System.out.println("top==>" + top);
            System.out.println("right==>" + right);
            System.out.println("bottom==>" + bottom);

            ImageView image_test = findViewById(R.id.image_test);
//            playAnimation(image_head_man_common, left, top, right, bottom, 0, 60, 1f, 0.75f, 1, 0.4f, 3000);
//            playAnimation(image_head_man_common, 0, 0, 412.5f, 412.5f, 0, 345, 1f, 1f, 1, 1f, 10000);
            playAnimation(image_test, 0, 0, dpToPx(130), dpToPx(130), 0, 359, 1f, 1f, 1, 1f, 10000);
        });
    }

    //
    private void playAnimation(View view, float left, float top, float right, float bottom
            , int startAngle, int sweepAngle, float startScale, float endScale, float startAlpha, float endAlpha, int duration) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //
            Path path = new Path();
            path.arcTo(left, top, right, bottom, startAngle, sweepAngle, true);
            ObjectAnimator animTranslation = ObjectAnimator.ofFloat(view, View.X, View.Y, path);
            ObjectAnimator animationAlpha = ObjectAnimator.ofFloat(view, View.ALPHA, startAlpha, endAlpha);
            ObjectAnimator animationScaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, startScale, endScale);
            ObjectAnimator animationScaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, startScale, endScale);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(duration);
            animatorSet.playTogether(animTranslation, animationAlpha, animationScaleX, animationScaleY);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
//                    if (animatorSet != null) {
//                        animatorSet.cancel();
//                    }
//                    view.setX(0);
//                    view.setY(0);
//                    view.setAlpha(1f);
//                    view.setScaleX(1.0f);
//                    view.setScaleY(1.0f);
                }
            });
            animatorSet.start();
        } else {
            // Create animator without using curved path
        }
    }

    public  float dpToPx(int dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()
        );
    }
}