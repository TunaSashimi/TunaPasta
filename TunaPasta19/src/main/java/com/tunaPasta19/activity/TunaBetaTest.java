package com.tunaPasta19.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaView;

public class TunaBetaTest extends Activity {
    private float translateY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunabetatest);

        final TunaView tunaView = findViewById(R.id.tunaView);
        final TextView textCollect = findViewById(R.id.textCollect);
        final ScrollView scrollView = findViewById(R.id.scrollView);

        translateY = TunaView.convertToPX(36, TunaBetaTest.this);

        tunaView.setTunaTouchUpListener(new TunaView.TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                ObjectAnimator visToInvisTranslationY = ObjectAnimator.ofFloat(textCollect, "translationY", 0, -translateY).setDuration(500);
                visToInvisTranslationY.setRepeatMode(ValueAnimator.REVERSE);
                visToInvisTranslationY.start();

                ObjectAnimator visToInvisTranslationYOther = ObjectAnimator.ofFloat(scrollView, "translationY", 0, -translateY).setDuration(500);
                visToInvisTranslationYOther.setRepeatMode(ValueAnimator.REVERSE);
                visToInvisTranslationYOther.start();
            }
        });
    }
}
