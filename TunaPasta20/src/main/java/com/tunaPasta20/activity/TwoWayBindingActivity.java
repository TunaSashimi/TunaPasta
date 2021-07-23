package com.tunaPasta20.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tunaPasta20.R;
import com.tunaPasta20.data.Choice;
import com.tunaPasta20.databinding.ActivityTwoWayBindingBinding;

import androidx.databinding.DataBindingUtil;

/**
 * @author TunaSashimi
 * @date 2020-04-12 20:30
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class TwoWayBindingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityTwoWayBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_binding);
        Choice choice = new Choice();
        choice.name.set("Name");

        binding.setChoice(choice);
        choice.choice.set(true);
    }
}
