package com.tunaPasta19.application;

import java.util.HashMap;

import android.app.Application;

import com.tunaPasta19.tool.LockPatternUtils;

public class DataTrans extends Application {
	//
	public static HashMap<String, Object> tunaGraphicsMap = new HashMap<String, Object>();

	private static DataTrans mInstance;


	private LockPatternUtils mLockPatternUtils;
	public LockPatternUtils getLockPatternUtils() {
		return mLockPatternUtils;
	}

	public static DataTrans getInstance() {
		return mInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		mLockPatternUtils = new LockPatternUtils(this);
	}
}
