package com.tunaPasta19.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.tunaPasta19.R;

public class HollowEffectTest extends Activity {
    private boolean started;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new OpenMoveView(this));
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                    if (!started) {
                        started = true;
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void finish() {
        started = true;
        super.finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            started = true;
            finish();
        }
        return super.onTouchEvent(event);
    }
}

class OpenMoveView extends View {
    // 记录背景位图的实际高度
    // 背景图片
    private Bitmap backfirst, backmiddle, backlast;
    private Bitmap font, back;

    private Bitmap targetBitmap;

    // 背景播放的起始位置
    private int startX, startY, width, height;
    private int screenWidth, screenHeight;

    public OpenMoveView(Context context) {
        super(context);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display mDisplay = wm.getDefaultDisplay();
        screenWidth = mDisplay.getWidth();
        screenHeight = mDisplay.getHeight();

        // 得到backfirst,并把backfirst图像压缩成~~屏幕宽度*802
        backfirst = BitmapFactory.decodeResource(context.getResources(), R.drawable.holloweffecttest_openmoveview_welcome0);
        backfirst = Bitmap.createScaledBitmap(backfirst, screenWidth, 802, false);
        // 得到backmiddle,并把backfirst图像压缩成~~屏幕宽度*802
        backmiddle = BitmapFactory.decodeResource(context.getResources(), R.drawable.holloweffecttest_openmoveview_welcome1);
        backmiddle = Bitmap.createScaledBitmap(backmiddle, screenWidth, 802, false);
        // 得到backlast,原图大小1920*952,要缩小,不然虚拟机内存溢出
        // 稍后解决~
        backlast = BitmapFactory.decodeResource(context.getResources(), R.drawable.holloweffecttest_openmoveview_welcome2);
        backlast = Bitmap.createScaledBitmap(backlast, screenWidth, screenHeight, false);

        font = BitmapFactory.decodeResource(context.getResources(), R.drawable.holloweffecttest_openmoveview_font);
        font = Bitmap.createScaledBitmap(font, screenWidth, screenHeight, false);

        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setStrokeWidth(30);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

        targetBitmap = Bitmap.createBitmap(font.getWidth(), font.getHeight(), Config.ARGB_8888);
        // 产生一个同样大小的画布
        Canvas tmpCanvas = new Canvas(targetBitmap);
        // 首先绘制下层图片(在设置setXfermode之前)
        tmpCanvas.drawText("TunaSashimi", 20, 420, paint);
        // SRC_OUT
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        // 在设置玩SRC_OUT之后绘制上层图片
        tmpCanvas.drawBitmap(font, 0, 0, paint);

        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    invalidate();
                }
            }
        };

        new Thread() {
            @Override
            public void run() {
                try {
                    // 确定图片源以及最后所要绘制的高度宽度
                    back = backfirst;
                    width = screenWidth;
                    height = (int) (screenHeight / 320.0 * 100);
                    for (startY = 802 - width; startY > 0; startY -= 6) {
                        Thread.sleep(20);
                        handler.sendEmptyMessage(0);
                    }
                    back = backmiddle;
                    width = screenWidth;
                    height = (int) (screenHeight / 320.0 * 100);
                    for (startY = 802 - width; startY > 0; startY -= 6) {
                        Thread.sleep(20);
                        handler.sendEmptyMessage(0);
                    }
                    // 确定图片源以及最后所要绘制的高度宽度
                    back = backlast;
                    width = screenWidth;
                    height = (int) (screenHeight / 320.0 * 100);
                    startX = 0;
                    startY = 0;
                    for (int i = 0; i <= 15; i++) {
                        Thread.sleep(100);
                        back = Bitmap.createScaledBitmap(back, screenHeight - 6 * i, screenWidth - 6 * i, false);
                        startX += 5;
                        startY += 5;
                        width -= 1;
                        height -= 2;
                        handler.sendEmptyMessage(0);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onDraw(Canvas canvas) {
        // 创建图片的大小,图片源,起始x坐标点,起始y坐标点,宽度,高度
        Bitmap bitmap = Bitmap.createBitmap(back, startX, startY, width, height);
        // 画图片的位置,距离左端的距离,距离上端的距离
        canvas.drawBitmap(bitmap, 0, 50, null);
        // 局部透明层
        canvas.drawBitmap(targetBitmap, 0, 0, null);
    }
}