package com.tunaPasta06.activity;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GetPhoneLanguageTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 判断android设备当前设置的为什么语言使用Locale类中getLanguage()方法；
        // 一般获取到的比如中文为zh，英文为en，日文为ko；
        // 要获得具体的类别使用toString()方法。
        // 比如繁体为zh_TW，简体为zh_CN。英文中有en_GB；日文有ko_KR。
        //
        // 代码如下：
        String s1 = Locale.getDefault().getLanguage();
        String s2 = Locale.getDefault().toString();

        TextView textview01 = new TextView(this);
        textview01.setText(s1 + "\n" + s2);
        setContentView(textview01);
    }
}
