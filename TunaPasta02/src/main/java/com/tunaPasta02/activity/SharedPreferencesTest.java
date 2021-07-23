package com.tunaPasta02.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.TextView;

import com.tunaPasta02.R;

public class SharedPreferencesTest extends Activity {
    TextView textview01, textview02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sharepreferencestest);

        textview01 = findViewById(R.id.text01);
        textview02 = findViewById(R.id.text02);

        // SharedPreferences也是一种轻型的数据存储方式，它的本质是基于XML文件存储key-value键值对数据，通常用来存储一些简单的配置信息。其存储位置在/data/data/<包名>/shared_prefs目录下。SharedPreferences对象本身只能获取数据而不支持存储和修改，存储修改是通过Editor对象实现。实现SharedPreferences存储的步骤如下：
        // 一、根据Context获取SharedPreferences对象
        // 二、利用edit()方法获取Editor对象。
        // 三、通过Editor对象存储key-value键值对数据。
        // 四、通过commit()方法提交数据。
        // 在程序代码中，通过getXXX方法，可以方便的获得对应Key的Value值，如果key值错误或者此key无对应value值，SharedPreferences提供了一个赋予默认值的机会，以此保证程序的健壮性。如下图运行结果中因为并无值为"NOT_EXIST"的Key，所以Log打印出的是其默认值：“none”。在访问一个不存在key值这个过程中，并无任何异常抛出。　　
        // SharedPreferences对象与SQLite数据库相比，免去了创建数据库，创建表，写SQL语句等诸多操作，相对而言更加方便，简洁。但是SharedPreferences也有其自身缺陷，比如其职能存储boolean，int，float，long和String五种简单的数据类型，比如其无法进行条件查询等。所以不论SharedPreferences的数据存储操作是如何简单，它也只能是存储方式的一种补充，而无法完全替代如SQLite数据库这样的其他数据存储方式。

        SharedPreferences sp = getSharedPreferences("MyPref", MODE_PRIVATE);// 获取SharedPreferences对象,第一个参数是存储时的名称,第二个参数则是文件的打开方式~

        // 存入数据
        Editor editor = sp.edit();
        editor.putString("STRING_KEY", "string");
        editor.putInt("INT_KEY", 0);
        editor.putBoolean("BOOLEAN_KEY", true);
        editor.commit();

        textview01.setText(sp.getString("STRING_KEY", "none"));
        textview02.setText(sp.getString("NOT_EXIST", "none"));
    }
}
