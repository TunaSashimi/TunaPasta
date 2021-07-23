package com.tunaPasta19.widget;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

public class UIUtils {
	private static Handler shared_handler = null;
	
	public static Handler getHandler() {
		return shared_handler;
	}

	public static boolean isMainThread() {
		return Looper.myLooper() == Looper.getMainLooper();
	}
	
	public static void assertInMainThread() {
		if (!isMainThread())
			throw new RuntimeException("Main thread assertion failed");
	}
	
	public static void recycleBitmap(Bitmap bm) {
		if (bm != null)
			bm.recycle();
	}
	
	public static <T> T callInMainThread(Callable<T> call) throws Exception {
		if (isMainThread())
			return call.call();
		else {
			FutureTask<T> task = new FutureTask<T>(call);
			getHandler().post(task);
			return task.get();
		}
	}
}
