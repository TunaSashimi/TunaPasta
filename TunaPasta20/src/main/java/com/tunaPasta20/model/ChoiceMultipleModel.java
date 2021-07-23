package com.tunaPasta20.model;

import android.content.Intent;
import android.view.View;
import com.tunaPasta20.R;
import com.tunaPasta20.activity.ChoiceMultipleActivity;
import com.tunaPasta20.adapter.BindingAdapter;
import com.tunaPasta20.data.BindingData;
import com.tunaPasta20.data.Hotel;
import com.tunaPasta20.databinding.ActivityChoiceMultipleBinding;
import com.tunaPasta20.databinding.ActivityChoiceMultipleItemBinding;
import com.tunaPasta20.listener.OnItemClickListener;
import com.tunasushi.view.TView;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tunasashimi
 * @date 2020-01-19 15:42
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class ChoiceMultipleModel extends BindingModel<ActivityChoiceMultipleBinding> implements OnItemClickListener<ActivityChoiceMultipleItemBinding> {
    private ChoiceMultipleActivity activity;
    public List<Hotel> hotelList = new ArrayList();

    public ChoiceMultipleModel(ActivityChoiceMultipleBinding binding, Intent intent) {
        super(binding);
        this.binding = binding;
        this.activity = (ChoiceMultipleActivity) binding.getRoot().getContext();
        initData(intent);
    }

    @Override
    public void onItemClick(View v, int position, ActivityChoiceMultipleItemBinding binding) {
        switch (v.getId()) {
            case R.id.tViewSelect:
                TView t = (TView) v;
                if (t.isSelect()) {
                    //do something
                } else {
                    //do something
                }
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
        BindingAdapter bindingAdapter = new BindingAdapter(hotelList, R.layout.activity_choice_multiple_item, this);
        binding.setAdapter(bindingAdapter);

        BindingData bindingData = BindingData.getInstance();
        bindingData.setChooseAll(false);
    }

    //单选
    public void chooseAll() {
        BindingData bindingData = BindingData.getInstance();
        bindingData.setChooseIndex(-1);
        if (bindingData.getChooseAll().get()) {
            bindingData.setChooseAll(false);
            //do something
        } else {
            bindingData.setChooseAll(true);
            //do something
        }
    }
}
