package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.tunaPasta14.R;
import com.tunaPasta14.view.IDKeyboard;
import com.tunaPasta14.view.IDKeyboardView;

import java.lang.reflect.Method;

/**
 * @author Tunasashimi
 * @date 11/3/15 20:43
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class IDKeyBoardTest extends Activity {
    EditText edit_id;
    IDKeyboard idkeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idkeyboard);

        edit_id = findViewById(R.id.edit_id);

        //1 屏蔽掉系统默认输入法
        if (Build.VERSION.SDK_INT <= 10) {
            edit_id.setInputType(InputType.TYPE_NULL);
        } else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(edit_id, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //2 初试化键盘
        IDKeyboardView keyboardView = findViewById(R.id.idkeyboard);
        idkeyboard = new IDKeyboard(IDKeyBoardTest.this, keyboardView, edit_id);
        idkeyboard.showKeyboard();

        edit_id.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                idkeyboard.showKeyboard();
                return false;
            }
        });
    }

    //物理返回键
    @Override
    public void onBackPressed() {
        if (idkeyboard.isShowKeyboard()) {
            idkeyboard.hideKeyboard();
        } else {
            finish();
        }
    }
}
