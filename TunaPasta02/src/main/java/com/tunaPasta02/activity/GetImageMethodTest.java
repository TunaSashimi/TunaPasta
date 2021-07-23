package com.tunaPasta02.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.tunaPasta02.R;

public class GetImageMethodTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getimagemethodtest);

        ImageView image01 = findViewById(R.id.image01);
        ImageView image02 = findViewById(R.id.image02);
        ImageView image03 = findViewById(R.id.image03);

        image01.setImageResource(R.drawable.battlecity);
        image01.setDrawingCacheEnabled(true);
        image01.buildDrawingCache();

        Drawable drawable = image01.getDrawable();//貌似只能拿ImageResource的图片,拿到的是原图
        image02.setBackgroundDrawable(drawable);

        Bitmap bitmap = ((BitmapDrawable) image01.getDrawable()).getBitmap();

        image03.setImageBitmap(bitmap);

    }
}
