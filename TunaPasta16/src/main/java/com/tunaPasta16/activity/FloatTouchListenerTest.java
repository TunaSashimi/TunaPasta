package com.tunaPasta16.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tunaPasta16.R;
import com.tunaPasta16.view.FloatTouchListener;

public class FloatTouchListenerTest extends Activity {

    private FloatTouchListener floatTouchListener;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floattouchlistenertest);

        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.tunasashimi);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatTouchListenerTest.this, "OnClick==>", Toast.LENGTH_SHORT).show();
            }
        });

        floatTouchListener = new FloatTouchListener.Builder()
                .setActivity(this)
                .setLoacation(30, 30)
                .setNeedSuctEdge(false)
                .setSize(100, 100)
                .setView(imageView)
                .build();

        //
        button = findViewById(R.id.btn_move);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean needNearEdge = floatTouchListener.getSuctEdge();
                floatTouchListener.setNeedSuctEdge(!needNearEdge);
                if (needNearEdge) {
                    button.setText("移至边沿");
                } else {
                    button.setText("固定位置");
                }
            }
        });
    }
}
