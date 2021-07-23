package com.tunaPasta05.tool;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class ConnectionHelp{
	/**
	 * 判断是否有可用网络,没有的话加提示
	 */
	public static boolean netStatus(Context context){
		boolean flag = false;
		ConnectivityManager connectmanager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = connectmanager.getActiveNetworkInfo();
		if (networkinfo != null) {
			flag = networkinfo.isAvailable();
			if (networkinfo.getTypeName().equals("WIFI")) {
				Toast.makeText(context, "WIFI", Toast.LENGTH_LONG).show();
			} else if (networkinfo.getExtraInfo().equals("uninet")) {
				Toast.makeText(context, "UNINET", Toast.LENGTH_LONG).show();
			} else if (networkinfo.getExtraInfo().equals("uniwap")) {
				Toast.makeText(context, "UNIWAP", Toast.LENGTH_LONG).show();
			} else if (networkinfo.getExtraInfo().equals("3gwap")) {
				Toast.makeText(context, "3GWAP", Toast.LENGTH_LONG).show();
			} else if (networkinfo.getExtraInfo().equals("3gnet")) {
				Toast.makeText(context, "3GNET", Toast.LENGTH_LONG).show();
			}
		}
		if (flag == false) {
			Toast.makeText(context, "没有网络", Toast.LENGTH_LONG).show();
		}
		return flag;
	}

	/**
	 * 判断是否开启GPS
	 * 
	 * @return
	 */
	public static boolean openGPSSettings(Context context){
		LocationManager locationmanager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		if (locationmanager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getNetworkType(Context context){
		String strNetworkType = "";
		ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
				strNetworkType = "WIFI";
			} else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
				String _strSubTypeName = networkInfo.getSubtypeName();
				Log.e("cocos2d-x", "Network getSubtypeName : " + _strSubTypeName);
				// TD-SCDMA networkType is 17
				int networkType = networkInfo.getSubtype();
				switch (networkType) {
					case TelephonyManager.NETWORK_TYPE_GPRS:
					case TelephonyManager.NETWORK_TYPE_EDGE:
					case TelephonyManager.NETWORK_TYPE_CDMA:
					case TelephonyManager.NETWORK_TYPE_1xRTT:
					case TelephonyManager.NETWORK_TYPE_IDEN: // api < 8 : replace by 11
						strNetworkType = "2G";
						break;
					case TelephonyManager.NETWORK_TYPE_UMTS:
					case TelephonyManager.NETWORK_TYPE_EVDO_0:
					case TelephonyManager.NETWORK_TYPE_EVDO_A:
					case TelephonyManager.NETWORK_TYPE_HSDPA:
					case TelephonyManager.NETWORK_TYPE_HSUPA:
					case TelephonyManager.NETWORK_TYPE_HSPA:
					case TelephonyManager.NETWORK_TYPE_EVDO_B: // api < 9 :  replace by 14
					case TelephonyManager.NETWORK_TYPE_EHRPD: // api < 11 : replace by 12
					case TelephonyManager.NETWORK_TYPE_HSPAP: // api < 13 : replace by 15
						strNetworkType = "3G";
						break;
					case TelephonyManager.NETWORK_TYPE_LTE: // api < 11 : replace by 13
						strNetworkType = "4G";
						break;
					default:
						// http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
						if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
							strNetworkType = "3G";
						} else {
							strNetworkType = _strSubTypeName;
						}
						break;
				}
			}
		}
		return strNetworkType;
	}

}
