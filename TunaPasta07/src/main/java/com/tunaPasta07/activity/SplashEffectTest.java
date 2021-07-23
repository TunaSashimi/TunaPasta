package com.tunaPasta07.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tunaPasta07.R;

public class SplashEffectTest extends Activity {
    private Animation anm;
    private int marginsTop;
    public List<ImageView> images;
    public LinearLayout linearlayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = this.getResources().getDisplayMetrics();

        int height = dm.heightPixels;
        marginsTop = height - 100;
        anm = AnimationUtils.loadAnimation(this, R.anim.splasheffectanim);
        linearlayout = new LinearLayout(this);
        linearlayout.setBackgroundResource(R.drawable.background);

        images = new ArrayList();
        initImage(linearlayout);
        playAnimation();
        setContentView(linearlayout);
    }

    private void playAnimation() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int runcount = 0;
                while (true) {
                    if (runcount < 2) {
                        for (int i = 0; i <= 6; i++) {
                            handler.sendEmptyMessage(i);
                            try {
                                Thread.sleep(400);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        runcount++;
                    } else {
                        handler.sendEmptyMessage(99);
                        runcount = 0;
                    }
                }
            }
        }.start();
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    images.get(0).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.l));
                    images.get(0).startAnimation(anm);

                    break;
                case 1:
                    images.get(1).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.o));
                    images.get(1).startAnimation(anm);
                    break;
                case 2:
                    images.get(2).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.a));
                    images.get(2).startAnimation(anm);
                    break;
                case 3:
                    images.get(3).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.d));
                    images.get(3).startAnimation(anm);
                    break;
                case 4:
                    images.get(4).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.i));
                    images.get(4).startAnimation(anm);
                    break;
                case 5:
                    images.get(5).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.n));
                    images.get(5).setAnimation(anm);
                    break;
                case 6:
                    images.get(6).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.g));
                    images.get(6).setAnimation(anm);
                    break;
                case 99:
                    clearImage();
                    break;
            }
        }
    };

    private void clearImage() {
        for (ImageView image : images) {
            image.setImageDrawable(null);
            image.destroyDrawingCache();
        }
    }

    private void initImage(LinearLayout layout) {

        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(40, 40);
        param.setMargins(30, marginsTop, 0, 0);

        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(40, 40);
        param2.setMargins(-5, marginsTop, 0, 0);

        ImageView l = new ImageView(this);
        l.setLayoutParams(param);
        layout.addView(l);
        images.add(l);

        ImageView o = new ImageView(this);
        o.setLayoutParams(param2);
        layout.addView(o);
        images.add(o);

        ImageView a = new ImageView(this);
        a.setLayoutParams(param2);
        layout.addView(a);
        images.add(a);

        ImageView d = new ImageView(this);
        d.setLayoutParams(param2);
        layout.addView(d);
        images.add(d);

        ImageView i = new ImageView(this);
        i.setLayoutParams(param2);
        layout.addView(i);
        images.add(i);

        ImageView n = new ImageView(this);
        n.setLayoutParams(param2);
        layout.addView(n);
        images.add(n);

        ImageView g = new ImageView(this);
        g.setLayoutParams(param2);
        layout.addView(g);
        images.add(g);
    }
}