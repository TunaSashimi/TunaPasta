package com.tunaPasta05.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import com.tunaPasta05.R;

public class NotificationTest extends Activity {
	public final int HELLO_ID = 1;
	// 通知栏
	private NotificationManager notificationmanager;
	private Notification notification;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notificationtest);

		notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// 通知图标 ,状态栏显示的通知文本提示 ,通知产生的时间，会在通知信息里显示
		notification = new Notification(R.drawable.notification_icon, "Hello",
				System.currentTimeMillis());

		Intent notificationIntent = new Intent(this, NofificastionPrompt.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);

//		notification.setLatestEventInfo(this, "我的通知标题", "我的通知内容", contentIntent);// 修改为最新的通知内容

		// 添加声音
		// 默认提示
		notification.defaults |= Notification.DEFAULT_SOUND;// |=,即为与等于保证几种效果可以并行
		// 要使用应用程序指定的声音，则传递一个Uri引用给sound属性。以下例子使用已保存在设备SD卡上的音频文件作为提示音：
		// notification.sound =
		// Uri.parse("file:///sdcard/notification/ringer.mp3");

		// 添加振动
		// 默认震动
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		// 要自定义震动模式，须给vibrate属性传递一个long 类型的数组：
		// 长整型数组定义了震动开和关交替的时间（毫秒）。第一个数是开始振动前的等待时间（震动关闭），
		// 第二个数是第一次开启振动的持续时间，第三个数是下一次关闭时间，如此类推。振动模式的持续时间没有限制，
		// 但不能设置为重复振动。
		// long[] vibrate = {10000,20000,30000,40000};
		// notification.vibrate = vibrate;

		// 添加闪光
		notification.defaults |= Notification.DEFAULT_LIGHTS;
		// 例实现了绿色光闪烁300毫秒间歇1秒的闪光。
		// notification.ledARGB = 0xff00ff00;
		// notification.ledOnMS = 300;
		// notification.ledOffMS = 1000;
		// notification.flags = Notification.FLAG_SHOW_LIGHTS;

		// 利用Notification的属性和标志位，可以给通知添加更多的特性。
		// 下面列出了其中一些常用的特性：
		// “FLAG_AUTO_CANCEL”标志
		// 在flags属性中增加此标志，则在通知窗口点选后能自动取消通知。
		// “FLAG_INSISTENT”标志
		// 在flags属性中增加此标志，则在用户响应前一直循环播放声音。
		// “FLAG_ONGOING_EVENT”标志
		// 在flags属性中增加此标志，则将通知放入通知窗口的“正在运行”（Ongoing）组中。表示应用程序正在运行——进程仍在后台运行，即使应用程序不可见（比如播放音乐或接听电话）。
		// “FLAG_NO_CLEAR”标志
		// 在flags属性中增加此标志，表示通知不允许被“清除通知”按钮清除。这在期望通知保持运行时特别有用。
		// number属性
		// 表示通知所代表的事件数量。此数字显示在状态栏图标上。要利用此属性，必须在第一次创建通知时设为1。（如果只是在更新通知时才把此值从0改成任意大于0的数，则数字不会显示出来。）
		// iconLevel属性
		// 表示通知图标当前的LevelListDrawable等级。通过改变这个值，可以在状态栏中显示图标的动画，这个值与LevelListDrawable中drawable的定义相关。详情请参阅LevelListDrawable。
		// 程序能自定义更多特性，详情请参阅Notification。

		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notificationmanager.notify(HELLO_ID, notification);
	}
}
