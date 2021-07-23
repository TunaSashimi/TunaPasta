package com.tunaPasta17.activity;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.tunaPasta17.R;
import com.tunaPasta17.util.ARouterPath;

import androidx.appcompat.app.AppCompatActivity;

/**
 * URL 中转Activity
 */

@Route(path = ARouterPath.Activity.ACTIVITY_URL1)
public class ArouterURLTest1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arouterurltest1);
    }
}
