package com.tunaPasta16.activity;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.PathInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tunaPasta16.R;

public class PathInterpolatorTest extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pathinterpolatortest);

        ImageView image_test = findViewById(R.id.image_test);
        image_test.setOnClickListener(v -> {

            //java.lang.IllegalArgumentException: The Path must start at (0,0) and end at (1,1)
//            ObjectAnimator animation = ObjectAnimator.ofFloat(image_test, "translationX", 100f);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                Path path = new Path();
//                path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
//                PathInterpolator pathInterpolator = new PathInterpolator(path);
//                animation.setInterpolator(pathInterpolator);
//                animation.start();
//            }
            /////
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Path path = new Path();
                path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
                ObjectAnimator animator = ObjectAnimator.ofFloat(image_test, View.X, View.Y, path);
                animator.setDuration(2000);
                animator.start();
            } else {
                // Create animator without using curved path
            }


        });
    }

//    private int dpToPx(int dp) {
//        return (int) TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()
//        );
//    }
}