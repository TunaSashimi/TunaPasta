package com.tunaPasta17.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tunaPasta17.R;
import com.tunaPasta17.util.ARouterPath;

import androidx.appcompat.app.AppCompatActivity;


@Route(path = ARouterPath.Activity.ACTIVITY_URL2)
public class ArouterURLTest2 extends AppCompatActivity {

    private TextView textView;

    @Autowired
    String name;

    @Autowired
    int age;

    @Autowired
    boolean boy;

    @Autowired
    int high;

    @Autowired
    String obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.arouterurltest2);

        textView = findViewById(R.id.tv);

        //解析参数
        Bundle bundle = getIntent().getExtras();
        String name1 = bundle.getString("name");

        textView.setText("参数是： " + "name: " + name + "  age: " + age
                + " boy: " + boy + " name1: " + name1 + " obj: " + obj.toString());
    }
}
