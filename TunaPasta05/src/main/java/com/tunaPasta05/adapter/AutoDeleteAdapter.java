package com.tunaPasta05.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.tunaPasta05.R;
import com.tunaPasta05.tool.ImageLoadTask;

public class AutoDeleteAdapter extends BaseAdapter {
	private Context context;
	private String[] images;

	public AutoDeleteAdapter(Context context, String[] images) {
		this.context = context;
		this.images = images;

		// 如果存在sd卡,则优先建立缓存图片的文件夹,避免ImageLoadTask中文件夹不存在
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File file = new File(Environment.getExternalStorageDirectory()
					+ "/ceshiwenjianjia/");
			if (!file.exists()) {
				file.mkdir();
			}

			// 再清除冗余文件
			removeCache(Environment.getExternalStorageDirectory()
					+ "/ceshiwenjianjia/");

		}
	}

	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.autodeletetestitem, null);
		}
		ImageView imageview = (ImageView) convertView.findViewById(R.id.imageview);
		// 如果不存在sd卡,则改成默认图片后开启线程获取
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			try {
				String filename = images[position];

				filename = filename.replaceAll("/", "").replaceAll(":", ""); // 文件名里面不能带,和:的不然创建的时候会报错

				FileInputStream fis = new FileInputStream(
						Environment.getExternalStorageDirectory()
								+ "/ceshiwenjianjia/" + filename);

				Bitmap bitmap = BitmapFactory.decodeStream(fis);

				imageview.setImageBitmap(bitmap);

			} catch (FileNotFoundException e) {

				e.printStackTrace();

				new ImageLoadTask(context).execute(images[position],imageview);

			}

		} else {

			new ImageLoadTask(context).execute(images[position], imageview);

		}
		return convertView;
	}

	private void removeCache(String dirPath) {

		File dir = new File(dirPath);
		System.out.println(dir);
		File[] files = dir.listFiles();
		System.out.println(files);
		if (files == null) {
			return;
		}
		float dirSize = 0;
		for (int i = 0; i < files.length; i++) {
			dirSize += files[i].length();
		}
		dirSize = dirSize / 1024 / 1024;

		System.out.println("目前占用大小为" + dirSize + "MB");

		// 当文件总大小大于规定的CACHE_SIZE或者sdcard剩余空间小于FREE_SD_SPACE_NEEDED_TO_CACHE的规定

		if (dirSize > 20 || 20 > freeSpaceOnSd()) {
			int removeFactor = (int) ((0.4 * files.length) + 1);

			Arrays.sort(files, new FileLastModifSort());

			for (int i = 0; i < removeFactor; i++) {
				files[i].delete();
			}
		}

	}

	// 获取sd卡上的剩余空间
	private int freeSpaceOnSd() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) { // 如果内存卡插着的情况下,计算sdcard上的剩余空间
			StatFs statfs = new StatFs(Environment
					.getExternalStorageDirectory().getPath()); // 取得sdcard文件路径
			long blocSize = statfs.getBlockSize();// 获取block的SIZE
			long availaBlock = statfs.getAvailableBlocks();// 空闲的Block的数量
			return (int) ((availaBlock * blocSize) / 1024 / 1024);// 内存卡上的剩余空间MB单位,再多除1024为GB单位
		}
		return 0;
	}

	class FileLastModifSort implements Comparator<File> {
		public int compare(File arg0, File arg1) {
			if (arg0.lastModified() > arg1.lastModified()) {
				return 1;
			} else if (arg0.lastModified() == arg1.lastModified()) {
				return 0;
			} else {
				return -1;
			}
		}
	}
}
