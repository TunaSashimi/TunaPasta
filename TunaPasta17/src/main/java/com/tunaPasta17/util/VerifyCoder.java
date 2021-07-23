package com.tunaPasta17.util;

/**
 * @author jinsheng
 * @date 7/24/2019 5:46 PM
 * @Copyright 2019 TunaSashimi. All rights reserved.
 * @Description
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

/**
 * @author jinsheng
 * @date 7/24/2019 5:41 PM
 * @Copyright 2019 TunaSashimi. All rights reserved.
 * @Description
 */
public class VerifyCoder {
    static String sContent1 = "<html><head lang=\"zh-CN\"><title>验证码</title><meta charset=\"UTF-8\"><meta name=\"renderer\" content=\"webkit\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\"><meta name=\"format-detection\" content=\"address=no; email=no\"></head><body></body><script src=\"";
    static String sContent3 = "\"></script><script type=\"text/javascript\">\nfunction CapCallBack(resultJson)\n{\n  prompt(JSON.stringify(resultJson));\n}\nvar CapArgs = {\n\"showHeader\":";
    static String sContent5 = ",\n\"callback\": CapCallBack\n,\"readyCallback\":CapCallBack\n}\nwindow.onload=function(){capInit(document.body,CapArgs);}\n</script></html>";
    VerifyListener mListener;
    private boolean mShowtitle;
    private String mJson;
    private static VerifyCoder sVerifyCoder;
    private final WebChromeClient mChromeClient = new WebChromeClient() {
        public void onReceivedTitle(WebView view, String title) {
        }

        public void onProgressChanged(WebView view, int progress) {
        }

        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            try {
                JSONObject json = new JSONObject(message);
                String randstr;
                if (json.toString().indexOf("state") != -1 && json.toString().indexOf("info") != -1) {
                    int state = json.optInt("state");
                    randstr = json.optString("info");
                    if (mListener != null) {
                        if (state != 0 && state != 1) {
                            if (state == 2) {
                                mListener.onIFrameResize((float)json.optDouble("fwidth"), (float)json.optDouble("fheight"));
                            }
                        } else {
                            mListener.onIframeLoaded(state, randstr);
                        }
                    }
                } else if (json.optInt("ret") == 0) {
                    String ticket = json.optString("ticket");
                    randstr = json.optString("randstr");
                    if (mListener != null) {
                        mListener.onVerifySucc(ticket, randstr);
                    }
                } else if (mListener != null) {
                    mListener.onVerifyFail();
                }
            } catch (JSONException var9) {
                var9.printStackTrace();
            }

            result.cancel();
            return true;
        }
    };

    public VerifyCoder() {
    }

    public boolean isShowtitle() {
        return this.mShowtitle;
    }

    public void setShowtitle(boolean showtitle) {
        this.mShowtitle = showtitle;
    }

    public String getJson() {
        return this.mJson;
    }

    public void setJson(String json) {
        this.mJson = json;
    }

    public static VerifyCoder getVerifyCoder() {
        if (sVerifyCoder == null) {
            sVerifyCoder = new VerifyCoder();
        }

        return sVerifyCoder;
    }

    public void release() {
        this.mListener = null;
        this.mJson = null;
        this.mShowtitle = false;
        sVerifyCoder = null;
    }

    @SuppressLint("WrongConstant")
    public WebView getWebView(Context context, String jsurl, VerifyCoder.VerifyListener listener) {
        if (context == null) {
            Log.e("verify_error", "context is null");
            return null;
        } else if (jsurl == null) {
            Log.e("verify_error", "jsurl is null");
            return null;
        } else {
            this.mListener = listener;
            WebView wv = new WebView(context);
            wv.getSettings().setDefaultTextEncodingName("UTF-8");
            wv.setWebChromeClient(this.mChromeClient);

            try {
                Method removeJavascriptInterface = wv.getClass().getMethod("removeJavascriptInterface", String.class);
                if (removeJavascriptInterface != null) {
                    removeJavascriptInterface.invoke(wv, "searchBoxJavaBridge_");
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            WebSettings setting = wv.getSettings();
            setting.setUserAgentString("android");
            setting.setJavaScriptEnabled(true);
            setting.setNeedInitialFocus(false);
            setting.setCacheMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
            setting.setBuiltInZoomControls(false);
            setting.setSupportZoom(false);
            wv.loadDataWithBaseURL((String)null, this.getContent(jsurl), "text/html", "UTF-8", (String)null);
            return wv;
        }
    }


    @SuppressLint("WrongConstant")
    public WebView useWebView(Context context, String jsurl, VerifyCoder.VerifyListener listener, WebView webView) {
        if (context == null) {
            Log.e("verify_error", "context is null");
            return null;
        } else if (jsurl == null) {
            Log.e("verify_error", "jsurl is null");
            return null;
        } else {
            this.mListener = listener;
            webView.getSettings().setDefaultTextEncodingName("UTF-8");
            webView.setWebChromeClient(this.mChromeClient);

            try {
                Method removeJavascriptInterface = webView.getClass().getMethod("removeJavascriptInterface", String.class);
                if (removeJavascriptInterface != null) {
                    removeJavascriptInterface.invoke(webView, "searchBoxJavaBridge_");
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            WebSettings setting = webView.getSettings();
            setting.setUserAgentString("android");
            setting.setJavaScriptEnabled(true);
            setting.setNeedInitialFocus(false);
            setting.setCacheMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
            setting.setBuiltInZoomControls(false);
            setting.setSupportZoom(false);
            webView.loadDataWithBaseURL((String)null, this.getContent(jsurl), "text/html", "UTF-8", (String)null);
            return webView;
        }
    }


    public String getContent(String jsurl) {
        if (jsurl == null) {
            Log.e("verify_error", "jsurl is null");
            return null;
        } else {
            StringBuffer sb = new StringBuffer();
            String showh5titlestr = this.mShowtitle ? "true" : "false";
            sb = sb.append(sContent1).append(jsurl).append(sContent3).append(showh5titlestr);
            if (this.mJson != null) {
                sb.append(",\n").append(this.mJson);
            }

            sb.append(sContent5);
            return sb.toString();
        }
    }

    public static int getPopupIframeHeightByWidth(int width) {
        float height = (float)width * 16.1F / 18.2F;
        if (height >= 515.2F) {
            height = 515.2F;
        }

        return Math.round(height);
    }

    public static int getPopupIframeHeightByWidthAndCaptype(int width, int capType) {
        float height;
        if (capType != 4 && capType != 6) {
            height = (float)width * 16.1F / 18.2F;
            if (height >= 515.2F) {
                height = 515.2F;
            }
        } else {
            height = (float)width * 19.6F / 18.2F;
            if (height >= 627.2F) {
                height = 627.2F;
            }
        }

        return Math.round(height);
    }

    public interface VerifyListener {
        void onVerifySucc(String var1, String var2);

        void onVerifyFail();

        void onIframeLoaded(int var1, String var2);

        void onIFrameResize(float var1, float var2);
    }
}