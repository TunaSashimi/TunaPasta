package com.tunaPasta19.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.tunaPasta19.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tunasashimi
 * @date 11/3/15 20:43
 * @Copyright 2015 TunaSashimi. All rights reserved.
 * @Description
 */
public class CustomCakeChooseTest extends Activity {

    private String[] texts01 = {"3寸", "5寸", "7寸"};
    private String[] texts02 = {"圆形", "方形", "心形"};

    private String[] texts03 = {"芝士口味", "慕斯口味", "巧克力味", "拿破仑味", "鲜果口味", "奶油口味"};
    private int[] images03 = {R.drawable.customcaketest_taste01, R.drawable.customcaketest_taste02, R.drawable.customcaketest_taste03,
            R.drawable.customcaketest_taste04, R.drawable.customcaketest_taste01, R.drawable.customcaketest_taste02};

    private String[] texts04 = {"雪域牛乳", "芒果心愿", "唯美树林"};
    private int[] images04 = {R.drawable.customcaketest_pattern01, R.drawable.customcaketest_pattern02, R.drawable.customcaketest_pattern03,
    };


    private Spinner spinner01, spinner02, spinner03, spinner04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.customcakechoosetest);

        //
        spinner01 =  findViewById(R.id.spinner01);
        List<Map<String, Object>> data01 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < texts01.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", texts01[i]);
            data01.add(map);
        }
        spinner01.setAdapter(new SimpleAdapter(this, data01, R.layout.customcaketestitem01,
                new String[]{"text"}, new int[]{R.id.sp_item_text}));

        //
        spinner02 =  findViewById(R.id.spinner02);
        List<Map<String, Object>> data02 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < texts02.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", texts02[i]);
            data02.add(map);
        }
        spinner02.setAdapter(new SimpleAdapter(this, data02, R.layout.customcaketestitem01,
                new String[]{"text"}, new int[]{R.id.sp_item_text}));

        //
        spinner03 =  findViewById(R.id.spinner03);
        List<Map<String, Object>> data03 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < texts03.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", images03[i]);
            map.put("text", texts03[i]);
            data03.add(map);
        }
        spinner03.setAdapter(new SimpleAdapter(this, data03, R.layout.customcaketestitem03,
                new String[]{"img", "text"}, new int[]{R.id.sp_item_img, R.id.sp_item_text}));

        //
        spinner04 =  findViewById(R.id.spinner04);
        List<Map<String, Object>> data04 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < texts04.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", images04[i]);
            map.put("text", texts04[i]);
            data04.add(map);
        }
        spinner04.setAdapter(new SimpleAdapter(this, data04, R.layout.customcaketestitem04,
                new String[]{"img", "text"}, new int[]{R.id.sp_item_img, R.id.sp_item_text}));

        //
        spinner01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        spinner02.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        spinner03.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        spinner04.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        Button button01=  findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomCakeChooseTest.this, CustomCakeProcessTest.class));
            }
        });

    }
}
