package com.tunaPasta18.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.tunaPasta18.R;

import androidx.annotation.Nullable;

public class LottieTest02 extends Activity {
    private Button button1, button2;
    private TextView tv_seek;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottietest02);

        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        tv_seek = findViewById(R.id.tv_seek);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnima();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAnima();
            }
        });
        lottieAnimationView.setAnimation("LottieLogo.json");
        lottieAnimationView.playAnimation();
        lottieAnimationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv_seek.setText(" 动画进度" + (int) (animation.getAnimatedFraction() * 100) + "%");
            }
        });
    }

    /*
     * 开始动画
     */
    private void startAnima() {
        boolean inPlaying = lottieAnimationView.isAnimating();
        if (!inPlaying) {
            lottieAnimationView.setProgress(0f);
            lottieAnimationView.playAnimation();
        }
    }

    /*
     * 停止动画
     */
    private void stopAnima() {
        boolean inPlaying = lottieAnimationView.isAnimating();
        if (inPlaying) {
            lottieAnimationView.cancelAnimation();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        lottieAnimationView.cancelAnimation();
    }
}
