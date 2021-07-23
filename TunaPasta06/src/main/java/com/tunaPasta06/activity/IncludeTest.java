package com.tunaPasta06.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.tunaPasta06.R;

//在一个项目中我们可能会需要用到相同的布局设计，如果都写在一个xml文件中，代码显得很冗余，并且可读性也很差，
//所以我们可以把相同布局的代码单独写成一个模块，然后用到的时候可以通过<include /> 标签来重用layout代码。

public class IncludeTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.includetest);

//		直接把include里面的控件当成主xml中的控件来获取	

        ImageView head = findViewById(R.id.head_btn_refresh);    //获得布局容器对象
        head.setBackgroundResource(R.drawable.bg_top_bar);    //设置背景图片
    }
}