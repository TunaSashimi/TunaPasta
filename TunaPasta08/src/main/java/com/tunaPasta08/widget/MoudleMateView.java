package com.tunaPasta08.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.tunaPasta08.R;
import com.tunaPasta08.entity.Actor;

public class MoudleMateView extends View {
    private int screenWidth, screenHeight;
    private double ratio, density;//ratio为该屏幕上显示的位置与参考值的比率，density为屏幕像素的密度值，在本机图片缩放时必不可少~
    private Actor coat, hat, pants, skirt, tshirt;

    public MoudleMateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 创建surfaceview的时候得到当前设备并获得当前设备的宽度和高度，还有该屏幕的像素密度必不可少~
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;
        screenHeight = metric.heightPixels;
        density = metric.density;

        ratio = screenHeight * 0.80 / 0.67 / 814;// 屏幕中的模特全长与实际全长之比,原JSON中的body高度为814，即保存在本地图片比
    }

    //然后得到换算值，长度的和偏移量的，考虑做成单例，如果等于空的话会取不空直接得到
    protected void onDraw(Canvas canvas) {

        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));// 画布设置抗锯齿
        bodyDraw(canvas);                            //新的bodyDraw()方法~绘制模特的身体
        if (hat != null && hat.candrow != null) {
            compoDrawing(canvas, hat.candrow, 1, hat.offsetX, hat.offsetY);
        }
        if (pants != null && pants.candrow != null) {                    //腿是分几段的，需要跟三段身体一样的处理,取值为0.58
            compoDrawing(canvas, pants.candrow, 0.58, pants.offsetX, pants.offsetY);
        }
        if (skirt != null && skirt.candrow != null) {
            compoDrawing(canvas, skirt.candrow, 1, skirt.offsetX, skirt.offsetY);
        }
        if (tshirt != null && tshirt.candrow != null) {
            compoDrawing(canvas, tshirt.candrow, 1, tshirt.offsetX, tshirt.offsetY);
        }
        if (coat != null && coat.candrow != null) {
            compoDrawing(canvas, coat.candrow, 1, coat.offsetX, coat.offsetY);
        }
    }

    protected void bodyDraw(Canvas canvas) {
        compoDrawing(canvas, R.drawable.draw_body, 0.67, 109, 31);                //绘制中段身
        compoDrawing(canvas, R.drawable.draw_face, 1, 213, 34);// 绘制头部
        compoDrawing(canvas, R.drawable.draw_underwear, 1, 185, 112);    // 绘制内衣
    }

    //利用参数的不同进行重载~少参数的本地取,注意图片缩放的时候跟屏幕的像素密度有关,多参数的compoDrawing方法网上取
    private void compoDrawing(Canvas canvas, int type, double cut, int offsetX, int offsetY) {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), type);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = (int) (bitmap.getWidth() * ratio / density);
        int newHeight = (int) (bitmap.getHeight() * ratio / density);
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);

//		都是缩放图片,虽然下面的代码也可以实现,但是锯齿化很严重~~
//	bitmap=Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*ratio/density),(int)(bitmap.getHeight()*ratio/density), false);

        canvas.drawBitmap(bitmap,
                new Rect(0, 0, bitmap.getWidth(), (int) (bitmap.getHeight() * cut)),
                new Rect((int) (offsetX * ratio), (int) (offsetY * ratio + screenWidth / 6), bitmap.getWidth() + (int) (offsetX * ratio), (int) (bitmap.getHeight() * cut + offsetY * ratio + screenWidth / 6)),
                null);
    }

    // 参数:画布,图片,截取大小,偏移量X,偏移量Y
    private void compoDrawing(Canvas canvas, Bitmap candraw, double cut, int offsetX, int offsetY) {
        canvas.drawBitmap(
                candraw,
                new Rect(0, 0, candraw.getWidth(),
                        (int) (candraw.getHeight() * cut)),
                new Rect(
                        (int) (offsetX * ratio),
                        (int) (offsetY * ratio + screenWidth / 6),
                        candraw.getWidth() + (int) (offsetX * ratio),
                        (int) (candraw.getHeight() * cut + offsetY * ratio + screenWidth / 6)),
                null);
    }

    public Actor getCoat() {
        return coat;
    }

    public void setCoat(Actor coat) {
        this.coat = coat;
    }

    public Actor getHat() {
        return hat;
    }

    public void setHat(Actor hat) {
        this.hat = hat;
    }

    public Actor getPants() {
        return pants;
    }

    public void setPants(Actor pants) {
        this.pants = pants;
    }

    public Actor getSkirt() {
        return skirt;
    }

    public void setSkirt(Actor skirt) {
        this.skirt = skirt;
    }

    public Actor getTshirt() {
        return tshirt;
    }

    public void setTshirt(Actor tshirt) {
        this.tshirt = tshirt;
    }
}
