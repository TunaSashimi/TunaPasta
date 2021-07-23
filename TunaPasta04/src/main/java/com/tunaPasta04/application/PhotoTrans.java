package com.tunaPasta04.application;

import android.app.Application;
import android.graphics.Bitmap;

public class PhotoTrans extends Application {
    private Bitmap bitmap;

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
