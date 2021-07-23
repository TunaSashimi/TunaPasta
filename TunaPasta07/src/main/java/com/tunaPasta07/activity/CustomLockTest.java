package com.tunaPasta07.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.tunaPasta07.R;
import com.tunaPasta07.widget.SliderRelativeLayout;

public class CustomLockTest extends Activity {

    public static int MSG_LOCK_SUCESS = 1;

    private SliderRelativeLayout sliderLayout = null;

    private ImageView imgView_getup_arrow; // 动画图片
    private AnimationDrawable animArrowDrawable = null;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (MSG_LOCK_SUCESS == msg.what)
                finish(); // 锁屏成功时，结束我们的Activity界面
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*设置全屏，无标题*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.customlocktest);
        initViews();

        //改变系统锁屏
        //startService(new Intent(CustomLockTest.this, CustomLockService.class));

        sliderLayout.setMainHandler(mHandler);
    }

    private void initViews() {
        sliderLayout =  findViewById(R.id.slider_layout);
        //获得动画，并开始转动
        imgView_getup_arrow =  findViewById(R.id.getup_arrow);
        animArrowDrawable = (AnimationDrawable) imgView_getup_arrow.getBackground();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置动画
        mHandler.postDelayed(AnimationDrawableTask, 300);  //开始绘制动画
    }

    @Override
    protected void onPause() {
        super.onPause();
        animArrowDrawable.stop();
    }

    protected void onDestory() {
        super.onDestroy();
    }

    //通过延时控制当前绘制bitmap的位置坐标
    private Runnable AnimationDrawableTask = new Runnable() {

        public void run() {
            animArrowDrawable.start();
            mHandler.postDelayed(AnimationDrawableTask, 300);
        }
    };

    //屏蔽掉Home键
    public void onAttachedToWindow() {
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onAttachedToWindow();
    }

}