package com.tunaPasta17.activity;

import android.os.Bundle;

import com.tunaPasta17.R;
import com.tunaPasta17.databinding.DbAdapterBinding;
import com.tunaPasta17.model.Image;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class DBAdapterTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbAdapterBinding binding = DataBindingUtil.setContentView(this, R.layout.db_adapter);
        binding.setImage(new Image("xxxxxxx"));
        binding.setHandler(new Handler());
    }

    public class Handler {
        public void onClick(Image image) {
            image.getUrl().set("xxxxx" + new Random().nextInt(1000));
        }
    }
}