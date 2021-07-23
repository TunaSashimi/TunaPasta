package com.tunaPasta11.tools;

import android.app.Application;

public class DataTrans extends Application {
	private static DataTrans mInstance;
	private LockPatternUtils mLockPatternUtils;

	public static DataTrans getInstance() {
		return mInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		mLockPatternUtils = new LockPatternUtils(this);
	}

	public LockPatternUtils getLockPatternUtils() {
		return mLockPatternUtils;
	}
}
