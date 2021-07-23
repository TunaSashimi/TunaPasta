package com.tunaPasta06.activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

//第一个标签页显示的Activity，继承ActivityGroup，管理所有子Activity
public class ActivityGroup01 extends ActivityGroup {
    public static ActivityGroup activitygroup;//用于管理本Group中的所有Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitygroup = this;
    }

    @Override
    public void onBackPressed() {
        //把后退事件交给子Activity处理
        activitygroup.getLocalActivityManager().getCurrentActivity().onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //要跳转的Activity
        Intent intent = new Intent(ActivityGroup01.this, Activity01.class);
        // 把Activity转换成一个Window，然后转换成View
        Window w = activitygroup.getLocalActivityManager().startActivity("Activity01", intent);
        View view = w.getDecorView();
        //设置要跳转的Activity显示为本ActivityGroup的内容
        activitygroup.setContentView(view);
    }
}