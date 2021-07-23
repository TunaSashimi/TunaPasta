package com.tunaPasta19.activity;

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

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaView;

import java.util.ArrayList;
import java.util.List;

public class SplashEffectTest extends Activity {
    public LinearLayout linearlayout;
    public List<ImageView> imageViewList;
    private Animation anm;
    private int marginsTop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = this.getResources().getDisplayMetrics();

        int height = dm.heightPixels;
        marginsTop = height - (int) TunaView.convertToPX(200, SplashEffectTest.this);
        anm = AnimationUtils.loadAnimation(this, R.anim.splasheffecttest_anim_set);
        linearlayout = new LinearLayout(this);
        linearlayout.setBackgroundResource(R.drawable.splasheffexttest_linearlayout_background);

        imageViewList = new ArrayList<ImageView>();
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
                    imageViewList.get(0).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.splasheffexttest_imageview_l));
                    imageViewList.get(0).startAnimation(anm);

                    break;
                case 1:
                    imageViewList.get(1).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.splasheffexttest_imageview_o));
                    imageViewList.get(1).startAnimation(anm);
                    break;
                case 2:
                    imageViewList.get(2).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.splasheffexttest_imageview_a));
                    imageViewList.get(2).startAnimation(anm);
                    break;
                case 3:
                    imageViewList.get(3).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.splasheffexttest_imageview_d));
                    imageViewList.get(3).startAnimation(anm);
                    break;
                case 4:
                    imageViewList.get(4).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.splasheffexttest_imageview_i));
                    imageViewList.get(4).startAnimation(anm);
                    break;
                case 5:
                    imageViewList.get(5).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.splasheffexttest_imageview_n));
                    imageViewList.get(5).setAnimation(anm);
                    break;
                case 6:
                    imageViewList.get(6).setImageDrawable(
                            SplashEffectTest.this.getResources().getDrawable(R.drawable.splasheffexttest_imageview_g));
                    imageViewList.get(6).setAnimation(anm);
                    break;
                case 99:
                    clearImage();
                    break;
            }
        }
    };

    private void clearImage() {
        for (ImageView image : imageViewList) {
            image.setImageDrawable(null);
            image.destroyDrawingCache();
        }
    }

    private void initImage(LinearLayout layout) {

        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        int unit = (int) TunaView.convertToPX(40, SplashEffectTest.this);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(unit, unit);
        param.setMargins((int) TunaView.convertToPX(30, SplashEffectTest.this), marginsTop, 0, 0);

        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(unit, unit);
        param2.setMargins((int) TunaView.convertToPX(-5, SplashEffectTest.this), marginsTop, 0, 0);

        ImageView l = new ImageView(this);
        l.setLayoutParams(param);
        layout.addView(l);
        imageViewList.add(l);

        ImageView o = new ImageView(this);
        o.setLayoutParams(param2);
        layout.addView(o);
        imageViewList.add(o);

        ImageView a = new ImageView(this);
        a.setLayoutParams(param2);
        layout.addView(a);
        imageViewList.add(a);

        ImageView d = new ImageView(this);
        d.setLayoutParams(param2);
        layout.addView(d);
        imageViewList.add(d);

        ImageView i = new ImageView(this);
        i.setLayoutParams(param2);
        layout.addView(i);
        imageViewList.add(i);

        ImageView n = new ImageView(this);
        n.setLayoutParams(param2);
        layout.addView(n);
        imageViewList.add(n);

        ImageView g = new ImageView(this);
        g.setLayoutParams(param2);
        layout.addView(g);
        imageViewList.add(g);
    }
}