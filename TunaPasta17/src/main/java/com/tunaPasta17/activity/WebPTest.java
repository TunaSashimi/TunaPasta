package com.tunaPasta17.activity;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta17.R;

import androidx.annotation.Nullable;

/**
 * @author jinsheng
 * @date 7/18/2019 5:20 PM
 * @Copyright 2019 TunaSashimi. All rights reserved.
 * @Description
 */
public class WebPTest extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.webptest);
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.button_jpg01:
                testDecodeJPG01();
                break;
            case R.id.button_webp01:
                testDecodeWEBP01();
                break;
            case R.id.button_png02:
                testDecodePNG02();
                break;
            case R.id.button_webp02:
                testDecodeWEBP02();
                break;
            default:
                break;
        }
    }

    private void testDecodeJPG01() {
        long timeStart = System.currentTimeMillis();
        //
        for (int i = 1; i <= 5; i++) {
            BitmapFactory.decodeResource(getResources(), R.drawable.test_jpg01);
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("consumeJPG01==>" + (timeEnd - timeStart));
    }

    private void testDecodeWEBP01() {
        long timeStart = System.currentTimeMillis();
        for (int i = 1; i <= 5; i++) {
            BitmapFactory.decodeResource(getResources(), R.drawable.test_webp01);
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("consumeWEBP01==>" + (timeEnd - timeStart));
    }

    private void testDecodePNG02() {
        long timeStart = System.currentTimeMillis();
        //
        for (int i = 1; i <= 5; i++) {
            BitmapFactory.decodeResource(getResources(), R.drawable.test_png02);
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("consumePNG02==>" + (timeEnd - timeStart));
    }

    private void testDecodeWEBP02() {
        long timeStart = System.currentTimeMillis();
        for (int i = 1; i <= 5; i++) {
            BitmapFactory.decodeResource(getResources(), R.drawable.test_webp02);
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("consumeWEBP02==>" + (timeEnd - timeStart));
    }
}
