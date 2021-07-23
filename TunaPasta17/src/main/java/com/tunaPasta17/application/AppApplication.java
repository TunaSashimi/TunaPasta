package com.tunaPasta17.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author Tunasashimi
 * @date 2018/8/20 17:20
 * @Copyright 2018 TunaSashimi. All rights reserved.
 * @Description
 */
public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

//        MyMonitor myMonitor = new MyMonitor();
//        Looper.getMainLooper().setMessageLogging(myMonitor);
    }
}
