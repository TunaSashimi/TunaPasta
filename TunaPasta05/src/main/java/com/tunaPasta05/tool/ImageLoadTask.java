package com.tunaPasta05.tool;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StatFs;
import android.widget.ImageView;

import com.tunaPasta05.R;


public class ImageLoadTask extends AsyncTask<Object, Object, Bitmap> {
	private ImageView imageView;
	private Context context;

	public ImageLoadTask(Context context) {
		
		this.context = context;
	}

	@Override
	protected Bitmap doInBackground(Object... params) {
		
		Bitmap bitmap = null;
		
		imageView = (ImageView) params[1];
		
		try {
			
			bitmap = BitmapFactory.decodeStream(new PatchInputStream(new URL((String) params[0]).openStream()));
			
			saveBmpToSd(bitmap, (String) params[0]);
			
			return bitmap;
			
		} catch (Exception e) {
			
			System.out.println("baocuo");
			
			e.printStackTrace();
			
			bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon); // 默认的加载图标
		}
		return bitmap;
	}

	
	@Override
	protected void onPostExecute(Bitmap result) {
		imageView.setImageBitmap(result);
	}

	// 判断sdcard上的空间,如果不够就不保存
	private void saveBmpToSd(Bitmap bitmap, String filename) {
		if (bitmap == null) {// System.out.println("trying to savenull bitmap");
			return;
		}
		if (freeSpaceOnSd() < 10) {// System.out.println("Low free space onsd, do not cache");
			return;
		}
		filename = filename.replaceAll("/", "").replaceAll(":", ""); // 文件名里面不能带/和:的不然创建的时候会报错
		savePictureOnSd(filename, bitmap);
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

	// 往sd卡上存数据
	private void savePictureOnSd(String filename, Bitmap bitmap) {
		File picfile = new File(Environment.getExternalStorageDirectory()+ "/ceshiwenjianjia/" + filename);
		try {
			picfile.createNewFile();
			FileOutputStream fileoutput = new FileOutputStream(picfile);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileoutput);
			fileoutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
