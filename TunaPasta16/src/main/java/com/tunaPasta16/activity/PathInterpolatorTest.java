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
    //
    ImageView image_add,
            head_top_common,head_boy_common,image_head_man_common,head_woman_common,head_bottom_common;

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

        //要跟XML里面的一致
        float radiusPX = dpToPx(75);

        image_add = findViewById(R.id.image_add);
        head_top_common = findViewById(R.id.head_top_common);
        head_boy_common = findViewById(R.id.head_boy_common);
        image_head_man_common = findViewById(R.id.image_head_man_common);
        head_woman_common = findViewById(R.id.head_woman_common);
        head_bottom_common = findViewById(R.id.head_bottom_common);

        //
        image_add.setOnClickListener(v -> {
            //
            playAnimation(image_add, head_top_common, radiusPX, 255, -60, 1f, 1f, 0.4f, 0.4f, 2000);
            playAnimation(image_add, head_boy_common, radiusPX, 195, -60, 1f, 1.4f, 0.4f, 1f, 2000);
            playAnimation(image_add, image_head_man_common, radiusPX, 135, -60, 1f, 0.7f, 1f, 0.4f, 2000);
            playAnimation(image_add, head_woman_common, radiusPX, 75, -60, 1f, 1f, 0.4f, 0.4f, 2000);
            playAnimation(image_add, head_bottom_common, radiusPX, 15, -60, 1f, 1f, 0.4f, 0.4f, 2000);
        });
    }

    //
    private void playAnimation(View centerView, View targetView, float radiusPX
            , int startAngle, int sweepAngle, float startScale, float endScale
            , float startAlpha, float endAlpha, int duration) {

        //
        float centerX = centerView.getX() + centerView.getWidth() * 0.5f;
        float centerY = centerView.getY() + centerView.getHeight() * 0.5f;

        //
        float left = centerX - radiusPX - targetView.getWidth() * 0.5f;
        float top = centerY - radiusPX - targetView.getHeight() * 0.5f;
        float right = centerX + radiusPX - targetView.getWidth() * 0.5f;
        float bottom = centerY + radiusPX - targetView.getHeight() * 0.5f;

        //大头像的矩形框
        System.out.println("left==>" + (left));
        System.out.println("top==>" + (top));
        System.out.println("right==>" + (right + targetView.getWidth()));
        System.out.println("bottom==>" + (bottom + targetView.getHeight()));

        float originalX = targetView.getX();
        float originalY = targetView.getY();
        float originalAlpha = targetView.getAlpha();
        float originalScaleX = targetView.getScaleX();
        float originalScaleY = targetView.getScaleY();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //
            Path path = new Path();
            path.arcTo(left, top, right, bottom, startAngle, sweepAngle, true);
            ObjectAnimator animTranslation = ObjectAnimator.ofFloat(targetView, View.X, View.Y, path);
            ObjectAnimator animationAlpha = ObjectAnimator.ofFloat(targetView, View.ALPHA, startAlpha, endAlpha);
            ObjectAnimator animationScaleX = ObjectAnimator.ofFloat(targetView, View.SCALE_X, startScale, endScale);
            ObjectAnimator animationScaleY = ObjectAnimator.ofFloat(targetView, View.SCALE_Y, startScale, endScale);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(duration);
            animatorSet.playTogether(animTranslation, animationAlpha, animationScaleX, animationScaleY);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (animatorSet != null) {
                        animatorSet.cancel();
                    }
                    targetView.setX(originalX);
                    targetView.setY(originalY);
                    targetView.setAlpha(originalAlpha);
                    targetView.setScaleX(originalScaleX);
                    targetView.setScaleY(originalScaleY);
                }
            });
            animatorSet.start();
        } else {
            // Create animator without using curved path
        }
    }

    public float dpToPx(float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()
        );
    }
}