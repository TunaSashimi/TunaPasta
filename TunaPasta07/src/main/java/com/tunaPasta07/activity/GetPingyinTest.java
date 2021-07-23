package com.tunaPasta07.activity;

import java.util.Locale;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta07.R;

public class GetPingyinTest extends Activity {
    EditText et;
    Button bt;
    TextView tv;

    //获取拼音,有点bug还未解决需要查两次才能获取到

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.getpingyintest);

        bt = findViewById(R.id.bt);
        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);

        bt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                Uri rawContactUri = getContentResolver().insert(RawContacts.CONTENT_URI, values);
                long rawContactId = ContentUris.parseId(rawContactUri);
                String name = et.getText().toString();
                if (name.length() != 0) {
                    values.clear();
                    values.put(Data.RAW_CONTACT_ID, rawContactId);
                    values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
                    values.put(StructuredName.GIVEN_NAME, name);
                    getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
                    hanziToPinyin(rawContactId);

//					delete(rawContactId);

                } else {
                    Toast.makeText(GetPingyinTest.this, "请输入汉字！", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void hanziToPinyin(long rawContactId) {
        String result = "";
        String Where = ContactsContract.RawContacts.CONTACT_ID + " =" + rawContactId;
        String[] projection = {"sort_key"};
        Cursor cur = getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, projection, Where, null, null);
        int pinyin1 = cur.getColumnIndex("sort_key");

        cur.moveToFirst();

        String pinyin = cur.getString(pinyin1);

        for (int i = 0; i < pinyin.length(); i++) {
            String temp = pinyin.substring(i, i + 1);
            if (temp.matches("[a-zA-Z]")) {
                result = result + temp;
            } else
                result = result + " ";
        }
        tv.setText(result.toLowerCase(Locale.CHINESE));
    }

    public void delete(long rawContactId) {
        getContentResolver().delete(ContentUris.withAppendedId(RawContacts.CONTENT_URI, rawContactId), null, null);
    }
}