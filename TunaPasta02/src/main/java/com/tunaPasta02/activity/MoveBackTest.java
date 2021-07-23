package com.tunaPasta02.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.tunaPasta02.R;
import com.tunaPasta02.tools.NativeRequest;

public class MoveBackTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //宽度是关键
        int screenWidth = NativeRequest.getScreenWidth(this);
        int screenHeight = NativeRequest.getScreenHeight(this);

        setContentView(new AutoMoveBackView(this, screenWidth, screenHeight));
    }
}

class AutoMoveBackView extends View {
    // 背景图片
    private Bitmap souceBackBitmap;
    private Bitmap adaptBackBitmap;

    private Bitmap plane;

    //屏幕宽度和高度
    int screenWidth;
    int screenHeight;

    //绘制开始时候的Y坐标
    int adaptBackBitmapHeight;
    int startY;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                // 重新开始移动
                if (startY <= 0) {
                    startY = adaptBackBitmapHeight - screenHeight;
                } else {
                    startY -= 3;
                }
            }
            invalidate();
        }
    };

    public AutoMoveBackView(Context context, int screenWidth, int screenHeight) {
        // 构造方法~
        super(context);

        //由于density的存在获取的宽度和实际的宽度不符合
        this.souceBackBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.soucebackbitmap);
        this.plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.soucebackplane);

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        //计算屏幕宽度和背景画面的比值
        int souceBackBitmapWidth = souceBackBitmap.getWidth();

        float scaley = screenWidth * 1f / souceBackBitmapWidth;

        Matrix matrix = new Matrix();
        matrix.setScale(scaley, scaley);

        adaptBackBitmap = Bitmap.createBitmap(souceBackBitmap, 0, 0, souceBackBitmap.getWidth(), souceBackBitmap.getHeight(), matrix, true);
        adaptBackBitmapHeight = adaptBackBitmap.getHeight();

        startY = adaptBackBitmapHeight - screenHeight;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        }, 2000, 50);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // 根据原始位图和Matrix创建新图片,从0,startY点开始截图~
        Bitmap drawBitmap = Bitmap.createBitmap(adaptBackBitmap, 0, startY, screenWidth, screenHeight);
        // 绘制新位图
        canvas.drawBitmap(drawBitmap, 0, 0, null);
        // 绘制飞机,横坐标160,纵坐标380
        canvas.drawBitmap(plane, 160, 380, null);
    }
}