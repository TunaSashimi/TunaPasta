package com.tunaPasta10.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.tunaPasta10.R;
import com.tunaPasta10.widget.FollowFingerBallView;

public class FollowFingerBallViewTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followfingerballviewtest);

        final FollowFingerBallView draw = findViewById(R.id.drawview);

        // 为draw组件绑定Touch事件
        draw.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                // 修改draw组件的currentX、currentY两个属性
                draw.currentX = event.getX();
                draw.currentY = event.getY();
                // 通知draw组件重绘
                draw.invalidate();
                // 返回true表明处理方法已经处理该事件
                return true;
            }
        });
    }
}