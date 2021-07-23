package com.tunaPasta13.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tunaPasta13.R;

public class UseJSResolveTest extends Activity {
    private String dataDhfTitile = "data-dhf-titile";
    private String dataDhfUrl = "data-dhf-url";

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usejsresolvetest);

        WebView webView = findViewById(R.id.wv1);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        webView.setWebViewClient(new MyWebViewClient());

        webView.loadUrl("file:///android_asset/startapp.html");
//		webView.loadUrl("http://www.baidu.com/");
    }

    final class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loadMeta(view, dataDhfTitile);
            loadMeta(view, dataDhfUrl);
        }
    }

    private void loadMeta(WebView webView, String attrName) {
        String script = "javascript:"
                + "function getMeta() { "
                + "    var meta = document.getElementsByTagName('meta'); "
                + "    for (var i = 0; i < meta.length; i++) {"
                + "        if (meta[i] && meta[i].getAttribute('" + attrName + "')) {"
                + "            return meta[i].getAttribute('" + attrName + "');"
                + "        }"
                + "    }"
                + "    return '';"
                + "}";
        webView.loadUrl(script);
        webView.loadUrl("javascript:window.local_obj.showSource('" + attrName + "', getMeta());");
    }

    final class InJavaScriptLocalObj {
        public void showSource(String key, String string) {
            System.out.println("key==>" + key + ",string==>" + string);
            if (key.equals(dataDhfTitile)) {
                dataDhfTitile = string;
            } else if (key.equals(dataDhfUrl)) {
                dataDhfUrl = string;
            }
        }
    }
}
