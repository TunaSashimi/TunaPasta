package com.tunaPasta07.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tunaPasta07.R;

public class MatrixChangeTest extends Activity {
    private MatrixChangeView mGameView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.matrixchangetest);

        LinearLayout linear_scale01 = findViewById(R.id.linear_scale01);

        mGameView = new MatrixChangeView(this);

        linear_scale01.addView(mGameView);

        Button btn_scale01 = findViewById(R.id.btn_scale01);
        Button btn_scale02 = findViewById(R.id.btn_scale02);
        Button btn_scale03 = findViewById(R.id.btn_scale03);
        Button btn_scale04 = findViewById(R.id.btn_scale04);

        btn_scale01.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (mGameView.Scale > 0.3) {
                    mGameView.Scale -= 0.1;
                }
            }
        });

        btn_scale02.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (mGameView.Scale < 1.5) {
                    mGameView.Scale += 0.1;
                }
            }
        });

        btn_scale03.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mGameView.angle--;
            }
        });
        btn_scale04.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mGameView.angle++;
            }
        });
    }

    static class MatrixChangeView extends View implements Runnable {
        /* 声明Bitmap对象 */
        Bitmap mBitQQ;
        int BitQQwidth;
        int BitQQheight;

        float Scale = 1.0f;
        float angle = 0.0f;

        /* 构建Matrix对象 */
        Matrix mMatrix = new Matrix();

        public MatrixChangeView(Context context) {
            super(context);

            /* 装载资源 */
            mBitQQ = ((BitmapDrawable) getResources().getDrawable(R.drawable.qq)).getBitmap();

            /* 得到图片的宽度和高度 */
            BitQQwidth = mBitQQ.getWidth();
            BitQQheight = mBitQQ.getHeight();

            /* 开启线程 */
            new Thread(this).start();
        }

        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            /* 重置mMatrix */
            mMatrix.reset();

            /* 设置旋转 */
            mMatrix.setRotate(angle);

            /* 设置缩放 */

            //postScale必须放在Rotate下面才能缩放和旋转同时有效果
            mMatrix.postScale(Scale, Scale);

            /* 按mMatrix得旋转构建新的Bitmap */
            Bitmap mBitQQ2 = Bitmap.createBitmap(mBitQQ, 0, 0, BitQQwidth, BitQQheight, mMatrix, true);

            /* 绘制旋转之后的图片 */
            MatrixChangeView.drawImage(canvas, mBitQQ2, (320 - BitQQwidth) / 2, 10);
        }

        /**
         * 线程处理
         */
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                // 使用postInvalidate可以直接在线程中更新界面
                postInvalidate();
            }
        }

        /**
         * 绘制一个Bitmap
         *
         * @param canvas 画布
         * @param bitmap 图片
         * @param x      屏幕上的x坐标
         * @param y      屏幕上的y坐标
         */
        public static void drawImage(Canvas canvas, Bitmap bitmap, int x, int y) {
            /* 绘制图像 */
            canvas.drawBitmap(bitmap, x, y, null);
        }
    }
}