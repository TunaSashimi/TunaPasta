package com.tunaPasta17.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tunaPasta17.R;
import com.tunaPasta17.util.ARouterPath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx

@Route(path = ARouterPath.Activity.ACTIVITY_JUMPTEST)
public class ArouterJumpTest extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arouterjumptest);

        textView = findViewById(R.id.tv);
        button = findViewById(R.id.addFragment);

        //接收参数
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");

        textView.setText("参数是：" + key);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取Fragment 实例
                Fragment fragment = (Fragment) ARouter.getInstance().build(ARouterPath.Fragment.FRAGMENT_JUMPTEST).navigation();

                //添加Fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragmentLayout, fragment);
                fragmentTransaction.commit();
            }
        });

    }
}
