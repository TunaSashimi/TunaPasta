package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaDownload;
import com.tunaPasta19.tuna.TunaGif;

import java.util.HashMap;

public class TunaGifTest extends Activity {

    TunaGif tunaGif = null;

    private final static int TUNAGIF_DISMISS = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TUNAGIF_DISMISS:
                    if (tunaGif != null) {
                        tunaGif.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tunagiftest);

        tunaGif = findViewById(R.id.tunaGif);
        tunaGif.init(R.drawable.tunagiftest_loading);

        handler.sendEmptyMessageDelayed(TUNAGIF_DISMISS, 5000);

        TunaDownload tunaDownload = findViewById(R.id.tunaDownload);
        tunaDownload.init(new HashMap<String, Object>(), "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/2D869AA12DEE47A8ABF06203A5349EA6");
//        tunaDownload.setTunaDownloadCompleteListener(new TunaDownload.TunaDownloadCompleteListener() {
//            @Override
//            public void tunaDownloadComplete() {
//                tunaGif.setVisibility(View.GONE);
//            }
//        });
    }
}