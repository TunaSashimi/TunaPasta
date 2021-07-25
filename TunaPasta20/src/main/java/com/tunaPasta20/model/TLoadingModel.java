package com.tunaPasta20.model;

import android.content.Intent;

import com.tunaPasta20.activity.TLoadingActivity;
import com.tunaPasta20.databinding.ActivityTLoadingBinding;

/**
 * @author Tunasashimi
 * @date 2020-01-19 15:42
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class TLoadingModel extends BindingModel<ActivityTLoadingBinding> {
    private TLoadingActivity activity;
    public TLoadingModel(ActivityTLoadingBinding binding, Intent intent) {
        super(binding);
        this.binding = binding;
        this.activity = (TLoadingActivity) binding.getRoot().getContext();
        initData(intent);
    }
    protected void initData(Intent intent) {
    }
    public void start(Class cla) {
        Intent intent = new Intent(activity, cla);
        activity.startActivity(intent);
    }
}
