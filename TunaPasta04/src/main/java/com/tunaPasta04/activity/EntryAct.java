package com.tunaPasta04.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.tunaPasta04.R;

public class EntryAct extends Activity {
    private int[] image = {R.drawable.calcu, R.drawable.getpicture, R.drawable.camera, R.drawable.arrow,
            R.drawable.photo, R.drawable.facedect, R.drawable.facedect2, R.drawable.localfile};
    private Class<?>[] classes = {CalculaterTest.class, GetPicture.class, CustomCamera.class, BackFormTest.class,
            ImageProcessTest.class, FaceTest.class, PhotoTest.class, LocalFileManagerTest.class};
    private GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entryact);

        List<Map<String, Object>> data = new ArrayList();
        //把要填充到GridView的元素写到res/values/文件中的<string-array>元素中,使界面与代码更好地分离！
        String[] title = getResources().getStringArray(R.array.root);
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> map = new HashMap();
            map.put("image", image[i]);
            map.put("title", title[i]);
            data.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.entryactitem, new String[]{"image", "title"}, new int[]{R.id.itemImage, R.id.itemText});
        gridView = findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(EntryAct.this, classes[arg2]));
            }
        });
    }
}
