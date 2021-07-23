package com.tunaPasta19.activity;

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
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaView;

public class MoveBackGroundTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //宽度是关键
        int screenWidth = TunaView.getScreenWidth(this);
        int screenHeight = TunaView.getScreenHeight(this);

        FrameLayout framelayout = new FrameLayout(this);
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(LinearLayout.VERTICAL);

        linearlayout.setGravity(Gravity.CENTER);

        Button button01 = new Button(this);
        button01.setText("头条");
        linearlayout.addView(button01);

        Button button02 = new Button(this);
        button02.setText("体育");
        linearlayout.addView(button02);

        Button button03 = new Button(this);
        button03.setText("娱乐");
        linearlayout.addView(button03);

        Button button04 = new Button(this);
        button04.setText("财经");
        linearlayout.addView(button04);

        Button button05 = new Button(this);
        button05.setText("更多");
        linearlayout.addView(button05);

        OnClickListener btnOnClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MoveBackGroundTest.this, "按钮被点击", Toast.LENGTH_LONG).show();
            }
        };

        button01.setOnClickListener(btnOnClickListener);
        button02.setOnClickListener(btnOnClickListener);
        button03.setOnClickListener(btnOnClickListener);
        button04.setOnClickListener(btnOnClickListener);
        button05.setOnClickListener(btnOnClickListener);

        framelayout.addView(new AutoMoveBackView(this, screenWidth, screenHeight));
        framelayout.addView(linearlayout);

        setContentView(framelayout);
    }
}

class AutoMoveBackView extends View {
    // 背景图片
    private Bitmap souceBackBitmap;
    private Bitmap adaptBackBitmap;

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
        super(context);

        this.souceBackBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.movebacktest_automovebackview_souceback);

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

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
        Bitmap drawBitmap = Bitmap.createBitmap(adaptBackBitmap, 0, startY, screenWidth, screenHeight);
        canvas.drawBitmap(drawBitmap, 0, 0, null);
    }
}