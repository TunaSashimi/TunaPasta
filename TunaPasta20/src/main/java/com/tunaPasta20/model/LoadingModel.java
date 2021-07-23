package com.tunaPasta20.model;

import android.content.Intent;

import com.tunaPasta20.activity.LoadingActivity;
import com.tunaPasta20.databinding.ActivityLoadingBinding;

/**
 * @author Tunasashimi
 * @date 2020-01-19 15:42
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class LoadingModel extends BindingModel<ActivityLoadingBinding> {
    private LoadingActivity activity;
    public LoadingModel(ActivityLoadingBinding binding, Intent intent) {
        super(binding);
        this.binding = binding;
        this.activity = (LoadingActivity) binding.getRoot().getContext();
        initData(intent);
    }
    protected void initData(Intent intent) {
    }
    public void start(Class cla) {
        Intent intent = new Intent(activity, cla);
        activity.startActivity(intent);
    }
}
