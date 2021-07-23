package com.tunaPasta06.activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.tunaPasta06.R;

/**
 * 使用ActivityGroup来切换Activity和Layout
 */
public class ActivityGroup02 extends ActivityGroup {

//	我们知道在Android中只允许一个Activity活动在当前界面，在这里我们就不能同时让多个Activity同时存活在ActivityGroup中，
//	当我们加载一个Activity到ActivityGroup中来的时候我们要做的就是移除其他的存在于当前 ActivityGroup中的view，
//	然后加载需要的Activity到当前的ActivityGroup中来。

    private ScrollView container = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置视图
        setContentView(R.layout.activitygroup02);
        container = findViewById(R.id.containerBody);

        //  想动态改变标题可以通过cust_title获取TextView进行设置。

        // 模块1
        ImageView btnModule1 = findViewById(R.id.btnModule1);
        btnModule1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                container.removeAllViews();

//给将要启动Activity设置一个唯一的字符串ID与之关联，如果以后调用startActivity（）的上一次相同的活动对象将被保留。                
//如果当前活动采用非多重启动模式（如singleTop），或意图有Intent.FLAG_ACTIVITY_SINGLE_TOP标志设置，
//那么当前活动将继续运行，Activity.onNewIntent（）方法调用。如果新的意图是与前一个相同，而新的意图没有Intent.FLAG_ACTIVITY_CLEAR_TOP设置，
//那么当前的活动将继续运行原样。否则，目前的活动将结束，一个新的开始。

                container.addView(getLocalActivityManager().startActivity("Module1",
                        new Intent(ActivityGroup02.this, Activity04.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView());
            }
        });

        // 模块2
        ImageView btnModule2 = findViewById(R.id.btnModule2);
        btnModule2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                container.removeAllViews();
                container.addView(getLocalActivityManager().startActivity("Module2",
                        new Intent(ActivityGroup02.this, Activity05.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView());
            }
        });

        // 模块3
        ImageView btnModule3 = findViewById(R.id.btnModule3);
        btnModule3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                container.removeAllViews();
                container.addView(getLocalActivityManager().startActivity("Module3",
                        new Intent(ActivityGroup02.this, Activity06.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView());
            }
        });
    }
}