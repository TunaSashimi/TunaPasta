package com.tunaPasta13.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta13.R;

//如果要实现这种功能，我们需要关注的是Thread类里面的一个接口,
//UncaughtExceptionHandler，还有一个设置Thread.setDefaultUncaughtExceptionHandler(),
//UncaughtExceptionHandler 这个接口是当Thread因为未被捕获的异常而要被终止的时候，会被调用的回调接口。
//而Thread.setDefaultUncaughtExceptionHandler()这个方法，则是设置当线程由于未捕获到异常而突然终止,
//并且没有为该线程定义其他处理程序时所调用的默认处理程序。
//因此，如果我们想自己处理程序要崩溃时的处理逻辑,我们只需要实现UncaughtExceptionHandler接口,
//并调用Thread.setDefaultUncaughtExceptionHandler()设置即可。

public class CustomExceptionTest extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customexceptiontest);
		// 注册默认的未捕捉异常处理类
		Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
		AppManager.getAppManager().addActivity(this);
	}

	public void onClick(View view) {
		// 除零错误，程序会崩溃
		int c = 1 / 0;
	}

}
