package com.tunaPasta02.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.tunaPasta02.R;

public class WebViewTest extends Activity {
    private EditText edittext;
    private Button btn01, btn02, btn03;
    private WebView webView;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 200:
                    int progress = (Integer) msg.obj;
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.webviewtest);

        edittext = findViewById(R.id.webview_et);

        btn01 = findViewById(R.id.webview_btn01);
        btn02 = findViewById(R.id.webview_btn02);
        btn03 = findViewById(R.id.webview_btn03);

        webView = findViewById(R.id.web_view);

        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);//启用或禁止WebView访问文件数据

        webSettings.setBlockNetworkImage(true);//是否显示网络图像,这个显示了反而不出图片了
        webSettings.setBuiltInZoomControls(true);//是否支持缩放
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓冲的模式
        webSettings.setDefaultFontSize(15); //设置默认的字体大小
        webSettings.setDefaultTextEncodingName("utf-8"); //设置在解码时使用的默认编码
        webSettings.setFixedFontFamily("monospace"); //设置固定使用的字体
        webSettings.setJavaScriptEnabled(true); //设置是否支持Javascript,这句话不加无法调用html5的功能
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //设置布局方式
        webSettings.setLightTouchEnabled(true); //设置用鼠标激活被选项
        webSettings.setSupportZoom(true); //设置是否支持变焦

        btn01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 想要webview加载页面,只需webView.loadUrl("http://www.baidu.com");当然别忘了添加网络权限
                // 此时点击网页里的超链接,会弹出多个浏览器选择,但是我们想要在自己的浏览器打开,就需要设置WebViewClient
                webView.loadUrl(edittext.getText().toString().trim());
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    }
                    @Override
                    public void onPageFinished(WebView view, String url) {
                    }
                });
                webView.setWebChromeClient(new WebChromeClient() {
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        Message msg = new Message();
                        msg.what = 200;
                        msg.obj = newProgress;
                        handler.sendMessage(msg);
                    }
                });
            }
        });

        btn02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("file:/android_asset/webviewtest.html");
            }
        });

        btn03.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "tunapasta03://";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    //	如果希望浏览的网 页回退而不是推出浏览器，需要在当前Activity中处理并消费掉该Back事件。
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
