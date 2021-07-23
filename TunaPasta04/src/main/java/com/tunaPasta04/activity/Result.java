package com.tunaPasta04.activity;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.tunaPasta04.R;

public class Result extends Activity {

    private ImageView image00, image01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        image00 = findViewById(R.id.image01);
        image01 = findViewById(R.id.image02);

    }

    private void setBitmap(ImageView image, String path) {
        path = Environment.getExternalStorageDirectory() + path;
        File file = new File(path);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            image.setImageBitmap(bitmap);
        }

    }

    public void onViewClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.btn01:
                setBitmap(image00, "/image00.jpg");
                setBitmap(image01, "/image01.jpg");

                break;
        }
    }

}
