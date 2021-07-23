package com.tunaPasta07.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnTouchModeChangeListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tunaPasta07.R;

//加载FrameAnimation的另一种方法
public class MoonChangeTest extends Activity {
    private LinearLayout frameanim_linear;
    private ImageView image;
    private Button btn01, btn02;
    private AnimationDrawable frameAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.moonchangetest);

        frameanim_linear = findViewById(R.id.frameanim_linear);

        image = findViewById(R.id.frameanim_img);
        btn01 = findViewById(R.id.frameanim_btn01);
        btn02 = findViewById(R.id.frameanim_btn02);

        /* 装载动画布局文件 */
        image.setBackgroundResource(R.drawable.frameanimation);

        /* 构建动画 */
        frameAnimation = (AnimationDrawable) image.getBackground();

        /* 设置是否循环 */
        frameAnimation.setOneShot(false);

        btn01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                frameAnimation.start();
            }
        });
        btn02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                frameAnimation.stop();
            }
        });

        // 通过 getViewTreeObserver 获得 ViewTreeObserver 对象
        // 通过 这种写法可以让 FrameAnimation在onCreate中启动
        ViewTreeObserver viewtreeobserver = frameanim_linear.getViewTreeObserver();
        viewtreeobserver.addOnTouchModeChangeListener(new OnTouchModeChangeListener() {
            @Override
            public void onTouchModeChanged(boolean isInTouchMode) {
                frameAnimation.start();
            }
        });
    }
}
