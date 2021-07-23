package com.tunaPasta05.activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.app.Activity;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.TextView;

import com.tunaPasta05.R;

public class GetWifiInformationTest extends Activity {

	private WifiManager wifiManager;
	private DhcpInfo dhcpInfo;
	private WifiInfo wifiInfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		 setContentView(R.layout.getwifiinformationtest);
		
		wifiManager = (WifiManager) this.getApplicationContext().getSystemService(WIFI_SERVICE);
		dhcpInfo = wifiManager.getDhcpInfo();
		wifiInfo = wifiManager.getConnectionInfo();
		int ip = wifiInfo.getIpAddress();
		int speed = wifiInfo.getLinkSpeed();
		int networkId = wifiInfo.getNetworkId();
		int getRssi = wifiInfo.getRssi();
		String macAddr = wifiInfo.getMacAddress();
		String getSSID = wifiInfo.getSSID();
		String detail = wifiInfo.toString();
		String bssid = wifiInfo.getBSSID();
		StringBuffer tv = new StringBuffer();
		
		tv.append("ip :" + ip + "\n");
		tv.append("speed :" + speed + "\n");
		tv.append("macAddr :" + macAddr + "\n");
		tv.append("networkId :" + networkId + "\n");
		tv.append("getRssi :" + getRssi + "\n");
		tv.append("getSSID :" + getSSID + "\n");
		tv.append("detail :" + detail + "\n");
		tv.append("bssid :" + bssid + "\n");
		tv.append("dhcpInfo geteway is :" + dhcpInfo.gateway + "\n");
		tv.append("dhcpInfo mask is :" + dhcpInfo.netmask + "\n");
		tv.append("dhcpInfo ip is :" + dhcpInfo.ipAddress + "\n");
		tv.append("ip is :" + FormatIP(ip) + "\n");
		tv.append("geteway is :" + FormatIP(dhcpInfo.gateway) + "\n");
		tv.append("mask is :" + FormatIP(dhcpInfo.netmask) + "\n");

		TextView textview=(TextView) findViewById(R.id.text01);
		textview.setText(tv.toString());
	}

	// IP地址转化为字符串格式
	public String FormatIP(int IpAddress) {
		return Formatter.formatIpAddress(IpAddress);
	}

	/****
	 * 获取默认网关的ＩＰ地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getDefaultGatewayIp() throws Exception {
		try {
			Process result = Runtime.getRuntime().exec("su");
			BufferedReader output = new BufferedReader(new InputStreamReader(
					result.getInputStream()));
			String line = output.readLine();
			while (line != null) {

				line = output.readLine();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
}