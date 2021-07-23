package com.tunaPasta06.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.tunaPasta06.R;

//实现的主要原理是，将需要进行多个Activity跳转的TabActivity设置为ActivityGroup，
//用ActivityGroup管理多个Activity之间的跳转。这里用一个简单的例子作为讲解。

//ActivityGroupTest为一个TabActivity，其中包含GroupAct01和OrdinaryAct；
//GroupAct01为第一个标签页的ActivityGroup，
//用于管理Activity01、Activity02和Activity03在第一个标签页内之间的跳转；
//Act02为第二个标签页的Activity。

public class ActivityGroupTest01 extends TabActivity {
    private TabHost tabHost;  //声明TabHost
    private Intent intent;  //声明Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitygrouptest01);

        tabHost = getTabHost();
        intent = new Intent(this, ActivityGroup01.class);
        tabHost.addTab(
                tabHost.newTabSpec("Tab01")
                        .setIndicator("选项卡1")
                        .setContent(intent));

        intent = new Intent(this, OrdinaryAct.class);
        tabHost.addTab(
                tabHost.newTabSpec("Tab02")
                        .setIndicator("选项卡2")
                        .setContent(intent));
        tabHost.setCurrentTab(0);
    }
}