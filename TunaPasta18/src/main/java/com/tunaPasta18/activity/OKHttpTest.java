package com.tunaPasta18.activity;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpTest extends Activity {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //
        client = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();

        new Thread() {
            public void run() {
                try {
                    httpGet("http://baidu.com/");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //HTTP GET
    String httpGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    //HTTP POST
    String httpPost(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
