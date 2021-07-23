package com.tunaPasta05.activity;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;

import com.tunaPasta05.R;

public class MeasureSDcardTest extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.measuresdcardtest);
		
		int free=freeSpaceOnSd();
//		System.out.println("SD卡上剩余空闲值为"+free+"MB");
		
		Toast.makeText(this, "SD卡上剩余空闲值为"+free+"MB", 1000).show();
	}
	
	//获取sd卡上的剩余空间
		private int freeSpaceOnSd(){
			//如果内存卡插着的情况下,计算sdcard上的剩余空间
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath()); //取得sdcard文件路径
			long blocSize = statfs.getBlockSize();//		获取block的SIZE
			long availaBlock = statfs.getAvailableBlocks();//		空闲的Block的数量
			System.out.println((availaBlock * blocSize)/1024 /1024); //内存卡上的剩余空间MB单位
			System.out.println((availaBlock * blocSize)/1024 /1024/1024); //内存卡上的剩余空间GB单位	
			return (int) ((availaBlock * blocSize)/1024 /1024);
			}
			return 0;
		}
	
}
