package com.tunaPasta17.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tunaPasta17.R;
import com.tunaPasta17.service.IService;
import com.tunaPasta17.util.ARouterPath;

import androidx.appcompat.app.AppCompatActivity;

public class ArouterTest extends AppCompatActivity implements View.OnClickListener {

    @Autowired(name = "/service/hello1")
    IService service1;

    @Autowired(name = "/service/hello2")
    IService service2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aroutertest);

        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
        findViewById(R.id.bt5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                ARouter.getInstance()
                        .build(ARouterPath.Activity.ACTIVITY_JUMPTEST)
                        .withString("key", "value:123")
                        .navigation(this, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                                String group = postcard.getGroup();
                                Log.e("zhao", "分组是: " + group);
                            }
                        });
                break;
            case R.id.bt2:
                //自定义路由分组，发起路由
                ARouter.getInstance().build("/com/CustomGroupActivity", "customGroup").navigation();
                break;
            case R.id.bt3:
                //加载本地html
                ARouter.getInstance().build(ARouterPath.Activity.ACTIVITY_WEB).navigation();
                break;

            case R.id.bt4:
                //暴露服务
                ARouter.getInstance().inject(this);
                service1.sayHello(this);
                break;

            case R.id.bt5:
                //暴露服务
                ARouter.getInstance().inject(this);
                service2.sayHello(this);
                break;
        }
    }
}
