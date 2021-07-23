package com.tunaPasta20.data;


import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

/**
 * @author TunaSashimi
 * @date 2020-04-11 23:42
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class Choice extends BaseObservable {
    public ObservableBoolean choice = new ObservableBoolean();
    public ObservableField<String> name = new ObservableField();
}