package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.tunaPasta19.R;
import com.tunaPasta19.widget.ProtrusionLayoutAnimation;

/**
 * 主Activity,实现各点击事件，这里只实现了两个
 */
public class ProtrusionLayoutTest extends Activity {

    private ImageButton home;// 主home按钮
    private ImageButton menu;// 二级菜单按钮

    private RelativeLayout level2;// 二级相对布局
    private RelativeLayout level3;// 三级相对布局

    private boolean isLevel2Show = true;// 2级相对布局显示判断变量
    private boolean isLevel3Show = true;// 3级相对布局显示判断变量

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.protrusionlayouttest);

        home = findViewById(R.id.home);
        menu = findViewById(R.id.menu);

        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);

        // 二级的菜单键
        menu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (isLevel3Show) {
                    // 如果3级布局处于显示状态,就隐藏3级导航菜单
                    ProtrusionLayoutAnimation.startAnimationOUT(level3, 500, 0);
                } else {
                    // 否则就显示3级导航菜单
                    ProtrusionLayoutAnimation.startAnimationIN(level3, 500);
                }
                // 同时每次点击都改变3级布局判断变量的布尔值类型
                isLevel3Show = !isLevel3Show;
            }
        });
        // 一级的home键
        home.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (!isLevel2Show) {
                    // 如果2级布局处于未显示状态,就显示2级导航菜单
                    ProtrusionLayoutAnimation.startAnimationIN(level2, 500);
                } else {
                    // 否则分两种情况：
                    if (isLevel3Show) {
                        // 如果3级布局显示,隐藏3级导航菜单
                        ProtrusionLayoutAnimation.startAnimationOUT(level3, 500, 0);
                        // 也隐藏2级导航菜单
                        ProtrusionLayoutAnimation.startAnimationOUT(level2, 500, 500);
                        // 同时每次点击都改变3级布局判断变量的布尔值类型
                        isLevel3Show = !isLevel3Show;
                    } else {// 如果只有2级布局显示
                        // 就只隐藏2级导航菜单
                        ProtrusionLayoutAnimation.startAnimationOUT(level2, 500, 0);
                    }
                }
                isLevel2Show = !isLevel2Show;// 每次点击都改变2级布局判断变量的布尔值类型
            }
        });
    }
}
