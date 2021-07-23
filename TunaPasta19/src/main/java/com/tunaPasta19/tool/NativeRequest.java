package com.tunaPasta19.tool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.tunaPasta19.entity.CheckContent;
import com.tunaPasta19.entity.Constant;
import com.tunaPasta19.entity.FormSecurityCheck;

public class NativeRequest {

	//	设置Test的值	Button,Edittext为 TextView 的子类
	public static void setText(TextView textview,String string,OnClickListener onClickListener){
		if (string!=null) {
			textview.setText(string);
		}else {
			textview.setText(" ");//安卓3.0和2.2的区别,不能直接设置为""
		}
		textview.setOnClickListener(onClickListener);
	}
	
	// 把信息保存至本机(用户信息,表信息)~
	public static void saveInfomation(Context context, Object object,String checkbat) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(context.getFilesDir() + "/" + checkbat));
			oos.writeObject(object);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 从本机获取信息~
		public static Object getInfomation(Context context, int type, String checkbat,String projectName,int rowNumber) {
			Object object = null;
			if (new File(context.getFilesDir() + "/" + checkbat).exists()) {
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(context.getFilesDir() + "/"+ checkbat));
					object = ois.readObject();
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				switch (type) {
				case Constant.TYPE_SECURITYCHECK:
					FormSecurityCheck securitycheck = new FormSecurityCheck();
					securitycheck.checkcontentlist = new ArrayList<CheckContent>();
					for (int i = 0; i < rowNumber; i++) {
						securitycheck.checkcontentlist.add(new CheckContent());
					}
					object = securitycheck;
					break;
//				case Constant.TYPE_QUALITYCHECK:
//					FormRealCheck formrealcheck = new FormRealCheck();
//					formrealcheck.checkresultlist = new ArrayList<CheckResult>();
//					for (int i = 0; i < rowNumber; i++) {
//						formrealcheck.checkresultlist.add(new CheckResult());
//					}
//					formrealcheck.checkcontentlist = new ArrayList<CheckContent>();
//					for (int i = 0; i < rowNumber; i++) {
//						formrealcheck.checkcontentlist.add(new CheckContent());
//					}
//					formrealcheck.projectName = projectName;
//					object = formrealcheck;
//					break;
				}
			}
			return object;
		}
	
	//异步操作保存图片
	public static void savePicture(final Bitmap bitmap, final String path) {
			new Thread(){
			public void run() {
				try {
					File picfile = new File(Environment.getExternalStorageDirectory() + path);
					picfile.createNewFile();
					FileOutputStream fileoutput = new FileOutputStream(picfile);
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileoutput);
					fileoutput.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();		
			
	}
	
}
