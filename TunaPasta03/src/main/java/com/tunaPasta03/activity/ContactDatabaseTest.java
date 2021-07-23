package com.tunaPasta03.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.tunaPasta03.R;

public class ContactDatabaseTest extends Activity {
    private ListView lv;
    private ContactDataBaseHelper helper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactdatabasetest);
        lv = findViewById(R.id.database_test_lv);
//		getReadableDatabase()并不是以只读方式打开数据库，而是先执行getWritableDatabase()，失败的情况下才调用。 
//		getWritableDatabase()和getReadableDatabase()方法都可以获取一个用于操作数据库的SQLiteDatabase实例。 
//		但getWritableDatabase() 方法以读写方式打开数据库，一旦数据库的磁盘空间满了，数据库就只能读而不能写，倘若使用getWritableDatabase()打开数据库就会出错。
//		getReadableDatabase()方法先以读写方式打开数据库，如果数据库的磁盘空间满了，就会打开失败，当打开失败后会继续尝试以只读方式打开数据库。 

        helper = new ContactDataBaseHelper(this, 2);
//		helper=new ContactDataBaseHelper(this,2);//通过修改这里来执行update!

        SQLiteDatabase db = helper.getReadableDatabase();//只有调用了getWritableDatabase或者getReadableDatabase才会真正的创建db文件
        cursor = db.query("contact", new String[]{"_id", "name", "tel"}, null, null, null, null, null);

        refreshUI();

        lv.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                createSelectDialog(arg2);
                return false;
            }
        });

//		把数据库文件拖出来,注意方法copyDB2Sdcard中默认databases的特有路径

//		try {
//			helper.copyDB2Sdcard(this);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(0, 0, 0, "添加联系人");
        menu.add(0, 1, 1, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                createAddContactDialog();
                break;
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createAddContactDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.contactdatabasedialog, null);
        final EditText et01 = (EditText) layout.findViewById(R.id.contact_name_et);
        final EditText et02 = (EditText) layout.findViewById(R.id.contact_tel_et);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加联系人");
        builder.setView(layout);
        builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = et01.getText().toString();
                String tel = et02.getText().toString();
                helper.insertContact(name, tel);
                refreshUI();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    private void createSelectDialog(final int index) {
        Builder builder = new Builder(this);
        builder.setTitle("请选择要执行的操作");
        builder.setSingleChoiceItems(new String[]{"删除联系人", "修改联系人"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        switch (which) {
                            case 0:
                                cursor.moveToPosition(index);
                                helper.deleteContact(cursor.getInt(0));//返回目标列的从零开始的索引。
                                refreshUI();
                                break;
                            case 1:
                                LayoutInflater inflater = getLayoutInflater();
                                View layout = inflater.inflate(R.layout.contactdatabasedialog, null);
                                final EditText et01 = (EditText) layout.findViewById(R.id.contact_name_et);
                                final EditText et02 = (EditText) layout.findViewById(R.id.contact_tel_et);
                                AlertDialog.Builder builder = new AlertDialog.Builder(ContactDatabaseTest.this);
                                builder.setTitle("修改联系人");
                                builder.setView(layout);
                                builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String name = et01.getText().toString();
                                        String tel = et02.getText().toString();
                                        cursor.moveToPosition(index);
                                        helper.updateContact(cursor.getInt(0), name, tel);
                                        refreshUI();
                                    }
                                });
                                builder.setNegativeButton("取消", null);
                                builder.show();
                                break;
                        }
                    }
                }
        );
        builder.show();
    }

    private void refreshUI() {
        cursor.requery();
        CursorAdapter cursoradapter = new SimpleCursorAdapter(this, R.layout.database_item, cursor,
                new String[]{"name", "tel"}, new int[]{R.id.contact_name, R.id.contact_tel});
        lv.setAdapter(cursoradapter);
    }
}