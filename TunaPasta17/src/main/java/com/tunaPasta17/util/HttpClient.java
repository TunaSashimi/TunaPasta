package com.tunaPasta17.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by pamelayuan on  2017/7/26 16:09.
 */
public class HttpClient {


    protected static Handler handler;

    public static final int HTTP_SECCESS = -100;
    public static final int HTTP_ERROR = -200;
    public static final int CONN_TYPE_UNKNOW = 0;
    public static final int CONN_TYPE_WIFI = 1;
    public static final int CONN_TYPE_APN_NET = 2;
    public static final int CONN_TYPE_APN_CMWAP = 3;
    // public static final int CONN_TYPE_APN_UNIWAP = 4;
    public static final int CONN_TYPE_APN_CTWAP = 5;
    public static final int CONN_TYPE_CLOSE = 6;
    public static final int CONNECT_TYPE_MOBILE_BASE = 200000;
    public static final int CONNECT_TYPE_WIFI = 100000;
    public static final int CONNECT_TYPE_CLOSE = 300000;
    public static int mConnType = CONNECT_TYPE_CLOSE;



    public static int getConnType(Context context) {

		/* 兼容老的没有加入context的 */

        if (null != context) {
            NetworkInfo netInfo = ((ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();
            if (netInfo != null) {
                if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // 获取代理host
                    String pHost = android.net.Proxy.getDefaultHost();
                    int pPort = android.net.Proxy.getDefaultPort();
                    String apnType = netInfo.getExtraInfo();
                    int subType = netInfo.getSubtype();
                    mConnType = CONNECT_TYPE_MOBILE_BASE + subType;

                    // 是移动并且用的是cmwap则使用代理
                    if ((null != pHost && -1 != pPort)
                            && (null != apnType && (apnType.toLowerCase(
                            Locale.getDefault()).equals("cmwap")
                            || apnType.toLowerCase(Locale.getDefault())
                            .equals("uniwap") || apnType
                            .toLowerCase(Locale.getDefault()).equals(
                                    "3gwap")))) {

                        return CONN_TYPE_APN_CMWAP;
                    }

                    // if ((null != pHost && -1 != pPort)
                    // && (null != apnType
                    // &&
                    // (apnType.toLowerCase(Locale.getDefault()).equals("uniwap")
                    // ||
                    // apnType.toLowerCase(Locale.getDefault()).equals("3gwap"))))
                    // {
                    //
                    // return CONN_TYPE_APN_UNIWAP;
                    // }

                    if ((null != pHost && -1 != pPort)
                            && (null != apnType && (apnType.toLowerCase(Locale
                            .getDefault()).equals("ctwap")))) {
                        return CONN_TYPE_APN_CTWAP;
                    }
                    return CONN_TYPE_APN_NET;
                }
                mConnType = CONNECT_TYPE_WIFI;
                return CONN_TYPE_WIFI;
            }

            return CONN_TYPE_CLOSE;
        }

        return CONN_TYPE_UNKNOW;
    }

    public static String getUrlProtocol(String url) {
        if (url == null || url.length() == 0) {
            return null;
        }

        String HTTP = "http://";
        String HTTPS = "https://";
        if (url.startsWith(HTTP)) {
            return HTTP;
        } else if (url.startsWith(HTTPS)) {
            return HTTPS;
        }
        return null;
    }

    public static String getUrlHost(String url) {
        String protocol = getUrlProtocol(url);
        if (protocol == null) {
            return null;
        }
        String subUrl = url.substring(protocol.length());
        int index = subUrl.indexOf('/');
        if (index == -1) {
            return subUrl;
        } else {
            return subUrl.substring(0, index);
        }
    }

    HttpsURLConnection getConn(Context context, String urlPath) {

        HttpsURLConnection conn = null;
        try {
            URL url = new URL(urlPath);
            switch (getConnType(context)) {
//                case CONN_TYPE_APN_CMWAP:
//                    String protocol = getUrlProtocol(urlPath);
//                    String host = getUrlHost(urlPath);
//                    if (protocol == null
//                            || host == null
//                            || urlPath.length() <= protocol.length()
//                            + host.length()) {
//                        conn = (HttpsURLConnection) url.openConnection();
//                        break;
//                    }
//
//                    String urlAssem = protocol + android.net.Proxy.getDefaultHost()
//                            + urlPath.substring(protocol.length() + host.length());
//                    url = new URL(urlAssem);
//                    conn = (HttpsURLConnection) url.openConnection();
//                    conn.setRequestProperty("X-Online-Host", host);
//                    conn.setRequestProperty("Host",
//                            android.net.Proxy.getDefaultHost());
//                    break;
                // case CONN_TYPE_APN_UNIWAP:
                // InetSocketAddress uniInetAddr = new InetSocketAddress(mProxyHost,
                // mProxyPort);
                // java.net.Proxy uniNetProxy = new java.net.Proxy(
                // java.net.Proxy.Type.HTTP, uniInetAddr);
                // conn = (HttpURLConnection) url.openConnection(uniNetProxy);
                // break;
                case CONN_TYPE_APN_CTWAP:
                    InetSocketAddress ctIndetAddr = new InetSocketAddress(
                            android.net.Proxy.getDefaultHost(),
                            android.net.Proxy.getDefaultPort());
                    java.net.Proxy ctNetProxy = new java.net.Proxy(
                            java.net.Proxy.Type.HTTP, ctIndetAddr);
                    conn = (HttpsURLConnection) url.openConnection(ctNetProxy);
                    break;
                case CONN_TYPE_CLOSE:

                    return null;

                default:
                    conn = (HttpsURLConnection) url.openConnection();
                    break;
            }

            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            conn.setRequestMethod("GET");

        } catch (IOException e) {

            conn = null;
        } catch (Exception e) {

            e.printStackTrace();
            conn = null;
        }

        return conn;
    }
    public byte[] request(Context context, String urlPath) {
        HttpURLConnection conn = null;
        DataOutputStream out  = null;
        InputStream in   = null;
        ByteArrayOutputStream baos = null;
        byte[] rspData = null;
        try{
            conn = getConn(context,urlPath);
            if (null == conn) {
                return null;
            }
            int response = conn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK
                    || response == HttpURLConnection.HTTP_PARTIAL
                    || response == -1) {

                String contentType = conn.getContentType();
                contentType = contentType == null ? "" : contentType.toLowerCase();
                in = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int ch;
                while ((ch = in.read()) != -1) {
                    baos.write(ch);
                }

                baos.flush();
                rspData = baos.toByteArray();

            }
        } catch (SocketTimeoutException e){
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }catch (Exception e) {
            e.printStackTrace();

        } catch (Throwable e) {
            e.printStackTrace();

        } finally {
            try{
                if(baos != null){
                    baos.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            try{
                if(in != null){
                    in.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            try{
                if(out != null){
                    out.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            if (conn != null) {
                conn.disconnect();
            }
        }


        return rspData;
    }



}