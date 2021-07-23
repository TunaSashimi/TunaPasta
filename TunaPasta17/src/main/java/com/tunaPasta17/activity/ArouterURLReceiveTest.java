package com.tunaPasta17.activity;

import android.net.Uri;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tunaPasta17.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * URL 中转Activity
 */
public class ArouterURLReceiveTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.arouterurlreceivetest);

        //对URI 数据分发
        Uri uri = getIntent().getData();
        ARouter.getInstance().build(uri).navigation(this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                finish();
            }
        });
    }
}
