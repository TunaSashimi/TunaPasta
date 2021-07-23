package com.tunaPasta05.activity;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import com.tunaPasta05.R;

public class TextViewPaintFlagTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textviewpaintflagtest);
        ((TextView) findViewById(R.id.text01)).getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
        ((TextView) findViewById(R.id.text02)).getPaint().setFlags(Paint.DEV_KERN_TEXT_FLAG);
        ((TextView) findViewById(R.id.text03)).getPaint().setFlags(Paint.DITHER_FLAG);
        ((TextView) findViewById(R.id.text04)).getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        ((TextView) findViewById(R.id.text05)).getPaint().setFlags(Paint.FILTER_BITMAP_FLAG);
        ((TextView) findViewById(R.id.text06)).getPaint().setFlags(Paint.LINEAR_TEXT_FLAG);

        ((TextView) findViewById(R.id.text07)).setText("测试各种字体风格");
        ((TextView) findViewById(R.id.text07)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ((TextView) findViewById(R.id.text08)).getPaint().setFlags(Paint.SUBPIXEL_TEXT_FLAG);
        ((TextView) findViewById(R.id.text09)).getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }
}