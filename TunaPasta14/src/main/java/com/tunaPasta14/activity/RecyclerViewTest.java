package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.tunaPasta14.R;
import com.tunaPasta14.adapter.RecycleViewAdapter;
import com.tunaPasta14.widget.RecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class RecyclerViewTest extends Activity {
    private List<String> dataList;

    private Button buttonAdd, buttonGet;
    private RadioButton radioButton01, radioButton02, radioButton03;

    private RecycleViewAdapter recycleViewAdapter;
    private RecyclerView recyclerViewSource, recyclerViewLink;

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
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonGet = findViewById(R.id.buttonGet);

        //
        radioButton01 = findViewById(R.id.radioButton01);
        radioButton02 = findViewById(R.id.radioButton02);
        radioButton03 = findViewById(R.id.radioButton03);

        //
        recyclerViewSource = findViewById(R.id.recyclerViewSource);
        recyclerViewLink = findViewById(R.id.recyclerViewLink);

        //适配器
        recycleViewAdapter = new RecycleViewAdapter(this, dataList);
        recyclerViewSource.setAdapter(recycleViewAdapter);
        recyclerViewLink.setAdapter(recycleViewAdapter);

        //线性布局
        recyclerViewSource.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLink.setLayoutManager(new LinearLayoutManager(this));

        //Item间的间隔
        recyclerViewSource.addItemDecoration(new RecyclerItemDecoration(this));
        recyclerViewLink.addItemDecoration(new RecyclerItemDecoration(this));

        //Item删减动画
        recyclerViewSource.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLink.setItemAnimator(new DefaultItemAnimator());

        //
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycleViewAdapter.addData(0);
            }
        });

        //
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayoutManager layoutManagerSource = (LinearLayoutManager) recyclerViewSource.getLayoutManager();
                int x = recyclerViewSource.computeHorizontalScrollOffset();
                int y = recyclerViewSource.computeVerticalScrollOffset();
                int position = layoutManagerSource.findFirstVisibleItemPosition();
                //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
                LinearLayoutManager layoutManagerLink = (LinearLayoutManager) recyclerViewLink.getLayoutManager();
                int firstItem = layoutManagerLink.findFirstVisibleItemPosition();
                int lastItem = layoutManagerLink.findLastVisibleItemPosition();
                //然后区分情况
                if (position <= firstItem) {
                    //当要置顶的项在当前显示的第一个项的前面时
                    recyclerViewLink.scrollToPosition(position);
//                    recyclerViewLink.scrollBy(x, y);
                } else if (position <= lastItem) {
                    //当要置顶的项已经在屏幕上显示时
                    int top = recyclerViewLink.getChildAt(position - firstItem).getTop();
                    recyclerViewLink.scrollBy(position, top);
                } else {//当要置顶的项在当前显示的最后一项的后面时
                    recyclerViewLink.scrollToPosition(position);
//                    recyclerViewLink.scrollBy(x, y);
                }
            }
        });

        //
        radioButton01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    recyclerViewSource.setLayoutManager(new LinearLayoutManager(RecyclerViewTest.this));    //线性布局
                }
            }
        });
        radioButton02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    recyclerViewSource.setLayoutManager(new GridLayoutManager(RecyclerViewTest.this, 6));     //网格
                }
            }
        });
        radioButton03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    recyclerViewSource.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));     //流式布局
                }
            }
        });
    }
}

