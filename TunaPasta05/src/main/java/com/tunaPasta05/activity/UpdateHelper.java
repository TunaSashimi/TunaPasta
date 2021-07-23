package com.tunaPasta05.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

public class UpdateHelper {
	private Context context;

	private ProgressBar progressbar; // 进度条与通知ui刷新的handler和msg常量

	private static final int DOWN_UPDATE = 1;
	private static final int DOWN_OVER = 2;
	
	private int progress;
	private boolean interceptFlag = false;
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				progressbar.setProgress(progress);
				break;
			case DOWN_OVER:
				installApk();
				break;
			}
		}
	};

	public UpdateHelper(Context context) {
		this.context = context;
	}

	public void checkUpdateInfo() { // 外部接口让主Activity调用
		showNoticeDialog();
	}

	private void showNoticeDialog() {
		new Builder(context).setMessage("是否下载软件包?")
				.setPositiveButton("下载", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						showDownloadDialog();
					}
				}).setNegativeButton("取消", null).show();
	}

	private void showDownloadDialog() {
		progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
		progressbar.setScrollBarStyle(0);
		new Builder(context).setMessage("更新进度").setView(progressbar)
				.setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						interceptFlag = true;
					}
				}).show();

		downloadApk();
	}

	/**
	 * 下载apk
	 */
	private void downloadApk() {
		new Thread() {
			public void run() {
				try {
					URL url = new URL("http://file.liqucn.com/upload/2011/shipin/com.tencent.qqlive_3.6.0.5941_liqucn.com.apk");
					HttpURLConnection conn = (HttpURLConnection) url .openConnection();
					conn.connect(); // 建立连接
					
					int length = conn.getContentLength();
					
					InputStream is = conn.getInputStream();

					File file = new File( Environment.getExternalStorageDirectory() + "/yizeUpdate/");
					if (!file.exists()) {
						file.mkdir();
					}
					String apkFile = Environment.getExternalStorageDirectory() + "/yizeUpdate/Yize.apk";
					File ApkFile = new File(apkFile);
					FileOutputStream fos = new FileOutputStream(ApkFile);

					int count = 0;
					byte buf[] = new byte[1024];

					do {
						int numread = is.read(buf);
						count += numread;
						
						progress = (int) (((float) count / length) * 100);
						// 更新进度
						mHandler.sendEmptyMessage(DOWN_UPDATE);
						
						if (numread <= 0) {
							mHandler.sendEmptyMessage(DOWN_OVER);
							break;
						}
						fos.write(buf, 0, numread);
					} while (!interceptFlag);// 点击取消就停止下载.

					fos.close();
					is.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * 安装apk
	 */
	private void installApk() {
		File apkfile = new File(Environment.getExternalStorageDirectory()+ "/yizeUpdate/Yize.apk");
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),"application/vnd.android.package-archive");
		context.startActivity(i);
	}
}
