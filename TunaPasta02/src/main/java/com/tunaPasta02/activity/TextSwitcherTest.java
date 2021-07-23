package com.tunaPasta02.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.tunaPasta02.R;
import com.tunaPasta02.widget.GestureListener;

public class TextSwitcherTest extends Activity {
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textswitchertest);

        //
        final String radioGroupTitleArray[] = {"金", "枪", "鱼"};
        final int imageSrcArray[] = {
                R.drawable.background1,
                R.drawable.background2,
                R.drawable.background3
        };

        //
        final Animation left_in = AnimationUtils.loadAnimation(this, R.anim.textswitcher_left_in_set);
        final Animation left_out = AnimationUtils.loadAnimation(this, R.anim.textswitcher_left_out_set);
        final Animation right_in = AnimationUtils.loadAnimation(this, R.anim.textswitcher_right_in_set);
        final Animation right_out = AnimationUtils.loadAnimation(this, R.anim.textswitcher_right_out_set);

        //
        final TextSwitcher textSwitcher = findViewById(R.id.textSwitcher);
        final ImageSwitcher imageSwitcher = findViewById(R.id.imageSwitcher);

        textSwitcher.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getApplicationContext());
                textView.setText(radioGroupTitleArray[index]);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                return textView;
            }
        });

        imageSwitcher.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(imageSrcArray[index]);
                return imageView;
            }
        });

        imageSwitcher.setLongClickable(true);
        imageSwitcher.setOnTouchListener(new GestureListener(getApplicationContext()) {
            @Override
            public boolean left() {
                //
                if (index < radioGroupTitleArray.length - 1) {
                    index += 1;

                    textSwitcher.setInAnimation(left_in);
                    textSwitcher.setOutAnimation(left_out);
                    imageSwitcher.setInAnimation(left_in);
                    imageSwitcher.setOutAnimation(left_out);

                    textSwitcher.setText(radioGroupTitleArray[index]);
                    imageSwitcher.setImageResource(imageSrcArray[index]);

                } else {
                    Toast.makeText(getApplicationContext(), "右滑动到底", Toast.LENGTH_SHORT).show();
                }
                return super.left();
            }

            @Override
            public boolean right() {
                //
                if (index > 0) {
                    index -= 1;

                    textSwitcher.setInAnimation(right_in);
                    textSwitcher.setOutAnimation(right_out);
                    imageSwitcher.setInAnimation(right_in);
                    imageSwitcher.setOutAnimation(right_out);

                    textSwitcher.setText(radioGroupTitleArray[index]);
                    imageSwitcher.setImageResource(imageSrcArray[index]);

                } else {
                    Toast.makeText(getApplicationContext(), "左滑动到底", Toast.LENGTH_SHORT).show();
                }
                return super.right();
            }
        });
    }
}