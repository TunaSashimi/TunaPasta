package com.tunaPasta05.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.Toast;

import com.tunaPasta05.R;

public class CallOtherActTest extends Activity {
    String pkg = "com.tunaPasta03";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.callotheracttest);

        if (checkApkExist(this, pkg)) {
            Intent intent = new Intent();
            ComponentName comp = new ComponentName(pkg, "com.tunaPasta03.activity.EntryAct");
            intent.setComponent(comp);
            intent.setAction("android.intent.action.Main");
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);//调用其他的类
        } else {
            Toast.makeText(this, "很抱歉,未安装相应的程序", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 检查android平台移动终端是否安装指定程序
     */
    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

}