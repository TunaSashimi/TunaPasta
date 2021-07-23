package com.tunaPasta20.model;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.tunaPasta20.R;
import com.tunaPasta20.activity.GridViewActivity;
import com.tunaPasta20.adapter.BindingAdapter;
import com.tunaPasta20.data.Person;
import com.tunaPasta20.databinding.ActivityGridItemBinding;
import com.tunaPasta20.databinding.ActivityGridViewBinding;
import com.tunaPasta20.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tunasashimi
 * @date 2020-01-19 15:42
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class GridViewModel extends BindingModel<ActivityGridViewBinding> implements OnItemClickListener<ActivityGridItemBinding> {
    private GridViewActivity activity;
    private BindingAdapter bindingAdapter;
    //
    public List<Person> personList = new ArrayList();
    public GridViewModel(ActivityGridViewBinding binding, Intent intent) {
        super(binding);
        this.binding = binding;
        this.activity = (GridViewActivity) binding.getRoot().getContext();
        initData(intent);
    }

    @Override
    public void onItemClick(View v, int position, ActivityGridItemBinding binding) {
        switch (v.getId()) {
            case R.id.tvName:
                Toast.makeText(activity, "text_name==>position==>" + position, Toast.LENGTH_SHORT).show();
                personList.get(position).age = 0;
                break;
            case R.id.tViewAge:
                Toast.makeText(activity, "text_age==>position==>" + position, Toast.LENGTH_SHORT).show();
                for (int i = 0; i < personList.size(); i++) {
                    Person person = personList.get(i);
                    if (i == position) {
                        person.marry = !person.marry;
                    } else {
                        person.marry = false;
                    }
                }
                bindingAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    protected void initData(Intent intent) {
        personList.add(new Person("张三", 25, false));
        personList.add(new Person("张三", 26, false));
        personList.add(new Person("张三", 27, false));
        personList.add(new Person("张三", 28, false));
        personList.add(new Person("张三", 29, false));

        personList.add(new Person("李四", 26, false));
        personList.add(new Person("李四", 27, false));
        personList.add(new Person("李四", 28, false));
        personList.add(new Person("李四", 29, false));
        personList.add(new Person("李四", 30, false));

        //
        bindingAdapter = new BindingAdapter(personList, R.layout.activity_grid_item, this);
        binding.setAdapter(bindingAdapter);
    }

    public void change() {
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            person.name = "王五";
            person.age = 10;
        }
        binding.setAdapter(bindingAdapter);
    }

    public String calcu() {
        int total = 0;
        if (personList != null && personList.size() > 0) {
            for (int i = 0; i < personList.size(); i++) {
                Person person = personList.get(i);
                total += person.age;
            }
            return total + "==>";
        } else {
            return "没有数据";
        }
    }
}
