package com.tunaPasta17.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tunaPasta17.R;
import com.tunaPasta17.databinding.FragmentBlankBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class BlankFragment extends Fragment {
    public BlankFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentBlankBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false);
        binding.setHint("Hello");
        return binding.getRoot();
    }

}
