package com.tunaPasta08.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tunaPasta08.R;

public class P04_FashionGuiderTest extends Activity {
    private Spinner sp01, sp02, sp03, sp04;
    private List<String> type, season, body, skin;
    private ArrayAdapter<String> a01, a02, a03, a04;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p04_fashionguidertest);

//		button = (Button) findViewById(R.id.appo_bu);
//		button.setOnClickListener(new android.view.View.OnClickListener() {
//			@Override
//			public void onClick(android.view.View v) {
//				Intent it=new Intent(P04_FashionGuiderAct.this,Result.class);
//				it.putExtra("values", "" + sp02.getSelectedItemPosition());
//				startActivity(it);
//			}
//		});

        sp01 = findViewById(R.id.appo_sp01);
        type = new ArrayList();
        type.add("清纯萝莉");
        type.add("性感火辣");
        type.add("文静淑女");
        type.add("知性美女");
        a01 = new ArrayAdapter(this,
            android.R.layout.simple_spinner_item, type);
        a01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp01.setAdapter(a01);

        sp02 = findViewById(R.id.appo_sp02);
        season = new ArrayList();
        season.add("春季");
        season.add("夏季");
        season.add("秋季");
        season.add("冬季");
        a02 = new ArrayAdapter(this,
            android.R.layout.simple_spinner_item, season);
        a02.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp02.setAdapter(a02);

        sp03 = findViewById(R.id.appo_sp03);
        body = new ArrayList();
        body.add("比较骨感");
        body.add("稍微偏瘦");
        body.add("稍微偏胖");
        body.add("丰满匀称");
        a03 = new ArrayAdapter(this,
            android.R.layout.simple_spinner_item, body);
        a03.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp03.setAdapter(a03);

        sp04 = findViewById(R.id.appo_sp04);
        skin = new ArrayList();
        skin.add("白皙");
        skin.add("偏白");
        skin.add("偏黑");
        skin.add("棕色");
        a04 = new ArrayAdapter(this,
            android.R.layout.simple_spinner_item, skin);
        a04.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp04.setAdapter(a04);
    }
}
