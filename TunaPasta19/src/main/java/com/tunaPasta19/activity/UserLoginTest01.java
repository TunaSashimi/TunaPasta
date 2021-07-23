package com.tunaPasta19.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tunaPasta19.R;

public class UserLoginTest01 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogintest01);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                Bundle b = data.getExtras();
                String string = b.getString("result");

                EditText edittext_username = (EditText) findViewById(R.id.edittext_username);
                edittext_username.setText(string);
                break;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn01:
                finish();
                break;
            case R.id.btn02:
                Toast.makeText(UserLoginTest01.this, getResources().getString(R.string.userlogin_login), Toast.LENGTH_SHORT).show();
                break;
            case R.id.text01:
                startActivityForResult(new Intent(this, CaptureCodeTest.class), RESULT_OK);
                break;
        }
    }

}
