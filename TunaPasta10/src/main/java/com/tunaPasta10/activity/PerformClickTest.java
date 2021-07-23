package com.tunaPasta10.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tunaPasta10.R;

public class PerformClickTest extends Activity {
    private Button btn1;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.performclicktest);

        btn1 = findViewById(R.id.button1);
        tv1 = findViewById(R.id.textview1);

//		btn1.performClick();

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setText("已经被点击过了");
            }
        });

        //这行代码要写在setOnClickListener下面才可以
        btn1.performClick();
    }

}
