package com.tunaPasta11.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tunaPasta11.R;

public class TextViewDrawableLeftTest extends Activity {
    private Button btn1;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textviewdrawablelefttest);

        btn1 = findViewById(R.id.button1);
        tv1 = findViewById(R.id.textview1);

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Drawable drawable = getResources().getDrawable(R.drawable.tunasashimi);
                // 这一步必须要做,否则不会显示
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tv1.setCompoundDrawables(drawable, null, null, null);

            }
        });

    }

}
