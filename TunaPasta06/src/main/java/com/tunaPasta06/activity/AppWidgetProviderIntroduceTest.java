package com.tunaPasta06.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import com.tunaPasta06.R;

public class AppWidgetProviderIntroduceTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.appwidgetproviderintroducetest);

        readAssetsFile();

    }

    //读取Assets文件~
    private void readAssetsFile() {
        AssetManager as = this.getAssets();
        try {
            InputStream is = as.open("AppWidgetProviderIntroduce.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuffer s = new StringBuffer();
            String str;
            while ((str = br.readLine()) != null) {
                s.append(str + "\n");
            }

            TextView text01 = findViewById(R.id.text01);
            text01.setText(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
