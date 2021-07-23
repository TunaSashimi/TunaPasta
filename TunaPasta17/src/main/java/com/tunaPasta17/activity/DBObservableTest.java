package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tunaPasta17.R;
import com.tunaPasta17.databinding.DbObservableBinding;

import java.util.Random;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableList;
import androidx.databinding.ObservableMap;

public class DBObservableTest extends Activity {
    private ObservableMap<String, String> observableMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbObservableBinding binding = DataBindingUtil.setContentView(this, R.layout.db_observable);

        binding.setIndex(0);

        observableMap = new ObservableArrayMap();
        observableMap.put("name", "Tuna");
        observableMap.put("age", "34");
        binding.setObservableMap(observableMap);

        ObservableList<String> observableList = new ObservableArrayList();
        observableList.add("observableList0");
        observableList.add("observableList1");

        //
        binding.setObservableList(observableList);

        binding.setKey("name");
    }

    public void onClick(View view) {
        observableMap.put("name", "leavesC,hi" + new Random().nextInt(100));
    }
}
