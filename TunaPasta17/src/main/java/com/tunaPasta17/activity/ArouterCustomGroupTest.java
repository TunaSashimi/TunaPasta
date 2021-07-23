package com.tunaPasta17.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tunaPasta17.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 *  自定义路由组
 */

@Route(path = "/com/CustomGroupActivity" , group = "customGroup")
public class ArouterCustomGroupTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aroutercustomgrouptest);
    }
}
