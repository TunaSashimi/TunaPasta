package com.tunaPasta02.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.tunaPasta02.R;

public class LearnGameViewTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new LearnGameView(this));
    }
}

class LearnGameView extends View {
    int x = 0, y = 0;
    Bitmap bmp;

    public LearnGameView(Context context) {
        super(context);
        setFocusable(true);
        Resources res = context.getResources();
        bmp = BitmapFactory.decodeResource(res, R.drawable.battlecity);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawText("坦克大战", 150, 300, paint);
        canvas.drawBitmap(bmp, x, y, new Paint());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                y -= 10;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                y += 10;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                x -= 10;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                x += 10;
                break;
        }
        postInvalidate(); // 通知系统重绘View
        return super.onKeyDown(keyCode, event);
    }
}