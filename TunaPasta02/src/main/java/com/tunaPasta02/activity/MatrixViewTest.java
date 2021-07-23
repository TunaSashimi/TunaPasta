package com.tunaPasta02.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta02.R;

public class MatrixViewTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MatrixView(this));
    }
}

class MatrixView extends View {
    private Bitmap bitmap;
    private Matrix matrix = new Matrix();

    public MatrixView(Context context) {
        super(context);
        setFocusable(true);
        initialize();
    }

    //初始化
    private void initialize() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.battlecity);
        bitmap = bmp;
        /*
         * 首先，将缩放为100*100。这里scale的参数是比例。有一点要注意，如果直接用100/
         * bmp.getWidth()的话，会得到0，因为是整型相除，所以必须其中有一个是float型的，直接用100f就好。
         */
        matrix.postScale(100f / bmp.getWidth(), 100f / bmp.getHeight());
        // 平移到（150，150）处
        matrix.postTranslate(100, 100);
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    for (int i = -8; i < 88; i += 2) {
                        stretching(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, matrix, null);
    }

    private void stretching(int i) throws InterruptedException {
        if (i % 2 == 0) {
            matrix.preSkew(-0.2f, -0.2f, 100, 100);
        } else {
            matrix.preSkew(0.2f, 0.2f, 100, 100);
        }
        postInvalidate();
        Thread.sleep(180);
    }
}