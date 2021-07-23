package com.tunaPasta20.activity;

import android.view.View;

import com.tunaPasta20.R;
import com.tunaPasta20.databinding.ActivityGridViewBinding;
import com.tunaPasta20.model.BindingModel;
import com.tunaPasta20.model.GridViewModel;

public class GridViewActivity extends BindingActivity<ActivityGridViewBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_grid_view;
    }

    @Override
    protected BindingModel getModel() {
        return new GridViewModel(binding, getIntent());
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}


