package com.tunaPasta01.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.tunaPasta01.R;

public class SpinnerTest extends Activity {
    private Spinner sp_01, sp_02;
    private String[] texts = {"上海", "香港", "台北", "云南"};
    private int[] images = {R.drawable.mv01, R.drawable.mv02, R.drawable.mv03, R.drawable.mv04};
    private String[][] cities = {{"黄浦", "闸北", "徐汇", "静安"}, {"九龍", "灣仔", "新界", "西貢"},
            {"台北", "新竹", "高雄", "基隆"}, {"昆明", "玉溪", "大理", "香格里拉"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinnertest);

        sp_01 = findViewById(R.id.spinner_test_sp_01);
        sp_02 = findViewById(R.id.spinner_test_sp_02);

        List<Map<String, Object>> data = new ArrayList();
        for (int i = 0; i < texts.length; i++) {
            Map<String, Object> map = new HashMap();
            map.put("img", images[i]);
            map.put("text", texts[i]);
            data.add(map);
        }

        sp_01.setAdapter(new SimpleAdapter(this, data, R.layout.spinnerdrop, new String[]{"img", "text"}, new int[]{R.id.sp_item_img, R.id.sp_item_text}));
        sp_01.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                List<String> list = new ArrayList();
                for (int i = 0; i < cities[arg2].length; i++)
                    list.add(cities[arg2][i]);
                sp_02.setAdapter(new ArrayAdapter(SpinnerTest.this, android.R.layout.simple_spinner_item, list));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                System.out.println("onNothingSelected==>");
            }
        });
    }
}
