package com.tunaPasta18.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.tunaPasta18.R;
import com.tunaPasta18.entity.IndentFocus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * @author Tunasashimi
 * @date 09/11/2017 18:12
 * @Copyright 2017 TunaSashimi. All rights reserved.
 * @Description
 */
public class IndentFocusListTest extends Activity {
    public static boolean listExpend = false;
    private RelativeLayout relative_indentfocuslisttest;
    private ListView list_indentfocuslisttest;
    private ClaimsMaterialAdapter mAdapter;
    private ArrayList<IndentFocus> claimMaterialItemsArrary;
    public static final String ARG_CLAIM_MATERIAL_LIST = "arg_claim_material_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.indentfocuslisttest);

        claimMaterialItemsArrary = new ArrayList();

        for (int i = 0; i < 29; i++) {
            IndentFocus claimMaterialItems = new IndentFocus();
            claimMaterialItems.id = String.valueOf(i);
            claimMaterialItems.sort = String.valueOf(i);
            claimMaterialItems.name = "测试" + i;
            claimMaterialItems.count = i == 28 ? "" : "0";
            claimMaterialItemsArrary.add(claimMaterialItems);
        }

        //计算是否需要展开列表项
        listExpend = false;
        for (int i = 0; i < claimMaterialItemsArrary.size() - 1; i++) {
            if (claimMaterialItemsArrary.get(i).hasValue()) {
                listExpend = true;
                break;
            }
        }

        //计算排序
        Collections.sort(claimMaterialItemsArrary, new Comparator<IndentFocus>() {
            @Override
            public int compare(IndentFocus t1, IndentFocus t2) {
                if (!t1.hasValue() && t2.hasValue()) {
                    return 1;
                } else if (t1.hasValue() && !t2.hasValue()) {
                    return -1;
                } else {
                    int sort1 = Integer.parseInt(t1.sort);
                    int sort2 = Integer.parseInt(t2.sort);
                    if (sort1 > sort2) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });

        //
        list_indentfocuslisttest = findViewById(R.id.list_indentfocuslisttest);

        mAdapter = new ClaimsMaterialAdapter(IndentFocusListTest.this, claimMaterialItemsArrary);
        list_indentfocuslisttest.setAdapter(mAdapter);

        //
        relative_indentfocuslisttest = findViewById(R.id.relative_indentfocuslisttest);
        relative_indentfocuslisttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listExpend = true;
                relative_indentfocuslisttest.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
            }
        });

        Button button_indentfocuslisttest = findViewById(R.id.button_indentfocuslisttest);
        button_indentfocuslisttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra(ARG_CLAIM_MATERIAL_LIST, claimMaterialItemsArrary);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}