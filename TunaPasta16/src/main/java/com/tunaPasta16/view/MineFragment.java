package com.tunaPasta16.view;

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

public class MineFragment extends Fragment {
    //
    ImageView image_add,
            head_top_common, head_boy_common, head_man_common, head_woman_common, head_bottom_common;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_mine, null);

        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_mine, container, false);

        //要跟XML里面的一致
        float radiusPX = dpToPx(75);

        image_add = constraintLayout.findViewById(R.id.image_add);
        head_top_common = constraintLayout.findViewById(R.id.head_top_common);
        head_boy_common = constraintLayout.findViewById(R.id.head_boy_common);
        head_man_common = constraintLayout.findViewById(R.id.head_man_common);
        head_woman_common = constraintLayout.findViewById(R.id.head_woman_common);
        head_bottom_common = constraintLayout.findViewById(R.id.head_bottom_common);

        //
        image_add.setOnClickListener(v -> {
            //
            playAnimation(image_add, head_top_common, radiusPX, R.drawable.head_boy_common, 255, -60, 1f, 1f, 0.4f, 0.4f, 2000);
            playAnimation(image_add, head_boy_common, radiusPX, R.drawable.head_boy_common, 195, -60, 1f, 1.4f, 0.4f, 1f, 2000);
            playAnimation(image_add, head_man_common, radiusPX, R.drawable.head_boy_common, 135, -60, 1f, 0.7f, 1f, 0.4f, 2000);
            playAnimation(image_add, head_woman_common, radiusPX, R.drawable.head_man_common, 75, -60, 1f, 1f, 0.4f, 0.4f, 2000);
            playAnimation(image_add, head_bottom_common, radiusPX, R.drawable.head_woman_common, 15, -60, 1f, 1f, 0.4f, 0.4f, 2000);
        });
        return constraintLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void playAnimation(ImageView centerView, ImageView targetView, float radiusPX, int resId
            , int startAngle, int sweepAngle, float startScale, float endScale, float startAlpha, float endAlpha, int duration) {

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
