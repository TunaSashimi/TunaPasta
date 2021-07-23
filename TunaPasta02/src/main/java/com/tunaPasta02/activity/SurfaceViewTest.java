package com.tunaPasta02.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import com.tunaPasta02.R;
import com.tunaPasta02.entity.Box;
import com.tunaPasta02.widget.CustomSurfaceView;

public class SurfaceViewTest extends Activity {
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private int ScreenHeight, ScreenWidth;
    private Box box;
    private CustomSurfaceView customSurfaceView;

    private SurfaceHolder surfaceHolder;

    private boolean flag, flag1, flag2;

    @Override
    public void finish() {
        flag = !flag;
        super.finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surfaceviewtest);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        ScreenWidth = display.getWidth();
        ScreenHeight = display.getHeight();

        box = new Box(0, 0, 10, 10);
        customSurfaceView = findViewById(R.id.customsurfaceview);
        customSurfaceView.setBox(box);
        customSurfaceView.draw();

        new Thread() {
            @Override
            public void run() {
                for (; !flag; ) {
                    try {
                        if (box.x < 0 || box.x >= ScreenWidth - box.width)
                            flag1 = !flag1;
                        if (box.y < 0 || box.y >= ScreenHeight - box.height)
                            flag2 = !flag2;
                        box.x += flag1 ? -10 : 10;
                        box.y += flag2 ? -10 : 10;
                        // 方法是写入缓存,所以可以在分线程中进行
                        customSurfaceView.draw();
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();

        //surfaceview的创建跟camera无关
        surfaceHolder = customSurfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                System.out.println("surfaceCreated==>");
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                System.out.println("surfaceChanged==>");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                System.out.println("surfaceDestroyed==>");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        box.width += 5;
        box.height += 5;
        customSurfaceView.draw();
        return super.onTouchEvent(event);
    }
}