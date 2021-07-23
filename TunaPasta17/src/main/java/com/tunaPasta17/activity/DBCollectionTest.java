package com.tunaPasta17.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;

import com.tunaPasta17.R;
import com.tunaPasta17.databinding.DbCollectionBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.databinding.DataBindingUtil;

public class DBCollectionTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbCollectionBinding binding = DataBindingUtil.setContentView(this, R.layout.db_collection);

        binding.setArray(new String[]{"array0", "array1"});
        binding.setIndex(0);

        List<String> list = new ArrayList();
        list.add("list0");
        list.add("list1");
        binding.setList(list);

        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(0, "sparseArray0");
        sparseArray.put(1, "sparseArray1");
        binding.setSparse(sparseArray);

        binding.setKey("key");

        Map<String, String> map = new HashMap<>();
        map.put("key", "key");
        map.put("map", "map");
        binding.setMap(map);

        Set<String> set = new HashSet<>();
        set.add("set");
        binding.setSet(set);
    }
}