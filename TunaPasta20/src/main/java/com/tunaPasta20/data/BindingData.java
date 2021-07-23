package com.tunaPasta20.data;


import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * @author Tunasashimi
 * @date 2019-06-29 21:00
 * @Copyright 2019 TunaSashimi. All rights reserved.
 * @Description
 */
public class BindingData {
    private BindingData() {
    }

    private static BindingData instance = new BindingData();

    public static BindingData getInstance() {
        return instance;
    }

    private ObservableField<String> hotelInfo = new ObservableField<>();

    public ObservableField<String> getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(String hotelInfo) {
        this.hotelInfo.set(hotelInfo);
    }


    private ObservableBoolean hotelSelect = new ObservableBoolean();

    public ObservableBoolean getHotelSelect() {
        return hotelSelect;
    }

    public void setHotelSelect(boolean hotelSelect) {
        this.hotelSelect.set(hotelSelect);
    }

    //
    private ObservableBoolean chooseAll = new ObservableBoolean(false);

    public ObservableBoolean getChooseAll() {
        return chooseAll;
    }

    public void setChooseAll(Boolean chooseAll) {
        this.chooseAll.set(chooseAll);
    }

    //
    private ObservableInt chooseIndex = new ObservableInt(-1);

    public ObservableInt getChooseIndex() {
        return chooseIndex;
    }

    public void setChooseIndex(int chooseIndex) {
        this.chooseIndex.set(chooseIndex);
    }
}