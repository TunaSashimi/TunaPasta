package com.tunaPasta17.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.tunaPasta17.R;
import com.tunaPasta17.util.VerifyCoder;

public class VerifyPopupTest extends Activity {
    private WebView mWebView;
    private RelativeLayout mContainer;
    private ProgressBar mProgressBar;
    private float mDensity;
    private float mScale = 0.7f; //默认弹框验证码宽度是屏幕宽度*0.7
    private final float F_DEFAULT_POPUP_IFRAME_WIDTH = 18.2f * 16;
    private final int F_MAX_IFRAME_WIDTH_SCALE = 2;
    private final int F_CAP_TYPE_CLICK_CHAR_ZH = 4;//图中点字(中文)
    private final int F_CAP_TYPE_CLICK_CHAR_EN = 6;//图中点字(英文)
    private final int F_CAP_TYPE_SLIDE_PUZZLE = 7;//滑动拼图
    private VerifyCoder.VerifyListener mListener = new VerifyCoder.VerifyListener() {

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
            //收到验证码页面(包括图片)加载完成回调时，把Loading隐藏，WebView显示
            mProgressBar.setVisibility(View.INVISIBLE);
            mWebView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onIFrameResize(float width, float height) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//      要弹窗效果就设置标签  android:theme="@style/dialog"
        String jsurl = getIntent().getStringExtra("jsurl");
        if (jsurl == null) {
            finish();
            return;
        }

        //
        WindowManager manager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        mDensity = metrics.density;


        /*
         * 以滑动拼图弹框验证码为例，取弹框验证码宽度为屏幕宽度0.7
         * 滑动拼图标准宽18.2*16dp，标准高16.1*16dp,最大缩放比例2 ----capType=7
         *
         * 图中点字标准宽18.2*16dp，标准高19.6*16dp,最大缩放比例2 ----capType=4,6
         * */
        setContentView(R.layout.verifypopuptest);

        mProgressBar = findViewById(R.id.progressBar);

        mWebView = findViewById(R.id.webview);

        VerifyCoder verifyCoder = VerifyCoder.getVerifyCoder();
        verifyCoder.setJson("themeColor:'ff0000',type:'popup',fwidth:" + 182);

        //根据验证码类型和弹框宽度，获取验证码弹框高度
        int iframeHeightDP = VerifyCoder.getPopupIframeHeightByWidthAndCaptype(182, F_CAP_TYPE_SLIDE_PUZZLE);
        System.out.println("iframeHeightDP==>" + iframeHeightDP);

        verifyCoder.useWebView(getApplicationContext(), jsurl, mListener, mWebView);
        mWebView.requestFocus();
        mWebView.forceLayout();
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
