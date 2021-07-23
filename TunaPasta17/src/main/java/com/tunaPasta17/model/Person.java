package com.tunaPasta17.model;


import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * @author TunaSashimi
 * @date 2020-03-13 15:59
 * @Copyright 2020 TunaSashimi. All rights reserved.
 * @Description
 */
public class Person {
    public final ObservableInt id = new ObservableInt();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> url = new ObservableField<>();
}
