package com.tunaPasta18.util;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.tunaPasta18.BuildConfig;
import com.tunaPasta18.web.WebService;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tunaPasta18.request.WebRequest.SIGN;

/**
 * @author Tunasashimi
 * @date 2018/9/18 14:28
 * @Copyright 2018 TunaSashimi. All rights reserved.
 * @Description
 */
public class HttpUtil {

    public static final String BASE_DEFAULT_URL = "http://gammaq-stg.jryzt.com/monsa/";

    private static final int DEFAULT_TIMEOUT_SECONDS = 30;
    private static final int UPLOAD_TIMEOUT_SECONDS = 30;

    private WebService mWebService;
    private static HttpUtil sHttpHelper;

    private HttpUtil() {
    }

    public static HttpUtil getInstance() {
        if (sHttpHelper == null) {
            synchronized (HttpUtil.class) {
                if (sHttpHelper == null) {
                    sHttpHelper = new HttpUtil();
                }
            }
        }
        return sHttpHelper;
    }

    /**
     * 获取 {@link Retrofit.Builder} ，用于在此统一重复设置代码。
     *
     * @param baseUrl api接口域名
     * @return {@link Retrofit.Builder}
     */
    private Retrofit.Builder getRetrofitBuilder(@NonNull String baseUrl) {
        //这里的baseUrl就是网络请求URL相对固定的地址，一般包括请求协议（如Http）、域名或IP地址、端口号等
        //addConverterFactory方法表示需要用什么转换器来解析返回值，GsonConverterFactory.create()表示调用Gson库来解析json返回值。
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        if (BuildConfig.DEBUG) {
            OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
            retrofitBuilder.client(client);
        }
        return retrofitBuilder;
    }

    /**
     * 获取 WebService 。
     */
    public WebService getWebService() {
        if (mWebService == null) {
            synchronized (this) {
                if (mWebService == null) {
                    mWebService = getRetrofitBuilder(BASE_DEFAULT_URL)
                        .build()
                        .create(WebService.class);
                }
            }
        }
        return mWebService;
    }

    public String sortSignature(Map map) {
        //加签方式：将请求参数的key按照由小到大排序组织为 k1=v1&k2=v2... 的形式，使用SHA256方式加签
        Set<String> requestKeySet = map.keySet();
        String[] keyArray = new String[requestKeySet.size()];
        requestKeySet.toArray(keyArray);
        Arrays.sort(keyArray);
        StringBuilder builder = new StringBuilder();
        for (String key : keyArray) {
            builder.append(map.get(key));
        }
        try {
            SIGN = SignUtil.signSHA256Base64(builder.toString(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SIGN;
    }
}
