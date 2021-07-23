package com.tunaPasta04.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;

import com.tunaPasta04.R;

public class ImageProcessTest extends Activity {
    private ImageView image01, image02, image03, image04;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageprocesstest);

        Bitmap bitmapSource = BitmapFactory.decodeResource(getResources(), R.drawable.model_bg0);

        //正常图片
        image01 =  findViewById(R.id.test01);
        image01.setImageBitmap(bitmapSource);

        // 通过在BitmapCut上面的drawBitmap处理使得BitmapCut变化
        Bitmap bitmapCut = Bitmap.createBitmap(96, 96, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCut);

        canvas.drawBitmap(bitmapSource, new Rect(10, 0, 96, 96), new Rect(10, 0, 96, 96), null);

        image02 =  findViewById(R.id.test02);

//        image01.setImageBitmap(bitmapSource);
        image02.setImageBitmap(bitmapCut);

        Bitmap bitmapFillet = getRoundBitmap(bitmapSource, 40);

        image03 =  findViewById(R.id.test03);
        image03.setImageBitmap(bitmapFillet);


        //图片透明处理
        image04 =  findViewById(R.id.test04);
        image04.setImageBitmap(getAlphaBitmap(bitmapFillet, 0.5f));
    }


    // 图片圆角处理
    public static Bitmap getRoundBitmap(Bitmap sourceBitmap, float round) {
        Bitmap roundBitmap = Bitmap.createBitmap(sourceBitmap.getWidth(), sourceBitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(roundBitmap);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sourceBitmap.getWidth(), sourceBitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(rectF, round, round, paint);// 值越大就越圆
        // 方法用于画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(sourceBitmap, rect, rect, paint);
        return roundBitmap;
    }

    public static Bitmap getAlphaBitmap(Bitmap sourceBitmap, float fraction) {
        int[] argb = new int[sourceBitmap.getWidth() * sourceBitmap.getHeight()];

        // 获得图片的ARGB值
        sourceBitmap.getPixels(argb, 0, sourceBitmap.getWidth(), 0, 0, sourceBitmap.getWidth(), sourceBitmap.getHeight());

        int number = (int) (fraction * 255);
        for (int i = 0; i < argb.length; i++) {
            if (argb[i] != 0) {
                argb[i] = (number << 24) | (argb[i] & 0x00FFFFFF);
            } else {
                argb[i] = 0x00000000;
            }
        }
        sourceBitmap = Bitmap.createBitmap(argb, sourceBitmap.getWidth(), sourceBitmap.getHeight(), Config.ARGB_8888);
        return sourceBitmap;
    }

}