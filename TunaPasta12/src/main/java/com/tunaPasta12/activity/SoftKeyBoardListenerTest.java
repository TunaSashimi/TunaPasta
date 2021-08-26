package com.tunaPasta12.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tunaPasta12.R;


/**
 * @author TunaSashimi
 * @date 2020-06-19 16:26
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class SoftKeyBoardListenerTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_softkeyboardlistenertest);

        onKeyBoardListener();
    }

    //监听软件盘是否弹起
    private void onKeyBoardListener() {
        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                Toast.makeText(SoftKeyBoardListenerTest.this, "keyBoardShow==>" + height, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void keyBoardHide(int height) {
                Toast.makeText(SoftKeyBoardListenerTest.this, "keyBoardHide==>" + height, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
