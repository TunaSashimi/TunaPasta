package com.tunaPasta15.activity;

import com.tunaPasta15.R;
import com.tunaPasta15.widget.ViewPropertyAnimator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewPropertyAnimatorTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.propertyanimationtest);

        final LinearLayout container = findViewById(R.id.container);

        final Button fadeOut = findViewById(R.id.fadeOut);
        final Button fadeIn = findViewById(R.id.fadeIn);
        final Button moveOver = findViewById(R.id.moveOver);
        final Button moveBack = findViewById(R.id.moveBack);
        final Button rotate = findViewById(R.id.rotate);
        final Button animatingButton = findViewById(R.id.animatingButton);

        // Set long default duration for the animator, for the purposes of this
        // demo
        ViewPropertyAnimator.animate(animatingButton).setDuration(2000);

        fadeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPropertyAnimator.animate(animatingButton).alpha(0);
            }
        });

        fadeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPropertyAnimator.animate(animatingButton).alpha(1);
            }
        });

        moveOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int xValue = container.getWidth() - animatingButton.getWidth();
                int yValue = container.getHeight()
                        - animatingButton.getHeight();
                ViewPropertyAnimator.animate(animatingButton).x(xValue)
                        .y(yValue);
            }
        });

        moveBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPropertyAnimator.animate(animatingButton).x(0).y(0);
            }
        });

        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPropertyAnimator.animate(animatingButton).rotationYBy(720);
            }
        });
    }
}