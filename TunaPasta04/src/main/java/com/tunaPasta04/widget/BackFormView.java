package com.tunaPasta04.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tunaPasta04.R;
import com.tunaPasta04.entity.Box;

public class BackFormView extends SurfaceView implements SurfaceHolder.Callback {
    private Box box;
    private boolean flag;

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public BackFormView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    //把bitmap放到成员变量中去~
    public void draw() {
        if (flag) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawColor(Color.BLACK);

            Bitmap bitmapop = BitmapFactory.decodeResource(this.getResources(), R.drawable.test);
            BitmapDrawable bdop = new BitmapDrawable(bitmapop);
            bdop.setBounds(0, 400, 320, 450);
            bdop.draw(canvas);

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);
            BitmapDrawable bd = new BitmapDrawable(bitmap);
            bd.setBounds(box.x, box.y, box.x + box.width, box.y + box.height);
            bd.draw(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

}
