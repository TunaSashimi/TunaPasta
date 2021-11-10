package com.tunaPasta11.tools;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

public class DLog {

	private static boolean isDebug = true;
	private static final String SHOOT_ME = "DUODUO";
	public static final String SHOOT_SOCKET = "DUODUO_SOCKET";
	public static final String SHOOT_LOCTION = "DUODUO_LOCATION";
	public static final String SHOOT_NEW_SOCKET = "DUODUO_NEW_SOCKET";

	public static void setIsDebug(boolean isDe) {
		isDebug = isDe;
	}

	public static boolean getIsDebug() {
		return isDebug;
	}

	public static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(SHOOT_ME, tag + " :" + msg);
		}
	}

	public static void d(String nameString, String tag, String msg) {
		if (isDebug) {
			Log.d(nameString, tag + " :" + msg);
		}
	}

	public static void e(String tag, String msg) {
		if (isDebug) {
			Log.e(SHOOT_ME, tag + " :" + msg);
			wirteFile(msg);

		}
	}

	public static void v(String tag, String msg) {
		if (isDebug) {
			Log.v(SHOOT_ME, tag + " :" + msg);
		}
	}

	public static void error(String tag, Throwable ex) {
		if (isDebug) {
		}

	}

	private static void wirteFile(String errorMsg) {
		try {
			File file = new File(Environment.getExternalStorageDirectory() + "/NemesisJS/Cache/TmpFile/LOG");

			if (file.exists() == false)
				file.mkdirs();

			File file_log = new File(Environment.getExternalStorageDirectory() + "/NemesisJS/Cache/TmpFile/LOG/Log.txt");

			if (file_log.exists() == true) {
			} else {
				file_log.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(file_log, true);

			fos.write(errorMsg.getBytes());
			fos.flush();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
