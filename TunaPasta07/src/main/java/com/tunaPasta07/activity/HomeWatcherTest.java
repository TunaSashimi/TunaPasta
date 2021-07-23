package com.tunaPasta07.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tunaPasta07.R;
import com.tunaPasta07.tools.HomeWatcher;
import com.tunaPasta07.tools.HomeWatcher.OnHomePressedListener;

//尝试用复写onKeyDown事件用if(keyCode == KeyEvent.KEYCODE_HOME)监听,
//但实际上，这样是行不通的，因为此时home键的消息在framework层就已经被拦截，
//所以，我们在应用中通过此方法是无法监听到Home键的消息的。
//那么我们如何捕获Home键事件，并作出相应处理呢？其实系统还是给我们发出广播的，
//下面，就跟大家分享一下，封装好了的监听Home键的方法：

//HomeWatcher类：

/**
 * 应用HomeWatcher小例
 */
public class HomeWatcherTest extends Activity {
	private HomeWatcher mHomeWatcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.homewatchertest);

		mHomeWatcher = new HomeWatcher(this);
		mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
			public void onHomePressed() {
				Toast.makeText(HomeWatcherTest.this, "Home键被按下", Toast.LENGTH_LONG).show();
			}

			public void onHomeLongPressed() {
				Toast.makeText(HomeWatcherTest.this, "Home键被长按", Toast.LENGTH_LONG).show();
			}
		});
		mHomeWatcher.startWatch();
	}

//	查看BroadcastReceiver API文档，了解到广播的onReceive方法执行完，
//	然后广播就不再处于active状态了。所以当发出信息成功之后，
//	通过Toast.makeText弹出提示告知用户，然后onReceive方法结束，
//	广播的生命周期就结束了。
//	从中还了解到onReceive方法中可以执行Toast.makeText()
//	表现了广播的onReceive方法是在主线程中执行的，否则就无法执行Toast.makeText方法了。
	
	@Override
	protected void onPause() {
		super.onPause();
//		mHomeWatcher.stopWatch();// 在onPause中停止监听，不然会报错的。
	}
}
