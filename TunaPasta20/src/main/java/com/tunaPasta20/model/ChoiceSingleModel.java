package com.tunaPasta20.model;

import android.content.Intent;
import android.view.View;

import com.tunaPasta20.R;
import com.tunaPasta20.activity.ChoiceSingleActivity;
import com.tunaPasta20.adapter.BindingAdapter;
import com.tunaPasta20.data.BindingData;
import com.tunaPasta20.data.Hotel;
import com.tunaPasta20.databinding.ActivityChoiceSingleBinding;
import com.tunaPasta20.databinding.ActivityChoiceSingleItemBinding;
import com.tunaPasta20.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tunasashimi
 * @date 2020-01-19 15:42
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class ChoiceSingleModel extends BindingModel<ActivityChoiceSingleBinding> implements OnItemClickListener<ActivityChoiceSingleItemBinding> {
    private ChoiceSingleActivity activity;
    public List<Hotel> hotelList = new ArrayList();

    public ChoiceSingleModel(ActivityChoiceSingleBinding binding, Intent intent) {
        super(binding);
        this.binding = binding;
        this.activity = (ChoiceSingleActivity) binding.getRoot().getContext();
        initData(intent);
    }

    @Override
    public void onItemClick(View v, int position, ActivityChoiceSingleItemBinding binding) {
        switch (v.getId()) {
            case R.id.tViewSelect:
                BindingData bindingData = BindingData.getInstance();
                int index = bindingData.getChooseIndex().get();
                if (position == index) {
                    bindingData.setChooseIndex(-1);
                } else {
                    bindingData.setChooseIndex(position);
                }
                //do something
                break;
            default:
                break;
        }
    }

    protected void initData(Intent intent) {
        hotelList.add(new Hotel("7天"));
        hotelList.add(new Hotel("如家"));
        hotelList.add(new Hotel("汉庭"));
        hotelList.add(new Hotel("锦江"));
        hotelList.add(new Hotel("莫泰"));

        hotelList.add(new Hotel("海友"));
        hotelList.add(new Hotel("布丁"));
        hotelList.add(new Hotel("桔子"));
        hotelList.add(new Hotel("全季"));
        hotelList.add(new Hotel("怡莱"));

        //
        BindingAdapter bindingAdapter = new BindingAdapter(hotelList, R.layout.activity_choice_single_item, this);
        binding.setAdapter(bindingAdapter);

        BindingData bindingData = BindingData.getInstance();
        bindingData.setChooseAll(false);
    }
}
