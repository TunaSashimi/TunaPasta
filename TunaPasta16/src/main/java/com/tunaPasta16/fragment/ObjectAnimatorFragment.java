package com.tunaPasta16.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.tunaPasta16.R;

public class ObjectAnimatorFragment extends Fragment {
    //
    ImageView image_add,
            img_angle_345, img_angle_285, img_angle_225, img_angle_165, img_angle_105;
    //
    int[] resourceArray = {R.drawable.head_woman_common, R.drawable.head_woman_common, R.drawable.head_man_common, R.drawable.head_boy_common, R.drawable.head_boy_common};
    int dialNum;

    //
    private static long DURATION = 1500;
    private long lastTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_objectanimator, container, false);

        //要跟XML里面的一致
        float radiusPX = dpToPx(75);

        image_add = constraintLayout.findViewById(R.id.image_add);
        img_angle_345 = constraintLayout.findViewById(R.id.img_angle_345);
        img_angle_285 = constraintLayout.findViewById(R.id.img_angle_285);
        img_angle_225 = constraintLayout.findViewById(R.id.img_angle_225);
        img_angle_165 = constraintLayout.findViewById(R.id.img_angle_165);
        img_angle_105 = constraintLayout.findViewById(R.id.img_angle_105);

        //
        image_add.setOnClickListener(v -> {
            if (System.currentTimeMillis() - lastTime < DURATION) {
                return;
            }

            lastTime = System.currentTimeMillis();
            dialNum++;
            //
            playAnimation(image_add, img_angle_345, radiusPX, resourceArray[(4 + dialNum) % 5], 255, -60, 1f, 1f, 0.4f, 0.4f, DURATION);
            playAnimation(image_add, img_angle_285, radiusPX, resourceArray[(3 + dialNum) % 5], 195, -60, 1f, 1.4f, 0.4f, 1f, DURATION);
            playAnimation(image_add, img_angle_225, radiusPX, resourceArray[(2 + dialNum) % 5], 135, -60, 1f, 0.7f, 1f, 0.4f, DURATION);
            playAnimation(image_add, img_angle_165, radiusPX, resourceArray[(1 + dialNum) % 5], 75, -60, 1f, 1f, 0.4f, 0.4f, DURATION);
            playAnimation(image_add, img_angle_105, radiusPX, resourceArray[(0 + dialNum) % 5], 15, -60, 1f, 1f, 0.4f, 0.4f, DURATION);
        });
        return constraintLayout;
    }

    private void playAnimation(ImageView centerView, ImageView targetView, float radiusPX, int resId
            , int startAngle, int sweepAngle, float startScale, float endScale, float startAlpha, float endAlpha, long duration) {
        //
        float centerX = centerView.getX() + centerView.getWidth() * 0.5f;
        float centerY = centerView.getY() + centerView.getHeight() * 0.5f;

        //
        float left = centerX - radiusPX - targetView.getWidth() * 0.5f;
        float top = centerY - radiusPX - targetView.getHeight() * 0.5f;
        float right = centerX + radiusPX - targetView.getWidth() * 0.5f;
        float bottom = centerY + radiusPX - targetView.getHeight() * 0.5f;

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
                    targetView.setImageResource(resId);
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
