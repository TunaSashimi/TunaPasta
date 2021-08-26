package com.tunaPasta16.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tunaPasta16.R;
import com.tunaPasta16.view.FloatingDecorTouchListener;

public class FloatingDecorViewTest extends Activity {
    private FloatingDecorTouchListener floatingDecorTouchListener;
    private Button btn_move, btn_Visible;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floatingdecorviewtest);

        imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.tunasashimi);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatingDecorViewTest.this, "OnClick==>", Toast.LENGTH_SHORT).show();
            }
        });

        floatingDecorTouchListener = new FloatingDecorTouchListener.Builder()
                .setActivity(this)
                .setLoacation(30, 30)
                .setNeedSuctEdge(false)
                .setSize(100, 100)
                .setView(imageView)
                .build();

        //
        btn_move = findViewById(R.id.btn_move);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean needNearEdge = floatingDecorTouchListener.getSuctEdge();
                floatingDecorTouchListener.setNeedSuctEdge(!needNearEdge);
                if (needNearEdge) {
                    btn_move.setText("移至边沿");
                } else {
                    btn_move.setText("固定位置");
                }
            }
        });

        btn_Visible = findViewById(R.id.btn_Visible);
        btn_Visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getVisibility() == View.VISIBLE) {
                    imageView.setVisibility(View.INVISIBLE);
                    btn_Visible.setText("显示");
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    btn_Visible.setText("隐藏");
                }
            }
        });
    }
}
