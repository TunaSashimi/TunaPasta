package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta17.R;
import com.tunaPasta17.databinding.DbModelBinding;
import com.tunaPasta17.model.ObservableGoods;
import com.tunaPasta17.model.UserInfo;

import androidx.databinding.DataBindingUtil;

public class DBModelTest extends Activity {
    DbModelBinding binding;
    ObservableGoods observableGoods;
    UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.db_model);

        user = new UserInfo("User", "123456");
        binding.setUserInfo(user);

        observableGoods = new ObservableGoods("Goods", "new", 56);
        binding.setGoods(observableGoods);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observableGoods.setName("Chair");
            }
        });
    }
}
