package com.tunaPasta20.model;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.tunaPasta20.R;
import com.tunaPasta20.activity.GridViewActivity;
import com.tunaPasta20.activity.TWrapActivity;
import com.tunaPasta20.adapter.BindingAdapter;
import com.tunaPasta20.data.Label;
import com.tunaPasta20.data.Person;
import com.tunaPasta20.databinding.ActivityGridItemBinding;
import com.tunaPasta20.databinding.ActivityGridViewBinding;
import com.tunaPasta20.databinding.ActivityTwrapBinding;
import com.tunaPasta20.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tunasashimi
 * @date 2020-01-19 15:42
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class TWrapModel extends BindingModel<ActivityTwrapBinding> {
    private TWrapActivity activity;

    //
    public TWrapModel(ActivityTwrapBinding binding, Intent intent) {
        super(binding);
        this.binding = binding;
        this.activity = (TWrapActivity) binding.getRoot().getContext();
        initData(intent);
    }


    protected void initData(Intent intent) {
    }

    public void setLabel() {
        Label label = new Label();
        label.name = "name";
//        label.wrapTextArray = new String[]{"ABC", "BBB", "CCC"};
        label.wrapTextArray = new ArrayList();
        label.wrapTextArray .add("ABc");
        label.wrapTextArray .add("BBB");
        label.wrapTextArray .add("CCC");
        binding.setBean(label);
    }
}
