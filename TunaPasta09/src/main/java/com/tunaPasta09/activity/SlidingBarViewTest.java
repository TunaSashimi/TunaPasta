package com.tunaPasta09.activity;

import java.util.Hashtable;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.tunaPasta09.R;
import com.tunaPasta09.widget.SlidingBarView;

public class SlidingBarViewTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.slidingbarviewtest);

        Bitmap bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        //
        Bitmap slider_normal = BitmapFactory.decodeResource(getResources(),
                R.drawable.slider_normal);
        //
        Bitmap slider_pressed = BitmapFactory.decodeResource(getResources(),
                R.drawable.silder_pressed);
        //
        String[] tag = new String[]{"0", "5", "10", "20"};
        //
        Hashtable<String, Bitmap> mTagImg = new Hashtable<String, Bitmap>();
        Bitmap tag_0 = BitmapFactory.decodeResource(getResources(),
                R.drawable.tag_0);
        Bitmap tag_5 = BitmapFactory.decodeResource(getResources(),
                R.drawable.tag_5);
        Bitmap tag_10 = BitmapFactory.decodeResource(getResources(),
                R.drawable.tag_10);
        Bitmap tag_20 = BitmapFactory.decodeResource(getResources(),
                R.drawable.tag_20);

        mTagImg.put(tag[0], tag_0);
        mTagImg.put(tag[1], tag_5);
        mTagImg.put(tag[2], tag_10);
        mTagImg.put(tag[3], tag_20);

        SlidingBarView sSliding_Bar = findViewById(R.id.getTaxiCar_addPriceSilder);
        sSliding_Bar.init(bg, slider_normal, slider_pressed, tag, mTagImg);

        sSliding_Bar
                .setOnChangedListener(new SlidingBarView.OnChangedListener() {
                    @Override
                    public void OnChanged(SlidingBarView componentSlidingBar,
                                          String tag) {
                    }
                });
    }

}
