package com.tunaPasta20.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.tunaPasta20.R;
import com.tunaPasta20.databinding.ActivityEntryBinding;
import com.tunaPasta20.model.BindingModel;

import java.util.ArrayList;
import java.util.List;

public class EntryActivity extends BindingActivity<ActivityEntryBinding> {
    private Class<?>[] cla = {
            BindingDataActivity.class, ChoiceMultipleActivity.class, ChoiceSingleActivity.class, GridViewActivity.class,
            TLoadingActivity.class, TViewSrcActivity.class, TwoWayBindingActivity.class, TWrapActivity.class,
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_entry;
    }

    @Override
    protected BindingModel getModel() {
        return null;
    }

    @Override
    public void onInit() {
        List<String> stringList = new ArrayList();
        for (int i = 0; i < cla.length; i++) {
            stringList.add(cla[i].getSimpleName());

        }
        binding.listView.setAdapter(new ArrayAdapter(this, R.layout.activity_entry_item, stringList));
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startActivity(new Intent(EntryActivity.this, cla[arg2]));
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
