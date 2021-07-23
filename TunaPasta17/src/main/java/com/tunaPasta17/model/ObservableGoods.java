package com.tunaPasta17.model;


import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;

public class ObservableGoods {

    private ObservableField<String> name;

    private ObservableField<String> detail;

    private ObservableFloat price;

    public ObservableGoods(String name, String details, float price) {
        this.name = new ObservableField<>(name);
        this.detail = new ObservableField<>(details);
        this.price = new ObservableFloat(price);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public ObservableField<String> getDetail() {
        return detail;
    }

    public ObservableFloat getPrice() {
        return price;
    }


    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setDetail(ObservableField<String> detail) {
        this.detail = detail;
    }

    public void setDetails(String details) {
        this.detail.set(details);
    }

    public void setPrice(ObservableFloat price) {
        this.price = price;
    }

    public void setPrice(Float price) {
        this.price.set(price);
    }
}