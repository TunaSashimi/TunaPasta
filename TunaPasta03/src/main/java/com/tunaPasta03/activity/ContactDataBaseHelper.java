package com.tunaPasta03.activity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
/**
 * 
 * 最近在做android项目的时候遇到一个问题，应用程序初始化时需要批量的向sqlite中插入大量数，导致应用启动过慢。
android使用的是sqlite数据库，sqlite是比较轻量级的数据库，在Google了之后发现，sqlite事务处理的问题，
在sqlite插入数据的时候默认一条语句就是一个事务，有多少条数据就有多少次磁盘操作。我的应用初始5000条记录也就是要5000次读写磁盘操作。
解决方法：
添加事务处理，把5000条插入作为一个事务
dataBase.beginTransaction();        //手动设置开始事务
//数据插入操作循环
dataBase.setTransactionSuccessful();        //设置事务处理成功，不设置会自动回滚不提交
dataBase.endTransaction();        //处理完成 
 * 
*/
public class ContactDataBaseHelper extends SQLiteOpenHelper{
	public ContactDataBaseHelper(Context context,int version){
		super(context,"contacts.db",null,version);
	}
//	Called when the database is created for the first time. onCreate方法,只有在database被创建的时候才会执行
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("create table contact(_id integer primary key autoincrement,name text not null,tel text not null)");
		String insertSql1="insert into contact(name,tel) " +"values(\"张三\",\"13812345678\")";
		String insertSql2="insert into contact(name,tel) " +"values(\"李四\",\"13678901234\")";
		String insertSql3="insert into contact(name,tel) " +"values(\"王五\",\"13988889999\")";
		String insertSql4="insert into contact(name,tel) " +"values(\"赵六\",\"13190908080\")";
		db.execSQL(insertSql1);
		db.execSQL(insertSql2);
		db.execSQL(insertSql3);
		db.execSQL(insertSql4);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		System.out.println("onUpdate");
		
		if(oldVersion == 1 && newVersion == 2){
			//从版本1到版本2时，contact表增加了一个字段 desc,增加表T_QUALIFICATION_ACCESS
			db.execSQL("CREATE TABLE T_QUALIFICATION_ACCESS (_ID  INTEGER PRIMARY KEY AUTOINCREMENT, ID,DATE,PLACE,JOIN_PEOPLE,CONTENT,SUMMARY_SITUATION,NAME,STATE,VERSIONS_NO,TYPE, USER_ID,USER_NAME,FILE_ALIAS,FILE_NAME, ATTACHMENT)");
			db.execSQL("ALTER TABLE contact ADD COLUMN FAX");
		}
	}
	
	//如果是启动事务的情况,效率会大幅提高
	public void insertContact(String name,String tel){
		SQLiteDatabase db=getWritableDatabase();
//		db.beginTransaction();
		ContentValues cv=new ContentValues();//键值对,键是列名,值是希望插入到这一列的值,值必须和列名的类型匹配
		cv.put("name", name);
		cv.put("tel", tel);
		db.insert("contact", null, cv);//SQL doesn't allow inserting a completely empty row, so if initialValues is empty this column will explicitly be assigned a NULL value
//		db.setTransactionSuccessful();
//		db.endTransaction();
	}
	public void deleteContact(int _id){
		SQLiteDatabase db=getWritableDatabase();
		db.delete("contact", "_id=?", new String[]{""+_id});
	}
	 /**
     * 更新数据
     */
    public void updateContact(int _id,String name,String tel) {
    	SQLiteDatabase db=getWritableDatabase();
    	ContentValues cv=new ContentValues();
		cv.put("name", name);
		cv.put("tel", tel);
        db.update("contact",cv, "_id=?",new String[]{""+_id});//更新的表名,ContentValues对象,where子句
    }
    
    
	public static void copyDB2Sdcard(Context context) throws IOException {
//			File f = new File(context.getFilesDir().getPath() + "/"+ "contacts.db");
			File f = new File("/data/data/com.nemesisJS03/databases/contacts.db");//这个是databases的特有路径
		
			// Open your local db as the input stream
			InputStream myInput = new FileInputStream(f);
			// Open the empty db as the output stream
			File theFileForTest = null;
			OutputStream sdkOutputStream = null;
			theFileForTest = new File(Environment.getExternalStorageDirectory(), "dbtest"+ new Date().toLocaleString().replace(" ", "_").replace(":", "-") + ".db");
			theFileForTest.createNewFile();
			sdkOutputStream = new FileOutputStream(theFileForTest);
			try {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = myInput.read(buffer)) > 0) {
					if (sdkOutputStream != null) {
						sdkOutputStream.write(buffer, 0, length);
					}
				}
				if (sdkOutputStream != null) {
					sdkOutputStream.flush();
				}
			} catch (Exception e) {
				if (e != null) {
					e.printStackTrace();
				}
			} finally {
				if (sdkOutputStream != null) {
					sdkOutputStream.close();
				}
				myInput.close();
			}
	}
}
