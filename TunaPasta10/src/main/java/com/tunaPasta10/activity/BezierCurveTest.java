package com.tunaPasta10.activity;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tunaPasta10.R;

public class BezierCurveTest extends Activity {
    private ValueAnimator valueAnimator;
    private int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beziercurvetest);
        width = 480;
        height = 480;

        final Button button = findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator.start();
            }
        });

        valueAnimator = ValueAnimator.ofObject(new BezierEvaluator(), new PointF(0, 0), new PointF(width, height));
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                button.setX(pointF.x);
                button.setY(pointF.y);
            }
        });
        valueAnimator.setTarget(button);
        valueAnimator.setRepeatCount(1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
    }

    @SuppressLint("NewApi")
    class BezierEvaluator implements TypeEvaluator<PointF> {

        @Override
        public PointF evaluate(float fraction, PointF startValue,
                               PointF endValue) {
            final float t = fraction;
            float oneMinusT = 1.0f - t;
            PointF point = new PointF();

            PointF point0 = startValue;

            PointF point1 = new PointF();
            point1.set(width, 0);

            PointF point2 = new PointF();
            point2.set(0, height);

            PointF point3 = endValue;

            point.x = oneMinusT * oneMinusT * oneMinusT * (point0.x)
                + 3 * oneMinusT * oneMinusT * t * (point1.x)
                + 3 * oneMinusT * t * t * (point2.x)
                + t * t * t * (point3.x);

            point.y = oneMinusT * oneMinusT * oneMinusT * (point0.y)
                + 3 * oneMinusT * oneMinusT * t * (point1.y)
                + 3 * oneMinusT * t * t * (point2.y)
                + t * t * t * (point3.y);
            return point;
        }
    }
}
