package com.tunaPasta15.activity;

import android.os.Bundle;

import com.tunaPasta15.R;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class FragmentSwitchTest extends FragmentActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentswitchtest);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentSwitchItem01 first = new FragmentSwitchItem01();
        ft.add(R.id.container, first);
        ft.commit();
    }
}
