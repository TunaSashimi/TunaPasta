package com.tunaPasta19.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.tunaPasta19.R;
import com.tunaPasta19.adapter.StageImageAdapter;
import com.tunaPasta19.widget.StageGallery;

public class StageLayoutTest extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stagelayouttest);

        Integer[] images = {R.drawable.stagelayouttest_imageview_item01, R.drawable.stagelayouttest_imageview_item02,
            R.drawable.stagelayouttest_imageview_item03, R.drawable.stagelayouttest_imageview_item04, R.drawable.stagelayouttest_imageview_item05,
            R.drawable.stagelayouttest_imageview_item06, R.drawable.stagelayouttest_imageview_item07, R.drawable.stagelayouttest_imageview_item08,
            R.drawable.stagelayouttest_imageview_item09, R.drawable.stagelayouttest_imageview_item10,};

        StageImageAdapter stageimageAdapter = new StageImageAdapter(this, images);
        stageimageAdapter.createReflectedImages();// 创建倒影效果

        StageGallery galleryFlow = findViewById(R.id.Gallery01);

        galleryFlow.setFadingEdgeLength(0);
        galleryFlow.setSpacing(10); // 图片之间的间距
        galleryFlow.setAdapter(stageimageAdapter);

        galleryFlow.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        galleryFlow.setSelection(200);
    }
}