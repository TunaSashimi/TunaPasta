package com.tunaPasta07.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.tunaPasta07.R;

public class MessageSeeTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.messageseetest);

        Intent intent = this.getIntent();
        String str;
        str = intent.getStringExtra("mess");

        TextView text01 = findViewById(R.id.text01);

        if (str != null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Received SMS: " + str)
                    .setCancelable(false)
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });

            builder.create().show();

            text01.setText("您收到的短信内容为\n\n" + str);

        }

    }
}