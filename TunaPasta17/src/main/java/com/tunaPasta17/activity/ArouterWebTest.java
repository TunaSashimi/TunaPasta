package com.tunaPasta17.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tunaPasta17.R;
import com.tunaPasta17.util.ARouterPath;

import androidx.appcompat.app.AppCompatActivity;

@Route(path = ARouterPath.Activity.ACTIVITY_WEB)
public class ArouterWebTest extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arouterwebtest);

        webView =  findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/schame-test.html");
    }
}
