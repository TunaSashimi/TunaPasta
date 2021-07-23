package com.tunaPasta14.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

/**
 * 管理联网操作，包括管理url参数、下载APK包、获取任务字符串。<br/>
 */
public class NetworkTool{
	/**
	 * 开启一个HTTP链接。
	 */
	public static HttpURLConnection openUrl(Context context, String urlStr){
		// LogOut.out("Network", "urlStr[" + urlStr + "]");
		System.out.println("Network==>urlStr[" + urlStr + "]");

		URL urlURL = null;
		HttpURLConnection httpConn = null;
		try {
			urlURL = new URL(urlStr);
			// 需要android.permission.ACCESS_NETWORK_STATE
			// 在没有网络的情况下，返回值为null。
			NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
			// 如果是使用的运营商网络
			if (networkInfo != null) {
				if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
					// 获取默认代理主机ip
					String host = android.net.Proxy.getDefaultHost();
					// 获取端口
					int port = android.net.Proxy.getDefaultPort();
					if (host != null && port != -1) {
						// 封装代理連接主机IP与端口号。
						InetSocketAddress inetAddress = new InetSocketAddress(host, port);
						// 根据URL链接获取代理类型，本链接适用于TYPE.HTTP
						java.net.Proxy.Type proxyType = java.net.Proxy.Type.valueOf(urlURL.getProtocol().toUpperCase());
						java.net.Proxy javaProxy = new java.net.Proxy(proxyType, inetAddress);
						httpConn = (HttpURLConnection) urlURL.openConnection(javaProxy);
					} else {
						httpConn = (HttpURLConnection) urlURL.openConnection();
					}
				} else {
					httpConn = (HttpURLConnection) urlURL.openConnection();
				}
				httpConn.setDoInput(true);
			} else {
				// LogOut.out(this, "No Avaiable Network");
			}
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return httpConn;
	}

	/** 启动链接并将RespondCode值返回。 */
	public static int connect(HttpURLConnection httpConn){
		int code = -1;
		if (httpConn != null) {
			try {
				httpConn.connect();
				code = httpConn.getResponseCode();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// LogOut.out("NetworkTool", "respond_code=" + code);
		System.out.println("NetworkTool==>respond_code=" + code);
		return code;
	}

	/**
	 * 将指定的HTTP链接内容存储到指定的的文件中。<br/>
	 * 返回值仅当参考。<br/>
	 * 
	 * @param httpConn
	 * @param filePath
	 *            指定存储的文件路径。
	 */
	public static boolean download2File(HttpURLConnection httpConn, String filePath){
		boolean result = true;
		File file = new File(filePath);
		FileOutputStream fos = null;
		byte[] data = new byte[1024];
		int readLength = -1;
		InputStream is = null;
		try {
			fos = new FileOutputStream(file);
			is = httpConn.getInputStream();
			while ((readLength = is.read(data)) != -1) {
				fos.write(data, 0, readLength);
				fos.flush();
			}
			fos.flush();
		} catch (IOException ie) {
			result = false;
			ie.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 将bean资源下载。<br/>
	 * 支持断点续传。
	 * 
	 */
	public static void download2File(Context context, BeanDownload bean, Handler handler){
		String filePath = BreakPointsResumeTest.RES_LOAD_FOLDER + bean.name + ".tmp";
		HttpURLConnection httpConn = null;
		File file = new File(filePath);
		RandomAccessFile randomFile = null;
		FileOutputStream fos = null;
		int dataBlockLength = 2048;
		byte[] data = new byte[dataBlockLength];
		int readLength = -1;
		InputStream is = null;
		try {
			if (bean.size <= 0) {
				bean.loadedSize = 0;
				if (file.getParentFile().exists() == false) {
					file.getParentFile().mkdirs();
				}
				if (file.exists() == false) {
					file.createNewFile();
				}
				// 采用普通的下载方式
				fos = new FileOutputStream(file);
				httpConn = openUrl(context, bean.url);
				int respondCode = connect(httpConn);
				if (respondCode == HttpURLConnection.HTTP_OK) {
					bean.size = httpConn.getContentLength();
					is = httpConn.getInputStream();
					while ((readLength = is.read(data)) != -1 && bean.enable) {
						fos.write(data, 0, readLength);
						bean.loadedSize += readLength;
						handler.sendEmptyMessage(BreakPointsResumeTest.REFRESH);
					}
				}
			} else {
				// 采用断点续传方式
				randomFile = new RandomAccessFile(file, "rw");
				randomFile.setLength(bean.size);
				httpConn = openUrl(context, bean.url);
				httpConn.setRequestProperty("Range", "bytes=" + bean.loadedSize + "-" + (bean.size - 1));
				int respondCode = connect(httpConn);
				if (respondCode == HttpURLConnection.HTTP_PARTIAL) {
					is = httpConn.getInputStream();
					while ((readLength = is.read(data)) != -1 && bean.enable) {
						randomFile.seek(bean.loadedSize);
						randomFile.write(data, 0, readLength);
						bean.loadedSize += readLength;
						handler.sendEmptyMessage(BreakPointsResumeTest.REFRESH);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (httpConn != null) {
					disconnect(httpConn);
				}
				if (fos != null) {
					fos.close();
				}
				if (randomFile != null) {
					randomFile.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** 读取HttpURLConnection的数据并关闭相关流。 */
	public static byte[] fetchData_doClose(HttpURLConnection httpConn){
		byte[] data = null;
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		int read = -1;
		try {
			baos = new ByteArrayOutputStream();
			is = httpConn.getInputStream();
			while ((read = is.read()) != -1) {
				baos.write(read);
			}
			data = baos.toByteArray();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (baos != null) {
					baos.close();
				}
				if (httpConn != null) {
					httpConn.disconnect();
				}
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
		return data;
	}

	public static void disconnect(HttpURLConnection httpConn){
		if (httpConn != null) {
			httpConn.disconnect();
		}
	}
}