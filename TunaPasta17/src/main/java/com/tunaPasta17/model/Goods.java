package com.tunaPasta17.model;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Goods extends BaseObservable {

    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @Bindable
    public String name;

    //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
    private String detail;

    private float price;

    public Goods(String name, String detail, float price) {
        this.name = name;
        this.detail = detail;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.tunaPasta17.BR.name);
    }

    @Bindable
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
        notifyChange();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                '}';
    }

}