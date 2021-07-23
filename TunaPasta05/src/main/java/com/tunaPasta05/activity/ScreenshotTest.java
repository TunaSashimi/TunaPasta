package com.tunaPasta05.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.tunaPasta05.R;

public class ScreenshotTest extends Activity {
    private ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screenshottest);
        im = findViewById(R.id.image);
        im.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = ScreenshotTest.this.getWindow().getDecorView();
                view.setDrawingCacheEnabled(true);
                view.buildDrawingCache(false);
                Bitmap bitmap = view.getDrawingCache();
                im.setImageBitmap(bitmap);
            }
        });
    }
}