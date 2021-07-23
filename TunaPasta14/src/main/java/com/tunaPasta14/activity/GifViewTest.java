package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.tunaPasta14.R;
import com.tunaPasta14.widget.GifView;
import com.tunaPasta14.widget.GifView.GifImageType;

public class GifViewTest extends Activity {
    private GifView gifView1;
    private GifView gifView2;
    private boolean isPlaying = true;// 是否正在播放

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gifviewtest);
        gifView1 = findViewById(R.id.main_gif1);
        // 设置要播放的GIF图片
        gifView1.setGifImage(R.drawable.animation);
        // 设置点击监听器
        gifView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 对于gifView1，如果正在播放，点击则显示第一帧，如果当前为显示第一帧，点击则继续播放
                if (isPlaying) {
                    // 只显示第一帧
                    gifView1.showCover();
                    isPlaying = false;
                } else {
                    // 继续播放动画（该方法在调用showCover后使用才有效）
                    gifView1.showAnimation();
                    isPlaying = true;
                }
            }
        });

        gifView2 = findViewById(R.id.main_gif2);
        // 设置解析过程中显示方式为只显示第一帧
        gifView2.setGifImageType(GifImageType.COVER);
        // 设置图片大小
        gifView2.setShowDimension(300, 300);
        // 设置要播放的GIF图片
        gifView2.setGifImage(R.drawable.loading);
    }
}