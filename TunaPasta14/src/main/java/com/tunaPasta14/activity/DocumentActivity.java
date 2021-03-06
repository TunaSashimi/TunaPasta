package com.tunaPasta14.activity;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tunaPasta14.R;

/**
 * @author Tunasashimi
 * @date 11/9/15 10:14
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class DocumentActivity extends Activity {
    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documenactivity);

        container = findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < container.getChildCount(); ++i) {
                    animateDrawables(container.getChildAt(i));
                }
            }
        });
        for (int i = 0; i < container.getChildCount(); ++i) {
            View child = container.getChildAt(i);
            if (child instanceof TextView) {
                child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i = 0; i < container.getChildCount(); ++i) {
                            animateDrawables(container.getChildAt(i));
                        }
                    }
                });
            }
        }
    }

    private void animateDrawables(View view) {
        if (!(view instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) view;
        for (final Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            }
        }
    }
}