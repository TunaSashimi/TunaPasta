package com.tunaPasta19.widget;
import java.io.BufferedInputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

import com.tunaPasta19.entity.WaterFallTestFlowTag;

public class WaterFallTestFlowImageView extends ImageView implements View.OnClickListener{
	public WaterFallTestFlowTag flowTag;
	private Context context;
	public Bitmap bitmap;
	public int columnIndex;				// 图片属于第几列
	public int rowIndex;					// 图片属于第几行
	public Handler viewHandler;
	public WaterFallTestFlowImageView(Context context) {
		super(context);
		this.context = context;
		setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		Toast.makeText(context, "单击：" + this.flowTag.fileName,1000).show();
	}
	/**
	 * 加载图片
	 */
	public void LoadImage() {
			new LoadImageThread().start();
	}
	/**
	 * 重新加载图片
	 */
	public void Reload() {
		if (this.bitmap == null && flowTag != null) {
			new ReloadImageThread().start();
		}
	}
	/**
	 * 回收内存
	 */
	public void recycle() {
		setImageBitmap(null);
		if ((this.bitmap == null) || (this.bitmap.isRecycled()))
			return;
		this.bitmap.recycle();
		this.bitmap = null;
	}
	
	class ReloadImageThread extends Thread {
		public void run() {
				BufferedInputStream buf;
				try {
					buf = new BufferedInputStream(flowTag.assetManager.open(flowTag.fileName));
					bitmap = BitmapFactory.decodeStream(buf);
				} catch (IOException e) {
					e.printStackTrace();
				}
				((Activity) context).runOnUiThread(new Runnable() {
					public void run() {
						if (bitmap != null) {					// 此处在线程过多时可能为null
							setImageBitmap(bitmap);
						}
					}
				});
		}
	}
	
	class LoadImageThread extends Thread {
		public void run() {
				BufferedInputStream buf;
				try {
					buf = new BufferedInputStream(flowTag.assetManager.open(flowTag.fileName));
					bitmap = BitmapFactory.decodeStream(buf);
				} catch (IOException e) {
					e.printStackTrace();
				}
				((Activity) context).runOnUiThread(new Runnable() {
					public void run() {
						if (bitmap != null) {// 此处在线程过多时可能为null
							int width = bitmap.getWidth();// 获取真实宽高
							int height = bitmap.getHeight();
							int layoutHeight = height * flowTag.ItemWidth/ width;		// 调整高度
							setLayoutParams(new LayoutParams(flowTag.ItemWidth,layoutHeight));
							setImageBitmap(bitmap);
							Message message = viewHandler.obtainMessage(1, width,layoutHeight, WaterFallTestFlowImageView.this);
							viewHandler.sendMessage(message);
						}
					}
				});
		}
	}
}
