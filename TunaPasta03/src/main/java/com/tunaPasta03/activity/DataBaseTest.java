package com.tunaPasta03.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.tunaPasta03.R;

public class DataBaseTest extends Activity {
    private DataBaseHelper helper;
    private ListView listView;
    private Button button;
    private ArrayList<String> titleList;//查询所得数据
    private ArrayList<String> listdata;//查询所得数据
    private ArrayList<ArrayList<String>> lists;//所有数据

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databasetest);        //注意scrollview和listview相互冲突
        listView = findViewById(R.id.listview);
        button = findViewById(R.id.button);

        String path = getFilesDir().getAbsolutePath() + "/trade.db";

        titleList = new ArrayList();    //标题栏
        titleList.add("序号");
        titleList.add("序列号");
        titleList.add("金额");
        titleList.add("卡号");
        titleList.add("日期");
        titleList.add("状态");

        helper = new DataBaseHelper(this, path, 1);
        final SQLiteDatabase db = helper.getWritableDatabase();

        //每次点击的时候,会增加一次,然后查询
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                db.execSQL("insert into trade values(null,?,?,?,?,?)", new String[]{"101", "6000", "0051", "2013年2月", "未还"});
                db.execSQL("insert into trade values(null,?,?,?,?,?)", new String[]{"102", "10000", "0052", "2013年2月", "已还"});

                lists = new ArrayList();
                lists.add(titleList);

                Cursor cursor = db.rawQuery("select * from trade", null);
                while (cursor.moveToNext()) {
                    listdata = new ArrayList();
                    listdata.add(cursor.getString(cursor.getColumnIndex("_id")));
                    listdata.add(cursor.getString(cursor.getColumnIndex("serialNo")));
                    listdata.add(cursor.getString(cursor.getColumnIndex("money")));
                    listdata.add(cursor.getString(cursor.getColumnIndex("cardNum")));
                    listdata.add(cursor.getString(cursor.getColumnIndex("date")));
                    listdata.add(cursor.getString(cursor.getColumnIndex("status")));
                    lists.add(listdata);
                }
                cursor.close();

                listView.setAdapter(new DataBaseAdapter(DataBaseTest.this, lists));
            }
        });
    }
}
