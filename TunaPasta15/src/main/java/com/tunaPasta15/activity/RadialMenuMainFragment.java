package com.tunaPasta15.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tunaPasta15.R;

import androidx.fragment.app.Fragment;

/**
 * @author Arindam Nath
 */
public class RadialMenuMainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radialmenumainfragment, container, false);
        return view;
    }
}
