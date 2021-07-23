package com.tunaPasta20.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.tunaPasta20.R;
import com.tunasushi.tool.BitmapTool;
import com.tunasushi.view.TView;

/**
 * @author TunaSashimi
 * @date 2020-04-13 11:26
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class TViewSrcActivity extends Activity {
    TView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tview_src);

        boolean bool = false;

        t = findViewById(R.id.tView);

        if (bool) {
            t.setSrcNormal(R.drawable.bitmap_tview_srcnormal03);
        } else {
            Bitmap bitmap = BitmapTool.decodeBitmapResource(getResources(), R.drawable.bitmap_tview_srcnormal04);
            t.setSrcNormal(bitmap);
        }
    }
}
