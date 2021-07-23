package com.tunaPasta06.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.tunaPasta06.R;
import com.tunaPasta06.entity.Pet;

public class GridViewTest extends Activity {
    private List<Map<String, Object>> data;
    private GridView gridView01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gridviewtest);

        gridView01 = findViewById(R.id.gridView01);

        initData();

        gridView01.setAdapter(new SimpleAdapter(this, data,
                R.layout.gridviewtestitem, new String[]{Pet.PHOTO,
                Pet.NAME}, new int[]{R.id.photo, R.id.name}));

        gridView01.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageV = view.findViewById(R.id.photo);
                Animation animation = new RotateAnimation(0, 360 * 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(1000);
                imageV.setAnimation(animation);
            }
        });
    }

    private void initData() {
        data = new ArrayList();
        data.add(Pet.toMap("name0", "13683395890", R.drawable.item_00));
        data.add(Pet.toMap("name1", "13683395890", R.drawable.item_01));
        data.add(Pet.toMap("name2", "13683395890", R.drawable.item_02));
        data.add(Pet.toMap("name3", "13683395890", R.drawable.item_03));
        data.add(Pet.toMap("name4", "13683395890", R.drawable.item_04));
        data.add(Pet.toMap("name5", "13683395890", R.drawable.item_05));
        data.add(Pet.toMap("name6", "13683395890", R.drawable.item_06));
        data.add(Pet.toMap("name7", "13683395890", R.drawable.item_07));
        data.add(Pet.toMap("name8", "13683395890", R.drawable.item_08));
        data.add(Pet.toMap("name9", "13683395890", R.drawable.item_09));
        data.add(Pet.toMap("name10", "13683395890", R.drawable.item_10));
        data.add(Pet.toMap("name11", "13683395890", R.drawable.item_11));
    }
}
