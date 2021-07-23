package com.tunaPasta20.activity;

import android.view.View;

import com.tunaPasta20.R;
import com.tunaPasta20.data.BindingData;
import com.tunaPasta20.databinding.ActivityBindingDataBinding;
import com.tunaPasta20.model.BindingModel;

/**
 * @author TunaSashimi
 * @date 2020-03-11 11:17
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class BindingDataActivity extends BindingActivity<ActivityBindingDataBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_binding_data;
    }

    @Override
    protected BindingModel getModel() {
        return null;
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                BindingData.getInstance().setHotelInfo("hotel");
                BindingData.getInstance().setHotelSelect(true);
                break;
            default:
                break;
        }
    }
}
