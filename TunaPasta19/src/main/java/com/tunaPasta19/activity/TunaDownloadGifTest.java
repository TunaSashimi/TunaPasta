package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta19.R;
import com.tunaPasta19.application.DataTrans;
import com.tunaPasta19.tuna.TunaDownload;

public class TunaDownloadGifTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tunadownloadgiftest);

        TunaDownload tunaDownload = findViewById(R.id.tunaDownload);
        tunaDownload.init(DataTrans.tunaGraphicsMap, "http://note.youdao.com/yws/public/resource/2dd3c3c857734250c0a88261f1344477/6330A674DC224B868296763564D75432");
    }
}
