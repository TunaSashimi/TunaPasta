package com.tunaPasta05.activity;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.tunaPasta05.R;

public class RotaryDialTest extends Activity {
    private int[] centerP = {R.drawable.c0, R.drawable.c1, R.drawable.c2,
            R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6};
    private int[] wordP = {R.drawable.over0, R.drawable.over1,
            R.drawable.over2, R.drawable.over3, R.drawable.over4,
            R.drawable.over5, R.drawable.over6};
    private ImageView ground, word, center;
    private int screenWidth, screenHeight;
    private float x, y, change, degree;// degree原有度数，change改变量，x和y为改变的初始值坐标

    // handler先不写，屏幕中心的点击事件先不写~
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotarydialtest);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();
        // 得到控件
        ground = findViewById(R.id.img_ground);
        word = findViewById(R.id.img_word);
        center = findViewById(R.id.img_center);
        // 初始化控件布局
        initImage(ground, R.drawable.circle, 16);
        initImage(word, wordP[0], 16);
        initImage(center, centerP[0], 11);
    }

    private void initImage(ImageView view, int id, int ratio) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
        bitmap = Bitmap.createScaledBitmap(bitmap, screenWidth * 8 / 10, screenWidth * 8 / 10, false);
        view.setImageBitmap(bitmap);
        view.setPadding((screenWidth - screenWidth * ratio / 20) / 2,
                (screenHeight - screenWidth * ratio / 20) / 2,
                (screenWidth - screenWidth * ratio / 20) / 2,
                (screenHeight - screenWidth * ratio / 20) / 2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // 点击的时候刚点下去确定改变初值
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            x = e.getX();
            y = e.getY();
            // 根据不同的区间来选择的方向
        } else if (e.getAction() == MotionEvent.ACTION_MOVE) {
            if (e.getX() > screenWidth * 0.5 && e.getY() < screenHeight * 0.5) {
                change = +(e.getX() - x) + (e.getY() - y);
            }
            if (e.getX() > screenWidth * 0.5 && e.getY() > screenHeight * 0.5) {
                change = -(e.getX() - x) + (e.getY() - y);
            }
            if (e.getX() < screenWidth * 0.5 && e.getY() > screenHeight * 0.5) {
                change = -(e.getX() - x) - (e.getY() - y);
            }
            if (e.getX() < screenWidth * 0.5 && e.getY() < screenHeight * 0.5) {
                change = +(e.getX() - x) - (e.getY() - y);
            }

            rotAni(change / 2.6, 0);
            x = e.getX();
            y = e.getY();
            // 以上为图像根据用户手纸旋转而旋转的动作
        } else if (e.getAction() == MotionEvent.ACTION_UP) {

            // 51.42为360处以7得出
            double rem = degree % 360 % 51.42;
            if (rem >= 25) {
                rotAni(52 - rem, 500);
            } else {
                rotAni(-rem, 500);
            }
        }
        return super.onTouchEvent(e);
    }

    private void rotAni(double value, long time) {
        Animation anim = new RotateAnimation(degree, (float) (degree + value),
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setDuration(time);
        // 改变完设置初始值的改变
        word.startAnimation(anim);
        degree += value;
        switch ((int) Math.round(((degree % 360) / 51.42))) {
            case -7:
            case 0:
            case 7:
                initImage(word, wordP[0], 16);
                initImage(center, centerP[0], 11);
                break;
            case -6:
            case 1:
                initImage(word, wordP[6], 16);
                initImage(center, centerP[6], 11);
                break;
            case -5:
            case 2:
                initImage(word, wordP[5], 16);
                initImage(center, centerP[5], 11);
                break;
            case -4:
            case 3:
                initImage(word, wordP[4], 16);
                initImage(center, centerP[4], 11);
                break;
            case -3:
            case 4:
                initImage(word, wordP[3], 16);
                initImage(center, centerP[3], 11);
                break;
            case -2:
            case 5:
                initImage(word, wordP[2], 16);
                initImage(center, centerP[2], 11);
                break;
            case -1:
            case 6:
                initImage(word, wordP[1], 16);
                initImage(center, centerP[1], 11);
                break;
            default:
                break;
        }
    }
}