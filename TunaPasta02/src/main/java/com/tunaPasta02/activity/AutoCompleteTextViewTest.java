package com.tunaPasta02.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta02.R;

public class AutoCompleteTextViewTest extends Activity {
    AutoCompleteTextView autoCompleteTextView;
    Button button;
    boolean isShowDropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.autocompletetextview);

        TextView text01 = findViewById(R.id.text01);
        TextView text02 = findViewById(R.id.text02);

        String str01 = "波士登";
        text01.setText(str01);


        String str02 = "";
        char[] chars = str01.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            str02 += "\\u" + Integer.toHexString(chars[i]);
        }

        text02.setText(str02);

        autoCompleteTextView = findViewById(R.id.autocompletetextview);
        autoCompleteTextView.setAdapter(new ArrayAdapter(this, R.layout.mysimple, getResources().getStringArray(R.array.ProjectNameTips)));
        autoCompleteTextView.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                Toast.makeText(AutoCompleteTextViewTest.this, "onDismissListener==>监听点击消失和点空白消失", Toast.LENGTH_LONG).show();
            }
        });

        //
        button = findViewById(R.id.button01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                if (!isShowDropDown) {
                    autoCompleteTextView.showDropDown();
                    isShowDropDown = true;
                } else {
                    autoCompleteTextView.dismissDropDown();
                    isShowDropDown = false;
                }
            }
        });
    }
}
