package com.tunaPasta18.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.tunaPasta18.R;
import com.tunaPasta18.bean.ResponseBean;
import com.tunaPasta18.util.AESUtil;
import com.tunaPasta18.util.HttpUtil;
import com.tunaPasta18.util.RSAUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.tunaPasta18.request.WebRequest.ACCESS_TOKEN;
import static com.tunaPasta18.request.WebRequest.APP_ID;
import static com.tunaPasta18.request.WebRequest.CHANNEL;
import static com.tunaPasta18.request.WebRequest.CHANNEL_2;
import static com.tunaPasta18.request.WebRequest.CLIENT_ID;
import static com.tunaPasta18.request.WebRequest.DESCRIPTION;
import static com.tunaPasta18.request.WebRequest.DEVICEOS_AND_VERSION;
import static com.tunaPasta18.request.WebRequest.DEVICE_ID;
import static com.tunaPasta18.request.WebRequest.DEVICE_MODEL;
import static com.tunaPasta18.request.WebRequest.ENCODE_KEY;
import static com.tunaPasta18.request.WebRequest.FACE_SET;
import static com.tunaPasta18.request.WebRequest.FORMAT;
import static com.tunaPasta18.request.WebRequest.HTTP_METHOD;
import static com.tunaPasta18.request.WebRequest.IMG_DATA;
import static com.tunaPasta18.request.WebRequest.IMG_FORMAT;
import static com.tunaPasta18.request.WebRequest.IMG_TYPE;
import static com.tunaPasta18.request.WebRequest.PERSON_ID;
import static com.tunaPasta18.request.WebRequest.PUBLIC_KEY;
import static com.tunaPasta18.request.WebRequest.REQUEST_DATA;
import static com.tunaPasta18.request.WebRequest.REQUEST_ID;
import static com.tunaPasta18.request.WebRequest.SDK_VERSION;
import static com.tunaPasta18.request.WebRequest.SECOND_CHANNEL;
import static com.tunaPasta18.request.WebRequest.SECOND_CHANNEL_NAME;
import static com.tunaPasta18.request.WebRequest.SIGN_METHOD;
import static com.tunaPasta18.request.WebRequest.URI;
import static com.tunaPasta18.request.WebRequest.VERSION;

public class StethoTest extends Activity implements View.OnClickListener {
    //View
    private Button mBtnToken, mBtnFaceDetect, mBtnFaceCompare, mBtnBioDetect,
            mBtnIDComparison, mBtnFaceCollection, mBtnDescreen, mBtnImgCompare,
            mBtnFaceComparison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //
        setContentView(R.layout.stethotest);

        init();
    }

    private void init() {
        initView();
        initEvent();
    }

    private void initView() {
        mBtnToken = findViewById(R.id.btn_token);
        mBtnFaceDetect = findViewById(R.id.btn_faceDetect);
        mBtnFaceCompare = findViewById(R.id.btn_faceCompare);
        mBtnBioDetect = findViewById(R.id.btn_bioDetect);
        mBtnIDComparison = findViewById(R.id.btn_idComparison);
        mBtnFaceCollection = findViewById(R.id.btn_faceCollection);
        mBtnDescreen = findViewById(R.id.btn_descreen);
        mBtnImgCompare = findViewById(R.id.btn_imgCompare);
        mBtnFaceComparison = findViewById(R.id.btn_faceComparison);
    }

    private void initEvent() {
        mBtnToken.setOnClickListener(this);
        mBtnFaceDetect.setOnClickListener(this);
        mBtnFaceCompare.setOnClickListener(this);
        mBtnBioDetect.setOnClickListener(this);
        mBtnIDComparison.setOnClickListener(this);
        mBtnFaceCollection.setOnClickListener(this);
        mBtnDescreen.setOnClickListener(this);
        mBtnImgCompare.setOnClickListener(this);
        mBtnFaceComparison.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_token:
                token();
                break;
            case R.id.btn_faceDetect:
                faceDetect();
                break;
            case R.id.btn_faceCompare:
                faceCompare();
                break;
            case R.id.btn_bioDetect:
                bioDetect();
                break;
            case R.id.btn_idComparison:
                idComparison();
                break;
            case R.id.btn_faceCollection:
                faceCollection();
                break;
            case R.id.btn_descreen:
                descreen();
                break;
            case R.id.btn_imgCompare:
                imgCompare();
                break;
            case R.id.btn_faceComparison:
                faceComparison();
                break;
            default:
                break;
        }
    }

    private void token() {
        HttpUtil.getInstance().getWebService().getToken("clientId", "channelId", "SECOND_CHANNEL", "APP_ID")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        if (responseBean != null) {
                            Toast.makeText(StethoTest.this, responseBean.getResponseData(), Toast.LENGTH_SHORT).show();
                            ACCESS_TOKEN = responseBean.getResponseData();
                        }
                    }
                });
    }

    //

    private void faceDetect() {
        //组装业务数据json
        JSONObject dataJSON = new JSONObject();
        try {
            dataJSON.put("channelId", CHANNEL);
            dataJSON.put("sdkVersion", SDK_VERSION);
            dataJSON.put("deviceId", DEVICE_ID);
            dataJSON.put("deviceModel", DEVICE_MODEL);
            dataJSON.put("deviceOSAndVersion", DEVICEOS_AND_VERSION);
            dataJSON.put("imgType", IMG_TYPE);
            dataJSON.put("imgFormat", IMG_FORMAT);
            dataJSON.put("imgData", IMG_DATA);
            dataJSON.put("faceSet", FACE_SET);
            dataJSON.put("personId", PERSON_ID);
            dataJSON.put("secondChannel", SECOND_CHANNEL);
            dataJSON.put("secondChannelName", SECOND_CHANNEL_NAME);
            dataJSON.put("description", DESCRIPTION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //生成用于AES加密的随机字符串aesKey
        String aesKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        //对业务数据进行加密
        REQUEST_DATA = AESUtil.encryptString(dataJSON.toString(), aesKey);
        //使用公钥将生成的aesKey加密
        ENCODE_KEY = RSAUtil.encryptByPublic(aesKey, PUBLIC_KEY);
        //获取accessToken，这里需要调用"获取accessToken接口"来获取accessToken
        //组装公共请求参数
        Map<String, String> requestMap = new HashMap<String, String>(16);
        requestMap.put("method", "faceDetect");
        requestMap.put("clientId", CLIENT_ID);
        requestMap.put("requestId", REQUEST_ID);
        requestMap.put("channel", CHANNEL);
        requestMap.put("channel2", CHANNEL_2);
        requestMap.put("appId", APP_ID);
        requestMap.put("encodeKey", ENCODE_KEY);
        requestMap.put("accessToken", ACCESS_TOKEN);
        requestMap.put("signMethod", SIGN_METHOD);
        requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestMap.put("format", FORMAT);
        requestMap.put("version", VERSION);
        requestMap.put("uri", URI);
        requestMap.put("httpMethod", HTTP_METHOD);
        requestMap.put("comments", HTTP_METHOD);
        requestMap.put("requestData", REQUEST_DATA);
        requestMap.put("sign", HttpUtil.getInstance().sortSignature(requestMap));
        //
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(requestMap).toString());

        HttpUtil.getInstance().getWebService().postFaceDetectByJSON(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        System.out.println("getEncodeKey==>" + responseBean.getEncodeKey());
                        System.out.println("getResponseData==>" + responseBean.getResponseData());

                        String serverAesKey = RSAUtil.decryptByPublic(responseBean.getEncodeKey(), PUBLIC_KEY);
                        System.out.println("serverAesKey==>" + serverAesKey);

                        String serverData = AESUtil.decryptString(responseBean.getResponseData(), serverAesKey);
                        System.out.println("serverData==>" + serverData);
                    }
                });
    }


    private void faceCompare() {
        //组装业务数据json
        JSONObject dataJSON = new JSONObject();
        try {
            dataJSON.put("channelId", CHANNEL);
            dataJSON.put("sdkVersion", SDK_VERSION);
            dataJSON.put("deviceId", DEVICE_ID);
            dataJSON.put("deviceModel", DEVICE_MODEL);
            dataJSON.put("deviceOSAndVersion", DEVICEOS_AND_VERSION);

            dataJSON.put("imgType1", IMG_TYPE);
            dataJSON.put("imgFormat1", IMG_FORMAT);
            dataJSON.put("imgData1", IMG_DATA);

            dataJSON.put("imgType2", IMG_TYPE);
            dataJSON.put("imgFormat2", IMG_FORMAT);
            dataJSON.put("imgData2", IMG_DATA);

            dataJSON.put("personId", PERSON_ID);
            dataJSON.put("secondChannel", SECOND_CHANNEL);
            dataJSON.put("secondChannelName", SECOND_CHANNEL_NAME);
            dataJSON.put("description", DESCRIPTION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //生成用于AES加密的随机字符串aesKey
        String aesKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        //对业务数据进行加密
        REQUEST_DATA = AESUtil.encryptString(dataJSON.toString(), aesKey);
        //使用公钥将生成的aesKey加密
        ENCODE_KEY = RSAUtil.encryptByPublic(aesKey, PUBLIC_KEY);
        //获取accessToken，这里需要调用"获取accessToken接口"来获取accessToken
        //组装公共请求参数
        Map<String, String> requestMap = new HashMap<String, String>(16);
        requestMap.put("method", "faceCompare");
        requestMap.put("clientId", CLIENT_ID);
        requestMap.put("requestId", REQUEST_ID);
        requestMap.put("channel", CHANNEL);
        requestMap.put("channel2", CHANNEL_2);
        requestMap.put("appId", APP_ID);
        requestMap.put("encodeKey", ENCODE_KEY);
        requestMap.put("accessToken", ACCESS_TOKEN);
        requestMap.put("signMethod", SIGN_METHOD);
        requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestMap.put("format", FORMAT);
        requestMap.put("version", VERSION);
        requestMap.put("uri", URI);
        requestMap.put("httpMethod", HTTP_METHOD);
        requestMap.put("comments", HTTP_METHOD);
        requestMap.put("requestData", REQUEST_DATA);
        requestMap.put("sign", HttpUtil.getInstance().sortSignature(requestMap));
        //
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(requestMap).toString());

        HttpUtil.getInstance().getWebService().postFaceDetectByJSON(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        System.out.println("getEncodeKey==>" + responseBean.getEncodeKey());
                        System.out.println("getResponseData==>" + responseBean.getResponseData());

                        String serverAesKey = RSAUtil.decryptByPublic(responseBean.getEncodeKey(), PUBLIC_KEY);
                        System.out.println("serverAesKey==>" + serverAesKey);

                        String serverData = AESUtil.decryptString(responseBean.getResponseData(), serverAesKey);
                        System.out.println("serverData==>" + serverData);
                    }
                });
    }

    private void bioDetect() {
        //组装业务数据json
        JSONObject dataJSON = new JSONObject();
        try {
            dataJSON.put("channelId", CHANNEL);
            dataJSON.put("sdkVersion", SDK_VERSION);
            dataJSON.put("deviceId", DEVICE_ID);
            dataJSON.put("deviceModel", DEVICE_MODEL);
            dataJSON.put("deviceOSAndVersion", DEVICEOS_AND_VERSION);
            dataJSON.put("imgType", IMG_TYPE);
            dataJSON.put("imgFormat", IMG_FORMAT);
            dataJSON.put("imgData", IMG_DATA);
            dataJSON.put("personId", PERSON_ID);
            dataJSON.put("secondChannel", SECOND_CHANNEL);
            dataJSON.put("secondChannelName", SECOND_CHANNEL_NAME);
            dataJSON.put("description", DESCRIPTION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //生成用于AES加密的随机字符串aesKey
        String aesKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        //对业务数据进行加密
        REQUEST_DATA = AESUtil.encryptString(dataJSON.toString(), aesKey);
        //使用公钥将生成的aesKey加密
        ENCODE_KEY = RSAUtil.encryptByPublic(aesKey, PUBLIC_KEY);
        //获取accessToken，这里需要调用"获取accessToken接口"来获取accessToken
        //组装公共请求参数
        Map<String, String> requestMap = new HashMap<String, String>(16);
        requestMap.put("method", "bioDetect");
        requestMap.put("clientId", CLIENT_ID);
        requestMap.put("requestId", REQUEST_ID);
        requestMap.put("channel", CHANNEL);
        requestMap.put("channel2", CHANNEL_2);
        requestMap.put("appId", APP_ID);
        requestMap.put("encodeKey", ENCODE_KEY);
        requestMap.put("accessToken", ACCESS_TOKEN);
        requestMap.put("signMethod", SIGN_METHOD);
        requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestMap.put("format", FORMAT);
        requestMap.put("version", VERSION);
        requestMap.put("uri", URI);
        requestMap.put("httpMethod", HTTP_METHOD);
        requestMap.put("comments", HTTP_METHOD);
        requestMap.put("requestData", REQUEST_DATA);
        requestMap.put("sign", HttpUtil.getInstance().sortSignature(requestMap));
        //
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(requestMap).toString());

        HttpUtil.getInstance().getWebService().postFaceDetectByJSON(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        System.out.println("getEncodeKey==>" + responseBean.getEncodeKey());
                        System.out.println("getResponseData==>" + responseBean.getResponseData());

                        String serverAesKey = RSAUtil.decryptByPublic(responseBean.getEncodeKey(), PUBLIC_KEY);
                        System.out.println("serverAesKey==>" + serverAesKey);

                        String serverData = AESUtil.decryptString(responseBean.getResponseData(), serverAesKey);
                        System.out.println("serverData==>" + serverData);
                    }
                });
    }

    private void idComparison() {
        //组装业务数据json
        JSONObject dataJSON = new JSONObject();
        try {
            dataJSON.put("channelId", CHANNEL);
            dataJSON.put("sdkVersion", SDK_VERSION);
            dataJSON.put("deviceId", DEVICE_ID);
            dataJSON.put("deviceModel", DEVICE_MODEL);
            dataJSON.put("deviceOSAndVersion", DEVICEOS_AND_VERSION);

            dataJSON.put("clientName", "张三");
            dataJSON.put("clientCardNo", "310108198609153839");

            dataJSON.put("imgType", IMG_TYPE);
            dataJSON.put("imgFormat", IMG_FORMAT);
            dataJSON.put("imgData", IMG_DATA);
            dataJSON.put("personId", PERSON_ID);
            dataJSON.put("secondChannel", SECOND_CHANNEL);
            dataJSON.put("secondChannelName", SECOND_CHANNEL_NAME);
            dataJSON.put("description", DESCRIPTION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //生成用于AES加密的随机字符串aesKey
        String aesKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        //对业务数据进行加密
        REQUEST_DATA = AESUtil.encryptString(dataJSON.toString(), aesKey);
        //使用公钥将生成的aesKey加密
        ENCODE_KEY = RSAUtil.encryptByPublic(aesKey, PUBLIC_KEY);
        //获取accessToken，这里需要调用"获取accessToken接口"来获取accessToken
        //组装公共请求参数
        Map<String, String> requestMap = new HashMap<String, String>(16);
        requestMap.put("method", "idComparison");
        requestMap.put("clientId", CLIENT_ID);
        requestMap.put("requestId", REQUEST_ID);
        requestMap.put("channel", CHANNEL);
        requestMap.put("channel2", CHANNEL_2);
        requestMap.put("appId", APP_ID);
        requestMap.put("encodeKey", ENCODE_KEY);
        requestMap.put("accessToken", ACCESS_TOKEN);
        //
        requestMap.put("signMethod", SIGN_METHOD);
        requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestMap.put("format", FORMAT);
        requestMap.put("version", VERSION);
        requestMap.put("uri", URI);
        requestMap.put("httpMethod", HTTP_METHOD);
        requestMap.put("comments", HTTP_METHOD);
        requestMap.put("requestData", REQUEST_DATA);
        requestMap.put("sign", HttpUtil.getInstance().sortSignature(requestMap));
        //
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(requestMap).toString());

        HttpUtil.getInstance().getWebService().postFaceDetectByJSON(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        System.out.println("getEncodeKey==>" + responseBean.getEncodeKey());
                        System.out.println("getResponseData==>" + responseBean.getResponseData());

                        String serverAesKey = RSAUtil.decryptByPublic(responseBean.getEncodeKey(), PUBLIC_KEY);
                        System.out.println("serverAesKey==>" + serverAesKey);

                        String serverData = AESUtil.decryptString(responseBean.getResponseData(), serverAesKey);
                        System.out.println("serverData==>" + serverData);
                    }
                });
    }

    private void faceCollection() {
        //组装业务数据json
        JSONObject dataJSON = new JSONObject();
        try {
            dataJSON.put("channelId", CHANNEL);
            dataJSON.put("sdkVersion", SDK_VERSION);
            dataJSON.put("deviceId", DEVICE_ID);
            dataJSON.put("deviceModel", DEVICE_MODEL);
            dataJSON.put("deviceOSAndVersion", DEVICEOS_AND_VERSION);
            dataJSON.put("imgType", IMG_TYPE);
            dataJSON.put("imgFormat", IMG_FORMAT);
            dataJSON.put("imgData", IMG_DATA);
            dataJSON.put("faceSet", FACE_SET);
            dataJSON.put("personId", PERSON_ID);
            dataJSON.put("secondChannel", SECOND_CHANNEL);
            dataJSON.put("secondChannelName", SECOND_CHANNEL_NAME);
            dataJSON.put("description", DESCRIPTION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //生成用于AES加密的随机字符串aesKey
        String aesKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        //对业务数据进行加密
        REQUEST_DATA = AESUtil.encryptString(dataJSON.toString(), aesKey);
        //使用公钥将生成的aesKey加密
        ENCODE_KEY = RSAUtil.encryptByPublic(aesKey, PUBLIC_KEY);
        //获取accessToken，这里需要调用"获取accessToken接口"来获取accessToken
        //组装公共请求参数
        Map<String, String> requestMap = new HashMap<String, String>(16);
        requestMap.put("method", "faceCollection");
        requestMap.put("clientId", CLIENT_ID);
        requestMap.put("requestId", REQUEST_ID);
        requestMap.put("channel", CHANNEL);
        requestMap.put("channel2", CHANNEL_2);
        requestMap.put("appId", APP_ID);
        requestMap.put("encodeKey", ENCODE_KEY);
        requestMap.put("accessToken", ACCESS_TOKEN);
        //
        requestMap.put("signMethod", SIGN_METHOD);
        requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestMap.put("format", FORMAT);
        requestMap.put("version", VERSION);
        requestMap.put("uri", URI);
        requestMap.put("httpMethod", HTTP_METHOD);
        requestMap.put("comments", HTTP_METHOD);
        requestMap.put("requestData", REQUEST_DATA);
        requestMap.put("sign", HttpUtil.getInstance().sortSignature(requestMap));
        //
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(requestMap).toString());

        HttpUtil.getInstance().getWebService().postFaceDetectByJSON(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        System.out.println("getEncodeKey==>" + responseBean.getEncodeKey());
                        System.out.println("getResponseData==>" + responseBean.getResponseData());

                        String serverAesKey = RSAUtil.decryptByPublic(responseBean.getEncodeKey(), PUBLIC_KEY);
                        System.out.println("serverAesKey==>" + serverAesKey);

                        String serverData = AESUtil.decryptString(responseBean.getResponseData(), serverAesKey);
                        System.out.println("serverData==>" + serverData);
                    }
                });
    }

    private void descreen() {
        //组装业务数据json
        JSONObject dataJSON = new JSONObject();
        try {
            dataJSON.put("channelId", CHANNEL);
            dataJSON.put("sdkVersion", SDK_VERSION);
            dataJSON.put("deviceId", DEVICE_ID);
            dataJSON.put("deviceModel", DEVICE_MODEL);
            dataJSON.put("deviceOSAndVersion", DEVICEOS_AND_VERSION);
            dataJSON.put("imgType", IMG_TYPE);
            dataJSON.put("imgFormat", IMG_FORMAT);
            dataJSON.put("imgData", IMG_DATA);
            dataJSON.put("personId", PERSON_ID);
            dataJSON.put("secondChannel", SECOND_CHANNEL);
            dataJSON.put("secondChannelName", SECOND_CHANNEL_NAME);
            dataJSON.put("description", DESCRIPTION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //生成用于AES加密的随机字符串aesKey
        String aesKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        //对业务数据进行加密
        REQUEST_DATA = AESUtil.encryptString(dataJSON.toString(), aesKey);
        //使用公钥将生成的aesKey加密
        ENCODE_KEY = RSAUtil.encryptByPublic(aesKey, PUBLIC_KEY);
        //获取accessToken，这里需要调用"获取accessToken接口"来获取accessToken
        //组装公共请求参数
        Map<String, String> requestMap = new HashMap<String, String>(16);
        requestMap.put("method", "descreen");
        requestMap.put("clientId", CLIENT_ID);
        requestMap.put("requestId", REQUEST_ID);
        requestMap.put("channel", CHANNEL);
        requestMap.put("channel2", CHANNEL_2);
        requestMap.put("appId", APP_ID);
        requestMap.put("encodeKey", ENCODE_KEY);
        requestMap.put("accessToken", ACCESS_TOKEN);
        //
        requestMap.put("signMethod", SIGN_METHOD);
        requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestMap.put("format", FORMAT);
        requestMap.put("version", VERSION);
        requestMap.put("uri", URI);
        requestMap.put("httpMethod", HTTP_METHOD);
        requestMap.put("comments", HTTP_METHOD);
        requestMap.put("requestData", REQUEST_DATA);
        requestMap.put("sign", HttpUtil.getInstance().sortSignature(requestMap));
        //
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(requestMap).toString());

        HttpUtil.getInstance().getWebService().postFaceDetectByJSON(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        System.out.println("getEncodeKey==>" + responseBean.getEncodeKey());
                        System.out.println("getResponseData==>" + responseBean.getResponseData());

                        String serverAesKey = RSAUtil.decryptByPublic(responseBean.getEncodeKey(), PUBLIC_KEY);
                        System.out.println("serverAesKey==>" + serverAesKey);

                        String serverData = AESUtil.decryptString(responseBean.getResponseData(), serverAesKey);
                        System.out.println("serverData==>" + serverData);
                    }
                });
    }

    private void imgCompare() {
        //组装业务数据json
        JSONObject dataJSON = new JSONObject();
        try {
            dataJSON.put("channelId", CHANNEL);
            dataJSON.put("sdkVersion", SDK_VERSION);
            dataJSON.put("deviceId", DEVICE_ID);
            dataJSON.put("deviceModel", DEVICE_MODEL);
            dataJSON.put("deviceOSAndVersion", DEVICEOS_AND_VERSION);

            dataJSON.put("imgType1", IMG_TYPE);
            dataJSON.put("imgFormat1", IMG_FORMAT);
            dataJSON.put("imgData1", IMG_DATA);

            dataJSON.put("imgType2", IMG_TYPE);
            dataJSON.put("imgFormat2", IMG_FORMAT);
            dataJSON.put("imgData2", IMG_DATA);

            dataJSON.put("personId", PERSON_ID);
            dataJSON.put("secondChannel", SECOND_CHANNEL);
            dataJSON.put("secondChannelName", SECOND_CHANNEL_NAME);
            dataJSON.put("description", DESCRIPTION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //生成用于AES加密的随机字符串aesKey
        String aesKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        //对业务数据进行加密
        REQUEST_DATA = AESUtil.encryptString(dataJSON.toString(), aesKey);
        //使用公钥将生成的aesKey加密
        ENCODE_KEY = RSAUtil.encryptByPublic(aesKey, PUBLIC_KEY);
        //获取accessToken，这里需要调用"获取accessToken接口"来获取accessToken
        //组装公共请求参数
        Map<String, String> requestMap = new HashMap<String, String>(16);
        requestMap.put("method", "imgCompare");
        requestMap.put("clientId", CLIENT_ID);
        requestMap.put("requestId", REQUEST_ID);
        requestMap.put("channel", CHANNEL);
        requestMap.put("channel2", CHANNEL_2);
        requestMap.put("appId", APP_ID);
        requestMap.put("encodeKey", ENCODE_KEY);
        requestMap.put("accessToken", ACCESS_TOKEN);
        //
        requestMap.put("signMethod", SIGN_METHOD);
        requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestMap.put("format", FORMAT);
        requestMap.put("version", VERSION);
        requestMap.put("uri", URI);
        requestMap.put("httpMethod", HTTP_METHOD);
        requestMap.put("comments", HTTP_METHOD);
        requestMap.put("requestData", REQUEST_DATA);
        requestMap.put("sign", HttpUtil.getInstance().sortSignature(requestMap));
        //
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(requestMap).toString());

        HttpUtil.getInstance().getWebService().postFaceDetectByJSON(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        System.out.println("getEncodeKey==>" + responseBean.getEncodeKey());
                        System.out.println("getResponseData==>" + responseBean.getResponseData());

                        String serverAesKey = RSAUtil.decryptByPublic(responseBean.getEncodeKey(), PUBLIC_KEY);
                        System.out.println("serverAesKey==>" + serverAesKey);

                        String serverData = AESUtil.decryptString(responseBean.getResponseData(), serverAesKey);
                        System.out.println("serverData==>" + serverData);
                    }
                });
    }

    private void faceComparison() {
        //组装业务数据json
        JSONObject dataJSON = new JSONObject();
        try {
            dataJSON.put("channelId", CHANNEL);
            dataJSON.put("sdkVersion", SDK_VERSION);
            dataJSON.put("deviceId", DEVICE_ID);
            dataJSON.put("deviceModel", DEVICE_MODEL);
            dataJSON.put("deviceOSAndVersion", DEVICEOS_AND_VERSION);
            dataJSON.put("imgType", IMG_TYPE);
            dataJSON.put("imgFormat", IMG_FORMAT);
            dataJSON.put("imgData", IMG_DATA);
            dataJSON.put("face", IMG_DATA);
            dataJSON.put("personId", PERSON_ID);
            dataJSON.put("secondChannel", SECOND_CHANNEL);
            dataJSON.put("secondChannelName", SECOND_CHANNEL_NAME);
            dataJSON.put("description", DESCRIPTION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //生成用于AES加密的随机字符串aesKey
        String aesKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        //对业务数据进行加密
        REQUEST_DATA = AESUtil.encryptString(dataJSON.toString(), aesKey);
        //使用公钥将生成的aesKey加密
        ENCODE_KEY = RSAUtil.encryptByPublic(aesKey, PUBLIC_KEY);
        //获取accessToken，这里需要调用"获取accessToken接口"来获取accessToken
        //组装公共请求参数
        Map<String, String> requestMap = new HashMap<String, String>(16);
        requestMap.put("method", "faceComparison");
        requestMap.put("clientId", CLIENT_ID);
        requestMap.put("requestId", REQUEST_ID);
        requestMap.put("channel", CHANNEL);
        requestMap.put("channel2", CHANNEL_2);
        requestMap.put("appId", APP_ID);
        requestMap.put("encodeKey", ENCODE_KEY);
        requestMap.put("accessToken", ACCESS_TOKEN);
        //
        requestMap.put("signMethod", SIGN_METHOD);
        requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestMap.put("format", FORMAT);
        requestMap.put("version", VERSION);
        requestMap.put("uri", URI);
        requestMap.put("httpMethod", HTTP_METHOD);
        requestMap.put("comments", HTTP_METHOD);
        requestMap.put("requestData", REQUEST_DATA);
        requestMap.put("sign", HttpUtil.getInstance().sortSignature(requestMap));
        //
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new JSONObject(requestMap).toString());

        HttpUtil.getInstance().getWebService().postFaceDetectByJSON(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        System.out.println("getEncodeKey==>" + responseBean.getEncodeKey());
                        System.out.println("getResponseData==>" + responseBean.getResponseData());

                        String serverAesKey = RSAUtil.decryptByPublic(responseBean.getEncodeKey(), PUBLIC_KEY);
                        System.out.println("serverAesKey==>" + serverAesKey);

                        String serverData = AESUtil.decryptString(responseBean.getResponseData(), serverAesKey);
                        System.out.println("serverData==>" + serverData);
                    }
                });
    }

}
