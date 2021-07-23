package com.tunaPasta07.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.widget.TextView;

public class ClipboardResult extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClipboardManager cm = (ClipboardManager) this
                .getSystemService(Context.CLIPBOARD_SERVICE);

        cm.getText();

        TextView text = new TextView(this);
        text.setText(cm.getText());

        setContentView(text);

    }
}
