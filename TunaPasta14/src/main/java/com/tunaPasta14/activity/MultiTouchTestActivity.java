package com.tunaPasta14.activity;

import java.io.InputStream;

import com.tunaPasta14.R;
import com.tunaPasta14.widget.ZoomTextView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MultiTouchTestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multitouchtestactivity);
        TextView textView = this.findViewById(R.id.text_view);
        try {
            textView.setText(readText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        float zoomScale = 0.5f;
        new ZoomTextView(textView, zoomScale);
    }

    public String readText() throws Exception {
        InputStream is = this.getClass().getResourceAsStream("/assets/text.txt");
        int index = is.available();
        byte data[] = new byte[index];
        is.read(data);
        return new String(data, "UTF-8");
    }
}