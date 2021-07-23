package com.tunaPasta06.activity;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.tunaPasta06.R;

public class Activity07 extends Activity {
    // 声明Button对象
    private Button m_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity07);
        // 构建Button对象
        m_Button = findViewById(R.id.but);
        // 监听Button事件
        m_Button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取group的view 注意：如果你
                // 动态显示界面的是LinearLayout布局就把ViewFlipper改成LinearLayout就行了
                // 其余的布局以此类推
                ViewFlipper container = (getParent()).getWindow().findViewById(R.id.fliper);
                // 移除当前视图
                container.removeView(container.getCurrentView());
                // 构建Intent对象
                Intent intent = new Intent(Activity07.this, Activity08.class);
                // 设置Intent.FLAG_ACTIVITY_CLEAR_TOP表示当前视图将结束，如果不设置那么当前视图将继续运行
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // 给你将要启动的Activity设置一个唯一的字符串ID与之关联
                Window subActivity = ((ActivityGroup) Activity07.this.getParent()).getLocalActivityManager().startActivity("locallist", intent);
                // 新添加窗口到窗口管理器中
                container.addView(subActivity.getDecorView(), 1);
                // 设置显示的子视图
                container.setDisplayedChild(1);
            }
        });
    }
}
