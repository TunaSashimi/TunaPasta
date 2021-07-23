package com.tunaPasta01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.tunaPasta01.R;

public class TableLayoutTest extends Activity {

    private ImageView imageview01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tablelayouttest);

        imageview01 = findViewById(R.id.imageview01);
        imageview01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AniCreator.getInstance().apply_animation_translate(
                        imageview01,
                        AniCreator.ANIMATION_MODE_DROPDOWN_RESEVERD,
                        View.GONE,
                        true,
                        null);
            }
        });
    }
}