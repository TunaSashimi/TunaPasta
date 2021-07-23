package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.tunaPasta14.R;
import com.tunaPasta14.widget.RecycleViewAdapter;
import com.tunaPasta14.widget.RecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class RecyclerViewTest extends Activity {
    private List<String> dataList;

    private Button button;
    private RadioButton radioButton01, radioButton02, radioButton03;

    private RecycleViewAdapter recycleViewAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerviewtest);

        //
        dataList = new ArrayList();
        for (int i = 'A'; i < 'Z'; i++) {
            dataList.add("" + (char) i);
        }

        //
        button = findViewById(R.id.button);

        //
        radioButton01 = findViewById(R.id.radioButton01);
        radioButton02 = findViewById(R.id.radioButton02);
        radioButton03 = findViewById(R.id.radioButton03);

        //
        recyclerView = findViewById(R.id.recyclerView);

        //适配器
        recycleViewAdapter = new RecycleViewAdapter(this, dataList);
        recyclerView.setAdapter(recycleViewAdapter);

        //线性布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Item间的间隔
        recyclerView.addItemDecoration(new RecyclerItemDecoration(this));
        //Item删减动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycleViewAdapter.addData(0);
            }
        });

        //
        radioButton01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewTest.this));    //线性布局
                }
            }
        });
        radioButton02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    recyclerView.setLayoutManager(new GridLayoutManager(RecyclerViewTest.this, 6));     //网格
                }
            }
        });
        radioButton03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));     //流式布局
                }
            }
        });
    }
}

