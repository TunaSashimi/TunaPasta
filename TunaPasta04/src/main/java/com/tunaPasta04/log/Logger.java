package com.tunaPasta04.log;

import android.util.Log;

public class Logger {
	private Class<?> clazz;

	public Logger(Class<?> clazz){
		this.clazz = clazz;
	}
	
	public void info(String mess){
		Log.i(clazz.getName(), mess);
	}
	
	public void info(String mess, Throwable tr){
		Log.i(clazz.getName(), mess, tr);
	}
	
	public void debug(String mess){
		Log.d(clazz.getName(), mess);
	}
	
	public void debug(String msg,Throwable tr){
		Log.d(clazz.getName(), msg,tr);
	}
	
	public void error(String msg){
		Log.e(clazz.getName(), msg);
	}
	
	public void error(String msg, Throwable tr){
		Log.e(clazz.getName(), msg, tr);
	}
	
	public void warning(String msg){
		Log.w(clazz.getName(), msg);
	}
	
	public void warning(String msg, Throwable tr){
		Log.w(clazz.getName(), msg, tr);
	}
	
	public String getStackTraceString(Throwable tr){
		return Log.getStackTraceString(tr);
	}
}
