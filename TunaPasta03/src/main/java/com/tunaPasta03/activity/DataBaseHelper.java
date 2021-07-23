package com.tunaPasta03.activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DataBaseHelper extends SQLiteOpenHelper {
	String sql = "create table trade(_id integer primary key autoincrement,serialNo,money,cardNum,date,status)";
									//trade表名				id号主键自增					   序列号    	金额		卡号		日期		状态
	public DataBaseHelper(Context context, String name, int version) {
		super(context, name, null, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sql);
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}
}
