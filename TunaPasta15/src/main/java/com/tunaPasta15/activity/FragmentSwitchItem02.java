package com.tunaPasta15.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tunaPasta15.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentSwitchItem02 extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentswitchitem02, container, false);
        Button bt_second = view.findViewById(R.id.bt_second);

        bt_second.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.fragmentswitchtest_anim_translate_in, R.anim.fragmentswitchtest_anim_translate_out);
                FragmentSwitchItem01 first = new FragmentSwitchItem01();
                ft.replace(R.id.container, first);
                ft.commit();
            }
        });

        return view;
    }
}
