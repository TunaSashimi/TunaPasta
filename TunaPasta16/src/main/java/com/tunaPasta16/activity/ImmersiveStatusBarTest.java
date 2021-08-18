package com.tunaPasta16.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tunaPasta16.R;

public class ImmersiveStatusBarTest extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //界面默认情况下是全屏的，状态栏和导航栏都不会显示。而当我们需要用到状态栏或导航栏时，
        // 只需要在屏幕顶部向下拉，或者在屏幕右侧向左拉，状态栏和导航栏就会显示出来，
        // 此时界面上任何元素的显示或大小都不会受影响。过一段时间后如果没有任何操作，
        // 状态栏和导航栏又会自动隐藏起来，重新回到全屏状态。

        setContentView(R.layout.immersivestatusbartest);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}