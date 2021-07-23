package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaScale;

public class TunaScaleTest extends Activity {

    private TunaScale tunaScaleBitmapSetBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunascaletest);

        tunaScaleBitmapSetBitmap = findViewById(R.id.tunaScaleBitmapSetBitmap);
        tunaScaleBitmapSetBitmap.setTunaScaleBitmapSrc(R.drawable.viewpagertest_imageview_resource02);
    }
}
