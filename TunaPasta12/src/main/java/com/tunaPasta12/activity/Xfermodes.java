
package com.tunaPasta12.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.os.Bundle;
import android.view.View;

public class Xfermodes extends GraphicsActivity {
    
    // create a bitmap with a circle, used for the "dst" image
    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        
        p.setColor(0xFFFFCC44);    
        c.drawOval(new RectF(0, 0, w*3/4, h*3/4), p);
        return bm;
    }
    
    // create a bitmap with a rect, used for the "src" image
    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        
        p.setColor(0xFF66AAFF);
        c.drawRect(w/3, h/3, w*19/20, h*19/20, p);
        return bm;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }
    
    private static class SampleView extends View {
        private static final int W = 160;
        private static final int H = 160;
        private static final int ROW_MAX = 4;   // number of samples per row

        private Bitmap mSrcB;
        private Bitmap mDstB;
        private Shader shaderBG;     // background checker-board pattern
        
        
//     1.PorterDuff.Mode.CLEAR
//        所绘制不会提交到画布上。
//     2.PorterDuff.Mode.SRC
//        显示上层绘制图片
//     3.PorterDuff.Mode.DST
//       显示下层绘制图片
//     4.PorterDuff.Mode.SRC_OVER
//       正常绘制显示，上下层绘制叠盖。
//     5.PorterDuff.Mode.DST_OVER
//       上下层都显示。下层居上显示。
//     6.PorterDuff.Mode.SRC_IN
//        取两层绘制交集。显示上层。
//     7.PorterDuff.Mode.DST_IN
//       取两层绘制交集。显示下层。
//     8.PorterDuff.Mode.SRC_OUT
//      取上层绘制非交集部分。
//     9.PorterDuff.Mode.DST_OUT
//      取下层绘制非交集部分。
//     10.PorterDuff.Mode.SRC_ATOP
//      取下层非交集部分与上层交集部分
//     11.PorterDuff.Mode.DST_ATOP
//       取上层非交集部分与下层交集部分
//     12.PorterDuff.Mode.XOR
//       取上下层非交集部分
//     //红色部分均与透明度有关，所以要用Bitmap.config.ARGB的值
//     13.PorterDuff.Mode.DARKEN
//      上下层全部显示，交集部分深色处理
//     14.PorterDuff.Mode.LIGHTEN
//      上下层全部显示，交集部分浅色处理
//     15.PorterDuff.Mode.MULTIPLY
//      取上下层交集部分深色处理
//     16.PorterDuff.Mode.SCREEN
//     上下层全部显示，交集部分浅色处理
        
        
        private static final Xfermode[] sModes = {
            new PorterDuffXfermode(PorterDuff.Mode.CLEAR),
            new PorterDuffXfermode(PorterDuff.Mode.SRC),
            new PorterDuffXfermode(PorterDuff.Mode.DST),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_IN),
            new PorterDuffXfermode(PorterDuff.Mode.DST_IN),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),
            new PorterDuffXfermode(PorterDuff.Mode.DST_OUT),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.XOR),
            new PorterDuffXfermode(PorterDuff.Mode.DARKEN),
            new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
            new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY),
            new PorterDuffXfermode(PorterDuff.Mode.SCREEN)
        };
        
        private static final String[] sLabels = {
            "Clear", "Src", "Dst", "SrcOver",
            "DstOver", "SrcIn", "DstIn", "SrcOut",
            "DstOut", "SrcATop", "DstATop", "Xor",
            "Darken", "Lighten", "Multiply", "Screen"
        };
        
        public SampleView(Context context) {
            super(context);
            
            mSrcB = makeSrc(W, H);
            mDstB = makeDst(W, H);
            
            // make a ckeckerboard pattern
            Bitmap bm = Bitmap.createBitmap(new int[] { 0xFFFFFFFF, 0xFFCCCCCC, 0xFFCCCCCC, 0xFFFFFFFF }, 2, 2,Bitmap.Config.RGB_565);
            shaderBG = new BitmapShader(bm,Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
            Matrix m = new Matrix();
            m.setScale(8, 8);
            shaderBG.setLocalMatrix(m);
        }
        
        @Override 
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            
            Paint labelP = new Paint(Paint.ANTI_ALIAS_FLAG);
            labelP.setTextAlign(Paint.Align.CENTER);
            
            Paint paint = new Paint();
            paint.setFilterBitmap(false);
            
            canvas.translate(15, 35);
            
            int x = 0;
            int y = 0;
            for (int i = 0; i < sModes.length; i++) {
            	
                // draw the border
                paint.setStyle(Paint.Style.STROKE);
                paint.setShader(null);
                canvas.drawRect(x - 0.5f, y - 0.5f,x + W + 0.5f, y + H + 0.5f, paint);
                
                // draw the checker-board pattern
                paint.setStyle(Paint.Style.FILL);
                paint.setShader(shaderBG);
                canvas.drawRect(x, y, x + W, y + H, paint);
                
                // draw the src/dst example into our offscreen bitmap
                int sc = canvas.saveLayer(x, y, x + W, y + H, null);
                
                canvas.translate(x, y);
                canvas.drawBitmap(mDstB, 0, 0, paint);
                paint.setXfermode(sModes[i]);
                canvas.drawBitmap(mSrcB, 0, 0, paint);
                paint.setXfermode(null);
                canvas.restoreToCount(sc);
                
                // draw the label
                canvas.drawText(sLabels[i], x + W/2, y - labelP.getTextSize()/2, labelP);
                
                x += W + 10;
                
                // wrap around when we've drawn enough for one row
                if ((i % ROW_MAX) == ROW_MAX - 1) {
                    x = 0;
                    y += H + 30;
                }
            }
        }
    }
}

