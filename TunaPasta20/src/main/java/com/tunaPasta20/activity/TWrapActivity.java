package com.tunaPasta20.activity;

import android.view.View;

import com.tunaPasta20.R;
import com.tunaPasta20.databinding.ActivityTwrapBinding;
import com.tunaPasta20.model.BindingModel;
import com.tunaPasta20.model.TWrapModel;

public class TWrapActivity extends BindingActivity<ActivityTwrapBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_twrap;
    }

    @Override
    protected BindingModel getModel() {
        return new TWrapModel(binding, getIntent());
    }

    @Override
    public void onInit() {
    }

    @Override
    public void onClick(View v) {
    }
}


