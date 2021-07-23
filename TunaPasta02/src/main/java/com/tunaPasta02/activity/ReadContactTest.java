package com.tunaPasta02.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tunaPasta02.R;

public class ReadContactTest extends Activity {
    private TextView tv;
    private Button btn01, btn02, btn03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readcontacttest);

        tv = findViewById(R.id.read_contact_tv);
        btn01 = findViewById(R.id.search_contact_btn);
        btn02 = findViewById(R.id.search_call_log_btn);
        btn03 = findViewById(R.id.search_sms_btn);

        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.search_contact_btn:
                        tv.setText(readContact());
                        break;
                    case R.id.search_call_log_btn:
                        tv.setText(readCallLog());
                        break;
                    case R.id.search_sms_btn:
                        tv.setText(readSmsMessage());
                        break;
                }
            }
        };

        btn01.setOnClickListener(listener);
        btn02.setOnClickListener(listener);
        btn03.setOnClickListener(listener);

    }

    /**
     * 读取手机中的联系人信息
     *
     * @return
     */
    private String readContact() {
        String[] columns = {"_id", "display_name", "has_phone_number"};
        StringBuffer sb = new StringBuffer();

        ContentResolver cr = this.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, columns, null, null, null);

//		Cursor phones=cr.query(
//				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//				null, null, null, null);

//		for(int i=0;i<cursor.getColumnCount();i++){
//			Log.i("OUTPUT", phones.getColumnName(i));
//			sb.append(phones.getColumnName(i)+",");
//		}

        if (cursor.moveToFirst()) {
            do {
                int _id = cursor.getInt(0);
                String name = cursor.getString(1);
                String has_phone = cursor.getString(2);
                sb.append(_id + ":" + name + ":" + has_phone + ":");

                //如果当前联系人有电话
                if (has_phone.trim().equals("1")) {
                    //定义要查询的列名
                    String[] phone_cols = {
                            ContactsContract.CommonDataKinds.Phone.NUMBER,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                    };
                    //查询电话表
                    Cursor phones = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            phone_cols, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + _id, null, null);

                    //处理结果集
                    if (phones.moveToFirst()) {
                        do {
                            String number = phones.getString(0);
                            sb.append(number + ",");
                        } while (phones.moveToNext());
                    }
                }

                sb.append("\n");

            } while (cursor.moveToNext());
        }

        return sb.toString();
    }


    /**
     * 读取手机中通话记录
     *
     * @return
     */
    private String readCallLog() {
        StringBuffer sb = new StringBuffer();
        String[] cols = {
                CallLog.Calls.NUMBER,//电话号码
                CallLog.Calls.CACHED_NAME,//联系人名
                CallLog.Calls.TYPE,//通话类型：1：已接，2：已拨，3：未接
                CallLog.Calls.DATE,//通话日期时间
                CallLog.Calls.DURATION//通话时长
        };
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(CallLog.Calls.CONTENT_URI,
                cols, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                sb.append(cursor.getString(0) + ",");
                sb.append(cursor.getString(1) + ",");
                sb.append(cursor.getString(2) + ",");
                long times = Long.parseLong(cursor.getString(3));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = new Date(times);
                String dateStr = sdf.format(date);
                sb.append(dateStr + ",");
                sb.append(cursor.getString(4) + "\n");

            } while (cursor.moveToNext());
        }

        return sb.toString();
    }

    /**
     * 读取手机中短消息
     *
     * @return
     */
    private String readSmsMessage() {
        StringBuffer sb = new StringBuffer();
        ContentResolver cr = getContentResolver();

        String[] cols = {"address", "type", "body", "date"};
        //"content://sms/"  所有短消息
        //"content://sms/inbox"  收信箱
        //"content://sms/sent"   已发送
        //"content://sms/draft"  草稿
        //"content://sms/outbox" 发信箱

        Cursor cursor = cr.query(Uri.parse("content://sms/"), cols, null, null, null);
//		for(int i=0;i<cursor.getColumnCount();i++){
//			sb.append(cursor.getColumnName(i)+",");
//		}

        if (cursor.moveToFirst()) {
            do {
                sb.append(cursor.getString(0) + ",");
                sb.append(cursor.getString(1) + ",");
                sb.append(cursor.getString(2) + ",");

                long times = Long.parseLong(cursor.getString(3));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = new Date(times);
                String dateStr = sdf.format(date);
                sb.append(dateStr + "\n");
            } while (cursor.moveToNext());
        }
        return sb.toString();
    }

}