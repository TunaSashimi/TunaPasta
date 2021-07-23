package com.tunaPasta18.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tunaPasta18.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WebViewTest extends Activity {
    private WebView webview;
    private Timer timer = new Timer();

    private static final int what = 1;
    private int count = 0;
    private List<String> siteList = new ArrayList();

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case what:
                    count++;
                    if (count == siteList.size()) {
                        count = 0;
                    }
                    webview.loadUrl(siteList.get(count));
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.webviewtest);
        webview = findViewById(R.id.webview);

        //组织弹出系统浏览器

        WebSettings webSettings = webview.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);

        webSettings.setBuiltInZoomControls(true);
        webview.requestFocus();

        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());

        String site1 = "http://jenkins.lang.didichuxing.com/view/02_CI/view/Compile-Monitor/";
        String site2 = "http://jenkins.lang.didichuxing.com/view/02_CI/view/UT-Monitor/";
        String site3 = "http://sonar.lang.didichuxing.com/measures/filter/3";

        siteList.add(site1);
        siteList.add(site2);
        siteList.add(site3);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(what);
            }
        }, 0, 15000);
    }

    //	如果希望浏览的网 页回退而不是推出浏览器，需要在当前Activity中处理并消费掉该Back事件。
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
