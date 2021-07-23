package com.tunaPasta20.activity;

import android.view.View;

import com.tunaPasta20.R;
import com.tunaPasta20.databinding.ActivityChoiceSingleBinding;
import com.tunaPasta20.model.BindingModel;
import com.tunaPasta20.model.ChoiceSingleModel;

public class ChoiceSingleActivity extends BindingActivity<ActivityChoiceSingleBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_choice_single;
    }

    @Override
    protected BindingModel getModel() {
        return new ChoiceSingleModel(binding, getIntent());
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


