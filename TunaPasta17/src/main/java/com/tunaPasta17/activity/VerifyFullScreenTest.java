package com.tunaPasta17.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;

import com.tunaPasta17.util.VerifyCoder;

public class VerifyFullScreenTest extends Activity {
    private WebView mWebView;
    private VerifyCoder.VerifyListener listener = new VerifyCoder.VerifyListener() {

        @Override
        public void onVerifySucc(String ticket, String randstr) {
            Intent it = new Intent();
            it.putExtra("ticket", ticket);
            it.putExtra("randstr", randstr);
            setResult(Activity.RESULT_OK, it);
            finish();
        }

        @Override
        public void onVerifyFail() {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }

        @Override
        public void onIframeLoaded(int state, String info) {

        }

        @Override
        public void onIFrameResize(float width, float height) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        String jsurl = getIntent().getStringExtra("jsurl");
        if (jsurl == null) {
            finish();
            return;
        }
        VerifyCoder verify = VerifyCoder.getVerifyCoder();
        verify.setShowtitle(true);
        mWebView = verify.getWebView(getApplicationContext(), jsurl, listener);
        mWebView.requestFocus();
        mWebView.forceLayout();
        setContentView(mWebView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.clearHistory();
            mWebView.clearCache(true);
            mWebView.freeMemory();
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (parent != null) {
                parent.removeView(mWebView);
            }
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
        VerifyCoder.getVerifyCoder().release();
    }
}
