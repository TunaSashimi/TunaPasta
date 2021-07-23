package com.tunaPasta09.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.tunaPasta09.R;

public class StringFormatTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stringformattest);

        // $ 后面是填充数据的类型
        //%d：表示整数型；
        //%f：表示浮点型，其中f前面的.2表示小数的位数
        //%s：表示字符串

        //<string name="data">整数型:%1$d，浮点型：%2$.2f，字符串:%3$s</string>
        String data = getResources().getString(R.string.data);
        data = String.format(data, 100, 10.3, "2011-07-01");

        TextView textview01 = findViewById(R.id.textview01);
        textview01.setText(data);
    }
}
