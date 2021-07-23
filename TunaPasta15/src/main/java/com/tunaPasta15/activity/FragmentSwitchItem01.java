package com.tunaPasta15.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.tunaPasta15.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentSwitchItem01 extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentswitchitem01, container, false);
        Button bt_first = view.findViewById(R.id.bt_first);
        bt_first.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.fragmentswitchtest_anim_translate_in, R.anim.fragmentswitchtest_anim_translate_out);
                FragmentSwitchItem02 second = new FragmentSwitchItem02();
                ft.replace(R.id.container, second);
                ft.commit();
            }
        });
        return view;
    }
}
