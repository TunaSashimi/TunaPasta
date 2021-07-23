package com.tunaPasta20.activity;

import android.view.View;

import com.tunaPasta20.R;
import com.tunaPasta20.databinding.ActivityChoiceMultipleBinding;
import com.tunaPasta20.model.BindingModel;
import com.tunaPasta20.model.ChoiceMultipleModel;

public class ChoiceMultipleActivity extends BindingActivity<ActivityChoiceMultipleBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_choice_multiple;
    }

    @Override
    protected BindingModel getModel() {
        return new ChoiceMultipleModel(binding, getIntent());
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


