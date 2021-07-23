package com.tunaPasta06.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Gallery;

import com.tunaPasta06.R;
import com.tunaPasta06.adapter.ImageAdapter;

public class AnimatedGalleryTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animatedgallerytest);

        ImageAdapter imageAdapter = new ImageAdapter(this);

        Gallery g1 = findViewById(R.id.gallery1);
        g1.setAdapter(imageAdapter);

        Gallery g2 = findViewById(R.id.gallery2);
        g2.setAdapter(imageAdapter);
    }
}