package com.tunaPasta15.activity;


import java.util.ArrayList;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tunaPasta15.R;
import com.tunaPasta15.entity.ShapeHolder;

/**
 * This application demonstrates loading Animator objects from XML resources.
 */
public class AnimationLoading extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_loading);
        LinearLayout container = findViewById(R.id.container);
        final MyAnimationView animView = new MyAnimationView(this);
        container.addView(animView);

        Button starter = findViewById(R.id.startButton);
        starter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                animView.startAnimation();
            }
        });
    }

    public class MyAnimationView extends View implements
            ValueAnimator.AnimatorUpdateListener {

        private static final float BALL_SIZE = 100f;

        public final ArrayList<ShapeHolder> balls = new ArrayList();
        Animator animation = null;

        public MyAnimationView(Context context) {
            super(context);
            addBall(50, 50);
            addBall(200, 50);
            addBall(350, 50);
            addBall(500, 50, Color.GREEN);
        }

        private void createAnimation() {
            Context appContext = AnimationLoading.this;
            if (animation == null) {

            }
        }

        public void startAnimation() {
            createAnimation();
            if (animation != null) {
                animation.start();
            }
        }

        private ShapeHolder createBall(float x, float y) {
            OvalShape circle = new OvalShape();
            circle.resize(BALL_SIZE, BALL_SIZE);
            ShapeDrawable drawable = new ShapeDrawable(circle);
            ShapeHolder shapeHolder = new ShapeHolder(drawable);
            shapeHolder.setX(x);
            shapeHolder.setY(y);
            return shapeHolder;
        }

        private void addBall(float x, float y, int color) {
            ShapeHolder shapeHolder = createBall(x, y);
            shapeHolder.setColor(color);
            balls.add(shapeHolder);
        }

        private void addBall(float x, float y) {
            ShapeHolder shapeHolder = createBall(x, y);
            int red = (int) (100 + Math.random() * 155);
            int green = (int) (100 + Math.random() * 155);
            int blue = (int) (100 + Math.random() * 155);
            int color = 0xff000000 | red << 16 | green << 8 | blue;
            Paint paint = shapeHolder.getShape().getPaint();
            int darkColor = 0xff000000 | red / 4 << 16 | green / 4 << 8 | blue / 4;
            RadialGradient gradient = new RadialGradient(37.5f, 12.5f, 50f,
                    color, darkColor, Shader.TileMode.CLAMP);
            paint.setShader(gradient);
            balls.add(shapeHolder);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            for (ShapeHolder ball : balls) {
                canvas.translate(ball.getX(), ball.getY());
                ball.getShape().draw(canvas);
                canvas.translate(-ball.getX(), -ball.getY());
            }
        }

        public void onAnimationUpdate(ValueAnimator animation) {

            invalidate();
            ShapeHolder ball = balls.get(0);
            ball.setY((Float) animation.getAnimatedValue());
        }
    }
}