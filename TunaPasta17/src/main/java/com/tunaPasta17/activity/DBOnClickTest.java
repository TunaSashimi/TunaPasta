package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;
import com.tunaPasta17.R;
import com.tunaPasta17.databinding.DbOnClickBinding;
import com.tunaPasta17.model.Goods;

import java.util.Random;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

public class DBOnClickTest extends Activity {
    private Goods goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbOnClickBinding binding = DataBindingUtil.setContentView(this, R.layout.db_on_click);
        goods = new Goods("Goods", "old", 24);
        binding.setGoods(goods);
        binding.setGoodsHandler(new GoodsHandler());
        goods.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == com.tunaPasta17.BR.name) {
                    System.out.println("==>BR.name");
                } else if (propertyId == com.tunaPasta17.BR.detail) {
                    System.out.println("==>BR.details");
                } else if (propertyId == com.tunaPasta17.BR._all) {
                    System.out.println("==>BR._all");
                } else {
                    System.out.println("==>未知");
                }
            }
        });
    }

    public class GoodsHandler {
        public void changeGoodsName() {
            goods.setName("name" + new Random().nextInt(100));
            goods.setPrice(new Random().nextInt(100));
        }

        public void changeGoodsDetails() {
            goods.setDetail("detail" + new Random().nextInt(100));
            goods.setPrice(new Random().nextInt(100));
        }
    }
}
