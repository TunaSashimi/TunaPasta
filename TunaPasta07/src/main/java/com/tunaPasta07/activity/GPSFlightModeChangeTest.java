package com.tunaPasta07.activity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.tunaPasta07.R;

public class GPSFlightModeChangeTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gpsflightmodechangetest);

        Button button01 = findViewById(R.id.button01);
        Button button02 = findViewById(R.id.button02);
        Button button03 = findViewById(R.id.button03);
        Button button04 = findViewById(R.id.button04);

        button01.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(GPSFlightModeChangeTest.this, "WIFI正在打开中,请稍后", Toast.LENGTH_LONG).show();

                toggleWiFi(GPSFlightModeChangeTest.this, true);
            }
        });

        button02.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(GPSFlightModeChangeTest.this, "移动网络正在打开中,请稍后", Toast.LENGTH_LONG).show();

                toggleMobileData(GPSFlightModeChangeTest.this, true);
            }
        });

        button03.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(GPSFlightModeChangeTest.this, "GPS定位正在切换中,请稍后", Toast.LENGTH_LONG).show();

                toggleGPS();
            }
        });
        button04.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(GPSFlightModeChangeTest.this, "飞行模式正在切换中,请稍后", Toast.LENGTH_LONG).show();

                flightMode();
            }
        });

    }

//	打开WiFi最简单，直接调用系统的方法即可：

    /**
     * WIFI网络开关
     */
    private void toggleWiFi(Context context, boolean enabled) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wm.setWifiEnabled(enabled);
    }

//	打开移动网络比较麻烦，系统没有直接提供开放的方法，
//	只在ConnectivityManager类中有一个不可见的setMobileDataEnabled方法，
//	查看源代码发现，它是调用IConnectivityManager类中的setMobileDataEnabled(boolean)方法。
//	由于方法不可见，只能采用反射来调用：

    /**
     * 移动网络开关
     */
    private void toggleMobileData(Context context, boolean enabled) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        Class<?> conMgrClass = null; // ConnectivityManager类
        Field iConMgrField = null; // ConnectivityManager类中的字段
        Object iConMgr = null; // IConnectivityManager类的引用
        Class<?> iConMgrClass = null; // IConnectivityManager类
        Method setMobileDataEnabledMethod = null; // setMobileDataEnabled方法
        try {
            // 取得ConnectivityManager类
            conMgrClass = Class.forName(conMgr.getClass().getName());
            // 取得ConnectivityManager类中的对象mService
            iConMgrField = conMgrClass.getDeclaredField("mService");
            // 设置mService可访问
            iConMgrField.setAccessible(true);
            // 取得mService的实例化类IConnectivityManager
            iConMgr = iConMgrField.get(conMgr);
            // 取得IConnectivityManager类
            iConMgrClass = Class.forName(iConMgr.getClass().getName());
            // 取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法
            setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod(
                    "setMobileDataEnabled", Boolean.TYPE);
            // 设置setMobileDataEnabled方法可访问
            setMobileDataEnabledMethod.setAccessible(true);
            // 调用setMobileDataEnabled方法
            setMobileDataEnabledMethod.invoke(iConMgr, enabled);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

//	 打开GPS也比较麻烦，与打开移动网络一样，没有直接的方法。
//	在网上搜索了一下，据说采用反射去调用系统的方法仍然会失败，
//	有网友用另外一条路实现了同样的功能，在2.3.5系统测试可以通过，
//	在4.1.1系统中测试无效，待解决：

    /**
     * <p>
     * GPS开关
     * <p>
     * 当前若关则打开
     * <p>
     * 当前若开则关闭
     */
    private void toggleGPS() {
        Intent gpsIntent = new Intent();
        gpsIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        gpsIntent.addCategory("android.intent.category.ALTERNATIVE");
        gpsIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(this, 0, gpsIntent, 0).send();
        } catch (CanceledException e) {
            e.printStackTrace();
        }
    }

    /**
     * 飞行模式
     */
    private void flightMode() {

        ContentResolver cr = getContentResolver();
        if (Settings.System.getString(cr, Settings.System.AIRPLANE_MODE_ON).equals("0")) {
            //获取当前飞行模式状态,返回的是String值0,或1.0为关闭飞行,1为开启飞行
            //如果关闭飞行,则打开飞行
            Settings.System.putString(cr, Settings.System.AIRPLANE_MODE_ON, "1");
            Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            sendBroadcast(intent);
        } else {
            //否则关闭飞行
            Settings.System.putString(cr, Settings.System.AIRPLANE_MODE_ON, "0");
            Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            sendBroadcast(intent);
        }

    }


}
