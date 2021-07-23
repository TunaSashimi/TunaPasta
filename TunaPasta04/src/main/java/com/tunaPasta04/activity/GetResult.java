package com.tunaPasta04.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;

import com.tunaPasta04.R;
import com.tunaPasta04.application.PhotoTrans;

public class GetResult extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getresult);
        ImageView imageView = findViewById(R.id.img_pai);
        Bitmap bitmap = ((PhotoTrans) getApplication()).getBitmap();
        imageView.setImageBitmap(getOval(bitmap));
    }

    protected Bitmap getOval(Bitmap bitmap) {
        Bitmap dstbmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(dstbmp);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        canvas.drawOval(rectF, paint);    //画椭圆~

        //也可以使用绘制圆角矩形的方式绘图,第一个参数为图形显示区域,第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径
        //	canvas.drawRoundRect(rectF, roundPX, roundPY, paint);// 值越大就越圆

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return dstbmp;
    }
}
