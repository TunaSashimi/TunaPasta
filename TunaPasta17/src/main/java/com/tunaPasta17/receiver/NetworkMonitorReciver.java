package com.tunaPasta17.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkMonitorReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //如果是在开启wifi连接和有网络状态下
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (NetworkInfo.State.CONNECTED == info.getState()) {
                //连接状态 处理自己的业务逻辑
            } else {
                Toast.makeText(context, "网络链接失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}