package com.tunaPasta19.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.tunaPasta19.R;

public class DialLayoutTest extends Activity implements OnGestureListener, OnTouchListener {
    private int[] centerp = {R.drawable.diallayout_imageview_circle1, R.drawable.diallayout_imageview_circle2, R.drawable.diallayout_imageview_circle3,
            R.drawable.diallayout_imageview_circle4, R.drawable.diallayout_imageview_circle5, R.drawable.diallayout_imageview_circle6, R.drawable.diallayout_imageview_circle7};
    private int[] wordp = {R.drawable.diallayouttest_imageview_round1, R.drawable.diallayouttest_imageview_round2,
            R.drawable.diallayouttest_imageview_round3, R.drawable.diallayouttest_imageview_round4, R.drawable.diallayouttest_imageview_round5,
            R.drawable.diallayouttest_imageview_round6, R.drawable.diallayouttest_imageview_round7};
    private GestureDetector myGestureDetector;
    private FrameLayout frame;
    private ImageView ground, word, center;
    private int screenWidth, screenHeight;
    private int index;// 上方的位置索引
    private float degree;
    private Handler handler;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diallayouttest);
        // 获得屏幕的大小来设置各图片的大小,暂定为屏幕宽度的0.8倍
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();
        // 获取手势类，定义可接触属性
        myGestureDetector = new GestureDetector(this);
        frame = findViewById(R.id.main);
        frame.setLongClickable(true);
        frame.setOnTouchListener(this);

        ground = findViewById(R.id.img_ground);
        word = findViewById(R.id.img_word);
        center = findViewById(R.id.img_center);
        //修改的代码~
        index = 1;

        initImage(ground, R.drawable.diallayout_imageview_circle0, 16);
        initImage(word, wordp[index], 16);
        initImage(center, centerp[index], 11);

        //修改的代码~
        rotateImage(-51);

        handler = new android.os.Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                super.handleMessage(msg);
                initImage(center, centerp[index], 11);
                initImage(word, wordp[index], 16);
            }
        };
    }

    //ratio,即为20分之多少~
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
    public boolean onTouch(View arg0, MotionEvent e) {
        //如果点击点击以距离屏幕中心，屏幕高度的1/6 环绕的区域，则运行新的Activity
        if (e.getAction() == MotionEvent.ACTION_DOWN &&
                Math.pow((e.getX() - screenWidth * 0.5)
                        * (e.getX() - screenWidth * 0.5)
                        + (e.getY() - screenHeight * 0.5)
                        * (e.getY() - screenHeight * 0.5), 0.5) <= screenHeight / 6) {
            //			System.out.println(index);
            //	startActivity(new android.content.Intent(this, cc[index]));

            Toast.makeText(DialLayoutTest.this, "位置为" + index + "按钮被点击", Toast.LENGTH_LONG).show();

        }
        return myGestureDetector.onTouchEvent(e);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e0, MotionEvent e1, float arg2,
                           float arg3) {
        // 前一个直角三角形的角度减去后一个直角三角形的角度
        double d1 = Math.toDegrees(Math.atan((screenHeight * 0.5 - e0.getY()) / (e0.getX() - screenWidth * 0.5)));
        double d2 = Math.toDegrees(Math.atan((screenHeight * 0.5 - e1.getY()) / (e1.getX() - screenWidth * 0.5)));
        double d = d1 - d2;
        // 特例情况，如果用户横穿手机横向中轴的话，需要把d取反~
        if ((e0.getX() - screenWidth * 0.5) * (e1.getX() - screenWidth * 0.5) < 0) {
            d = -d;
        }
        // 横切面大于10度的时候开始旋转
        if (d > 10) {
            switch (index) {
                case 0:
                case 2:
                case 4:
                case 6:
                    rotateImage(51);
                    break;
                case 1:
                case 3:
                case 5:
                    rotateImage(52);
                    break;
                default:
                    break;
            }
            if (index > 0) {
                index--;
            } else {
                index = 6;
            }
        } else if (d < -10) {
            switch (index) {
                case 0:
                case 1:
                case 3:
                case 5:
                    rotateImage(-51);
                    break;
                case 2:
                case 4:
                case 6:
                    rotateImage(-52);
                    break;
                default:
                    break;
            }
            if (index < 6) {
                index++;
            } else {
                index = 0;
            }
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return false;
    }

    private void rotateImage(float num) {
        Animation anim = new RotateAnimation(degree, degree + num,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        anim.setDuration(500);
        anim.setFillAfter(true);
        word.startAnimation(anim);
        degree += num;
    }

    @Override
    public void onLongPress(MotionEvent arg0) {

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
                            float arg3) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        return false;
    }
}