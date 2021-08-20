package com.tunaPasta16.activity;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
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



            ObjectAnimator animation = ObjectAnimator.ofFloat(image_test, "translationX", 100f);
//            animation.setDuration(2000);

            // arcTo() and PathInterpolator only available on API 21+
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Path path = new Path();
                path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
                PathInterpolator pathInterpolator = new PathInterpolator(path);
                animation.setInterpolator(pathInterpolator);
                animation.start();
            }




//            System.out.println("==>"+dpToPx(150 - 30));

//            Animation animation = new TranslateAnimation(0, dpToPx(150 - 30), 0, dpToPx(150 - 30));
//            Animation animation = new TranslateAnimation(0, 330, 0,330);
//            Animation animation = new TranslateAnimation(0, 660, 0, 0);
//            animation.setDuration(5000);
//            animation.setFillAfter(true);
//
//            // arcTo() and PathInterpolator only available on API 21+
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                Path path = new Path();
//
//                path.moveTo(0, 0);
//
//
//                path.quadTo(1f, 1f, 1, 1);
//                PathInterpolator pathInterpolator = new PathInterpolator(path);
//                animation.setInterpolator(pathInterpolator);
//            }
//
//            image_test.startAnimation(animation);
        });
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()
        );
    }
}