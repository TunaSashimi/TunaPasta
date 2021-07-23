package com.tunaPasta14.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta14.R;

public class ImageFilterChoice extends Activity {
    ListView mListView;
    private String[] mListStr = {"1 冰冻", "2 熔铸", "3 连环画"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagefilterchoice);
        findView();
    }

    private void findView() {
        mListView = findViewById(R.id.listview);
        mListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mListStr));
        mListView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(ImageFilterChoice.this, ImageFilterTest.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
}