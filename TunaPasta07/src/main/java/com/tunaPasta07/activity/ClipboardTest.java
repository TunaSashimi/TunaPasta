package com.tunaPasta07.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tunaPasta07.R;

public class ClipboardTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.clipboardtest);

        Button button01 = findViewById(R.id.button01);

        button01.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                ClipboardManager cm = (ClipboardManager) ClipboardTest.this
                        .getSystemService(Context.CLIPBOARD_SERVICE);

                cm.setText("通过剪切板复制的信息");

                Intent it = new Intent(ClipboardTest.this, ClipboardResult.class);
                startActivity(it);

            }
        });
    }
}
